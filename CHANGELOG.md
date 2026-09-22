## The Groups Update
Introducing the new Groups system. Raised 6.x brings you even more control over your HUD!

Combine and control different Layers in Groups of your creation. Groups makes it easy to sync Layers in an organized and easy-to-use fashion. Edit, add, remove, and rename Groups from the redesigned config screen. Make as many as you like!

### Changes:
- Added layer grouping feature that completely overhauls how layers are managed.
- Reworked configuration screen for groups.
- Reworked commands to support new config.
- Added vanilla action bar layer, now distinctly controllable from hotbar layer.
- Added vanilla chat input layer to move chat input and command suggestions/usage. Uniquely changes width based on x-axis offset.
- Added position font glyph set.
- Removed direction sprites.
- Reworked several vanilla layer sprites.
- Renamed several vanilla layers to be more distinctive.
- Renamed `texture` setting to be something human friendly.
- Replaced `direction` properties with singular `position` property for layers.
- Renamed `displacement` to `offset`, now properties of groups instead of layers.
- Removed `sync` property from layers.
- Added registration methods with position parameter to API so mods may set a default position for a layer they register.