package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.Border;
import javax.swing.text.DefaultCaret;

import game.Board;
import game.Game;

/**
 * Description: <br/>
 * <p>
 * This class is the class for constructing Graphical User Interface. It draw
 * the button and supply texts to the Players
 *
 * @author Yun Zhou & Ruiyang Zhang
 * @version 4.0
 */
public abstract class GameController extends JPanel {// implements KeyListener {
    /**
     * serialVersionUID:
     */
    private static final long serialVersionUID = 1L;

    /**
     * player_number: number of players that are involved in the current game
     */
    static int player_number;

    /**
     * height of the window
     */
    private static final int DEFAULT_DRAWING_HEIGHT = 400;

    /**
     * width of the winddow
     */
    private static final int DEFAULT_DRAWING_WIDTH = 400;

    /**
     * the row of the text out put area
     */
    private static final int TEXT_OUTPUTAREA_ROWS = 5;

    /**
     * the jframe window
     */
    protected static Window frame_window;

    /**
     * panel for controled part
     */
    private JPanel controled;

    /**
     * components for drawing part
     */
    private JComponent drawingComponents;

    /**
     * the area for showing the texts
     */
    private JTextArea text_Output_Area;

    /**
     * JTextField for player to enter their nick name
     */
    @SuppressWarnings("unused")
    private JTextField textField;

    /**
     * Description: <br/>
     * Is called when the drawing area is redrawn and performs all the logic for
     * the actual drawing, which is done with the passed Graphics object.
     *
     * @param g
     *            the graphic that will be draw on the Board
     * @author Yun Zhou & Ruiyang Zhang
     */
    protected abstract void drawBoard(Graphics g);

    /**
     * A constructor. It construct a new instance of GUI.
     */
    public GameController() {
        initialise();
    }

