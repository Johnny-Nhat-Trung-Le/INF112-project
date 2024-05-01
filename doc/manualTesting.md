Sjekk at vinduet/skjermen dukker opp når man kjører programmet.
Sjekk skjerm:
    - Hvis man trykker på taster som står på skjermen, så endres skjermen slik den skal
        - f.eks trykke 'p' for å skifte fra main til Level skjerm, trykke 'h' for å skifte til infoskjerm osv

Sjekk i GameSkjermen:
    - Sjekk at man har en HUD i game skjermen
        - Sjekk at HUD viser fram antall HP og inventory slot
    - Sjekk at det er en bakgrunn i gameskjermen som følger etter spilleren
Sjekk om disse tingene vises på skjerm:
    - Spiller
    - Verden
        - Tiles
Sjekk når man endre på størrelsen til vinduet:
    - Beholde samme Aspect Ratio
Sjekk at Spiller kan bevege seg:
    - Mot høyre når man trykker på tasten ´D´
    - Mot venstre når man trykker på tasten ´A´
    - Hoppe når man trykker på tasten ´W´
    - "Bevege seg nedover" når man trykker på tasten ´S´
Sjekk animasjon:
    - Løpende mot venstre og høyre når spilleren går mot venstre og høyre
    - Hoppende mot venstre og høyre når spilleren hopper mot venstre og høyre
    - Stå stille, venstre og høyre når man ikke trykker på knapper.
Sjekk interaksjon med Tiles:
    - Spiller kan stå oppå en Tile
    - Spiller kan gå inntil en Tile
    - Spiller kan ikke gå gjennom en Tile
        - Både fra siden og under   
    - Spiller skal ikke henge seg inntil en vegg, når den er inntil en vegg og skal bevege seg vekk fra den
    - Spiller skal kunne plukke opp en item
    - Spiller skal ikke plukke opp en item hvis inventoryen er full
Sjekk multi-"interaksjon" med controller:
    - hvis man trykker både på 'A' og 'D' så vil spiller stå stille
    - hvis man trykker både på 'W' og 'S' så vil man ikke hoppe, eller gå fortere nedover
Sjekk bruk av item:
    - Hvis man trykker på 'space', og man har en item i inventory, så skal spilleren få en effect
    - Se at item durability minker når man bruker opp en item
    - Se at item forsvinner fra inventory når durability er 0 og den har blitt brukt opp
SJekk lyd:
    - Sjekk at musikk spilles når programmet starter opp
    - Sjekk at musikk enten byttes eller stoppes når man skifter til ulike skjerm
    - Sjekk at det spilles en effekt når spiller plukke opp en item
    - Sjekk at det spilles en effekt når spiller hopper.
    - Sjekk at en effekt lyd ikke loopes når den spiller.
    - Sjekk at effekten som spilles når man hopper, ikke loopes i lufta når man holder 'w' tasten
Sjekk at spiller dør når man faller ut av "level"
Sjekk at spillet skifter fra level til victory skjerm når spilleren når døren.
Sjekk at spiller tar skade når den er i kontakt med 'damageable tiles'
    -Sjekk at spillet går over til 'game over' skjerm når spiller har 0 Hp igjen