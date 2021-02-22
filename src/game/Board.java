package game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import gui.GameController;

/**
 * Description: <br/>
 * It represent the
 * 
 * 
 * extends the GUI class
 *
 * @author Yun Zhou & Ruiyang Zhang
 * @version 2.0
 */
public class Board extends GameController {

    /**
     * temporary JFrame
     */
    // static JFrame tempFrame;

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * dialog stuff
     */
    // Controller myController = new Controller(this);

    /**
     * mark that some one win the game
     */
    private boolean somebody_won = false;

    // ------------------------
    // MEMBER VARIABLES
    // ------------------------

    // Board Associations
    /**
     * cells make up board map
     */
    private Cell[][] Cells = new Cell[24][25];

    /**
     * the list of players who are still playing in the current game status
     */
    private List<Player> playing;

    /**
     * hold every cards include room, weapon and players
     */
    private List<Card> allCards;

    /**
     * generate before game start, consist of room,player and weapon
     */
    private List<Card> murderCards;

    /**
     * whose turn is it
     */
    private Player player_turn;

    /**
     * cell for entering the room
     */
    private List<RoomEntranceCell> RoomEntranceCells;

    /**
     * resprent the state of current player, like player is making the
     * suggestion etc
     */
    private static String Des;

    /**
     * Description: <br/>
     * Direction for player to move to
     *
     * @author Yun Zhou & Ruiyang Zhang
     */
    @SuppressWarnings("javadoc")
    public enum dir {
        NORTH, EAST, SOUTH, WEST
    }

