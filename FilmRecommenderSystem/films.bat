;;; Author: Cristòfol Daudén Esmel
;;; Films Rules and Instances CLIPS

;;; Action
;;; Comedy
;;; SciFi
;;; Fantasy
;;; Musical
;;; Romance
;;; Thriller
;;; Terror
;;; Action-Adventure
;;; Action-Comedy
;;; Martial-Arts
;;; Action-Terror
;;; Disasters
;;; Action-SciFi
;;; Superheros
;;; Fantasy-SciFi
;;; SciFi-Adventure
;;; Musical-Comedy
;;; Romance-Comedy
;;; Romance-Thriller
;;; Spy
;;; Martial-Arts-Comedy
;;; Monsters
;;; DC
;;; Marvel
;;; Acction-Romance-Comedy
;;; Vampires
;;; Zombies-Infections

(clear)
(load "films.clp")

;;; Some film instances

(make-instance Transporter of Action-Adventure (film_name "Transporter") (actor JasonStatham) (duration 92) (minAge 18) (imdbRate 6.8) (weapon Gun) (aftermath TRUE) (characteristic Car) )
(make-instance FastAndFurious of Action-Adventure (film_name "Fast And Furious" ) (actor VinDiesel) (duration 106) (minAge 13) (imdbRate 6.8) (weapon Gun) (aftermath TRUE) (characteristic Car) )
(make-instance Rambo of Action-Adventure (film_name "John Rambo") (world War) (actor SylvesterStallone) (duration 92) (minAge 18) (imdbRate 7.0) (weapon Gun) (aftermath TRUE) )
(make-instance IndianaJones of Action-Adventure (film_name "Indiana Jones") (actor HarrisonFord) (duration 115) (minAge 0) (imdbRate 8.5) (year 1981) (weapon Gun) (aftermath TRUE) )

(make-instance TheKid of Action-Comedy (film_name "The Kid") (duration 104) (minAge 0) (imdbRate 6.1) (year 2000) )
(make-instance LooneyToons of Action-Comedy (film_name "Looney Toons: Back in Action") (world Fantastic) (duration 91) (minAge 0) (imdbRate 5.7) (year 2003) )

(make-instance Terminator of Action-Terror (film_name "Terminator") (world War) (duration 107) (minAge 18) (imdbRate 8.0) (year 1984) (weapon Gun) (aftermath TRUE) (characteristic Robot) )
(make-instance Blade of Action-Terror (film_name "Blade") (duration 120) (minAge 18) (imdbRate 7.1) (year 1998) (terrorCreature Vampire) (weapon Gun) (aftermath TRUE) )
(make-instance Riddick of Action-Terror (film_name "Riddick") (world Fantastic) (duration 119) (minAge 13) (imdbRate 6.4) (year 2013) (weapon RudimentaryWeapons) (aftermath TRUE) )
(make-instance PitchBlack of Action-Terror (film_name "Pitch Black") (world Fantastic) (duration 109) (minAge 13) (imdbRate 7.1) (year 2000) (weapon RudimentaryWeapons) (aftermath TRUE) )

(make-instance MadMax of Disasters (film_name "MadMax: Fury on the Road") (world War) (duration 120) (minAge 16) (imdbRate 8.1) (year 2015) (weapon Gun) )
(make-instance Pompeya of Disasters (film_name "Pompeii") (duration 105) (minAge 13) (imdbRate 5.5) (year 2014) (weapon RudimentaryWeapons) )
(make-instance 2012Film of Disasters (film_name "2012") (duration 158) (minAge 7) (imdbRate 5.8) (year 2009) )
(make-instance LoImpossible of Disasters (film_name "Lo Impossible") (duration 114) (minAge 16) (imdbRate 7.6) (year 2012) )

