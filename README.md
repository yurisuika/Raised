<p align="center"><img src="https://github.com/yurisuika/Raised/blob/Fabric-1.18.x/src/main/resources/assets/raised/icon.png?raw=true" width="256" height="256"></p>

**Raised** is a Fabric/Forge mod for Minecraft that fixes the hotbar! Perhaps if you've not used many resource packs you may not have noticed, but the hotbar has been broken for a long time in Java Edition ([MC-67532](https://bugs.mojang.com/browse/MC-67532)). Not only is the selector texture cut off by a couple pixels, but the entire hotbar was actually designed for being raised up off of the bottom of the screen. Restore how it was meant to be and raise it up!

Please note that, for Minecraft 1.20.2+, Raised requires its own hotbar selector asset, as the GUI atlas was broken up into separate sprites with the hotbar selector now being cut off in texture as well as code!

Raised features some basic adjustability! Either use the GUI or commands to control the configurable options. See the wiki for more information.

On the Fabric version, Raised encapsulates the various HUD elements' method calls in the main render method to translate their position on the screen. Mods that inject adjacent to these calls will naturally work, but mods that inject at the head and tail of the render method are also supported. On the Forge version, this works with the HUD elements that are pre-encapsulated by Forge render events. Depending on the game version, it can work with RenderGameOverlayEvent, RenderGuiOverlayEvent, OverlayRegistry, and RegisterGuiOverlaysEvent.

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