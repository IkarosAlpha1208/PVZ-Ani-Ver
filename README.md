**Gr. 12 ISU Assignment – David and Hamse**

This is a Plants vs. Zombies–style game with some characters from other games.
For more information, you can check the help page.

* Press **"A"** for the About page, **"H"** for the Help page.
* Click **Play** and select a level to start the game.
* **Endless Mode** is a level that never ends until the zombies eat your brain. Each wave makes the zombies stronger.
* **Gacha** lets you spend the money you earn in-game to roll for plants.
* **Team Page** is where you select plants to add to your team.
* Battles work just like Plants vs. Zombies: gather sun, place plants, and defeat the zombies.

Note: once you start a battle, there is no exit.
You can change the starting sunlight by going to the `Player` class and looking for a method called `resetForLevel` (or something similar).

Enjoy :)

---

### Contributions

**David worked on:**

* Plant
* Character
* Projectile
* Projectile collision
* Gacha
* Team page
* Graphics for plant, projectile, gacha, and team page

**Hamse worked on:**

* Zombie
* Map
* Levels
* Endless mode
* Most sound effects and music
* Zombie collision
* Other graphics not done by David
* Zombie animation
* Scoreboard
* Other screens (e.g., winner/loser screens)

---

### Notes

* The scoreboard is mostly just for fulfilling requirements. It does track your highest wave if you want to check.
* The audio is very buggy and has delays, especially the zombie eating sound.
* The default save file already contains all the plants in the game.
* To change the save, just delete or move the `save.txt` file.
* For testing (and to make things easier for Ms. Wong), the game starts with 2000 sun and essentially unlimited money for rolling.
* There are some unused variables in the code—I forgot which ones, so I just left them.

