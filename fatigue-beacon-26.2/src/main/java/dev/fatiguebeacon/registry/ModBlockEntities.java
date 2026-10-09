package dev.fatiguebeacon.registry;

import dev.fatiguebeacon.FatigueBeaconMod;
import dev.fatiguebeacon.block.entity.FatigueBeaconBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public final class ModBlockEntities {
    public static final BlockEntityType<FatigueBeaconBlockEntity> FATIGUE_BEACON =
            register("fatigue_beacon", FatigueBeaconBlockEntity::new, ModBlocks.FATIGUE_BEACON);

    private ModBlockEntities() {
    }

    public static void initialize() {
        // Forces static registration.
    }

    private static <T extends BlockEntity> BlockEntityType<T> register(
            String name,
            FabricBlockEntityTypeBuilder.Factory<? extends T> entityFactory,
            Block... blocks
    ) {
        Identifier id = FatigueBeaconMod.id(name);
        return Registry.register(
                BuiltInRegistries.BLOCK_ENTITY_TYPE,
                id,
                FabricBlockEntityTypeBuilder.<T>create(entityFactory, blocks).build()
        );
    }
}
