package dev.fatiguebeacon.registry;

import dev.fatiguebeacon.block.FatigueBeaconBlock;
import java.util.function.Function;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

public final class ModBlocks {
    public static final Block FATIGUE_BEACON = register(
            ModBlockItemIds.FATIGUE_BEACON,
            FatigueBeaconBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.BEACON)
    );

    private ModBlocks() {
    }

    public static void initialize() {
        // Forces static registration.
    }

    private static Block register(
            BlockItemId id,
            Function<BlockBehaviour.Properties, Block> blockFactory,
            BlockBehaviour.Properties properties
    ) {
        Block block = register(id.block(), blockFactory, properties);
        BlockItem blockItem = new BlockItem(
                block,
                new Item.Properties().useBlockDescriptionPrefix().setId(id.item())
        );
        Registry.register(BuiltInRegistries.ITEM, id.item(), blockItem);
        return block;
    }

    private static Block register(
            ResourceKey<Block> key,
            Function<BlockBehaviour.Properties, Block> blockFactory,
            BlockBehaviour.Properties properties
    ) {
        Block block = blockFactory.apply(properties.setId(key));
        Registry.register(BuiltInRegistries.BLOCK, key, block);
        return block;
    }
}
