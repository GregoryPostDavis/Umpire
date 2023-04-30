# Umpire

A minecraft plugin for automating RFW games in 1.19. Based loosely on the
1.8 plugin AutoReferee. RFW maps are placed as zip files in the "maps"
directory, along with a file labeled "autoreferee.xml". This file is loaded
whenever a map is loaded. autoreferee.xml files use the same formatting as
in autoref, but not all functionality from them has been implemented.

### Commands

- /loadmap \<mapname> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
        Loads a map and sends the player to the map world.
- /lobby <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        Sends the player back to the lobby world.
- /join \<teamname> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  Joins the player to a team. (/j works aswell. If no team is provided, a 
  random non-observer team is joined)
- /leave <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  Leaves the current team and sends them to the observers team. (\l also works)
- /ready <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  Sets the status of the team that the player is currently on as ready. If
  the player is an observer and no other players are on, this will begin the
  game.
- /unready <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  Sets the team's status as unready and cancels any countdowns that have started.
- /unload <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  Unloads the current map and sends the player to the lobby.
- /reloadmap <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  Reloads the current map. (/reload is reserved by spigot for reloading all 
  plugins)
- /joinmatch \<player> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  Joins the match that a provided player is in.
- /viewinventory \<player> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  As an observer, views the inventory of a player. (/vi \<player> also works)

