Current Status:

-- The applet can be loaded into Eclipse; after that, it should work with the MAPLE server.
   All log files are written to /usr/share/glassfish3/glassfish/domains/domain1/cardplaying_logs/
 
Log File Format
 
The following lines may appear in the log file:

-- StartTime X

The starting time of the game; X is equal to the system time of the computer playing the game in milliseconds.

-- Freecell X X1 X2 ...

The contents of Freecell number X.  X1 X2 ... is a space separated list of cards.  Cards are noted as <rank><suite>.  Permitted ranks
are 2-10, J(ack), Q(ueen), K(ing), and A(ce).  Permitted suites are c(lubs), d(iamonds), h(earts), and s(pades).  If empty, the list is
replaced with a -.

-- Homecell X X1 X2 ...

Same as Freecell, but describes a homecell, the cell where cards are stacked to end the game.

-- Cascade X X1 X2 ...

Same as Freecell, but describes a cascade, one of the 8 piles of cards outside of the free and homecells.
   
-- MouseMove Time X Y

The mouse was moved at time Time (again, milliseconds system time) to window position with coordinates (X, Y).

-- MouseDown Time X Y Object
-- MouseUp Time X Y

The mouse was either pressed or depressed.  Same as MouseMove, but in the case of MouseDown, Object describes what was clicked.
Sometimes the value is canvas0, which indicates the window background.
   
-- CardMove Time StackFrom StackTo Valid|Invalid

The player attempted to move a card at time Time from the stack numbered StackFrom to the stack numbered StackTo.  See the numbers
associated with the game state.  Valid|Invalid indicates whether the move was valid or not.
   
-- GameRestart Time

The player restarted the game at time Time.

-- GameWin|GameLose,Time

The player won or lost the game at the indicated time.  Note that the comma should be replaced by a space.

-- NewGame Time

The player started a NewGame at time Time.

Other Features
   
The game will ask for a player name at the beginning to allow the tracking of a player's progress.
The player name is not allowed to have the following characters: {}<>();.
The game also stores the path to the servlet as an instance variable in Solitaire, for those who need to change it.
Lastly, the log file for the game is 
<machine id of computer used>_<player name>_<system time in milliseconds when the file was created>_freecell_<game number within current session>.txt.

In order to run the applet and have it execute code:
-- Create a new Eclipse project, with the packages jsolitare.freecell 
   and jsolitaire.shared in the src package directory.
-- Move the code from the appropriate directories in FreecellApplet to 
   the corresponding packages in Eclipse.  Thus, code in the package jsolitaire.freecell
   should to into the jsolitaire.freecell package.
-- In Eclipse, run the Freecell.java file and play.

To modify GameLogServlet, change the code file, recompile it, then make a war suitable for
Glassfish with the .java and the .class files.  On the MAPLE server, empty the
/usr/share/glassfish3/glassfish/domains/domain1/autodeploy/ directory, and then place your war
into it.  Note that this war must have a META-INF and WEB-INF directories; the .class files go
into a class directory in WEB-INF, and web.xml must also be added (provided with GitHub code).
   
If you have additional questions, contact Jonathan Clancy (clancy1@umbc.edu).