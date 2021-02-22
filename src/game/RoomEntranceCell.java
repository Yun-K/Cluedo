package game;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5109.efec3b425 modeling language!*/

// line 34 "model.ump"
// line 101 "model.ump"

/**
 *
 * Description: <br/>
 * The cell for entering to the Room.
 *
 * @author Yun Zhou & Ruiyang Zhang
 * @version 1.0
 */
public class RoomEntranceCell {

    // ------------------------
    // MEMBER VARIABLES
    // ------------------------

    // RoomEntranceCell Attributes

    /**
     * the cell for entering the room
     */
    private Cell cellEntrance;

    /**
     * cellWalkway: the walk way cell
     */
    private Cell cellWalkway;

    /**
     *
     * A constructor. It construct a new instance of RoomEntranceCell.
     *
     * @param aCellWalkway
     *            the walk way cell
     * @param aCellEntrance
     *            the cell for entering the room
     */
    public RoomEntranceCell(Cell aCellWalkway, Cell aCellEntrance) {
        cellEntrance = aCellEntrance;
        cellWalkway = aCellWalkway;
    }

    // ------------------------
    // INTERFACE
    // ------------------------

    /**
     *
     * Description: <br/>
     * Set the entrance cell
     *
     * @author Yun Zhou & Ruiyang Zhang
     * @param aCellEntrance
     *            the cell for setting to be the entrance cell
     *
     */
    public void setCellEntrance(Cell aCellEntrance) {
        cellEntrance = aCellEntrance;
    }

    /**
     *
     * Description: <br/>
     * Set the walkWay cell
     *
     * @author Yun Zhou & Ruiyang Zhang
     * @param aCellWalkway
     *            a cell to be set
     */
    public void setCellWalkway(Cell aCellWalkway) {
        cellWalkway = aCellWalkway;
    }

    /**
     *
     * Description: <br/>
     * Return the cell of the room entrance cell
     *
     * @author Yun Zhou & Ruiyang Zhang
     * @return the cell of the room entrance cell
     */
    public Cell getCellEntrance() {
        return cellEntrance;
    }

    /**
     *
     * Description: <br/>
     * Return the cell of the walk way cell
     *
     * @author Yun Zhou & Ruiyang Zhang
     * @return the cell of the walk way cell
     */
    public Cell getCellWalkway() {
        return cellWalkway;
    }

    /**
     *
     * Description: <br/>
     * for deleteing the entrance cell
     *
     * @author Yun Zhou & Ruiyang Zhang
     */
    public void delete() {
    }

    public String toString() {
        return super.toString() + "[" + "]" + System.getProperties().getProperty("line.separator") +
                "  " + "cellEntrance" + "=" +
                (getCellEntrance() != null ? !getCellEntrance().equals(this)
                        ? getCellEntrance().toString().replaceAll("  ", "    ")
                        : "this" : "null") +
                System.getProperties().getProperty("line.separator") +
                "  " + "cellWalkway" + "=" +
                (getCellWalkway() != null ? !getCellWalkway().equals(this)
                        ? getCellWalkway().toString().replaceAll("  ", "    ")
                        : "this" : "null");
    }
}