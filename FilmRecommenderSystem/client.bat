;;; Author: Cristòfol Daudén Esmel
;;; Client Rules and Instances CLIPS

;;;(clear)
(load "client.clp")

(make-instance client of Client)

(defrule numberFilms 
    ?obj <- ( object (is-a Client) )
    =>
    (printout T "Quantes pel·lícules vols que se't mostren? [1-8]" crlf)
    (bind ?r (read))
    (send ?obj put-filmsToShow ?r)
)

(defrule time-watching-film
    ?obj <- ( object (is-a Client) (time 0) )
    =>
    (printout T "Durant quant de temps pots estar assegut mirant una pel·lícula o una sèrie?" crlf)
    (printout T "1 - Me canso ràpid i en no res m'aixeco a fer coses." crlf)
    (printout T "2 - Puc veure una pel·lícula o un parell de capítols però no més." crlf)
    (printout T "3 - No ho se, però quan acabo tinc les cames adormides." crlf)
    (bind ?r (read))
    (send ?obj put-time ?r)
)

(defrule min-quality
    ?obj <- ( object (is-a Client) (quality 0) )
    =>
    (printout T "Prioritzes:" crlf)
    (printout T "1 - Passar una bona estona." crlf)
    (printout T "2 - Disfrutar d'una pel·lícula amb un mínim argument." crlf)
    (printout T "3 - L'argument." crlf)
    (bind ?q (read))
    (send ?obj put-quality ?q)
)

(defrule last-day
    ?obj <- ( object (is-a Client) (last-day nil) )
    =>
    (printout T "Si demà sigués el teu últim dia com el passaries:" crlf)
    (printout T "[Sports] - Practicant algún esport o perdent-me en la naturalesa." crlf)
    (printout T "[Family] - En companyia de la família i/o parella." crlf)
    (printout T "[Party] - Una última festa amb els amics." crlf)
    (printout T "[Karaoke] - Disfrutar d'una nit al Karaoke amb els amics." crlf)
    (bind ?r (read))
    (send ?obj put-last-day ?r)
)

(defrule min-year
    ?obj <- ( object (is-a Client) (time 0) )
    =>
    (printout T "Prefereixo..." crlf)
    (printout T "1 - lo relacionat amb l'antic." crlf)
    (printout T "2 - lo relativament resent." crlf)
    (printout T "3 - estar a la última." crlf)
    (bind ?r (read))
    (send ?obj put-year ?r)
)

(defrule favourite-sport
    ?obj <- ( object (is-a Client) (last-day Sports) )
    =>
    (printout T "Quin tipus d'esport prefereixes:" crlf)
    (printout T "[MatrialArts] - Alguna art marcial o esport de lliuta." crlf)
    (printout T "[OutdoorSports] - Alguna activitat que es realitzi a l'aire lliure (trailrunning, rafting, escalada,...)." crlf)
    (printout T "[TeamSports] - Esports en equip com futbol, basquet, ..." crlf)
    (printout T "[Others] - Cap dels esmentats." crlf)
    (bind ?r (read))
    (send ?obj put-favourite-sport ?r)
)

(defrule age
    ?obj <- ( object (is-a Client) (age 0) )
    =>
    (printout T "Quants anys tens?" crlf)
    (bind ?r (read))
    (send ?obj put-age ?r)
)

(defrule aftermath
    ?obj <- ( object (is-a Client) (aftermath nil) )
    =>
    (printout T "Las segundas partes..." crlf)
    (printout T "[No] nunca fueron buenas." crlf)
    (printout T "[Yes] siempre son bienvenidas." crlf)
    (bind ?r (read))
    (send ?obj put-aftermath ?r)
)

(defrule question1
    ?obj <- ( object (is-a Client) (characteristic nil) )
    =>
    (printout T "Si et donessin a triar que preferiries:" crlf)
    (printout T "[Magic] Poder fer màgia." crlf)
    (printout T "[Car] Tenir un cotxe de luxe." crlf)
    (printout T "[Robot] Un amic androide." crlf)
    (printout T "[Super] Tenir algun superpoder." crlf)
    (bind ?r (read))
    (send ?obj put-characteristic ?r)
)

(defrule question2
    ?obj <- ( object (is-a Client) (world nil) )
    =>
    (printout T "Els meus somnis són ambientats en:" crlf)
    (printout T "[War] un món en guerra." crlf)
    (printout T "[Fantastic] un món fantàstic." crlf)
    (printout T "[Real] la vida real." crlf)
    (bind ?q (read))
    (send ?obj put-world ?q)
)

