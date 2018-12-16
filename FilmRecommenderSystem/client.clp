;;; Author: Cristòfol Daudén Esmel
;;; Films hierarchy with CLIPS

(defclass Client
   (is-a USER)
   (role concrete)
   (pattern-match reactive)
   (slot moreThan18)
   (slot age (type INTEGER) (default 0) (create-accessor write) )
   (slot year (type INTEGER) (default 0) (create-accessor write) )
   (slot quality (default 0) (create-accessor write) )
   (slot filmsToShow (type INTEGER) (default 5) (create-accessor write) )
   (slot time (type INTEGER) (default 0) (create-accessor write) )
   (slot last-day (type SYMBOL) (allowed-values nil Sports Family Party Karaoke) (default nil) (create-accessor write) )
   (slot favourite-sport (type SYMBOL) (allowed-values nil MatrialArts OutdoorSports TeamSports Others) (default nil) (create-accessor write) )
   (slot aftermath (type SYMBOL) (allowed-values nil Yes No ) (default nil) )
   (slot characteristic (type SYMBOL) (allowed-values nil Magic Car Robot Super ) (default nil) )
   (slot world (type SYMBOL) (allowed-values nil War Fantastic Real ) (default nil) )
   (slot weapon (type SYMBOL) (allowed-values nil Gun Magic LightSable RudimentaryWeapons NoWeapons ) (default nil) )
   (slot characteristic2 (type SYMBOL) (allowed-values nil FantasticAnimals TerrorAnimals FarAwayFromAnimals) (default nil) )
   (slot terrorCreature (type SYMBOL) (allowed-values nil Vampire Mummy Invisible Zombie Others) (default nil) )
   (slot superPower (type SYMBOL) (allowed-values nil Decrease Transformation RunFast Super) (default nil) )
   (slot fight (type SYMBOL) )
   (slot fear (type INTEGER) (default -1) )
   (slot favouriteActor (type SYMBOL) (default nil) )
)


(defmessage-handler Client decreaseFilmsToShow ()
    (bind ?self:filmsToShow (- ?self:filmsToShow 1 ) )
)

(defmessage-handler Client put-time after (?t)
    (if (= ?t 1)
        then
        (bind ?self:time 95)
        else
        (if (= ?t 2)
            then
            (bind ?self:time 120)
            else
            (bind ?self:time 1000)))
)

(defmessage-handler Client put-age after (?t)
    (bind ?self:age ?t)
    (if (>= ?t 18)
        then
        (bind ?self:moreThan18 true)
        else
        (bind ?self:moreThan18 false))
)

(defmessage-handler Client put-quality after (?q)
    (if (= ?q 1)
        then
        (bind ?self:quality 0.1)
        else
        (if (= ?q 2)
            then
            (bind ?self:quality 6.5)
            else
            (bind ?self:quality 8)))
)

