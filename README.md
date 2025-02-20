[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/KprAwj1n)
# APCS - Stuyrim

## Features

### Required
:white_check_mark: add debuff system to adventurer

:white_check_mark: 2 required adventurer subclasses

:white_check_mark: stat display

:white_check_mark: description text

:white_check_mark: win/loss condition

:white_check_mark: gameplay loop


### Optional
:ballot_box_with_check: 1 extra adventurer subclass

:ballot_box_with_check: multiple random rounds

:x: boss

:x: items: bonuses, healing, etc.

:ballot_box_with_check: immune level system (for buffs/debuffs and difficulty scaling)

:x: allow player to choose classes and names

:ballot_box_with_check: random names for enemies


## Adventurer Subclasses

### HIV
HP: 30

Attack: 1-3 pts

Special: Costs 6 pts. Infect an enemy. 2 turns later, the enemy will be dealt 1 damage per turn and be affected by a damage debuff for 5 turns. During the first 3 turns after the infection, the enemy can spread it to its teammates by supporting them.

Support (self): Regains 2 points of special. Heals by 4 HP.

Support (team): Buffs team damage by 1.2x.


### COVID
HP: 18

Attack: 4-7 pts

Special: Costs 10 pts. Deals 12 of damage.

Support (self): Heals by 3 HP.

Support (team): Team heals by 4 HP.


### Norovirus
HP: 26

Attack: 5 pts

Special: Costs 6 pts. Enemy vomits. Skips turn.

Support (self): Regains 2 pts of special. Heals by 3 HP.

Support (team): Teammate regains 4 pts of special.