(defrule question3
    ?obj <- ( object (is-a Client) (weapon nil) )
    =>
    (printout T "En cas de necessiatar-ho usaria ... per defensar-me." crlf)
    (printout T "[Gun] armes militars( Pistoles, Metralladores, ...)" crlf)
    (printout T "[Magic] una vareta màgica." crlf)
    (printout T "[LightSable] una espasa làser" crlf)
    (printout T "[RudimentaryWeapons] una arma tradicional (arcs, espases, ganivets,...)" crlf)
    (printout T "[NoWeapons] les meves pròpies mans per defensar-me." crlf)
    (bind ?q (read))
    (send ?obj put-weapon ?q)
)

(defrule question4
    ?obj <- ( object (is-a Client) (characteristic2 nil) )
    =>
    (printout T "Si veiguès alguna cariatura fantàstica (drac, zombie, vampir,..)..." crlf)
    (printout T "[FantasticAnimals] m'agradaria tenir-lo com a mascota." crlf)
    (printout T "[TerrorAnimals] marxaria corrents tant ràpid com pugés." crlf)
    (printout T "[FarAwayFromAnimals] em quedaria a una diatància prudent, sense aproparme ni fugir." crlf)
    (bind ?q (read))
    (send ?obj put-characteristic2 ?q)
)

(defrule question6
    ?obj <- ( object (is-a Client) (characteristic Super) )
    =>
    (printout T "Quin superpoder voldries tenir?" crlf)
    (printout T "[Decrease] Poder-me fer superpetit, casi invisible." crlf)
    (printout T "[Transformation] Convertir-me en una massa muscular invencible." crlf)
    (printout T "[RunFast] Córrer tant ràpid com sigui possible." crlf)
    (printout T "[Super] Poder volar, i tenir una força sobrehumana." crlf)
    (bind ?q (read))
    (send ?obj put-superPower ?q)
)

(defrule question7
    ?obj <- ( object (is-a Client) (characteristic Super) )
    =>
    (printout T "Qui creus que guanyaria en una baralla?" crlf)
    (printout T "Hulk" crlf)
    (printout T "Superman." crlf)
    (bind ?q (read))
    (send ?obj put-fight ?q)
)

(defrule question8
    ?obj <- ( object (is-a Client) (fear -1) )
    =>
    (printout T "Et caracteritzes per ser ..." crlf)
    (printout T "0 - Una persona incapaç de suportar la por, sóc capaḉ de plorar." crlf)
    (printout T "1 - una persona que s'assusta amb facilitat i ho passa malament en aquests casos." crlf)
    (printout T "2 - una persona que li costa assustar-se." crlf)
    (printout T "3 - una persona que disfruta de la tensió en situacions de pànic." crlf)
    (bind ?q (read))
    (send ?obj put-fear ?q)
)

(defrule question5
    ?obj <- ( object (is-a Client) (fear ?f&:(> ?f 0) ) )
    =>
    (printout T "Tinc pànic ..." crlf)
    (printout T "[Vampire] als vampirs." crlf)
    (printout T "[Mummy] a les mòmies." crlf)
    (printout T "[Invisible] a que algú invisible em pugui atacar." crlf)
    (printout T "[Zombie] als zombies." crlf)
    (printout T "[Others] a res, sóc un valent." crlf)
    (bind ?q (read))
    (send ?obj put-terrorCreature ?q)
)


(defrule question9
    ?obj <- ( object (is-a Client) (favourite-sport OutdoorSports ) )
    =>
    (printout T "Quin dels següents actors t'agrada més?" crlf)
    (printout T "[JasonStatham] Jason Statham." crlf)
    (printout T "[VinDiesel] Vin Diesel." crlf)
    (printout T "[SylvesterStallone] Sylvester Stallone." crlf)
    (printout T "[HarrisonFord] Harrison Ford." crlf)
    (bind ?q (read))
    (send ?obj put-favouriteActor ?q)
)

(defrule question10
    ?obj <- ( object (is-a Client) ( last-day ?x&:( or (eq ?x Party) (eq ?x Karaoke) ) ) )
    =>
    (printout T "Quin dels següents actors t'agrada més?" crlf)
    (printout T "[JohnTravolta] John Travolta." crlf)
    (printout T "[LadyGaga] Lady Gaga." crlf)
    (printout T "[RamiMalek] Rami Malek." crlf)
    (printout T "[HughJackman] Hugh Jackman." crlf)
    (printout T "[MerylStreep] Meryl Streep." crlf)
    (bind ?q (read))
    (send ?obj put-favouriteActor ?q)
)
