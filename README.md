# INF112 Project – *Lil bro's adventure back home*

* Team: *The Googlers* (Gruppe 8): *Fredrik Rød, Johnny Nhat Trung Le, Isak Yau*
* Lenke til GitLab: https://git.app.uib.no/Isak.Yau/the_googlers.

## Om spillet
* Lil bro has been on a journey and is starting his adventure back home.
* Help Lil bro find his way back home safely by jumping and traversing through mountains and terrains.

## Kontroll
  * Brukeren styrer spilleren ved å bruke disse tastene:
    - 'W':Hopp
    - 'A':Venstre
    - 'S':Ned 
    - 'D':Høyre 
    - 'Space':Bruk item som spilleren holder
  * Brukeren kan bruke disse tastene for å interagere med de ulike skjermene:
    - 'P':Start/Pause 
    - 'R':Start spillet pånytt
    - 'H':Bytt til informasjonskjerm
    - 'B':Bytt tilbake til forrige skjerm
    - 'esc':Avslutte programmet

## Kjøring
* Kompileres med `mvn package`.
* Kjøres med `java -jar target/gdx-app-Lil-bros-adventure-home-1.0-fat.jar`
* Krever Java 21 eller senere

## Test
* Tester kompileres med `mvn test`
* Manuelle tester som ikke kan kjøres automatisk ligger [her](doc/manualTesting.md)


## Kjente feil
* Det er en bug når spilleren hopper mot en vegg, så "bouncer" den tilbake

## Credits
* **Spritesheet:**
  * **Tittel:** Free Tiny Hero Sprites Pixel Art
  * **Artist:** Free Game Assets (GUI, Sprite, Tilesets)
  * **Utgivelses dato:** 12.08.2019
  * **Copyright:** © 2022 CraftPix
  * Licensed under CraftPix File Licenses
  * **Link:** https://free-game-assets.itch.io/free-tiny-hero-sprites-pixel-art
* **Tileset:**
  * **Tittel:** Basic Platfomer Tileset
  * **Artist:** Myna Studios
  * **Utgivelses dato:** 17.0.2022
  * **Copyright:** © 2024 itch corp
  * **Link:** https://mynastudios.itch.io/basic-platformer-tileset
* **Music/Sound effects:**
  * **Tittel:** Mister Exposition
  * **Artist:** Kevin MacLeod
  * **Utgivelses dato:** 30.12.2014
  * **Copyright:** ℗ Kevin MacLeod
  * Licensed under Creative Commons: By Attribution 3.0 License
  * **Link:** https://www.youtube.com/watch?v=QEWtJ7CYGaY&list=PLKH-YE4Goc7lZIe7l67TDDKURsRWWW0vc&index=42&ab_channel=KevinMacleod-Topic
  * **Tittel:** Pixel Peeker Polka - faster
  * **Artist:** Kevin MacLeod
  * **Utgivelses dato:** 25.12.2014
  * **Copyright:** ℗ Kevin MacLeod
  * Licensed under Creative Commons: By Attribution 3.0 License
  * **Link:** https://www.youtube.com/watch?v=JbspWYbuxgE&list=PLKH-YE4Goc7lZIe7l67TDDKURsRWWW0vc&index=3&ab_channel=KevinMacleod-Topic
  * **Tittel:** Cipher
  * **Artist:** Kevin MacLeod
  * **Utgivelses dato:** 27.12.2014
  * **Copyright:** ℗ Kevin MacLeod
  * Licensed under Creative Commons: By Attribution 3.0 License
  * **Link:** https://www.youtube.com/watch?v=4yV_gv299VM&list=PLKH-YE4Goc7lZIe7l67TDDKURsRWWW0vc&index=33&ab_channel=KevinMacleod-Topic
  * **Tittel:** Game over trombone
  * Liscensed under Mixkit SoundEffects Free License
  * **Link:** https://mixkit.co/free-sound-effects/game-over/
  * **Tittel:** Cartoon Jump
  * **Artist:** PixaBay
  * **Utgivelses dato:** 05.08.2021
  * Licensed under CC0
  * **Link:** https://pixabay.com/sound-effects/search/jump/
  * **Tittel:** Item Pick Up
  * **Artist:** PixaBay
  * **Utgivelses dato:** 08.06.2022
  * Licensed under CC0
  * **Link:** https://pixabay.com/sound-effects/search/pick-up/
  * **Tittel:** damage
  * **Artist:** PixaBay
  * **Utgivelses dato:** 12.06.2022
  * Licensed under CC0
  * **Link:** https://pixabay.com/sound-effects/search/damage/
* **PluginLoader class**
  * **Tittel:** PluginLoader class
  * **Forfatter:** Anya
  * **Nedlastnings-dato** 16.04.2024

