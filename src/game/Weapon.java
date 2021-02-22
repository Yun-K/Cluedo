package game;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5109.efec3b425 modeling language!*/
import java.awt.Color;

// line 41 "model.ump"
// line 90 "model.ump"
/**
 *
 * Description: <br/>
 * Represent the Weapon object of the game cluedo. The weapon can be moved if
 * player is making the accusation/suggestion
 *
 * @author Yun Zhou & Ruiyang Zhang
 * @version 1.0
 */
public class Weapon {

    // ------------------------
    // MEMBER VARIABLES
    // ------------------------
    // Weapon Attributes

    /**
     * the name of the weapon
     */
    private String name;

    /**
     * the position of the weapon
     */
    private Cell position;

    /**
     * the character which represent the weapon, like use s to represent the
     * weapon spanner
     */
    private char piece;

    /**
     * the color of the weapon
     */
    Color weaponColor;

    /**
     *
     * A constructor. It construct a new instance of Weapon.
     *
     * @param name
     *            name of the weapon
     * @param position
     *            the positon cell that the weapon is at
     * @param piece
     *            the character to represent the weapon, like use S to represent
     *            the Spanner
     */
    public Weapon(String name, Cell position, char piece) {
        this.name = name;
        this.position = position;
        this.piece = piece;
    }

    // ------------------------
    // INTERFACE
    // ------------------------

    /**
     *
     * Description: <br/>
     * To set the string name of weapon
     *
     * @author Yun Zhou & Ruiyang Zhang
     * @param name
     *            name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * Description: <br/>
     * To set the postion cell of the weapon. It's be used when Player is making
     * suggestions/accusation
     *
     * @author Yun Zhou & Ruiyang Zhang
     * @param position
     *            the position cell to set
     */
    public void setPosition(Cell position) {
        this.position = position;
    }

    /**
     *
     * Description: <br/>
     * The character piece to represent the weapon
     *
     * @author Yun Zhou & Ruiyang Zhang
     * @param piece
     *            the character piece to represent the weapon
     */
    public void setPiece(char piece) {
        this.piece = piece;
    }

    /**
     *
     * Description: <br/>
     * Get the string name
     *
     * @author Yun Zhou & Ruiyang Zhang
     * @return the string name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * Description: <br/>
     * Get the Position Cell of the Weapon
     *
     * @author Yun Zhou & Ruiyang Zhang
     * @return the Position Cell of the Weapon
     */
    public Cell getPosition() {
        return position;
    }

    /**
     *
     * Description: <br/>
     * Return the character that represent the weapon
     *
     * @author Yun Zhou & Ruiyang Zhang
     * @return the character that represent the weapon
     */
    public char getPiece() {
        return piece;
    }

    public String toString() {
        return "[" + "name" + ":" + getName() + "," + "position:" + position.toString() + "]";
    }

}