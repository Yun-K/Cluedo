package game;

//import Guava.
/*PLEASE DO NOT EDIT THIS CODE*/

/*This code was generated using the UMPLE 1.30.1.5109.efec3b425 modeling language!*/

// line 69 "model.ump"
// line 106 "model.ump"

/**
 *
 * Description: <br/>
 * Card has name and type, type can be Room, Player, Weapon
 *
 * @author Yun Zhou & Ruiyang Zhang
 * @version 2.0
 */
public class Card {

    // ------------------------
    // MEMBER VARIABLES
    // ------------------------

    /**
     * Name of the Card
     */
    private String name;

    /**
     * type can be Player/ Weapon/ Room
     */
    private String type;

    /**
     * player who hold this Card
     */
    private Player player;

    /**
     *
     * Creates a new instance of Card.
     *
     * @param name
     *            name of the Card
     * @param type
     *            type can be Player/ Weapon/ Room
     */
    public Card(String name, String type) {
        this.name = name;
        this.type = type;
    }

    // ------------------------
    // INTERFACE
    // ------------------------
    /**
     *
     * Description: <br/>
     * Set the player to hold the card
     *
     * @author Yun Zhou
     * @param player
     *            player to set
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     *
     * Description: <br/>
     * Getter method
     *
     * @author Yun Zhou
     * @return player
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     *
     * Description: <br/>
     * Set the name of the Card. e.g. Kitchen, Hall etc
     *
     * @author Yun Zhou
     * @param name
     *            the string name
     */
    public void setName(String name) {
        this.name = name;
    }

    // public void test(int index) {
    // Preconditions.checkArgument(index >= 0);// , "age 必须大于0");
    // }

    /**
     *
     * Description: <br/>
     * Set the type of the Card
     *
     * @author Yun Zhou
     * @param type
     *            Player, Weapon,Room
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Description: <br/>
     * return name of the Card
     *
     * @author Yun Zhou
     * @return name of the Card
     */
    public String getName() {
        return name;
    }

    /**
     *
     * Description: <br/>
     * return the type of the Card
     *
     * @author Yun Zhou
     * @return the type of the Card
     */
    public String getType() {
        return type;
    }

    public String toString() {
        return "[" + getType() + ":" + getName() + "]";

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Card)) {
            return false;
        }
        Card other = (Card) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (type == null) {
            if (other.type != null) {
                return false;
            }
        } else if (!type.equals(other.type)) {
            return false;
        }
        return true;
    }
}