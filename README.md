This is Gr.12 ISU Assignment for david and hamse

This is a plant vs zombie game with some character from other game
for info about the game you can read from the help page
Click "a" for about page, "h" for help page
Click play and select level to start the game
endless is a level that never end until the zombie eat ur brain and they will get stronger every wave
gacha is where you can spend money that you earned from game to roll for plant
team page is where you select plant to add in ur team
The battle is just like plant vs zombie
gather sun, put down plant and kill all the zombies
Oh yea, just saying there is no exit once you start a battle
you can change the starting sunlight by going to the playe class and look for a method called resetForLevel or something like that
Enjoy :)

David worked on:
Plant
Character
Projectile
Collision for projectile
Gacha
Team page
all the graphic for plant, projectile, gacha, and team page

Hamse worked on:
zombie
map
all the level
endless mode
Most of the sound effect and music
collision for zombie
all the other graphic that i didnt do
animation for his zombie
scoreboard
all the other different screen like
winner screen
loser screen

Note:
- scoreboard is pretty much useless beside fulfilling the requirements I mean if you want you can check your previous highest wave
- the audio is very buggy and delaying, especially the zombie eating sound
- the default save file contains all the plant in the game
- to change save jsut delete or move the save.txt somewhere else
- for testing and let Ms.Wong have a better time, I change it so that you start with 2000 sun and have pretty much unlimited money for rolling
- There are some variable that is useless, I just forget which one so i just leave it there
Error:
Sometime there will be ConcurrentModificationException, i dont know how to fix it so i just catch it and leave it there
There is a bug where zombie are spawned but not coming out, and the level cannot continue because you cant kill these zombies
You might encounter times when you cant plant or plant on the wrong tile, this is mainly due to have have a bad algorithm for calculating tile