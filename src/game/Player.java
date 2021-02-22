package game;
/*PLEASE DO NOT EDIT THIS CODE*/

/*This code was generated using the UMPLE 1.30.1.5109.efec3b425 modeling language!*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import gui.GameController;

/**
 *
 * Description: <br/>
 * The player hold many cards after game starts. The player can make
 * suggestions/accustions. When a suggsetion is made,each player, in a clockwise
 * fashion, starting from the current player, attempts to refute the suggestion.
 * A suggestion is refuted by producing a card that matches one of the suggested
 * murder circumstances (such a card cannot be in the solution envelope, hence
 * refutes the suggestion). A refutation card is only shown to the player that
 * made the suggestion. If a player has multiple refutation cards, it is their
 * choice which one they pick. If no player can produce a refutation, the named
 * murder circumstances are a potential solution candidate that may or may not
 * be used to make an accusation later on (by any player).
 *
 * An accusation – which may directly follow an unrefuted suggestion – comprises
 * a character, a weapon, and a room (which can be any room, not just the one
 * the player making the accusation may be in). If the accusation made by a
 * player exactly matches the actual murder circumstances (only the accusing
 * player is allowed to see the solution) the player wins, otherwise the player
 * is excluded from making further suggestions or accusations. This means the
 * player cannot win the game anymore but will continue to refute suggestions by
 * others.
 *
 * @version 3.0
 */
public class Player {

    // ------------------------
    // MEMBER VARIABLES
    // ------------------------

    // Player Attributes
    /**
     * name of the player
     */
    private String name;

    /**
     * the position that player is at
     */
    private Cell position;

    /**
     * the cards that are hold by Player
     */
    private List<Card> hand;

    /**
     * the character piece that represent the player
     */
    private char piece;

    /**
     * check whether the player is be played or not
     */
    private boolean playing;

    /**
     * the player nick name
     */
    private String playerName = null;

    /**
     * all roomCards list
     */
    private List<Card> roomCards;

    /**
     * all playerCards list
     */
    private List<Card> playerCards;

    /**
     * all weaponCards list
     */
    private List<Card> weaponCards;

    /**
     * player initial cell position on Board
     */
    private Cell initialCellPositionCell;

    /**
     *
     * A constructor. It construct a new instance of Player.
     *
     * @param name
     *            the name to be set
     * @param position
     *            the position cell that the player is at
     * @param piece
     *            the piece char that represent the player on the Board
     * @param playing
     *            whether the player is played or not
     */
    public Player(String name, Cell position, char piece, boolean playing) {
        this.name = name;
        this.position = position;
        this.initialCellPositionCell = position;// initial position

        this.piece = piece;
        this.playing = playing;
        this.hand = new ArrayList<Card>();

        roomCards = new ArrayList<Card>();
        playerCards = new ArrayList<Card>();
        weaponCards = new ArrayList<Card>();
    }

    // ------------------------
    // INTERFACE
    // ------------------------
    /**
     *
     * Description: <br/>
     * Set the name of player
     *
     * @param name
     *            name to be set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * Description: <br/>
     * the position of the player to be set
     *
     * @param position
     *            the position of the player to be set
     */
    public void setPosition(Cell position) {
        this.position = position;
    }

    /**
     *
     * Description: <br/>
     * the nick name
     *
     * @param playerName
     *            the nick name of the player
     */
    public void setPlayer(String playerName) {
        this.playerName = playerName;
    }

    /**
     *
     * Description: <br/>
     * the character name like MR.Black
     *
     * @return the character name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * Description: <br/>
     * return the cell position that the player is at
     *
     * @return the cell position that the player is at
     */
    public Cell getPosition() {
        return position;
    }

    /**
     *
     * Description: <br/>
     * return the card that is hold by the player
     *
     * @return the card that is hold by the player
     */
    public List<Card> getHand() {
        List<Card> newHand = Collections.unmodifiableList(hand);
        return newHand;
    }

    /**
     *
     * Description: <br/>
     * Return the description of all hand cards
     *
     * @author Yun Zhou & Ruiyang Zhang
     * @return the description of all hand cards
     */
    public String get_des_handCards() {
        String str = "Your HandCards: \n";
        for (Card card : getHand()) {
            str += card.getName() + "\n";
        }
        return str;
    }

    /**
     *
     * Description: <br/>
     * Add the card into the Hand
     *
     * @param card
     *            the card to be at into the hand
     */
    public void addToHand(Card card) {
        if (card == null) {
            System.out.println("INVALID!");
        } else {
            this.hand.add(card);
        }
    }

