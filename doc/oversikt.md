
- Interface: player 
    - FieldVariabler:
        - itemSlot
    - methods:
        - Movement (Tar en keypress):
            - Registrere ulike taster til ulike movement
            - en tick call slik at den endrer posisjon
        - Privaet checkItem:
            - Sjekker "itemSlot" om spiller har item
        - useItem:
            - call på checkItem
            - hvis den har, call på useItem
            - Etter brukt useItem, call checkIfUsedUp()
            - Hvis true, fjern fra itemSlot (enten gjør den Null)
            - SPiller tolker hva useItem returnere buff avhengig av hva buff skal gjøre
        - pickUpItem:
            - call på checkItem
            -  Sjekker verden etter om spiller er på item (koordinat? ha en liste over hvor items spawner og sammenlinger med player koordinat)
            - Hvis spiller er på item, "itemSlot" blir full og man får en item objekt.
        

- Interface: Item
    - methods:
        - useItem (posisjon til item):
            - Call på checkIfUsedUp
            - mink durability med 1
            - return en buffeffekt.

        - checkIfUsedUp:
            - sjekker om item er brukt opp return true eller false

            