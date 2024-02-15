# Rapport – innlevering 1
**Team:** *The Googlers* – *Johnny Nhat Trung Le, Fredrik Rød, Isak Yau*

# A0
Gruppenavn: The Googlers	
Discord-Server: 8.1 The Googlers
Gruppe: 8
Gruppemedlemmer:
	Fredrik Rød
	Isak Yau
	Johnny Nhat Trung Le

Kompetanse:
	Fredrik Rød:
		INF100, INF101, INF102, INF122
			Hadde IT2 på vgs.
		Kan litt JavaScript, HTML, CSS. Kan godt Java, Python, Dart, Flutter, Haskell.
	Isak Yau:
		INF100, INF101, INF102, INF122
    Hadde IT1, IT2, programmering og modellering på vgs. Hadde programmering på ungdomsskole
		Kan litt Javascript, HTML, CSS. Moderat kompetanse
	Johnny Nhat Trung Le:
		 INF100, INF101, INF102, INF122
		 Ingen tidligere erfaring i koding. Moderat kompetanse.

# A1

# A2
	* Spillfigur som kan styres – gå til høyre/venstre, hoppe oppover og droppe ned.
	* Spilleren beveger seg oppover ved å hoppe, og nedover ved å falle.
	* Todimensjonal verden:
		* Verden er større enn skjermen og scroller horisontalt eller vertikalt
		* Verden bygget opp av blokker, med fast størrelse. 
		* Plattform – horisontal flate spilleren kan stå eller gå på (inkludert «bakken»).
		* Vegg – vertikal flate som spilleren ikke kan gå gjennom. Evt. kan gå gjennom, secret room.
		* Plattformer/ vegger som kan hoppes gjennom.
	* Fiender som beveger seg og er skadelige ved berøring.
	* Bekjempe fiender.
		* Hoppe på de, slå eller skyte.
	* Spilleren kan plukke opp "items" som gir ulik egenskaper til spillfiguren.
	* Utfordringen i spillet er: å bevege seg gjennom terrenget uten å falle utfor/ feller, å bekjempe fiendene, å nå frem til og bekjempe en «Final boss». 

	Krav:
		* Lyd/ UX
		* Forside
		* Objekt fabrikker
		* Items som endre egenskap (spiller eller andre)
		* tester
		* dokumentering

	Plattform:
		* Forside:
			* logo, play button, help/ control button
		* Character:
			* movemet/ set
				* run and jump
				* attack
			* Life
				* Difficulty based
		Bevegelse/ Kamera
			* Mario (en vei) eller alle retninger 
				* Opp-ned plattform
			* Hele mappet eller "litt og litt"
		* Map:
			* checkpoint (maybe)
			* ødelegge block
			* Traps/ void (death)
		Formål:
			* Komme seg til enden ''
			* Puzzle
			* Kill peoplz '
				* Mobs
					* Different kinds
				* Non-default killable mobs
			* Collecting items/ consumables '
				* What it do
				* Weapon
				* Randomizing
			* Endgame boss/ miniboss på veien

	* Important:
		* Forside:
			* logo, play button, help/ control button
		* Player
		* run and jump
		* Map and camera
			* fysikken
			* Traps/ void (death)
		* Respawn
		* Consumable/ item
			* movement
		* Win condition/ death
		