    protected void drawBoard(Graphics g) {
        // set the width and height of the Board
        int width = getDrawingAreaDimension().width;
        int height = getDrawingAreaDimension().height - 20;

        int offset;
        if (width < height) {
            offset = width / 24;
        } else {
            offset = height / 25;
        }

        // The number of pixels needed to center the board
        int centre = (width / 2) - (12 * offset);

        for (int x = 0; x < 24; x++) {
            for (int y = 0; y < 25; y++) {
                if (Cells[x][y].getName().equals("Inaccessible")) {
                    g.setColor(new Color(120, 250, 249));
                    g.fillRect((x * offset) + centre, y * offset, offset, offset);
                } else if (Cells[x][y].getName().equals("Walkway")) {
                    g.setColor(new Color(255, 255, 100));
                    g.fillRect((x * offset) + centre, y * offset, offset, offset);
                } else {
                    g.setColor(new Color(150, 130, 100));
                    g.fillRect((x * offset) + centre, y * offset, offset, offset);
                }
                if (Cells[x][y].player != null) {
                    g.setColor(new Color(0, 0, 0));
                    g.fillOval(x * offset + centre + (int) (offset * 0.125),
                            y * offset + (int) (offset * 0.125), (int) (offset * 0.8),
                            (int) (offset * 0.8));
                    g.setColor(new Color(255, 255, 255));
                    g.setFont(new Font("TimesRoman", Font.BOLD, (int) (offset * 0.6)));
                    g.drawString(Cells[x][y].player.getPiece() + "",
                            x * offset + centre + (offset / 4) + 1, y * offset + (offset / 1) - 5);
                }

                if (Cells[x][y].weapon != null) {
                    g.setColor(Cells[x][y].weapon.weaponColor);
                    g.fillOval(x * offset + centre + (int) (offset * 0.15),
                            y * offset + (int) (offset * 0.15), (int) (offset * 0.7),
                            (int) (offset * 0.7));
                    g.setColor(new Color(255, 255, 255));
                    g.setFont(new Font("Verdana", Font.BOLD, (int) (offset * 0.6)));
                    g.drawString(Cells[x][y].weapon.getPiece() + "",
                            x * offset + centre + (offset / 3), y * offset + (int) (offset * 0.66));
                }

            }
        }

        ((Graphics2D) g).setStroke(new BasicStroke(2));

        for (int x = 0; x < 24; x++) {
            for (int y = 0; y < 25; y++) {

                g.setColor(new Color(0, 0, 0));
                if (x + 1 < 24 && !(Cells[x][y].getName().equals(Cells[x + 1][y].getName()))) {
                    g.drawLine(x * offset + centre + offset, y * offset, x * offset + centre + offset,
                            y * offset + offset);
                }
                if (y + 1 < 25 && !(Cells[x][y].getName().equals(Cells[x][y + 1].getName()))) {
                    g.drawLine(x * offset + centre, y * offset + offset, x * offset + centre + offset,
                            y * offset + offset);
                }

                ((Graphics2D) g).setStroke(new BasicStroke(2));
                g.setColor(new Color(255, 255, 100));
                for (RoomEntranceCell d : RoomEntranceCells) {
                    if (Cells[x][y].isRoomEntranceCell) {
                        if (x == d.getCellEntrance().getXPos()
                                && y == d.getCellEntrance().getYPos()) {
                            if (x + 1 == d.getCellWalkway().getXPos()
                                    && y == d.getCellWalkway().getYPos()) {
                                g.drawLine(x * offset + centre + offset, y * offset,
                                        x * offset + centre + offset, y * offset + offset);
                            } else if (x - 1 == d.getCellWalkway().getXPos()
                                    && y == d.getCellWalkway().getYPos()) {
                                g.drawLine(x * offset + centre, y * offset, x * offset + centre,
                                        y * offset + offset);
                            } else if (x == d.getCellWalkway().getXPos()
                                    && y + 1 == d.getCellWalkway().getYPos()) {
                                g.drawLine(x * offset + centre, y * offset + offset,
                                        x * offset + centre + offset, y * offset + offset);
                            } else if (x == d.getCellWalkway().getXPos()
                                    && y - 1 == d.getCellWalkway().getYPos()) {
                                g.drawLine(x * offset + centre, y * offset,
                                        x * offset + centre + offset, y * offset);
                            }
                        }

                    }
                }

            }
        }

        float alpha = 0.15f; // Transparency of 0.15
        g.setColor(new Color(0, 0, 0, alpha));
        ((Graphics2D) g).setStroke(new BasicStroke(1));

        for (int x = 0; x < 24; x++) {
            int y = 0;
            g.drawLine(x * offset + centre, y * offset, x * offset + centre,
                    (y * offset) + (25 * offset));
        }

        for (int y = 0; y < 25; y++) {
            int x = 0;
            g.drawLine((x * offset) + centre, y * offset, (x * offset) + (24 * offset) + centre,
                    y * offset);
        }
        g.setColor(new Color(0, 0, 0));
        ((Graphics2D) g).setStroke(new BasicStroke(5));
        g.drawRect(centre, 0, 24 * offset, 25 * offset); // Black border around
        // the board

        // Here to end of method
        alpha = 0.4f; // Transparency of 0.15
        g.setColor(new Color(0, 0, 0, alpha));
        g.setFont(new Font("Verdana", Font.BOLD, (int) (offset / 1.5)));

        g.drawString("HALL", centre + (offset * 11), offset * 22);
        g.drawString("STUDY", centre + (offset * 19) + (offset / 2),
                offset * 24 - (int) (offset / 1.5));
        g.drawString("LIBRARY", centre + (offset * 19), offset * 17);
        g.drawString("BILLIARD", centre + (offset * 19) + (offset / 4), offset * 11 - (offset / 2));
        g.drawString("ROOM", centre + (offset * 20) - (offset / 4), offset * 12 - (offset / 2));
        g.drawString("CONSERV.", centre + (offset * 19), offset * 4);
        g.drawString("BALL ROOM", centre + (offset * 10) - (offset / 6), offset * 5);
        g.drawString("KITCHEN", centre + (offset) + (offset / 3), offset * 3 + (offset / 2));
        g.drawString("DINING ROOM", centre + (offset * 1) + (offset / 4), offset * 13);
        g.drawString("LOUNGE", centre + (offset * 2), offset * 22 + (offset / 4));
    }

    /**
     * Description: <br/>
     * get the playing players
     *
     * @return list of player who is still playing
     * @author Yun Zhou & Ruiyang Zhang
     */
    public List<Player> getPlayingList() {
        return playing;
    }

    /**
     * 
     * Description: <br/>
     * return the player who is in turn
     * 
     * @author Yun & Ruiyang & Andree & Gimani
     * @return the player who is in turn
     */
    public Player getPlayersinTurn() {
        return player_turn;
    }

    /**
     * the game is over when the true accusation is made OR every one has made a
     * false accusations
     */
    static boolean gameOver = false;

    /**
     * Cells to be moved which is the ajcant cells
     */
    private List<Cell> moveCells = new ArrayList<Cell>();

    /**
     * number of player still playing
     */
    public int playingCounts;

