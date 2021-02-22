package game;

/**
 *
 * Description: <br/>
 * Represent the current Game of the Cluedo. It will random choose the Murder
 * Cards in every game which are which Player is the killer, which Room does the
 * Killer kill the victim AND what Weapon does the killer use to kill the
 * victim.
 *
 * The game is over when the correct accusation is made by any player, that is
 * correct MurderCards are be choosed by the PLayer. OR every players in the
 * Game has made a FALSE accusation, so no one can accusate so that the game is
 * over, in this case, the system should tell everyone the truth.
 *
 * @author Yun Zhou && Ruiyang Zhang
 * @version 2.0
 */
public class Game {

    /**
     * represent the current data
     */
    private GameData gameData_current = new GameData(false, new Room("Kitchen"),
            new Room("Ball Room"), new Room("Conservatory"), new Room("Dining Room"),
            new Room("Cellar"), new Room("Billiard Room"), new Room("Library"),
            new Room("Lounge"),
            new Room("Hall"), new Room("Study"));;

    /**
     * The constructor. Creates a new instance of Game.
     *
     */
    public Game() {
        // set up the Model data
        new GameSetUp(gameData_current);

        Board board = new Board(gameData_current.getCells(),
                gameData_current.getRooms(),
                gameData_current.getMurderCards(),
                gameData_current.getRoomEntranceCells(),
                gameData_current.getAllCards(),
                gameData_current.getPlaying());

        gameData_current.setBoard(board);
        // board.redraw();
        run_Game();

    }

    /**
     *
     * Description: <br/>
     * Run the game until it reaches the game over status. That is, the right
     * accusation is made OR no one made a successful accusation.
     *
     * @author Yun Zhou & Ruiyang Zhang
     */
    public void run_Game() {
        while (!gameData_current.isGameOverStatus()) {
            // for checking whether or not each player is out
            int out_numm = 0;
            for (Player player : gameData_current.getBoard().getPlayingList()) {
                if (!player.getPlaying()) {
                    out_numm++;
                }
            }
            // if every one is out,show the announcement
            if (out_numm == gameData_current.getBoard().getPlayingList().size()) {
                gameData_current.getBoard().getTextOutputArea()
                        .setText("Nobody made a correct accusation!\n" + "Game is Over!");
            }

            // take turns of the player
            if (!gameData_current.getBoard().getPlayingList().isEmpty()) {
                for (Player playerinTurn : gameData_current.getBoard().getPlayingList()) {
                    gameData_current.getBoard().takeTurn(playerinTurn);
                    // gameData_current.getBoard().redraw();
                }
            }

        }

    }

    // ------------------------
    // INTERFACE
    // ------------------------

    /**
     *
     * Description: <br/>
     * Return the status of the Game
     *
     * @author Yun Zhou && Ruiyang Zhang
     * @return the status of the Game
     */
    public boolean getGameOverStat1ue() {
        return gameData_current.isGameOverStatus();
    }

    /**
     *
     * Description: <br/>
     *
     * Get the current game Board.
     *
     * @author Yun Zhou && Ruiyang Zhang
     * @return the current game Board.
     */
    public Board getBoard() {
        return gameData_current.getBoard();
    }

    public String toString() {
        return super.toString() + "[" +
               "gameOverStatue" + ":" + gameData_current.isGameOverStatus() + "]" +
               System.getProperties().getProperty("line.separator") +
               "  " + "board = " +
               (getBoard() != null ? Integer.toHexString(System.identityHashCode(getBoard()))
                       : "null");
    }

    // ==============Umple FILE=========================================
    // ==============Umple FILE=========================================
    // ==============Umple FILE=========================================
    // ==============Umple FILE=========================================
    // ==============Umple FILE=========================================
    // ==============Umple FILE=========================================
    // ==============Umple FILE=========================================
    // ==============Umple FILE=========================================

    /**
     *
     * Description: <br/>
     * Umple exception handler
     *
     * @author Yun Zhou & Ruiyang Zhang
     * @version Game
     */
    @SuppressWarnings("javadoc")
    public static class UmpleExceptionHandler implements Thread.UncaughtExceptionHandler {
        public void uncaughtException(Thread t, Throwable e) {
            translate(e);
            if (e.getCause() != null) {
                translate(e.getCause());
            }
            e.printStackTrace();
        }