# A3
    * Par programmering
    * Testing
    * Ukentlig oppmøte: Tirsdag evt. mer
    * Kommunikasjon:
        * Discord, text-message
    * Deling;
        * Git
        * Word document
	* Arbeidsfordeling:
		* Fleksible, tar det som det kommer
		* Møter og avgjør hvilke oppgaver

	Oppfølgning:
		- Merge request
		- Discord
	
	Kanban and Scrum, kombinasjon
		Kanban + deadlines
		Scrum uten roller

	Vi planlegger å ha flere møter i første omgang slik at vi kan ha en oversikt over hvordan vi skal fordele oppgaver, estimasjon på arbeidsoppgavene og oppfølging av arbeid. Oppfølging av arbeid blir kommunikasjon når man møter hverandre, discord og eventuelt merge requests. Vi skal prøve å bruke issue board slik at vi vet hva slags arbeidsoppgaver som bør prioriteres og hvor vi er i utviklingsprosessen.
	
	Målet for applikasjonen:
		Spiller skal gjennom ulike hindringer og utfordringer rømme fra fangenskap ved å komme seg til den ene utgangen.


	MVP - Minimum Viable Product (Prioritert i denne rekkefølgen)
	1. Vise et spillebrett/ verden
	2. Vise spiller på spillebrett/ verden
	3. Flytte spiller (vha taster e.l.)
	4. Spiller interagerer med terreng
	5. Mål for spiller (komme seg til enden og rømme)
	6. Spiller kan dø (ved kontakt med fiender, eller ved å falle utfor skjermen/ feller)
	7. Start-skjerm (instruksjoner) ved oppstart / game over /vinne 
	8. Plukke opp "items" som skal gi en egenskap (raskere movement, høyere hopp, etc.)
	9. Er mulig å generere verden ved hjelp av en objektfabrikk.
	10. Hopp/run lyd avhengig av terreng.

	Brukerhistorier (tall viser prioritering)
	#Spiller:
		5) Som spiller trenger jeg å vite hva slags elementer tilhører bakgrunnen slik at jeg kan vite hvor jeg kan bevege meg.
			Kriterier: 
				- Man må kunne se spilleren samt verden. Ha mulighet til å skille mellom bakgrunn og terreng.
			Arbeidsoppgaver:
				- Lage grensesnitt som skille mellom bakgrunn og terreng.
				- Implementere grensesnittene slik at de kan bli brukt i programmet.
				- Lage en visning for verden og spilleren (skille mellom dem)
				- MVP krav 1,2,4

		9) Som spiller trenger jeg å vite hvordan man beveger karakteren og hva karakteren kan gjøre slik at jeg kan spille spillet. 
			Kriterier:
				- Ha en oversikt hvordan man kan kontrollere spilleren.
			Arbeidsoppgaver:
				- Lage en hjelpemeny hvor den gir beskrivelse av hvordan spilleren skal bevege seg. 
				- MVP krav: 7

		7) Som spiller trenger jeg å vite hva som er målet med spillet slik at jeg kan vinne/tape
				Kriterier:
					- Er mulig å finne ut hva som er målet med spillet enten README.md eller i startskjerm.
				Arbeidsoppgaver:
					- SKrive i README eller i startskjerm scenen.
					MVP krav: 5

		8) Som spiller trenger jeg å vite hva de ulike items gjør og eventuelt hvordan jeg kan bruke dem.
			 Kriterier:
			 	- Det må være en måte å vise fram abstrakt hva den gjør og hvordan den brukes
				- Vite hva slags taster man skal trykke for å bruke den.
			Arbeidsoppgaver:
				- Ha en ikon for item hver gang man plukke dem opp
				- Lage visining som gir riktig info
				MVP krav: 8?

		6) Som spiller trenger jeg å vite hva de ulike plattformene/elementer sine funksjonalitet er slik at jeg kan vite hva som er trygge å gå på og hva som ikke er trygge.
			Kriterier:
				- Skille mellom solide og ikke solide objekter
				- Ulike objekter representere ulike terreng i objektfabrikken
			Arbeidsoppgaver:
				- Bruke ulike visninger på ulike type terreng/objekter for å forstå hva de skal gjøre.
				MVP krav: 1,2
	
	#Systemutvikler:
		1) Som systemutvikler trenger jeg å vite hvilke klasser og grensesnitter tilhører hverandre slik at jeg kan ha god innkapsling. 
			Kriterier:
				- Tydelig og oversiktlig klasser og grensesnitt
				- God mappestruktur
			Arbeidsoppgave:
				- Tydelig og beskrivende filer og mapper.
			Mvp krav: Ingen men greit å ha for oversiktlighetens skyld.

		3) Som systemutvikler trenger jeg å vite hva slags plattformer/elementer vi skal ha med slik at jeg kan programmere funksjonaliteter knyttet til de ulike plattformene/elementer. (eks: hvis det er traps, så skal man dø, eller hvis det er trapdoor så kan man bevege opp eller ned.)
			Kriterier:
				- Ha en veldetaljert plan på hvilke typer terreng, platform og elementer som skal være med.
			Arbeidsoppgave:
				- Et dokument/notat hvor man har oversikt over hva som skal være med i programmet når det gjelder platform, terreng, spiller osv.
				Mvp krav: 1?
		2) Som systemutvikler trenger jeg å vite hva slags egenskaper en spiller skal ha slik at jeg kan ha med de nødvendige funksjonalitetene knyttet til en spiller.(Items, eneimes, movement)
				Kriterier:
					- Ha en veldetaljert plan og oversikt over egenskapene til spilleren.
				Arbeidsoppgaver:
					- Følge planen 
					Mvp krav: 2,3,4,6
		
	

	#Tester:
		4) Som tester trenger jeg å vite hva som er formålet med testobjektet, slik at jeg kan lage tester som tester om testobjektet/testsystem utføre det den skal.
			Kriterier:
				- Veldefinerte grensesnitt
			Arbeidsoppgave:
				- Lage tester knyttet til testobjekt
				- God kommunikasjon mellom formål med testobjekt og hvordan man tester det.
			Mvp krav: Alt
# A4
	Testet ut controller (keypressed, keyjustpressed, osv.). Testet ut om eksempelet kjørte uten problemer. Sett gjennom vindu oppsettet. 
	Utforsket libgdx og utforsket biblioteket/ rammeverket dens. Eksperimentert med template og sett hva som skjer.

# Roller
    Team Leader: Fredrik Rød
    Referat skriver: Johnny Nhat Trung Le
    Oppfølging/ kommunikasjon manager: Isak Yau