    /**
     * A constructor. It construct a new instance of Board.
     *
     * @param Cells
     *            cells construct the Board
     * @param rooms
     *            list of rooms that are involved
     * @param murderCards
     *            consist of one player,one weapon and one room
     * @param RoomEntranceCells
     *            cells for entering the room
     * @param allCards
     *            contains everything include players,weapons and rooms
     * @param playing
     *            a list of players that are involved in the current game
     */
    public Board(Cell[][] Cells, List<Room> rooms,
            List<Card> murderCards,
            List<RoomEntranceCell> RoomEntranceCells, List<Card> allCards, List<Player> playing) {
        this.allCards = allCards;

        this.murderCards = murderCards;

        // convert every MODEL CELL DATA to be view
        for (int x = 0; x < 24; x++) {
            for (int y = 0; y < 25; y++) {
                this.Cells[x][y] = Cells[x][y];
            }
        }
        this.RoomEntranceCells = RoomEntranceCells;
        // this.weapons = weapons;
        // this.players = players;

        this.playing = playing;
        this.playingCounts = playing.size();
        Board.Des = "";
    }

    /**
     * Description: <br/>
     * A list of all murder cards that has 1 player,room and weapon
     *
     * @return A list of all murder cards that has 1 player,room and weapon
     * @author Yun Zhou & Ruiyang Zhang
     */
    public List<Card> getMurderCards() {
        return this.murderCards;
    }

    /**
     * Description: <br/>
     *
     * @param Des
     *            des string to set
     * @author Yun Zhou & Ruiyang Zhang
     */
    public static void setDes(String Des) {
        Board.Des = Des;
    }

    /**
     * Description: <br/>
     * get the string of des
     *
     * @return get the string of des
     * @author Yun Zhou & Ruiyang Zhang
     */
    public static String getDes() {
        return Board.Des;
    }

    /**
     * Description: <br/>
     * Roll two dices and add them up then show to the player
     *
     * @return number
     * @author Yun Zhou & Ruiyang Zhang
     */
    public static int rollDice() {
        int dice_add = 0;// value to return

        // define the range
        int max = 6;
        int min = 1;
        int range = max - min + 1;

        // generate random numbers within 1 to 6 twice
        for (int i = 0; i < 2; i++) {
            int rand = (int) (Math.random() * range) + min;
            dice_add += rand;
        }
        return dice_add;
    }