        public void translate(Throwable e) {
            java.util.List<StackTraceElement> result = new java.util.ArrayList<StackTraceElement>();
            StackTraceElement[] elements = e.getStackTrace();
            try {
                for (StackTraceElement element : elements) {
                    String className = element.getClassName();
                    String methodName = element.getMethodName();
                    boolean methodFound = false;
                    int index = className.lastIndexOf('.') + 1;
                    try {
                        java.lang.reflect.Method query = this.getClass().getMethod(
                                className.substring(index) + "_" + methodName, new Class[] {});
                        UmpleSourceData sourceInformation = (UmpleSourceData) query.invoke(this,
                                new Object[] {});
                        for (int i = 0; i < sourceInformation.size(); ++i) {
                            // To compensate for any offsets caused by injected
                            // code we need to loop through the other references
                            // to this function
                            // and adjust the start / length of the function.
                            int functionStart = sourceInformation.getJavaLine(i)
                                    + (("main".equals(methodName)) ? 3 : 1);
                            int functionEnd = functionStart + sourceInformation.getLength(i);
                            int afterInjectionLines = 0;
                            // We can leverage the fact that all inject
                            // statements are added to the uncaught exception
                            // list
                            // before the functions that they are within
                            for (int j = 0; j < i; j++) {
                                if (sourceInformation.getJavaLine(j) - 1 >= functionStart &&
                                        sourceInformation.getJavaLine(j) - 1 <= functionEnd &&
                                        sourceInformation.getJavaLine(j) - 1 <= element
                                                .getLineNumber()) {
                                    // A before injection, +2 for the comments
                                    // surrounding the injected code
                                    if (sourceInformation.getJavaLine(j) - 1 == functionStart) {
                                        functionStart += sourceInformation.getLength(j) + 2;
                                        functionEnd += sourceInformation.getLength(j) + 2;
                                    } else {
                                        // An after injection
                                        afterInjectionLines += sourceInformation.getLength(j) + 2;
                                        functionEnd += sourceInformation.getLength(j) + 2;
                                    }
                                }
                            }
                            int distanceFromStart = element.getLineNumber() - functionStart
                                    - afterInjectionLines;
                            if (distanceFromStart >= 0
                                    && distanceFromStart <= sourceInformation.getLength(i)) {
                                result.add(new StackTraceElement(element.getClassName(),
                                        element.getMethodName(), sourceInformation.getFileName(i),
                                        sourceInformation.getUmpleLine(i) + distanceFromStart));
                                methodFound = true;
                                break;
                            }
                        }
                    } catch (Exception e2) {
                    }
                    if (!methodFound) {
                        result.add(element);
                    }
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            e.setStackTrace(result.toArray(new StackTraceElement[0]));
        }

        // The following methods Map Java lines back to their original Umple
        // file / line
        public UmpleSourceData Game_main() {
            return new UmpleSourceData().setFileNames("model.ump").setUmpleLines(4).setJavaLines(73)
                    .setLengths(1);
        }

        public UmpleSourceData Board_drawBoard() {
            return new UmpleSourceData().setFileNames("model.ump").setUmpleLines(18).setJavaLines(671)
                    .setLengths(1);
        }

        public UmpleSourceData Board_checkSuggestion() {
            return new UmpleSourceData().setFileNames("model.ump").setUmpleLines(15).setJavaLines(666)
                    .setLengths(1);
        }

        public UmpleSourceData Board_checkAccusation() {
            return new UmpleSourceData().setFileNames("model.ump").setUmpleLines(14).setJavaLines(661)
                    .setLengths(1);
        }

        public UmpleSourceData Cell_isFree() {
            return new UmpleSourceData().setFileNames("model.ump").setUmpleLines(29).setJavaLines(222)
                    .setLengths(1);
        }

        public UmpleSourceData Cell_getLocationX() {
            return new UmpleSourceData().setFileNames("model.ump").setUmpleLines(27).setJavaLines(212)
                    .setLengths(1);
        }

        public UmpleSourceData Cell_getLocationY() {
            return new UmpleSourceData().setFileNames("model.ump").setUmpleLines(28).setJavaLines(217)
                    .setLengths(1);
        }

        public UmpleSourceData Player_accuse() {
            return new UmpleSourceData().setFileNames("model.ump").setUmpleLines(63).setJavaLines(329)
                    .setLengths(1);
        }

        public UmpleSourceData Player_move() {
            return new UmpleSourceData().setFileNames("model.ump").setUmpleLines(62).setJavaLines(324)
                    .setLengths(1);
        }

        public UmpleSourceData Player_suggest() {
            return new UmpleSourceData().setFileNames("model.ump").setUmpleLines(64).setJavaLines(334)
                    .setLengths(1);
        }

    }

    @SuppressWarnings("javadoc")
    public static class UmpleSourceData {
        String[] umpleFileNames;

        Integer[] umpleLines;

        Integer[] umpleJavaLines;

        Integer[] umpleLengths;

        public UmpleSourceData() {
        }

        public String getFileName(int i) {
            return umpleFileNames[i];
        }

        public Integer getUmpleLine(int i) {
            return umpleLines[i];
        }

        public Integer getJavaLine(int i) {
            return umpleJavaLines[i];
        }

        public Integer getLength(int i) {
            return umpleLengths[i];
        }

        public UmpleSourceData setFileNames(String... filenames) {
            umpleFileNames = filenames;
            return this;
        }

        public UmpleSourceData setUmpleLines(Integer... umplelines) {
            umpleLines = umplelines;
            return this;
        }

        public UmpleSourceData setJavaLines(Integer... javalines) {
            umpleJavaLines = javalines;
            return this;
        }

        public UmpleSourceData setLengths(Integer... lengths) {
            umpleLengths = lengths;
            return this;
        }

        public int size() {
            return umpleFileNames.length;
        }
    }

}