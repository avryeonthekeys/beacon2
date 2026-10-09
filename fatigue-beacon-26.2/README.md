# Mining Fatigue Beacon — Fabric 26.2

A separate Fabric block/item for Minecraft **26.2**.

## Behaviour

- Registry ID: `fatiguebeacon:fatigue_beacon`
- Separate craftable beacon item/block; it does not replace the vanilla beacon.
- Recipe is the vanilla beacon recipe with **tinted glass instead of glass**:
  - 5 × tinted glass
  - 1 × nether star
  - 3 × obsidian
- Activates only when sitting on a complete **four-layer / level-4 beacon pyramid**.
- Accepts the normal `#minecraft:beacon_base_blocks` tag, so it follows vanilla-compatible beacon-base materials rather than hard-coding metals.
- While active, applies **Mining Fatigue IV** to players within an exact **200-block spherical radius** from the beacon's centre — horizontally, above it, and below it.
- Effect logic runs server-side and refreshes every 40 ticks. The 60-tick duration means the effect clears shortly after a player leaves the radius or the pyramid becomes invalid.
- No vanilla beacon GUI or selectable powers: this block has one fixed function.

## Requirements

- Minecraft 26.2
- Fabric Loader 0.19.5+
- Fabric API 0.161.0+26.2
- Java 25
- Gradle 9.7.1 (or another version compatible with the current Loom setup)

## Build

From this directory, with Java 25 active:

```bash
gradle build
```

The built mod JAR will be under `build/libs/`.

If you put the project on GitHub, the included workflow builds it with Temurin Java 25 and Gradle 9.7.1.

## Notes on Minecraft 26.2

This project intentionally uses the post-26.1 unobfuscated Fabric toolchain:

- Loom plugin: `net.fabricmc.fabric-loom`
- No Yarn `mappings` dependency
- `implementation` for Loader/Fabric API
- `BlockItemId` / resource-key based block-item registration
- singular data paths such as `data/<modid>/recipe` and `data/<modid>/loot_table`
- client item definition under `assets/<modid>/items`

These are deliberate 26.2 choices; do not replace them with old 1.21.x examples.