    /**
     * Description: <br/>
     * Construct the graphic user interface for the Game
     *
     * @author Yun Zhou & Ruiyang Zhang
     */
    @SuppressWarnings("serial")
    private void initialise() {

        /*
         * Jbutton stuff
         */

        // Construct a button to go north
        JButton up_north = new JButton("\u2191");
        up_north.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                Board.setDes("w");
                redraw();
            }
        });

        // Construct a button to go left/west
        JButton left_west = new JButton("\u2190");
        left_west.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                Board.setDes("a");
                redraw();
            }
        });

        // Construct a button to go south
        JButton down_south = new JButton("\u2193");
        down_south.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                Board.setDes("s");
                redraw();
            }
        });

        // Construct a button to go east
        JButton right_east = new JButton("\u2192");
        right_east.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                Board.setDes("d");
                redraw();
            }
        });
        loadShortCut(up_north, left_west, down_south, right_east);

        // Construct a quit button
        JButton quit_button = new JButton("Quit");
        quit_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                // exit the game
                close_confirmation_dialog("exit");
            }
        });

        // Construct a button to make the accusation
        JButton accusation = new JButton("Accusation");
        String c = "Accusation";
        accusation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                Board.setDes("c");
                redraw();
            }
        });
        // Pressing CTRL + C for accusation
        Action printAccusation = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Board.setDes("c");
                redraw();
            }
        };
        accusation.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK), c);
        accusation.getActionMap().put(c, printAccusation);

        // Construct a button to make the suggestion
        JButton suggest = new JButton("Suggest");
        String g = "Suggest";
        suggest.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                Board.setDes("g");
                redraw();
            }
        });

        // Pressing CTRL + g for suggestion
        Action printSuggest = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Board.setDes("g");
                redraw();
            }
        };
        suggest.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(KeyEvent.VK_G, KeyEvent.CTRL_DOWN_MASK), g);
        suggest.getActionMap().put(g, printSuggest);

        // Construct a button to end the turn
        JButton end_turn = new JButton("End Turn");
        String e = "End Turn";
        end_turn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                Board.setDes("e");
                redraw();
            }
        });
        // Pressing CTRL + e for end turn
        Action printEndTurn = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Board.setDes("e");
                redraw();
            }
        };
        end_turn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_DOWN_MASK), e);
        end_turn.getActionMap().put(e, printEndTurn);

        // Construct button to show hand
        JButton hand = new JButton("Hand");
        String h = "Hand";
        hand.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                Board.setDes("h");
                redraw();
            }
        });
        // Pressing CTRL " h to show hand
        Action printHand = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Board.setDes("h");
                redraw();
            }
        };
        hand.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.CTRL_DOWN_MASK), h);
        hand.getActionMap().put(h, printHand);

        // Construct button to show shortcut keys
        JButton shortcutKeys = new JButton("Shortcut Keys");
        String sk = "Shortcut Keys";
        shortcutKeys.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                Board.setDes("sk");
                redraw();
            }
        });
        // Pressing CTRL " I to show what shortcut keys are available
        Action printshortcutKeys = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Board.setDes("sk");
                redraw();
            }
        };
        shortcutKeys.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(KeyEvent.VK_I, KeyEvent.CTRL_DOWN_MASK), sk);
        shortcutKeys.getActionMap().put(sk, printshortcutKeys);

        /*
         * construt the controled layout
         */
        controled = new JPanel();
        controled.setLayout(new BoxLayout(controled, BoxLayout.LINE_AXIS));
        // set the Border edge
        Border edge = BorderFactory.createEmptyBorder(6, 6, 6, 6);
        controled.setBorder(edge);

        // load the function of quit the game
        JPanel load_quit_function = new JPanel();
        load_quit_function.setLayout(new GridLayout(2, 1));
        load_quit_function.setMaximumSize(new Dimension(52, 104));
        load_quit_function.add(quit_button);
        controled.add(load_quit_function);
        controled.add(Box.createRigidArea(new Dimension(15, 0)));

        /*
         * for loading the function of the navgiation which is move,make
         * suggestion and make accusation
         */
        JPanel navigation = new JPanel();
        navigation.setMaximumSize(new Dimension(150, 60));
        navigation.setLayout(new GridLayout(2, 4));
        navigation.add(suggest);
        navigation.add(up_north);
        navigation.add(accusation);
        navigation.add(end_turn);
        navigation.add(shortcutKeys);
        navigation.add(left_west);
        navigation.add(down_south);
        navigation.add(right_east);
        navigation.add(hand);

        controled.add(navigation);
        controled.add(Box.createRigidArea(new Dimension(15, 0)));
        controled.add(Box.createHorizontalGlue());

        drawingComponents = new JComponent() {
            protected void paintComponent(Graphics g) {
                drawBoard(g);
            }
        };
        drawingComponents.setPreferredSize(new Dimension(DEFAULT_DRAWING_WIDTH,
                DEFAULT_DRAWING_HEIGHT));

        drawingComponents.setVisible(true);

        drawingComponents.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent mouseEvent) {
                redraw();
            }
        });

        drawingComponents.addMouseWheelListener(new MouseAdapter() {
            public void mouseWheelMoved(MouseWheelEvent mouseWheelEvent) {
            }
        });

        text_Output_Area = new JTextArea(TEXT_OUTPUTAREA_ROWS, 0);
        text_Output_Area.setLineWrap(true);
        text_Output_Area.setWrapStyleWord(true);
        text_Output_Area.setEditable(false);

        // JSplitpane for showing the broadcast message
        JSplitPane myJSplitPane = setSplitPane();

        frame_window = new Window("Cluedo Game");
        frame_window.setJMenuBar(myBar());

        frame_window.setLayout(new BorderLayout());
        frame_window.add(controled, BorderLayout.NORTH);
        frame_window.add(myJSplitPane, BorderLayout.CENTER);

        frame_window.pack();

        frame_window.setVisible(true);
    }

    /**
     * Description: <br/>
     * Return the JTextArea at the bottom of the screen for output.
     *
     * @return the JTextArea at the bottom of the screen for output.
     * @author Yun Zhou & Ruiyang Zhang
     */
    public JTextArea getTextOutputArea() {
        return text_Output_Area;
    }

    /**
     * Description: <br/>
     * return the dimensions of the drawing area.
     *
     * @return the dimensions of the drawing area.
     * @author Yun Zhou & Ruiyang Zhang
     */
    public Dimension getDrawingAreaDimension() {
        return drawingComponents.getSize();
    }

    /**
     * Description: <br/>
     * This method is for redrawing the window that represents the Game.
     * Actually it should be called automactially during the Game, such as
     * player press the button,search box is updated
     *
     * @author Yun Zhou & Ruiyang Zhang
     */
    public void redraw() {
        frame_window.repaint();
    }

    /**
     * Description: <br/>
     * Construct a dialog to ask how many players are involving in this game.
     *
     * @return the number of the player
     * @author Yun Zhou & Ruiyang Zhang
     */
    public static int getNumPlayers() {
        // Window tempWindow = new Window("Choose player number");
        // tempWindow.ask_number_player();
        // return player_number;

        Object[] fixed_option_number = { "3", "4", "5", "6" };
        String playerNumberString = (String) JOptionPane.showInputDialog(frame_window,
                "How many players are involved?", "Game set up",
                JOptionPane.PLAIN_MESSAGE, null, fixed_option_number, "3");

        // convert the string to int and return it
        if ((playerNumberString != null) && (playerNumberString.length() > 0)) {
            int numberOfPlayers = Integer.parseInt(playerNumberString);
            return numberOfPlayers;
        }

        // dead code
        return -1;
    }

    /**
     * Description: <br/>
     * Construct a dialog window for asking players to input their nick name of
     * the Game.
     *
     * @param text
     *            the text for waring that the nickName has already exist
     * @return the Player nick name
     * @author Yun Zhou & Ruiyang Zhang
     */
    public static String getPlayerNickName(String text) {

        // dialog window for asking
        String nickName = (String) JOptionPane.showInputDialog(frame_window,
                text + "Please enter your nick name: ", "Game set up",
                JOptionPane.PLAIN_MESSAGE,
                null, null, "Name to Enter");

        if ((nickName != null) && (!nickName.isEmpty())) {
            return nickName;
        }
        // player didn't enter the nick name,
        // so return the default nick name: "Unknown"
        return "Unknown";
    }

    /**
     * Description: <br/>
     * Construct a dialog window for asking players to choose their Character to
     * play in the Game.
     *
     * @param text
     *            the text for waring that the Player has already be choosed by
     *            others
     * @return index of the Character
     * @author Yun Zhou & Ruiyang Zhang
     */
    public static int getCharacterChoice(String text) {

        // the Character pool
        Object[] fixed_character_name = { "Miss Scarlett", "Colonel Mustard", "Mrs. White",
                "Mr. Green", "Mrs. Peacock", "Professor Plum" };

        // dialog window for asking
        String character_String = (String) JOptionPane.showInputDialog(frame_window,
                text + "Which character would you like to choose?", "Game set up",
                JOptionPane.PLAIN_MESSAGE,
                null, fixed_character_name, "Character to choose");

        // check the string and convert it to the number of index
        if ((character_String != null) && (!character_String.isEmpty())) {
            // return the index
            if (character_String.equals(fixed_character_name[0])) {
                return 0;
            } else if (character_String.equals(fixed_character_name[1])) {
                return 1;
            } else if (character_String.equals(fixed_character_name[2])) {
                return 2;
            } else if (character_String.equals(fixed_character_name[3])) {
                return 3;
            } else if (character_String.equals(fixed_character_name[4])) {
                return 4;
            } else if (character_String.equals(fixed_character_name[5])) {
                return 5;
            }
        }

        // dead code
        return -1;

    }

    /**
     * Description: <br/>
     * Construct a JOptionPane to ask the user to choose the Character. Return
     * the corresponding index of each Character
     *
     * @param text
     *            corner text and also for waring the Player has already be
     *            choosed by others
     * @return index of character for accusation/suggestions
     * @author Yun Zhou & Ruiyang Zhang
     */
    public static int character_for_accusation(String text) {
        // for top left corner string
        String cornerString = "";
        if (text == "setUp" || text == "") {
            cornerString = "Game set up";
        } else if (text == "accusation") {
            cornerString = "Make Accusation";
        } else if (text == "suggestion") {
            cornerString = "Make Suggestion";
        }

        Object[] fixed_character_name = { "Miss Scarlett", "Colonel Mustard", "Mrs. White",
                "Mr. Green", "Mrs. Peacock", "Professor Plum" };

        // dialog window for asking
        String character_String = (String) JOptionPane.showInputDialog(frame_window,
                "Which character would you like to choose?", cornerString,
                JOptionPane.PLAIN_MESSAGE,
                null, fixed_character_name, "Character to choose");

        // check the string and convert it to the number of index
        if ((character_String != null) && (!character_String.isEmpty())) {
            // return the index
            if (character_String.equals(fixed_character_name[0])) {
                return 0;
            } else if (character_String.equals(fixed_character_name[1])) {
                return 1;
            } else if (character_String.equals(fixed_character_name[2])) {
                return 2;
            } else if (character_String.equals(fixed_character_name[3])) {
                return 3;
            } else if (character_String.equals(fixed_character_name[4])) {
                return 4;
            } else if (character_String.equals(fixed_character_name[5])) {
                return 5;
            }
        }

        // dead code
        return -1;
    }

    /**
     * Description: <br/>
     * Construct a JOptionPane to ask the user to choose the Weapon. Return the
     * corresponding index of each Weapon.
     *
     * @param text
     *            for top left corner message
     * 
     * @return index of weapon for accusation and suggestions
     * @author Yun Zhou & Ruiyang Zhang
     */
    public static int weapon_for_accusation_suggestion(String text) {
        // for top left corner string
        String cornerString = "";
        if (text == "setUp" || text == "") {
            cornerString = "Game set up";
        } else if (text == "accusation") {
            cornerString = "Make Accusation";
        } else if (text == "suggestion") {
            cornerString = "Make Suggestion";
        }

        Object[] fixed_weapon_name = { "Candlestick", "Dagger", "Lead Pipe", "Revolver",
                "Rope", "Spanner" };
        String weapon_string = (String) JOptionPane.showInputDialog(frame_window,
                "Which weapon would you like to choose?", cornerString, JOptionPane.PLAIN_MESSAGE,
                null, fixed_weapon_name, "");

        // check the string and convert it to the number of index
        if ((weapon_string != null) && (!weapon_string.isEmpty())) {
            // return the index
            if (weapon_string.equals(fixed_weapon_name[0])) {
                return 0;
            } else if (weapon_string.equals(fixed_weapon_name[1])) {
                return 1;
            } else if (weapon_string.equals(fixed_weapon_name[2])) {
                return 2;
            } else if (weapon_string.equals(fixed_weapon_name[3])) {
                return 3;
            } else if (weapon_string.equals(fixed_weapon_name[4])) {
                return 4;
            } else if (weapon_string.equals(fixed_weapon_name[5])) {
                return 5;
            }

        }

        // dead code
        return -1;
    }

    /**
     * Description: <br/>
     * Construct a JOptionPane to ask the user to choose the Room for
     * ACCUSATION. Return the corresponding index of each ROOM
     *
     *
     * @param text
     *            player name
     * @return index of room for accusation and suggestions
     * @author Yun Zhou & Ruiyang Zhang
     */
    public static int room_for_accusation_suggestion(String text) {
        // fixed room options
        Object[] fixed_room_option = { "Hall", "Lounge", "Kitchen", "Ball Room", "Conservatory",
                "Billiard Room", "Library",
                "Study", "Dining Room" };

        String room_string = (String) JOptionPane.showInputDialog(frame_window,
                text + "Which room would you like to choose?", "Make Accusation",
                JOptionPane.PLAIN_MESSAGE, null,
                fixed_room_option, "");

        // check the string and convert it to the number of index
        if ((room_string != null) && (!room_string.isEmpty())) {
            // return the index
            if (room_string.equals(fixed_room_option[0])) {
                return 0;
            } else if (room_string.equals(fixed_room_option[1])) {
                return 1;
            } else if (room_string.equals(fixed_room_option[2])) {
                return 2;
            } else if (room_string.equals(fixed_room_option[3])) {
                return 3;
            } else if (room_string.equals(fixed_room_option[4])) {
                return 4;
            } else if (room_string.equals(fixed_room_option[5])) {
                return 5;
            } else if (room_string.equals(fixed_room_option[6])) {
                return 6;
            } else if (room_string.equals(fixed_room_option[7])) {
                return 7;
            } else if (room_string.equals(fixed_room_option[8])) {
                return 8;
            }

        }

        // dead code
        return -1;
    }

    /**
     * Description: <br/>
     * Load the quick shortcut function to each JButton, like player press
     * ALT+w/a/s/d to move the player
     * 
     * @author Yun & Ruiyang & Andree & Gimani
     * @param up_north
     *            upButton to load the function
     * @param left_west
     *            leftButton to load the function
     * @param down_south
     *            downButton to load the function
     * @param right_east
     *            rightButton to load the function
     */
    @SuppressWarnings("serial")
    public void loadShortCut(JButton up_north, JButton left_west, JButton down_south,
            JButton right_east) {

        /*
         * 
         * Pressing ALT + W for GOING UP
         * 
         */
        String w = "w";
        Action moveUpAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Board.setDes("w");
                redraw();
            }
        };
        up_north.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(KeyEvent.VK_W, KeyEvent.ALT_DOWN_MASK), w);
        up_north.getActionMap().put(w, moveUpAction);

        /*
         * 
         * Pressing ALT + A for GOING LEFT
         * 
         */
        String a = "a";
        Action moveLeftAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Board.setDes("a");
                redraw();
            }
        };
        left_west.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.ALT_DOWN_MASK), a);
        left_west.getActionMap().put(a, moveLeftAction);

        /*
         * 
         * Pressing ALT + S for GOING DOWN
         * 
         */
        String s = "s";
        Action moveDownAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Board.setDes("s");
                redraw();
            }
        };
        down_south.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.ALT_DOWN_MASK), s);
        down_south.getActionMap().put(s, moveDownAction);

        /*
         * 
         * Pressing ALT + D for GOING RIGHT
         * 
         */
        String d = "d";
        Action moveRightAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Board.setDes("d");
                redraw();
            }
        };
        right_east.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.ALT_DOWN_MASK), d);
        right_east.getActionMap().put(d, moveRightAction);

    }

    /**
     * 
     * Description: <br/>
     * Set up the JSplitpane for showing the broadcast message
     * 
     * @author Yun & Ruiyang & Andree & Gimani
     * @return the finished split pane
     */
    private JSplitPane setSplitPane() {

        JScrollPane scroll = new JScrollPane(text_Output_Area);

        DefaultCaret caret = (DefaultCaret) text_Output_Area.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        split.setDividerSize(8);
        split.setContinuousLayout(true);
        split.setResizeWeight(1);

        split.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        split.setTopComponent(drawingComponents);
        split.setBottomComponent(scroll);
        return split;

    }

    /**
     * 
     * Description: <br/>
     * Return the JMenu bar that contain two feature:exit, restart
     * 
     * @author Yun & Ruiyang & Andree & Gimani
     * @return the JMenu bar that contain two feature:exit, restart
     */
    public JMenuBar myBar() {
        // menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu gameMenu = new JMenu("Cluedo");
        menuBar.add(gameMenu);

        JMenuItem restart = new JMenuItem("Restart");
        restart.addActionListener(
                (ActionEvent e) -> restartGame());

        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(
                (ActionEvent e) -> close_window());

        // gameMenu.add(restart);
        gameMenu.add(exit);
        return menuBar;
    }

    /**
     * 
     * Description: <br/>
     * Restart game dialog
     * 
     * @author Yun & Ruiyang & Andree & Gimani
     */
    private void restartGame() {
        int option = JOptionPane.showConfirmDialog(
                frame_window, "Do you want to restart the game? ", "Hint",
                JOptionPane.YES_NO_CANCEL_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            System.out.println("Unexpected Error has occur");

            // new Game();
        }
    }

    /**
     * 
     * Description: <br/>
     * Double check whether the player want to exit the game.
     * 
     * @author Yun & Ruiyang & Andree & Gimani
     */
    public void close_window() {
        int option = JOptionPane.showConfirmDialog(
                frame_window, "Do you want to exit the game? ", "Hint",
                JOptionPane.YES_NO_CANCEL_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    /**
     * Show a message dialog
     *
     * @param message
     *            message to show
     */
    public void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(frame_window, message);
    }

    /**
     * 
     * Description: <br/>
     * exit confirmation dialog
     * 
     * @author Yun & Ruiyang & Andree & Gimani
     * 
     * @param state
     *            for the message to show
     */
    public void close_confirmation_dialog(String state) {
        String messageString = "";
        if (state == "newGame") {
            messageString = "Do you want to start a new Game?";
        } else if (state == "exit") {
            messageString = "Are you sure that you want to exit the game?";
        }

        int confirmed = JOptionPane.showConfirmDialog(frame_window, messageString,
                "Exit confirmation", JOptionPane.YES_NO_OPTION);
        if (state == "newGame") {
            if (confirmed != JOptionPane.YES_OPTION) {
                // dispose();
                System.out.println("Exit the Game");
                System.exit(0);
            } else {
                System.out.println("NEW GAME START!!!!!");
                new Game();
                return;
            }
        }
        if (state == "exit") {
            if (confirmed == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }

    }

}
