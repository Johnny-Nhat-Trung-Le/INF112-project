

—-Controller 🎮🕹️—-
(bruker bare keys, no mouse)
eventbus, eventlistenerv
- Interface: player
    - FieldVariabler:
        - itemSlot
        - Hp
        - dx
        -  dy
    - methods:
        - move (keypress):
            - Registrere ulike taster til ulike movement
            - en tick call slik at den endrer posisjon
        - Privat checkItem : Bool
            - Sjekker "itemSlot" om spiller har item
        - useItem:
            - call på checkItem
            - hvis den har, call på useItem
            - Etter brukt useItem, call checkIfUsedUp()
            - Hvis true, fjern fra itemSlot (enten gjør den Null)
            - Spiller tolker hva useItem returnere buff avhengig av hva buff skal gjøre
        - pickUpItem:
            - call på checkItem
            -  Sjekker verden etter om spiller er på item (koordinat? ha en liste over hvor items spawner og sammenlinger med player koordinat)
            - Hvis spiller er på item, "itemSlot" blir full og man får en item objekt.
        - tick
        - getHP
        - get…pos,speed,osv.
        - damage (Int)
        - isAlive : Bool
		Hvis false, send en event til model gamestate

- Interface: Item
    - methods:
        - useItem (posisjon) : BuffEffect
            - Call på checkIfUsedUp
            - mink durability med 1
        - checkIfUsedUp : Bool
            - sjekker om item er brukt opp

- Interface: ViewableItem
    - getTexture
    - getDescription

- Interface: ViewableEntity
    - getTexture
    - getSize
    - getPosition

- Interface: MoveableEntity
     - tick


—-Model 🌍—-
- Interface: World
    fieldvariabler:
Player
	List: Fysiske elementer (sjekk med fysikk bib)
	List: Bakgrunns elementer 
	List: ViewableEntities (items, projectile, etc.)
    getters …

- Interface: Model
    - vars 
        - enum gamestate
		
        - World


- Interface: ObjectFactory
	static createMap (String / List2d / List) : Fysiske elementer
	static createItems (String / List2d / List) : Liste over Items
	???Enemies👹👹??? Skal de være her, hvordan fikse det????


—–View 👀👀—-

-Interface: Skjerm:
	var
		Model
	methods (private): 🎨🎨👨‍🎨🥖
                       drawPause
                       drawMenu
                       drawGame
	           drawVictory???


            