    /**
     *
     * Description: <br/>
     * return the piece of char that represent the player, e.g. Use W for
     * MR.WHITE
     *
     * @return the piece of char that represent the player
     */
    public char getPiece() {
        return piece;
    }

    /**
     *
     * Description: <br/>
     * Return true if the player is playing
     *
     * @return true if the player is playing
     */
    public boolean getPlaying() {
        return playing;
    }

    /**
     *
     * Description: <br/>
     * make a player not able to make accusation no more
     */
    public void setPlaying() {
        this.playing = false;
    }

    /**
     *
     * Description: <br/>
     * Get nick name of player
     *
     * @return name of player
     */
    public String getPlayer() {
        return playerName;
    }

    /**
     *
     * Description: <br/>
     * get the number of cards that are holding by the player
     *
     * @return the number of cards that are holding by the player
     */
    public int numberOfCards() {
        if (hand == null) {
            throw new NullPointerException();
        }
        return hand.size();
    }

    /**
     *
     * Description: <br/>
     * check whether or not the player has ran out of the Card
     *
     * @return true if has cards
     */
    public boolean hasCards() {
        if (hand.isEmpty() || hand.size() <= 0) {
            return false;
        }
        if (hand.size() > 0) {
            return true;
        }

        return false;
    }

    /**
     * Description: <br/>
     * Player make suggetions by check the hand cards that can refute
     *
     * @author Yun Zhou & Ruiyang Zhang
     * @param tileName
     *            name of the card
     * @param allCards
     *            hand cards owned by player
     * @return list of cards that can make refutation
     *
     */
    public List<Card> suggestCards(String tileName, List<Card> allCards) {
        seprateCardsList(allCards);

        // set the guessedCards
        List<Card> guessedCards = new ArrayList<Card>();
        // add the current room to the list
        for (Card room : roomCards) {
            if (room.getName().equals(tileName)) {
                guessedCards.add(room);
            }
        }
        guessedCards.add(playerCards.get(GameController.character_for_accusation("suggestion")));
        guessedCards.add(weaponCards.get(GameController.weapon_for_accusation_suggestion("suggestion")));
        return guessedCards;
    }

    /**
     * Description: <br/>
     *
     * @author Yun Zhou & Ruiyang Zhang & Andree Saril & Gimani Telikada
     *         Palliyaguruge Weerasena
     * @param allCards
     *            cards pool
     * @return list of accusation cards
     */
    public List<Card> Accuse(List<Card> allCards) {
        seprateCardsList(allCards);

        // get the guessedCards and return it
        List<Card> guessedCards = new ArrayList<Card>();
        guessedCards.add(roomCards.get(GameController.room_for_accusation_suggestion("")));
        guessedCards.add(playerCards.get(GameController.character_for_accusation("accusation")));
        guessedCards.add(weaponCards.get(GameController.weapon_for_accusation_suggestion("accusation")));
        return guessedCards;
    }

    /**
     * 
     * Description: <br/>
     * Separte the all Cards pool into different type
     * 
     * @author Yun Zhou & Ruiyang Zhang & Andree Saril & Gimani Telikada
     *         Palliyaguruge Weerasena
     * @param allCards
     *            cardsList to separte
     */
    private void seprateCardsList(List<Card> allCards) {
        // seprate the whole card list to the different type list
        for (Card c : allCards) {
            if (c.getType().equals("Room")) {
                roomCards.add(c);
            } else if (c.getType().equals("Player")) {
                playerCards.add(c);
            } else if (c.getType().equals("Weapon")) {
                weaponCards.add(c);
            }
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((playerName == null) ? 0 : playerName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Player)) {
            return false;
        }
        Player other = (Player) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (playerName == null) {
            if (other.playerName != null) {
                return false;
            }
        } else if (!playerName.equals(other.playerName)) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "[" + "name" + ":" + getName() + "," +
               "position:" + position.toString() + "," +
               "piece" + ":" + getPiece() + "," +
               "playing" + ":" + getPlaying() + "]";
    }

    /**
     * 
     * Description: <br/>
     * Return the initial cell position, useful when player is out the game
     * 
     * @author Yun & Ruiyang & Andree & Gimani
     * @return the initial cell position, useful when player is out the game
     */
    public Cell getInitialCellPositionCell() {
        return initialCellPositionCell;
    }

}