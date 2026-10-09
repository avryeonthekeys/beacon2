package dev.fatiguebeacon;

import dev.fatiguebeacon.registry.ModBlockEntities;
import dev.fatiguebeacon.registry.ModBlocks;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.Identifier;

public final class FatigueBeaconMod implements ModInitializer {
    public static final String MOD_ID = "fatiguebeacon";

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }

    @Override
    public void onInitialize() {
        ModBlocks.initialize();
        ModBlockEntities.initialize();
    }
}
