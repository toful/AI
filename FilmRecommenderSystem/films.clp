;;; Author: Cristòfol Daudén Esmel
;;; Films hierarchy with CLIPS

(defclass Film
   (is-a USER)
   (role concrete)
   (pattern-match reactive)
   (slot value (create-accessor write) (type INTEGER) (default 0) )
   (slot duration (type INTEGER) (create-accessor write) )
   (slot film_name (type STRING))
   (slot minAge (type INTEGER) )
   (slot imdbRate (type FLOAT) )
   (slot year (type INTEGER) )
   (slot characteristic)
   (slot aftermath)
   (slot world)
   (slot weapon (allowed-values nil Gun Magic LightSable RudimentaryWeapons NoWeapons ) (default nil) )
   (slot actor)
)

(defmessage-handler Film updateValue (?v)
    (bind ?self:value (+ ?self:value ?v ) )
)

(defmessage-handler Film showFilm ()
   (printout T "Pel·licula: " ?self:film_name crlf)
   (printout T "Valoració per al client: " ?self:value crlf)
)

(defclass Action
   (is-a Film)
   (role concrete)
)

(defclass Comedy
   (is-a Film)
   (role concrete)
)

(defclass SciFi
   (is-a Film)
   (role concrete)
)

(defclass Fantasy
   (is-a Film)
   (role concrete)
)

(defclass Musical
   (is-a Film)
   (role concrete)
)

(defclass Romance
   (is-a Film)
   (role concrete)
)

(defclass Thriller
   (is-a Film)
   (role concrete)
)

(defclass Terror
   (is-a Film)
   (role concrete)
   (slot terrorCreature (type SYMBOL) (allowed-values nil Vampire Mummy Invisible Zombie Others) (default nil) )
   (slot fear (type INTEGER) (default 0) )
)

(defclass Action-Adventure
   (is-a Action)
   (role concrete)
)

(defclass Action-Comedy
   (is-a Action Comedy)
   (role concrete)
)

(defclass Martial-Arts
   (is-a Action)
   (role concrete)
)

(defclass Action-Terror
   (is-a Action Terror)
   (role concrete)
)

(defclass Disasters
   (is-a Action)
   (role concrete)
)

(defclass Action-SciFi
   (is-a Action SciFi)
   (role concrete)
)

(defclass Superheros
   (is-a Action SciFi Fantasy)
   (role concrete)
   (slot superPower (type SYMBOL) (allowed-values nil Decrease Transformation RunFast Super) (default nil) )
)

(defclass Fantasy-SciFi
   (is-a SciFi Fantasy)
   (role concrete)
)

(defclass SciFi-Adventure
   (is-a SciFi)
   (role concrete)
)

(defclass Musical-Comedy
   (is-a Comedy Musical)
   (role concrete)
)

(defclass Romance-Comedy
   (is-a Comedy Romance)
   (role concrete)
)

(defclass Romance-Thriller
   (is-a Romance Thriller)
   (role concrete)
)

(defclass Spy
   (is-a Action-Adventure)
   (role concrete)
)

(defclass Martial-Arts-Comedy
   (is-a Martial-Arts Comedy)
   (role concrete)
)

(defclass Monsters
   (is-a Action-Terror)
   (role concrete)
)

(defclass DC
   (is-a Superheros)
   (role concrete)
)

(defclass Marvel
   (is-a Superheros)
   (role concrete)
)

(defclass Acction-Romance-Comedy
   (is-a Action Romance-Comedy)
   (role concrete)
)

(defclass Vampires
   (is-a Monsters)
   (role concrete)
   (slot terrorCreature  (source composite) (default Vampire) )
)

(defclass Zombies-Infections
   (is-a Monsters Disasters)
   (role concrete)
   (slot terrorCreature  (source composite) (default Zombie) )
)