(make-instance IpMan of Martial-Arts (film_name "Ip Man") (duration 106) (minAge 13) (imdbRate 8.0) (year 2008) (aftermath TRUE) )
(make-instance KarateKid of Martial-Arts (film_name "The Karate Kid") (duration 140) (minAge 7) (imdbRate 6.2) (year 2010) )
(make-instance KillBill of Martial-Arts (film_name "Kill Bill") (duration 111) (minAge 18) (imdbRate 8.1) (year 2003 ) (aftermath TRUE) (weapon RudimentaryWeapons) )
(make-instance OngBak of Martial-Arts (film_name "Ong-Bak") (duration 108) (minAge 13) (imdbRate 7.2) (year 2003) (aftermath TRUE) )
(make-instance TheLastSamurai of Martial-Arts (film_name "The Last Samurai") (duration 154) (minAge 13) (imdbRate 7.7) (year 2003) (weapon RudimentaryWeapons) )

(make-instance JackieChan of Martial-Arts-Comedy (film_name "Jackie Chan: The Legend of Drunken Master") (duration 102) (minAge 0) (imdbRate 7.6) (year 1994) )

(make-instance Matrix of Action-SciFi (film_name "Matrix") (world Fantastic) (duration 136) (minAge 18) (imdbRate 8.7) (year 1999) (weapon Gun) (aftermath TRUE) )
(make-instance HungerGames of Action-SciFi (film_name "Hunger Games") (world Fantastic) (duration 142) (minAge 12) (imdbRate 7.2) (year 2012) (weapon RudimentaryWeapons) (aftermath TRUE) )
(make-instance IRobot of Action-SciFi (film_name "I, Robot") (duration 115) (minAge 0) (imdbRate 7.1) (year 2004) (characteristic Robot) )
(make-instance TotalRecall of Action-SciFi (film_name "Total Recall") (duration 113) (minAge 18) (imdbRate 7.5) (year 1990) (weapon Gun) )

(make-instance JamesBond of Spy (film_name "James Bond: Casino Royale") (duration 144) (minAge 13) (imdbRate 8.0) (year 2006) (weapon Gun) (aftermath TRUE) )
(make-instance ImpossibleMission of Spy (film_name "Impossible Mission") (duration 110) (minAge 13) (imdbRate 7.1) (year 1996) (weapon Gun) (aftermath TRUE) )
(make-instance JasonBourne of Spy (film_name "The Bourne Identity") (duration 119) (minAge 13) (imdbRate 7.9) (year 2002) (weapon Gun) (aftermath TRUE) )

(make-instance 2001ASpaceOdyssey of SciFi-Adventure (film_name "2001 A Space Odyssey") (duration 150) (minAge 0) (imdbRate 8.3) (year 1968) (characteristic Robot) )
(make-instance BladeRunner of SciFi-Adventure (film_name "Blade Runner") (duration 117) (minAge 13) (imdbRate 8.2) (year 1982) (weapon Gun) (aftermath TRUE) (characteristic Robot) )

(make-instance MammaMia of Musical-Comedy (film_name "Mamma Mia!") (actor MerylStreep) (duration 108) (minAge 0) (imdbRate 6.4) (year 2008) (aftermath TRUE) )
(make-instance TheGreatestShowman of Musical-Comedy (film_name "The Greatest Showman") (actor HughJackman) (duration 105) (minAge 0) (imdbRate 7.7) (year 2017) )

(make-instance AmericanPie of Comedy (film_name "American Pie") (duration 95) (minAge 18) (imdbRate 7.0) (year 1999) (aftermath TRUE) )
(make-instance MarxBrothers of Comedy (film_name "Marx Brothers: A Night at the Opera") (duration 96) (minAge 0) (imdbRate 8.0) (year 1935) (aftermath TRUE) )
(make-instance GrownUps of Comedy (film_name "Grown Ups") (duration 102) (minAge 0) (imdbRate 6.0) (year 2010) (aftermath TRUE) )

(make-instance PlanetOfApes of Fantasy-SciFi (film_name "Planet of Apes") (duration 119) (minAge 0) (imdbRate 5.7) (year 2001) (aftermath TRUE) )
(make-instance HarryPotter of Fantasy-SciFi (film_name "Harry Potter And the Sorcerer's Stone") (world Fantastic) (weapon Magic) (duration 152) (minAge 0) (imdbRate 7.6) (year 2001) (aftermath TRUE) (characteristic Magic) )
(make-instance StarWars of Fantasy-SciFi (film_name "Star Wars") (world Fantastic) (duration 121) (minAge 0) (imdbRate 8.6) (year 1977) (weapon LightSable) (aftermath TRUE) (characteristic Magic) )