    /**
     * Description: <br/>
     * Player take turns to play the Game
     *
     * @param player
     *            player to play
     * @author Yun Zhou & Ruiyang Zhang
     */
    public void takeTurn(Player player) {

        this.player_turn = player;
        // the player is out, so he can't make suggestion or accusation any more
        if (!player.getPlaying()) {
            setDes("e");
            showMessageDialog("Player " + player.getName() + " is out, next player in turn");
            return;
        }

        // roll the dice
        int moves = rollDice();
        int allMoves = moves;

        String CellName = "Walkway";
        String endTurnMessage = "";

        // print the message
        String outputMessage = ("It's " + player.getPlayer() + "'s turn! (" + player.getName() +
                                ")\n");
        outputMessage += (player.getName() + " rolled a " + moves + "!");
        getTextOutputArea().setText(outputMessage);

        // players now can move on the Board
        for (int i = moves; i > 0;) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (!getDes().equals("")) {
                boolean noMove = true;
                // boolean deadEnd = false;
                while (noMove) {
                    int playerX = player.getPosition().getXPos();
                    int playerY = player.getPosition().getYPos();

                    // Checks if player has moved into a dead end and ends their
                    // turn if they have
                    if (playerX + 1 > 23
                            || !canMove(player.getPosition(), Cells[playerX + 1][playerY])) {
                        if (playerX - 1 < 0
                                || !canMove(player.getPosition(), Cells[playerX - 1][playerY])) {
                            if (playerY + 1 > 24
                                    || !canMove(player.getPosition(), Cells[playerX][playerY + 1])) {
                                if (playerY - 1 < 0 || !canMove(player.getPosition(),
                                        Cells[playerX][playerY - 1])) {
                                    i = 0;
                                    noMove = false;
                                    // deadEnd = true;
                                    break;
                                }
                            }
                        }
                    }

                    if (Des.equalsIgnoreCase("w")) {
                        if (movePlayer(player, dir.NORTH)) {
                            noMove = false;
                            i--;
                        }
                    } else if (Des.equalsIgnoreCase("a")) {
                        if (movePlayer(player, dir.WEST)) {
                            noMove = false;
                            i--;
                        }
                    } else if (Des.equalsIgnoreCase("s")) {
                        if (movePlayer(player, dir.SOUTH)) {
                            noMove = false;
                            i--;
                        }
                    } else if (Des.equalsIgnoreCase("d")) {
                        if (movePlayer(player, dir.EAST)) {
                            noMove = false;
                            i--;
                        }
                    } else if (!(player.getPosition().getName().equals("Walkway"))
                            && Des.equalsIgnoreCase("e")) {
                        i = 0;
                        noMove = false;
                    } else if (Des.equalsIgnoreCase("h")) {
                        JDialog handDisplay = new JDialog(frame_window, "HAND ");
                        String hand = "<html><U>Your hand is: </U><br><br>";
                        for (Card card : player.getHand())
                            hand += "<html>" + card.getType() + ": " + card.getName() + "<br>";
                        JLabel labelForHand = new JLabel(hand);
                        labelForHand.setBackground(Color.getHSBColor(58, 92, 66));
                        labelForHand.setFont(new Font("New Times Roman", Font.PLAIN, 14));
                        labelForHand.setOpaque(true);
                        handDisplay.add(labelForHand);
                        handDisplay.setSize(200, 250);
                        handDisplay.setVisible(true);

                    } else if (Des.equalsIgnoreCase("sk")) {
                        JDialog shortcutKeyDisplay = new JDialog(frame_window, "SHORTCUT KEYS ");
                        String shortcut = "<html><U>Shortcuts available in-game: </U><br><br>";
                        shortcut += "<html>===============<br>To make a suggestion<br>" +
                                    "CTRL + g<br>===============<br><br>" +
                                    "===============<br> To make an Accusation<br>" +
                                    "CTRL + c <br>===============<br><br>" +
                                    "===============<br> To End your turn<br>" +
                                    "CTRL + e <br>===============<br><br>" +
                                    "===============<br> To show your Hand<br>" +
                                    "CTRL + h <br>===============<br><br>" +
                                    "===============<br> To show the shortcuts again<br>" +
                                    "CTRL + i <br>===============<br><br>" +
                                    "===============<br> To Move the character<br>" +
                                    "ALT + w/a/s/d <br>===============<br><br>" +
                                    "===============<br>";

                        JLabel labelForShortcut = new JLabel(shortcut);
                        labelForShortcut.setBackground(Color.getHSBColor(58, 92, 66));
                        labelForShortcut.setFont(new Font("New Times Roman", Font.PLAIN, 14));
                        labelForShortcut.setOpaque(true);
                        shortcutKeyDisplay.add(labelForShortcut);
                        shortcutKeyDisplay.setSize(200, 650);
                        shortcutKeyDisplay.setVisible(true);
                    }
                    // initialise decription
                    setDes("");
                }
                redraw();
                CellName = player.getPosition().getName();
                if (i > 0) {
                    outputMessage = (player.getName() + "has rolled a " + allMoves + "\n");
                    outputMessage += (player.getName() + " has " + (i) + " moves left.");
                    if (!player.getPosition().getName().equals("Walkway")) {
                        outputMessage += ("\n" + player.getName() +
                                          " is in a room \nEnd turn to make the suggestion");
                    }
                    getTextOutputArea().setText(outputMessage);
                } else {
                    endTurnMessage = (player.getName() + " has run out of moves!\n");
                    // displaying the hand cards by message
                    endTurnMessage += player.get_des_handCards();
                }

            }
            moveCells.clear();
            getTextOutputArea().setText(outputMessage);
            if (!gameOver) {
                redraw();
            }
        }

        setDes("");

        String messageToAdd = "\n End Turn.\n You can make an Accusation (usable once per game).";
        endTurnMessage += messageToAdd;

        if (!player.getPosition().getName().equals("Walkway")) {
            endTurnMessage += ("\n\t Make a Suggestion (using the " + player.getPosition().getName() +
                               ")\nClick endTurn button to swap player");
        }
        getTextOutputArea().setText(endTurnMessage);

