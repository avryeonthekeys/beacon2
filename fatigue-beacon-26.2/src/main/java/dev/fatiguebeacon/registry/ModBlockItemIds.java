package dev.fatiguebeacon.registry;

import dev.fatiguebeacon.FatigueBeaconMod;
import net.minecraft.references.BlockItemId;

public final class ModBlockItemIds {
    public static final BlockItemId FATIGUE_BEACON = create("fatigue_beacon");

    private ModBlockItemIds() {
    }

    private static BlockItemId create(String name) {
        var id = FatigueBeaconMod.id(name);
        return BlockItemId.create(id, id);
    }
}
