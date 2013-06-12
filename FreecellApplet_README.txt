Current Status:

-- The applet can log into a server using Apache Tomcat.  I installed this server on my localhost; these
   instructions may not work if you do not use Apache Tomcat.
   
Information Recorded:
-- Current game state

   For the first X lines, each line lists the current contents of that stack.  
   The (zero-indexed) lines correspond to:
   0-7: The freecells.  This game allows for up to 8 freecells, although the default is 4.
   8-11: The destination card stacks, going from ace to king at the end.
   12-19: The tableau of the freecell game.
   
-- Each move

   Each move has its own line with the following format:
   <source stack index, as listed above>,<destination stack index>,<milliseconds since the previous move>,<notes>.
   Notes include either "Valid","Undo","Redo".
   After the last move, one more line is printed depending on whether the player won or lost, with the line being
   either "-1,-1,-1,Win" or "-1,-1,-1,Loss".
   
-- Other Features
   
   The game will ask for a player name at the beginning to allow the tracking of a player's progress.
   The player name is not allowed to have the following characters: {}<>();.
   The game also stores the path to the servlet as an instance variable in Solitaire, for those who need to change it.
   Lastly, the log file for the game is <player name>_<system time in milliseconds when the file was created>_freecell.txt.

In order to run the applet and have it execute code:
-- Create a new Eclipse project, with the packages jsolitare.freecell 
   and jsolitaire.shared in the src package directory.
-- Move the code from the appropriate directories in FreecellApplet to 
   the corresponding packages in Eclipse.  You can also probably import 
   the directory FreecellApplet as an Eclipse project.
-- Install and start up a server on localhost.
-- Place GameLogServlet.java into the appropriate directory on your server.  
   
   For me, I used Tomcat on my localhost.  As an example, I installed
   Tomcat on my C:\Program Files (x86) directory.  Make the directory
   C:\Program Files (x86)\apache-tomcat-7.0.40\webapps\cyberlearning\WEB-INF\classes.
   Place GameLogServlet into the classes directory and compile with javac.
   Place web.xml in the cyberlearning\WEB-INF directory.  If you changed the directory
   where you put the compiled GameLogServlet, you must edit the web.xml file to use this
   new location.
   The server should be ready to go.
   
-- Start up the server by running startup.bat / .sh in the bin directory of the 
   tomcat server.
   
   IMPORTANT NOTE: If you make changes to GameLogServlet and recompile, you MUST 
   shutdown the server with shutdown.bat / .sh in the bin directory and restart 
   before the changes will be applied.
   
-- In the bin directory, create a directory named cyberlearning_logs.
-- Run the applet by selecting Freecell.java and selecting the Eclipse run button.
   It should be logging to the server as you play.  Any error messages will be 
   displayed in the console output for Eclipse.