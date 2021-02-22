package game;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5109.efec3b425 modeling language!*/

// line 22 "model.ump"
// line 78 "model.ump"

/**
 *
 * Description: <br/>
 * cells consist the game board. It has coordinations and the name. The name
 * mark the status of the cell, which can be inaccessable, Kitchen(room cell)
 * etc
 *
 * @author Yun Zhou & Ruiyang Zhang
 * @version 2.0
 */
public class Cell {

    // ------------------------
    // MEMBER VARIABLES
    // ------------------------

    // Cell Attributes

    /**
     * xpos of cell
     */
    private int xPos;

    /**
     * ypos of cell
     */
    private int yPos;

    /**
     * check whether the cell is entrance cell of room
     */
    public boolean isRoomEntranceCell;

    /**
     * the name that mark the cell status, can be inaccessable,Kitchen(Room
     * cell) and Walkway etc
     */
    private String name;

    // Cell Associations
    /**
     * weapon:the weapon that is at the cell
     */
    Weapon weapon = null;

    /**
     * the cell is the room cell or not
     */
    @SuppressWarnings("unused")
    private Room room;

    /**
     * player: the player that is at the cell
     */
    Player player;

    // ------------------------
    // CONSTRUCTOR
    // ------------------------

    /**
     *
     * A constructor. It construct a new instance of Cell.
     *
     * @param name
     *            name of the cell
     * @param xPos
     *            xpos of the cell
     * @param yPos
     *            ypos of the cell
     */
    public Cell(String name, int xPos, int yPos) {
        this.name = name;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    // ------------------------
    // INTERFACE
    // ------------------------
    /**
     *
     * Description: <br/>
     * the player to be set at the cell
     *
     * @author Yun Zhou & Ruiyang Zhang
     * @param player
     *            the player to be set at the cell
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     *
     * Description: <br/>
     * the weapon to be set at the cell
     *
     * @author Yun Zhou & Ruiyang Zhang
     * @param weapon
     *            the weapon to be set at the cell
     */
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    /**
     *
     * Description: <br/>
     * the RoomEntranceCell to be set at
     *
     * @author Yun Zhou & Ruiyang Zhang
     */
    public void setRoomEntranceCell() {
        this.isRoomEntranceCell = true;
    }

    /**
     *
     * Description: <br/>
     *
     * the name that mark the cell status, can be inaccessable,Kitchen(Room
     * cell) and Walkway etc
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
     * set the xpos of the cell
     *
     * @author Yun Zhou & Ruiyang Zhang
     * @param xPos
     *            position to be set
     */
    public void setXPos(int xPos) {
        this.xPos = xPos;
    }

    /**
     *
     * Description: <br/>
     * set the ypos of the cell
     *
     * @author Yun Zhou & Ruiyang Zhang
     * @param yPos
     *            position to be set
     */
    public void setYPos(int yPos) {
        this.yPos = yPos;
    }

    /**
     *
     * Description: <br/>
     * the name of the cell
     *
     * @author Yun Zhou & Ruiyang Zhang
     * @return the name of the cell
     */
    public String getName() {
        return name;
    }

    /**
     *
     * Description: <br/>
     * The xpos of the cell
     *
     * @author Yun Zhou & Ruiyang Zhang
     * @return The xpos of the cell
     */
    public int getXPos() {
        return xPos;
    }

    /**
     *
     * Description: <br/>
     * The ypos of the cell
     *
     * @author Yun Zhou & Ruiyang Zhang
     * @return The ypos of the cell
     */
    public int getYPos() {
        return yPos;
    }

    /**
     *
     * Description: <br/>
     * Check whether the next cell can be accessed by players
     *
     * @author Yun Zhou & Ruiyang Zhang
     * @param current
     *            the current cell
     * @param next
     *            the next cell to be check
     * @return true if next cell is also the accessable cell, false otherwise
     */
    public boolean canMove(Cell current, Cell next) {
        // Doesn't allow movement from one location to another
        if (!(current.name.equals(next.name))) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "[" + "name" + ":" + getName() + "," +
                "xPos" + ":" + getXPos() + "," +
                "yPos" + ":" + getYPos() + "]";
    }
}