(make-instance Grease of Musical (film_name "Grease") (actor JohnTravolta) (duration 110) (minAge 0) (imdbRate 7.2) (year 1978) )
(make-instance AStarIsBorn of Musical (film_name "A Star is Born") (actor LadyGaga) (duration 136) (minAge 12) (imdbRate 8.2) (year 2018) )
(make-instance BohemianRhapsody of Musical (film_name "Bohemian Rhapsody") (actor RamiMalek) (duration 134) (minAge 12) (imdbRate 8.4) (year 2018) )

(make-instance MrAndMrsSmith of Acction-Romance-Comedy (film_name "Mr & Mrs Smith") (duration 120) (minAge 13) (imdbRate 6.5) (year 2005) (weapon Gun) )
(make-instance KnightAndDay of Acction-Romance-Comedy (film_name "Knight And Day") (duration 109) (minAge 7) (imdbRate 6.3) (year 2010) )

(make-instance ThePanthomOfTheOpera of Romance-Thriller (film_name "The Panthom of the Opera") (duration 143) (minAge 13) (imdbRate 7.3) (year 2004) )

(make-instance TheFourHorsemenOfTheApocalypse of Romance (film_name "The Four Horsemen of the Apocalypse") (duration 153) (minAge 16) (imdbRate 6.7) (year 1962) )
(make-instance TheHunchbackOfNotreDame of Romance (film_name "The Hunchback of Notre Dame") (duration 91) (minAge 0) (imdbRate 6.9) (year 1996) )

(make-instance SolteraALos40 of Romance-Comedy (film_name "Soltera a los 40") (duration 103) (minAge 13) (imdbRate 5.6) (year 2014) )

(make-instance PulpFiction of Thriller (film_name "Pulp Fiction") (duration 154) (minAge 18) (imdbRate 8.9) (year 1994) (weapon Gun) )
(make-instance AClockworkOrange of Thriller (film_name "A Clockwork Orange") (duration 136) (minAge 18) (imdbRate 8.3) (year 1971) )

(make-instance ResidentEvil of Zombies-Infections (film_name "Resident Evil") (duration 100) (minAge 18) (imdbRate 6.7) (year 2002) (fear 2) (weapon Gun) (aftermath TRUE) )
(make-instance 28DaysLater of Zombies-Infections  (film_name "28 Days Later") (duration 113) (minAge 18) (imdbRate 7.6) (year 2002) (fear 3) (weapon Gun) )

(make-instance DrJekyllAndMrHyde of Monsters (film_name "Dr Jekyll And Mr Hyde") (duration 98) (minAge 13) (imdbRate 7.7) (year 1931) (terrorCreature Others) (fear 1) )
(make-instance TheMummy of Monsters (film_name "The Mummy") (duration 124) (minAge 7) (imdbRate 7.0) (year 1999) (weapon Gun) (terrorCreature Mummy) (fear 2) )
(make-instance TheInvisibleMan of Monsters (film_name "The Invisible Man") (duration 71) (minAge 13) (imdbRate 7.7) (year 1933) (terrorCreature Invisible) (fear 1) )

(make-instance Dracula of Vampires (film_name "Dracula") (duration 75) (minAge 18) (imdbRate 7.6) (year 1931) (fear 2) )
(make-instance Underworld of Vampires  (film_name "Underworld") (duration 121) (minAge 18) (imdbRate 7.0) (year 2003) (fear 1) (aftermath TRUE) (weapon Gun) )

(make-instance TheIntruders of Terror (film_name "Shut In") (duration 90) (minAge 13) (imdbRate 5.7) (year 2015) (fear 3) (terrorCreature Others) )
(make-instance TheShining of Terror (film_name "The Shining") (duration 146) (minAge 13) (imdbRate 8.4) (year 1980) (fear 2) (terrorCreature Others) )

