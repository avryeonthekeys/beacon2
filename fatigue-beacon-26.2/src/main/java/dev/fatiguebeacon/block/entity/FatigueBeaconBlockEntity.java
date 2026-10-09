package dev.fatiguebeacon.block.entity;

import dev.fatiguebeacon.registry.ModBlockEntities;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

public final class FatigueBeaconBlockEntity extends BlockEntity {
    private static final int PYRAMID_LEVELS = 4;
    private static final double EFFECT_RADIUS = 200.0;
    private static final double EFFECT_RADIUS_SQUARED = EFFECT_RADIUS * EFFECT_RADIUS;
    private static final int EFFECT_AMPLIFIER = 3; // Amplifier 3 = Mining Fatigue IV.
    private static final int EFFECT_DURATION_TICKS = 60;
    private static final int UPDATE_INTERVAL_TICKS = 40;

    private int tickCounter;

    public FatigueBeaconBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.FATIGUE_BEACON, pos, state);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, FatigueBeaconBlockEntity blockEntity) {
        if (!(level instanceof ServerLevel serverLevel)) {
            return;
        }

        blockEntity.tickCounter++;
        if (blockEntity.tickCounter < UPDATE_INTERVAL_TICKS) {
            return;
        }
        blockEntity.tickCounter = 0;

        if (!hasFullFourLayerPyramid(serverLevel, pos)) {
            return;
        }

        double centerX = pos.getX() + 0.5;
        double centerY = pos.getY() + 0.5;
        double centerZ = pos.getZ() + 0.5;

        AABB searchBox = new AABB(
                centerX - EFFECT_RADIUS,
                centerY - EFFECT_RADIUS,
                centerZ - EFFECT_RADIUS,
                centerX + EFFECT_RADIUS,
                centerY + EFFECT_RADIUS,
                centerZ + EFFECT_RADIUS
        );

        List<ServerPlayer> players = serverLevel.getEntitiesOfClass(
                ServerPlayer.class,
                searchBox,
                player -> player.distanceToSqr(centerX, centerY, centerZ) <= EFFECT_RADIUS_SQUARED
        );

        for (ServerPlayer player : players) {
            player.addEffect(new MobEffectInstance(
                    MobEffects.MINING_FATIGUE,
                    EFFECT_DURATION_TICKS,
                    EFFECT_AMPLIFIER,
                    true,
                    true,
                    true
            ));
        }
    }

    private static boolean hasFullFourLayerPyramid(ServerLevel level, BlockPos beaconPos) {
        for (int layer = 1; layer <= PYRAMID_LEVELS; layer++) {
            int y = beaconPos.getY() - layer;

            for (int x = beaconPos.getX() - layer; x <= beaconPos.getX() + layer; x++) {
                for (int z = beaconPos.getZ() - layer; z <= beaconPos.getZ() + layer; z++) {
                    if (!level.getBlockState(new BlockPos(x, y, z)).is(BlockTags.BEACON_BASE_BLOCKS)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
