package game;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5109.efec3b425 modeling language!*/

import java.util.ArrayList;
import java.util.List;

// line 49 "model.ump"
// line 84 "model.ump"

/**
 *
 * Description: <br/>
 * Represent the Room object of the Cluedo Game. A room is consist of many
 * cells. A room can contain lots of Players and Weapons.
 *
 * @author Yun Zhou & Ruiyang Zhang
 * @version 1.0
 */
public class Room {

    // ------------------------
    // MEMBER VARIABLES
    // ------------------------

    // Room Attributes

    /**
     * name of the Room
     */
    private String name;

    // ------------------------
    // Room Associations
    // ------------------------
    /**
     * these cells consist the room
     */
    private List<Cell> cells;

    /**
     * Players that might be in the Rooom
     */
    private List<Player> players;

    /**
     * weapons that might in the room
     */
    private List<Weapon> weapons;

    /**
     *
     * A constructor. It construct a new instance of Room.
     *
     * @param aName
     *            the name of the Room
     */
    public Room(String aName) {
        name = aName;
        cells = new ArrayList<Cell>();
        setPlayers(new ArrayList<Player>());
        setWeapons(new ArrayList<Weapon>());

    }

    // ------------------------
    // INTERFACE
    // ------------------------

    /**
     *
     * Description: <br/>
     * Get the name of the Room
     *
     * @author Yun Zhou & Ruiyang Zhang
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * Description: <br/>
     * Set these cells to be the room cells
     *
     * @author Yun Zhou & Ruiyang Zhang
     * @param cell
     *            cells to be set
     */
    public void setCells(Cell cell) {
        this.cells.add(cell);
    }

    public String toString() {
        return name;
    }

    /**
     *
     * Description: <br/>
     * Get Weapons that are in the room currently
     *
     * @author Yun Zhou & Ruiyang Zhang
     * @return weapons that are in the Room
     */
    public List<Weapon> getWeapons() {
        return weapons;
    }

    /**
     *
     * Description: <br/>
     * Set the weapons to be at the Room
     *
     * @author Yun Zhou & Ruiyang Zhang
     * @param weapons
     *            weapons to be set
     */
    public void setWeapons(List<Weapon> weapons) {
        this.weapons = weapons;
    }

    /**
     *
     * Description: <br/>
     * Get Players that are in the room currently
     *
     * @author Yun Zhou & Ruiyang Zhang
     * @return PLayers that are in the room currently
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     *
     * Description: <br/>
     * Set the players to be at the Room
     *
     * @author Yun Zhou & Ruiyang Zhang
     * @param players
     *            players to be set
     */
    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}