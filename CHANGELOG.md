## The Groups Update
Introducing the new Groups system. Raised 6.x brings you even more control over your HUD!

Combine and control different Layers in Groups of your creation. Groups makes it easy to sync Layers in an organized and easy-to-use fashion. Configure your own Groups from the redesigned config screen!

### Changes:
- Added layer groups that replace individual layer management.
- Reworked configuration screen into multiple screens.
- Reworked commands to reflect config changes.
- Added vanilla action bar layer `minecraft:action_bar`, now distinctly controllable from hotbar layer.
- Added vanilla chat input layer `minecraft:chat_input` to move chat input, command suggestions, and usage text. Uniquely adjusts width based on x-axis offset.
- Added font glyph set for position buttons.
- Removed direction sprites, superseded by position font.
- Reworked several layer sprites.
- Renamed several curated vanilla layers to be more distinctive.
- Renamed `texture` setting to `selectionIndicator` to be clearer as to what it adjusts.
- Replaced `direction` properties with singular `position` property for layers.
- Renamed `displacement` properties to `offset`, which are now properties of groups instead of layers.
- Removed `sync` property from layers. Synchronization is now handled through groups.
- Added layer registration methods with `position` parameter to API so mods may set a default.