        /*
         * run forever until the player make the suggestion/accusation
         */
        for (int wdnmd = 0; wdnmd < 1;) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (Des.equalsIgnoreCase("c") || Des.equalsIgnoreCase("e")) {
                wdnmd++;
            } else if (!player.getPosition().getName().equalsIgnoreCase("Walkway")) {
                if (Des.equalsIgnoreCase("g")) {
                    wdnmd++;
                }
            }
        }

        // when player is making the suggestion
        if (Des.equals("g")) {
            // get the list of suggest cards that is chosen by the player
            List<Card> suggestionList = player.suggestCards(CellName, allCards);

            String checkS = checkSuggestion(player, suggestionList);

            getTextOutputArea().setText(checkS);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // when player is making the accusation
        else if (Des.equals("c")) {
            // get the list of accusation cards that is chosen by the player
            List<Card> accusationList = player.Accuse(allCards);

            // check whether the player win or not and show the dialog
            String checkA = checkAccusation(player, accusationList);
            getTextOutputArea().setText(checkA);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // NEW HERE!!
            /*
             * Check whether or not the Game is over,
             *
             * if it is over,then it should go back to the set up and start a
             * new game
             */
            if (somebody_won || playingCounts <= 0) {

                // ask the user whether start a new game
                close_confirmation_dialog("newGame");

                // start a new Game
                new Game();
                System.exit(1);
            }

        }

    }

    /**
     * MODEL
     * 
     * Description: <br/>
     * This method is for checking suggestions and show the dialog to tell the
     * player who can use which card to refute you
     * 
     * the return string is for the announcement at the JTextArea
     *
     * @param player
     *            player who make the suggestion
     * @param guessedCards
     *            cards to guess
     * @return output string show on the Jtextarea
     * @author Yun Zhou & Ruiyang Zhang
     */
    public String checkSuggestion(Player player, List<Card> guessedCards) {
        String found = "";
        Card refuteCard = null;
        boolean has_refuteCard = false;
        for (Player otherPlayer : playing) {
            // can't refute oneself
            if (otherPlayer.equals(player)) {
                continue;
            }
            // other player made a refutation
            refuteCard = return_refuteCard(guessedCards, otherPlayer);
            if (refuteCard != null) {
                has_refuteCard = true;
                found += "Player " + otherPlayer.getPlayer() + " refute you with the Card: " +
                         refuteCard.getName() + "\n";

            }
        }

        //
        if (!has_refuteCard) {
            showMessageDialog("No one can refute you!\nIt is a possible solution!");
            return "No one can refute you!\nIt is a possible solution!";
        }

        showMessageDialog(found);
        return found;
    }

    /**
     * Description: <br/>
     * Method will return one of the refute cards that is chosen by the player
     * 
     * @author Yun & Ruiyang & Andree & Gimani
     * @param guessedCards
     *            cards to refute
     * @param otherPlayer
     *            player who need to refute
     * @return the refute card
     */
    private Card return_refuteCard(List<Card> guessedCards, Player otherPlayer) {
        List<Card> refute = new ArrayList<Card>();
        for (Card card : guessedCards) {
            if (otherPlayer.getHand().contains(card)) {
                refute.add(card);
            }
        }

        // update the window to let the player to choose one of the refute card
        if (!refute.isEmpty()) {
            int index = index_refuteCard(refute, otherPlayer.getPlayer());
            return refute.get(index);
        }

        // didn't find the matched refute Card, return null
        return null;
    }

    /**
     * 
     * Description: <br/>
     * Construct a JoptionPane to ask the player to choose one refutable Card to
     * refute
     * 
     * @author Yun & Ruiyang & Andree & Gimani
     * @param refute
     *            the list of cards that can refute
     * @param playerName
     *            string for print on the JOption pane
     * @return the index of the card that is chosen by the player
     */
    public int index_refuteCard(List<Card> refute, String playerName) {

        // add the refute cards string into the Object list
        // List<Object> fixed_refute_cards = new ArrayList<Object>();
        Object[] fixed_refute_cards = new Object[refute.size()];

        for (int i = 0; i < refute.size(); i++) {
            String card_name = refute.get(i).getName();
            fixed_refute_cards[i] = card_name;
        }

        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // dialog window for asking
        String refuteCards_string = (String) JOptionPane.showInputDialog(this,
                playerName + ", which card would you like to choose to refute?", "Choose refute card",
                JOptionPane.PLAIN_MESSAGE,
                null, fixed_refute_cards, "Card to choose");

        // check the string and convert it to the number of index
        if ((refuteCards_string != null) && (!refuteCards_string.isEmpty())) {
            // return the index
            if (refuteCards_string.equals(fixed_refute_cards[0])) {
                return 0;
            } else if (refuteCards_string.equals(fixed_refute_cards[1])) {
                return 1;
            } else if (refuteCards_string.equals(fixed_refute_cards[2])) {
                return 2;
            }
        }

        // dead code
        return -1;

    }

    /**
     * Description: <br/>
     * Check whether or not the player has made the correct accusation
     *
     * @param player
     *            player to check
     * @param guessedCards
     *            cards that are accusate by player
     * @return string that player is OUT or WIN
     * @author Yun Zhou & Ruiyang Zhang & Andree Saril & Gimani Telikada
     *         Palliyaguruge Weerasena
     */
    public String checkAccusation(Player player, List<Card> guessedCards) {
        String out = "";
        // myController = new Controller(this);

        if (guessedCards.get(0) == murderCards.get(0) &&
                guessedCards.get(1) == murderCards.get(1) &&
                guessedCards.get(2) == murderCards.get(2)) {
            out += (player.getPlayer() + "'s accusation was CORRECT and has won the game!\n");
            gameOver = true;
            somebody_won = true;
            /*
             * NEW HERE!!
             */
            String win_player = player.getPlayer();

            String message = "Congratulation! " + win_player + "!\nYou Win!";
            showMessageDialog(message);

        } else {
            out += (player.getName() + "'s accusation was INCORRECT!\n" + player.getName() +
                    " cannot make any more accusations!\n");
            player.setPlaying();
            playingCounts--;

            // NEW HERE!!
            String out_player = player.getPlayer();

            String message = "You're wrong! " + out_player +
                             "!\nYou're out! But you can still refute";
            showMessageDialog(message);

            if (playingCounts <= 0) {
                showMessageDialog("Nobody made a correct accusation! Game is over!");

                String solution_cardString = "";
                for (Card card : murderCards) {
                    solution_cardString += card.toString() + "\n";
                }
                showMessageDialog("The solution is:\n" + solution_cardString);

            }

        }

        return out;
    }

    /**
     * Description: <br/>
     * Check whether or not the next cell can move to
     *
     * @param current
     *            current cell
     * @param next
     *            destination cell
     * @return true if it's accessable, false otherwise
     * @author Yun Zhou & Ruiyang Zhang
     */
    public boolean canMove(Cell current, Cell next) {
        if (next.getName().equals("Inaccessible"))
            return false; // Player tries to move into inaccessible Cell
        if (next.player != null) {
            return false; // Player tries to move into a Cell occupied by
            // another player
        }
        for (Cell t : moveCells) {
            if (t.equals(next)) {
                return false; // Player tries to move to a Cell that they have
                // already been on in the move
            }
        }
        if (current.getName().equals(next.getName())) {
            return true; // Player moves Cells which are in the same room
        }

        if (current.isRoomEntranceCell && next.isRoomEntranceCell) {
            // Player moves through a Cell that is occupied by a door
            return true;
        }

        return false;
    }

    /**
     * Description: <br/>
     * Move the player to the direction
     *
     * @param player
     *            the player to move
     * @param destinationDirection
     *            the direction that the player will be moved
     * @return true if movement is made successcfully, false otherwise
     * @author Yun Zhou & Ruiyang Zhang
     */
    public boolean movePlayer(Player player, dir destinationDirection) {
        // get the description of the current cell that the player is at
        Cell current = player.getPosition();
        int xPos = current.getXPos();
        int yPos = current.getYPos();

        // the next cell that will
        Cell next = null;
        switch (destinationDirection) {
        case NORTH:
            // Boundary check
            if (yPos - 1 < 0) {
                return false;
            }
            next = Cells[xPos][yPos - 1];
            for (Cell t : moveCells) {
                if (t.equals(next)) {
                    return false;
                }
            }
            break;
        case SOUTH:
            // boundary check
            if (yPos + 1 > 24) {
                return false;
            }
            next = Cells[xPos][yPos + 1];
            for (Cell t : moveCells) {
                if (t.equals(next)) {
                    return false;
                }
            }
            break;
        case EAST:
            // boundary check
            if (xPos + 1 > 23) {
                return false;
            }
            next = Cells[xPos + 1][yPos];
            for (Cell t : moveCells) {
                if (t.equals(next)) {
                    return false;
                }
            }
            break;
        case WEST:
            // boundary check
            if (xPos - 1 < 0) {
                return false;
            }
            next = Cells[xPos - 1][yPos];
            for (Cell t : moveCells) {
                if (t.equals(next)) {

                    return false;
                }
            }
            break;
        }

        // Checks if the player can move between two Cells and the moves the
        // player if it can
        if (canMove(current, next)) {
            next.setPlayer(player);
            current.setPlayer(null);
            player.setPosition(next);
            moveCells.add(current);
            return true;
        }
        return false;
    }

}