(make-instance AntMan of Marvel (film_name "AntMan") (duration 143) (minAge 7) (imdbRate 8.1) (year 2012) (aftermath TRUE) (superPower Decrease) (characteristic Super) )
(make-instance Hulk of Marvel (film_name "The Hulk") (duration 100) (minAge 7) (imdbRate 6.3) (year 2015) (aftermath TRUE) (superPower Transformation) (characteristic Super) )

(make-instance Superman of DC (film_name "Superman") (duration 130) (minAge 7) (imdbRate 6.5) (year 2015) (superPower Super) (aftermath TRUE) (characteristic Super) )
(make-instance TheFlash of DC (film_name "The Flash") (duration 140) (minAge 7) (imdbRate 8.3) (year 2019) (superPower RunFast) (aftermath TRUE) (characteristic Super) )


(batch "client.bat")

(defrule show-results
    ( declare (salience -5) )
    ?obj1 <- ( object (is-a Client) (filmsToShow ?f&:(> ?f 0) ) )
    ?obj2 <- ( object (is-a Film ) (value ?v ) )
    (not ( object (is-a Film) ( value ?u&:(> ?u ?v) ) ) )
    =>
    (send ?obj1 decreaseFilmsToShow )
    (send ?obj2 showFilm )
    (printout T " " crlf)
    (send ?obj2 delete )
)

(defrule min-age
    ( object (is-a Client) (age ?a&:(> ?a 0) ) )
    ?obj <- ( object (is-a Film ) (minAge ?mAge&:(> ?mAge ?a) ) )
    =>
    (printout T "Object deleted." crlf)
    (send ?obj delete)
)

(defrule time
    ( object (is-a Client) (time ?t&:(> ?t 0) ) )
    ?obj <- ( object ( is-a Film ) (duration ?d&:(>= ?t ?d) ) (film_name ?n) )
    =>
    (send ?obj updateValue 10 )
)

(defrule lastDaySports1
    ( object (is-a Client ) (last-day Sports) )
    ?obj <-( object ( is-a Action ) )
    =>
    (send ?obj updateValue 30 )
)

(defrule lastDaySports2
    ( object (is-a Client ) (last-day Sports) )
    ?obj <-( object ( is-a Terror ) )
    =>
    (send ?obj updateValue 25 )
)

(defrule lastDayFamily1
    ( object (is-a Client ) (last-day Family) )
    ?obj <- ( object (is-a SciFi ) )
    =>
    (send ?obj updateValue 30 )
)

(defrule lastDayFamily2
    ( object (is-a Client ) (last-day Family) )
    ?obj <- ( object (is-a Fantasy ) )
    =>
    (send ?obj updateValue 30 )
)

(defrule lastDayFamily3
    ( object (is-a Client ) (last-day Family) )
    ?obj <- ( object (is-a Romance ) )
    =>
    (send ?obj updateValue 25 )
)

(defrule lastDayParty1
    ( object (is-a Client ) (last-day Party) )
    ?obj <- ( object (is-a Comedy ) )
    =>
    (send ?obj updateValue 30 )
)

(defrule lastDayParty2
    ( object (is-a Client ) (last-day Party) )
    ?obj <- ( object (is-a Musical ) )
    =>
    (send ?obj updateValue 30 )
)

(defrule lastDayKaraoke
    ( object (is-a Client ) (last-day Karaoke) )
    ?obj <- ( object (is-a Musical ) )
    =>
    (send ?obj updateValue 50 )
)


(defrule favouriteSportMartialArt
    ( object (is-a Client ) (favourite-sport MatrialArts) )
    ?obj <- ( object (is-a Martial-Arts ) )
    =>
    (send ?obj updateValue 25 )
)

(defrule favouriteSportOutdoorSports1
    ( object (is-a Client ) (favourite-sport OutdoorSports) )
    ?obj <- ( object (is-a Action-Adventure ) )
    =>
    (send ?obj updateValue 20 )
)

(defrule favouriteSportOutdoorSports2
    ( object (is-a Client ) (favourite-sport OutdoorSports) )
    ?obj <- ( object (is-a SciFi ) )
    =>
    (send ?obj updateValue 15 )
)

