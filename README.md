<p align="center"><img src="https://github.com/yurisuika/Raised/blob/Fabric-1.18.2/src/main/resources/assets/raised/icon.png?raw=true" width="256" height="256"></p>

**Raised** is a Fabric/Forge mod for Minecraft that fixes the hotbar! Perhaps if you've not used many resource packs you may not have noticed, but the hotbar has been broken for a long time in Java Edition ([MC-67532](https://bugs.mojang.com/browse/MC-67532)). Not only is the selector texture cut off by a couple pixels, but the entire hotbar was actually designed for being raised up off of the bottom of the screen. Restore how it was meant to be and raise it up!

Raised features some basic adjustability! Either use the keybinds or commands to control the position of the HUD and the chat. See the wiki for more information.

Raised version 3.x brings about an entirely new way of changing the height of the HUD! Instead of individually altering the height of different HUD elements, Raised now encapsulates where they are called to render to transform the vertical position of their stacks! On the Fabric version, this is done with the various HUD method calls in the main render method and also supports mods that inject at the head and tail of the render method. On the Forge version, this works with the existing render events. Depending on the game version, it can work with RenderGameOverlayEvent, RenderGuiOverlayEvent, OverlayRegistry, and RegisterGuiOverlaysEvent.

Every mod that Raised supported before through hacky mixins now works in at least one version of the game, but most work in all! Even some mods that could not be supported before will now work. There are sadly some instances where mods do not work in certain versions, please consult the wiki for more information.

Please note that the modular support system attempted in version 2.x will still be supported if you wish to remain with that version. Please see [the respective releases for version 2.0.0](https://github.com/yurisuika/Raised/releases/tag/2.0.0) to obtain these support files. Unfortunately, these additional support files are not supported by most launchers.

If you are a mod creator and find that your mod's HUD is not being raised, fear not because Raised supports Object Share on Fabric! See the wiki or use [Arrows Info](https://github.com/intact/arrows-info) for an example.

#### Compiling

To build from source you will need have JDK 17 to compile and, optionally, Git to clone the repository. Otherwise, download the archive and just run `./gradlew build` from the root project folder.

When using Git, just choose a directory you wish to keep the project root folder in, decide which branch you wish to compile, and then run these commands:

```shell script
git clone --branch <branch> --recursive https://github.com/yurisuika/raised.git

cd ./raised

./gradlew build
```

Afterwards, your compiled JAR will be in `./build/libs`.

#### Releases

Don't want to bother building from source? Get the releases *[right here](https://github.com/yurisuika/Raised/releases)* now!

#### Repositories

You can find Raised on both *[CurseForge](https://www.curseforge.com/minecraft/mc-mods/raised)* and *[Modrinth](https://modrinth.com/mod/raised)*!

#### Community

The one and only! Join the *[Discord](https://discord.gg/0zdNEkQle7Qg9C1H)* for the latest discussion on our server, resource pack, mods, or just to chat!