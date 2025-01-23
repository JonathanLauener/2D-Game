# Helldivers

### Mitglieder:
Len Wischnewski,
Jonathan Launer,
Emil Constein

### Spielkonzept:
Das Spiel ist ein klassisches dungeon adventure. 
Zunächst startet man in einem kleineren Raum. Das Ziel des Spieles ist das Erreichen des Endraumes.
Algemein besteht die Map aus vielen Räumen, die mit Gängen und Schlüsseltüren verbunden sind. 
Nach manchen Räumen hat man die Wahl zwischen mehreren Wegen.
Es gibt auch Monster (und einen Endgegener), die einen beim hervordringen hindern und die Schlüssel bewachen,
die man benutzen kann um sich Zugang zu weiteren Räumen zu verschaffen, bis man am Ziel angelangt ist.

### Changelog: 
##### 1.
Einführung der Türmechanik: Es ist nun möglich, die eingesammelten Schlüssel zu benutzen, indem man zu
einer Tür geht und "e" drückt (später zu f geändert)

Start Einführung der Textboxen: Die Textboxen sollen später erscheinen, wenn eine bestimmte Taste z.B. *E*
gedrückt werden soll, um eine Aktion auszulösen. Die Textboxen können natürlich auf für Dialoge benutzt werden.
Momentan erscheint eine Textbox: *Drücke E* sobald man eine Tür berührt.
Nachtrag: Die Textboxen wurden im laufe der Entwicklung verworfen.

Veränderung der Map: Es wurden erste Gänge und Räume hinzugefügt, die mit Schlüsseltüren getrennt sind. Später wurden
##### 2.
Start der Implementierung des A* algorithmus

Ein NPC wurde zum Test hinzugefügt, dieser bewegt sich zufällig und wurde später wieder entfernt (Platzhalter für den Gegner)
##### 3.
Eine weitere Hitbox wurde hinzugefügt. Später sollen die Gegner erst aktiv werden, wenn man sich ihnen nähert. 

Alle Texturen werden nach und nach durch selbst erstellte ersetzt. Zunächst wurden die Wände und der Boden ausgetauscht.

Es wurde eine neue komplett selbsterstellte Musik entwickelt, die die alte ersetzten soll.
##### 4.
Alle weiteren Texturen wurden hinzugefügt z.B. für Löcher. Aber auch neue Steinvarianten, wie bemoste und rissige Steine
wurden hinzugefügt.

Es wurde ein neues Gegnerdesign erstellt und eine zugehörige Animation. Die Animation wurde auf den
sich zufällig bewegenden NPC angewendet.

Der A* algorithmus wurde fertiggestellt.
##### 5.
Es wurden designs für Eier erstellt, aus denen später die Gegner (Wespen) schlüpfen sollen. 

Befindet sich der Spieler in der direkten nähe zu einem der Eier, schlüpft daraus ein Gegner, der den Spieler
verfolgt.

Durch das Drücken von "e" kann der Gegner deaktiviert werden.
##### 6.
Es wurden Schlaganimationen für alle Richtungen des Spielers erstellt und implementiert. 

Der Gegner kann nun durch Schlagen besiegt werden, woraufhin er auch verschwindet.

Der Spieler kann auch besiegt werden, wenn er sich zu nah am Gegner befindet. Passiert dies,
wird das Spiel automatisch geschlossen.
##### 7.
Es wurden Bildschirme für den Beginn, einen Tot und dem Beenden des Spieles hinzugefügt. Außerdem wurde eine
Pausenfunktion entwickelt.

Die Map wurde ein weiteres Mal überarbeitet und besitzt nun einen festen Beginn und einen Ausgang.
(Wurde der Ausgang erreicht, wird ein Endbildschirm eingeblendet.
##### 8.
Ein neues Gegnerdesign wurde entworfen und für alle Richtungen animiert. Dieser Gegner ist nicht nur 16x16
Pixel groß, wie alle anderen Objekte des Spieles, sondern 32x32 Pixel. Er soll später der Endboss sein, als letztes
Hindernis vor dem Ausgang.


### Weitere mögliche Änderungen:
##### 1.
Hinzufügen von verschiedenen Karten-> Verlängerung des Spieles
##### 2.
Hinzufügen eines Waffensystems-> Verschiedene Waffen, mit unterschiedlichem Schaden oder unterschiedlicher Haltbarkeit. Waffen könnten 
gefunden werden oder in einem Shop gekauft werden in Austausch gegen eine Spielwährung.
##### 5.
Lebenssystem: Ist im aktuellen Zustand nicht notwendig. Um das Spiel zu vereinfachen oder wenn neue schwere Gegnerarten
hinzugefügt werden, wäre ein Lebens-(Herzen)System praktisch.