(defrule favouriteSportTeamSports
    ( object (is-a Client ) (favourite-sport TeamSports) )
    ?obj <- ( object (is-a Superheros) )
    =>
    (send ?obj updateValue 15 )
)

(defrule aftermathYes
    ( object (is-a Client ) (aftermath Yes) )
    ?obj <- ( object (is-a Film) (aftermath TRUE) )
    =>
    (send ?obj updateValue 20 )
)

(defrule aftermathNo
    ( object (is-a Client ) (aftermath No) )
    ?obj <- ( object (is-a Film) (aftermath ~TRUE) )
    =>
    (send ?obj updateValue 20 )
)

(defrule question1-1
    ( object (is-a Client ) (characteristic Magic) )
    ?obj <- ( object (is-a Film) (characteristic Magic) )
    =>
    (send ?obj updateValue 25 )
)

(defrule question1-2
    ( object (is-a Client ) (characteristic Car) )
    ?obj <- ( object (is-a Film) (characteristic Car) )
    =>
    (send ?obj updateValue 25 )
)

(defrule question1-3
    ( object (is-a Client ) (characteristic Robot) )
    ?obj <- ( object (is-a Film) (characteristic Robot) )
    =>
    (send ?obj updateValue 25 )
)

(defrule question1-4
    ( object (is-a Client ) (characteristic Super) )
    ?obj <- ( object (is-a Film) (characteristic Super) )
    =>
    (send ?obj updateValue 25 )
)

(defrule quality
    ( object (is-a Client ) (quality ?q&:(> ?q 0) ) )
    ?obj <- ( object ( is-a Film ) (imdbRate ?qi&:(>= ?qi ?q) ) )
    =>
    (send ?obj updateValue 40 )
)

(defrule question2-1
    ( object (is-a Client ) (world War) )
    ?obj <- ( object (is-a Film) (world War) )
    =>
    (send ?obj updateValue 25 )
)

(defrule question2-2
    ( object (is-a Client ) (world Fantastic) )
    ?obj <- ( object (is-a Film) (world Fantastic) )
    =>
    (send ?obj updateValue 25 )
)

(defrule question2-3
    ( object (is-a Client ) (world Real) )
    ?obj <- ( object (is-a Film) (world ?w&:(and (neq ?w War) (neq ?w Fantastic) ) ) )
    =>
    (send ?obj updateValue 25 )
)

(defrule question3-1
    ( object (is-a Client ) (weapon Gun) )
    ?obj <- ( object (is-a Film) (weapon Gun) )
    =>
    (send ?obj updateValue 10 )
)

(defrule question3-2
    ( object (is-a Client ) (weapon Magic) )
    ?obj <- ( object (is-a Film) (weapon Magic) )
    =>
    (send ?obj updateValue 10 )
)

(defrule question3-3
    ( object (is-a Client ) (weapon LightSable) )
    ?obj <- ( object (is-a Film) (weapon LightSable) )
    =>
    (send ?obj updateValue 10 )
)

(defrule question3-4
    ( object (is-a Client ) (weapon RudimentaryWeapons) )
    ?obj <- ( object (is-a Film) (weapon RudimentaryWeapons) )
    =>
    (send ?obj updateValue 10 )
)

(defrule question3-5
    ( object (is-a Client ) (weapon NoWeapons) )
    ?obj <- ( object (is-a Film) (weapon ?w&:( and (neq ?w Gun) (neq ?w Magic) (neq ?w LightSable) (neq ?w RudimentaryWeapons) ) ) )
    =>
    (send ?obj updateValue 10 )
)

(defrule year1
    ( object (is-a Client ) (year 1) )
    ?obj <- ( object ( is-a Film ) (year ?y&:(< ?y 2000) ) )
    =>
    (send ?obj updateValue 30 )
)

(defrule year2
    ( object (is-a Client ) (year 2) )
    ?obj <- ( object ( is-a Film ) (year ?y&:( and (>= ?y 2000) (< ?y 2015) ) ) )
    =>
    (send ?obj updateValue 30 )
)

