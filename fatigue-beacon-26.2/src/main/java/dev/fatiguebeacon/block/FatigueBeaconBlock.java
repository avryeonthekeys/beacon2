package dev.fatiguebeacon.block;

import com.mojang.serialization.MapCodec;
import dev.fatiguebeacon.block.entity.FatigueBeaconBlockEntity;
import dev.fatiguebeacon.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public final class FatigueBeaconBlock extends BaseEntityBlock {
    public static final MapCodec<FatigueBeaconBlock> CODEC = simpleCodec(FatigueBeaconBlock::new);

    public FatigueBeaconBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new FatigueBeaconBlockEntity(pos, state);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(
            Level level,
            BlockState state,
            BlockEntityType<T> type
    ) {
        return createTickerHelper(
                type,
                ModBlockEntities.FATIGUE_BEACON,
                FatigueBeaconBlockEntity::tick
        );
    }
}
