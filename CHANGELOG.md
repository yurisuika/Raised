An attempt to alleviate crashes in light of some conflicts created with the major update.

### Changes:
- Changed `LayerRegistry` to extend `Layers` so mods that reference internal layer Identifier fields at their previous location will not cause a crash (except for renamed/removed fields).
- Reverted layer `boss_bar` to `bossbar` because it was actually consistent with how Mojang calls it.