(defrule year3
    ( object (is-a Client ) (year 3) )
    ?obj <- ( object ( is-a Film ) (year ?y&:(>= ?y 2015) ) )
    =>
    (send ?obj updateValue 30 )
)

(defrule question4-1
    ( object (is-a Client ) (characteristic2 FantasticAnimals) )
    ?obj <- ( object (is-a Fantasy) )
    =>
    (send ?obj updateValue 20 )
)

(defrule question4-2
    ( object (is-a Client ) (characteristic2 TerrorAnimals) )
    ?obj <- ( object (is-a Terror) )
    =>
    (send ?obj updateValue 10 )
)

(defrule question4-3
    ( object (is-a Client ) (characteristic2 FarAwayFromAnimals) )
    ?obj <- ( object (is-a Spy) )
    =>
    (send ?obj updateValue 20 )
)

(defrule question5-1
    ( object (is-a Client ) (terrorCreature Vampire) )
    ?obj <- ( object (is-a Terror) (terrorCreature Vampire) )
    =>
    (send ?obj updateValue 5 )
)

(defrule question5-2
    ( object (is-a Client ) (terrorCreature Mummy) )
    ?obj <- ( object (is-a Terror) (terrorCreature Mummy) )
    =>
    (send ?obj updateValue 5 )
)

(defrule question5-3
    ( object (is-a Client ) (terrorCreature Invisible) )
    ?obj <- ( object (is-a Terror) (terrorCreature Invisible) )
    =>
    (send ?obj updateValue 5 )
)

(defrule question5-4
    ( object (is-a Client ) (terrorCreature Zombie) )
    ?obj <- ( object (is-a Terror) (terrorCreature Zombie) )
    =>
    (send ?obj updateValue 5 )
)

(defrule question5-5
    ( object (is-a Client ) (terrorCreature Others) )
    ?obj <- ( object (is-a Terror) (terrorCreature Others) )
    =>
    (send ?obj updateValue 5 )
)

(defrule question6-1
    ( object (is-a Client ) (superPower Decrease) )
    ?obj <- ( object (is-a Superheros) (superPower Decrease) )
    =>
    (send ?obj updateValue 10 )
)

(defrule question6-2
    ( object (is-a Client ) (superPower Transformation) )
    ?obj <- ( object (is-a Superheros) (superPower Transformation) )
    =>
    (send ?obj updateValue 10 )
)

(defrule question6-3
    ( object (is-a Client ) (superPower RunFast) )
    ?obj <- ( object (is-a Superheros) (superPower RunFast) )
    =>
    ;;;(printout T "Run Fast Up." crlf)
    (send ?obj updateValue 10 )
)

(defrule question6-4
    ( object (is-a Client ) (superPower Super) )
    ?obj <- ( object (is-a Superheros) (superPower Super) )
    =>
    (send ?obj updateValue 10 )
)


(defrule question7-1
    ( object (is-a Client ) (fight Hulk) )
    ?obj <- ( object (is-a Marvel) )
    =>
    (send ?obj updateValue 10 )
)

(defrule question7-2
    ( object (is-a Client ) (fight Superman) )
    ?obj <- ( object (is-a DC) )
    =>
    (send ?obj updateValue 10 )
)

(defrule question8-1
    ( object (is-a Client ) (fear 0) )
    ?obj <- ( object (is-a Terror) )
    =>
    (send ?obj updateValue -20 )
)

(defrule question8-2
    ( object (is-a Client ) (fear ?f&:(> ?f 0) ) )
    ?obj <- ( object (is-a Terror) (fear ?fe&:(= ?f ?fe ) ) )
    =>
    (send ?obj updateValue 10 )
)

(defrule question9-10
    ( object (is-a Client ) (favouriteActor ?f&:(neq ?f nil) ) )
    ?obj <- ( object (is-a Film) (actor ?fe&:(eq ?f ?fe ) ) )
    =>
    (send ?obj updateValue 15 )
    ;;;(printout T "Actor " ?f crlf )
)


;;;visual paradigma
;;;(batch films.bat)

(run)