# Sjekk at vinduet/skjermen dukker opp når man kjører programmet.
# Sjekk skjerm:
    - Hvis man trykker på taster som står på skjermen, så endres skjermen slik den skal
    - f.eks trykke 'p' for å skifte fra main til Level skjerm, trykke 'h' for å skifte til infoskjerm osv
    - Sjekk at det ikke er mulig for andre taster å interagere med skjermen, 
        med mindre det eksplisitt står at en tast skal gjøre noe. (f.eks, i main_menu kan man bare trykke på 'p' for å spille og 'h' for infoskjerm)
    
# Sjekk i Main_menuScreen:
    - Sjekk at det viser et lyseblått bakgrunn med skyer og sol.
    - Sjekk at det står teksten 'Lil bro's Adventure Back Home' som tittel
    - SJekk at under tittelen, står det 'Press P to play' og 'Press H for help'
    - Sjekk at musikk spilles og at den ikke stoppes etter at den er ferdig spilt.
# Sjekk i InfoScreen:
    - Sjekk at bakgrunnen er en åpen bok.
    - Sjekk at det står kontrollene for programmet på venstre siden av skjermen
    - Sjekk at det viser item bilde, navn på item og hva den gjør på høyre side av skjermen.
    - SJekk at musikken har stoppet.
# Sjekk i GameScreen:
    - Sjekk at man har en HUD i game skjermen
    - Sjekk at HUD viser fram antall HP og inventory slot
    - Sjekk at Hp bildene er på øvre høyre side av skjermen, mens inventory slot er på øvre venstre side av skjermen
    - Sjekk at det er en bakgrunn i gameskjermen som følger etter spilleren
    - Sjekk at det viser en spiller
    - Sjekk at verden er tegnet med tiles
    - SJekk at ny musikk spilles, og at musikken ikke stoppes etter at den er ferdig spilt
    - Sjekk at man kan laste inn ulike spillverden avhengig av hvilken level man trykte på i Levelskjermen.
# Sjekk i PauseScreen:
    - Sjekk at skjermen viser en skog med svart bakgrunn.
    - Sjekk at musikken stoppes
    - Sjekk at det står midt i skjermen 'Pause!'
    - Under det står det 'Press P to continue' og under der igjen står 'Press H for help'
# Sjekk i LevelScreen:
    - Sjekk at skjermen viser en åpen bok (lik helpScreen)
    - Sjekk at musikken stoppes/ingen musikk spilles
    - Sjekk at det står '1' på venstre side og '2' på høyre side av skjermen
    - På toppen skal det stå 'Select level by pressing the corresponding number' med grønn skrift
    - På bunnen midten, står det 'B-Back' med rødt skrift.
# Sjekk i VictoryScreen:
    - Sjekk at skjermen viser spilleren ved huset sitt med fjell i bakgrunnen og blå himmel.
    - Sjekk at ny musikk spilles og at den ikke stoppes etter at musikken er ferdig spilt.
    - Sjekk at det står 'Victory' øverst, under der står det'You won the game!'
    - Under 'You won the game!' står det 'Thank you for playing!' og under der står det 'Press B to go back to Main Menu'
    - Tilslutt står det 'Press ESC to quit'
# Sjekk i GameOverScreen:
    - Sjekk at ny musikk spilles og at den ikke stoppes.
    - Sjekk at det er en blå bakgrunn med hvit tekst som det står 'Game Over !_!'
    - Under der står det 'Press R to restart!'
# Sjekk når man endre på størrelsen til vinduet:
    - Beholde samme Aspect Ratio
    - Mulig å spille fullscreen og windowed
# Sjekk at Spiller kan bevege seg:
    - Mot høyre når man trykker på tasten ´D´
    - Mot venstre når man trykker på tasten ´A´
    - Hoppe når man trykker på tasten ´W´
    - "Bevege seg nedover" når man trykker på tasten ´S´
# Sjekk animasjon:
    - Løpende mot venstre og høyre når spilleren går mot venstre og høyre
    - Hoppende mot venstre og høyre når spilleren hopper mot venstre og høyre
    - Stå stille, venstre og høyre når man ikke trykker på knapper.
# Sjekk interaksjon med Tiles:
    - Spiller kan stå oppå en Tile
    - Spiller kan gå inntil en Tile
    - Spiller kan ikke gå gjennom en Tile
        - Både fra siden og under   
    - Spiller skal ikke henge seg inntil en vegg, når den er inntil en vegg og skal bevege seg vekk fra den
    - Spiller skal kunne plukke opp en item når inventory er tom
    - Spiller skal ikke plukke opp en item hvis inventoryen er full.
    - Item skal ikke forsvinne fra verden om spilleren ikke plukker den opp.
    - Spiller skal kunne ta skade når den berører en saw eller spike tile
# Sjekk multi-"interaksjon" med controller:
    - hvis man trykker både på 'A' og 'D' så vil spiller stå stille
    - hvis man trykker både på 'W' og 'S' så vil man ikke hoppe, eller gå fortere nedover
# Sjekk bruk av item:
    - Hvis man trykker på 'space', og man har en item i inventory, skal spilleren få en effekt
    - Se at effekten funker (at spilleren hopper høyere, løper raskere eller får et ekstra hp)
    - Se at item durability minker når en item er aktivert.
    - Sjekk at item effekten ikke fortsatt er på spilleren etter at durabilityen har forsvunnet
    - Se at item forsvinner fra inventory når antall item igjen er 0.
    - Sjekk at antall item tilgjengelig for spilleren forsvinner etter antall ganger man har brukt item-et
# Sjekk lyd:
    - Sjekk at musikk spilles når programmet starter opp
    - Sjekk at musikk enten byttes eller stoppes når man skifter til ulike skjerm
    - Sjekk at det spilles en effekt når spiller plukke opp en item
    - Sjekk at det spilles en effekt når spiller hopper.
    - SJekk at det spilles en effekt når spiller tar skade
    - Sjekk at en effekt lyd ikke loopes når den spiller.
    - Sjekk at effekten som spilles når man hopper, ikke loopes i lufta når man holder 'w' tasten
    - Sjekk at det ikke spilles flere musikk etter at man skifter skjerm.
- Sjekk at spiller dør når man faller ut av "level"
- Sjekk at spillet skifter fra level til victory skjerm når spilleren når døren.
- Sjekk at spiller tar skade når den er i kontakt med 'damageable tiles'
    - Sjekk at spillet går over til 'game over' skjerm når spiller har 0 Hp igjen
    - Sjekk at spillet går over til 'game over' skjerm når spilleren faller ut av verden
