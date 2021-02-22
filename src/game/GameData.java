/**
 * Project Name:Final_cluedo
 * File Name:GameSet.java
 * Package Name:game
 * Date:5:19:51 pm
 * Copyright (c) 2020, bluemobi All Rights Reserved.
 *
*/

package game;

import java.util.List;

/**
 * Description: <br/>
 * It represent the Model of the MVC design. It contains all the data that the
 * Game need, like cells, cards, players etc.
 * 
 * This class is made by the eclipse -> right click -> Refactor -> ExtractClass
 * -> generate getter_Setter methods.
 * 
 * @author Yun Zhou 300442776
 * @version 1.0
 */
public class GameData {
    /**
     * it represent the Game map
     */
    private Board board;

    /**
     * Cells:cells make up the board
     */
    private Cell[][] Cells;

    /**
     * playing players
     */
    private List<Player> playing;

    /**
     * murderCards: it's consist of Room,Weapon and the player Card
     */
    private List<Card> murderCards;

    /**
     * allCards:it represents all Cards
     */
    private List<Card> allCards;

    /**
     * cards:the differece between previous is it rules out the Murder Cards.
     * That is: allCards - murderCards = cards.
     */
    private List<Card> cardsToHandOut;

    /**
     * players:all players
     */
    private List<Player> players;

    /**
     * weapons:all weapons
     */
    private List<Weapon> weapons;

    /**
     * rooms:all rooms
     */
    private List<Room> rooms;

    /**
     * RoomEntranceCells:
     */
    private List<RoomEntranceCell> RoomEntranceCells;

    /**
     *
     * Mark the game over status
     */
    private boolean gameOverStatus;

    /**
     * Below are for the Rooms
     */
    private Room Kitchen;

    private Room ballRoom;

    private Room Conservatory;

    private Room diningRoom;

    private Room Cellar;

    private Room billiardRoom;

    private Room Library;

    private Room Lounge;

    private Room Hall;

    private Room Study;

    /**
     * 
     * A constructor. It construct a new instance of GameData.
     *
     * @param gameOverStatus
     *            whether the game is over or not
     * @param kitchen
     *            kitchen room
     * @param ballRoom
     *            ballroom
     * @param conservatory
     *            room
     * @param diningRoom
     *            room
     * @param cellar
     *            room
     * @param billiardRoom
     *            room
     * @param library
     *            room
     * @param lounge
     *            room
     * @param hall
     *            room
     * @param study
     *            room
     */
    public GameData(boolean gameOverStatus, Room kitchen, Room ballRoom, Room conservatory,
            Room diningRoom, Room cellar, Room billiardRoom, Room library, Room lounge, Room hall,
            Room study) {
        this.gameOverStatus = gameOverStatus;
        Kitchen = kitchen;
        this.ballRoom = ballRoom;
        Conservatory = conservatory;
        this.diningRoom = diningRoom;
        Cellar = cellar;
        this.billiardRoom = billiardRoom;
        Library = library;
        Lounge = lounge;
        Hall = hall;
        Study = study;
    }

    // Below are getter setter methods
    // -----------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------

    /**
     * board.
     *
     * @return the board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * board.
     *
     * @param board
     *            the board to set
     */
    public void setBoard(Board board) {
        this.board = board;
    }

    public Cell[][] getCells() {
        return Cells;
    }

    public void setCells(Cell[][] cells) {
        Cells = cells;
    }

    public List<Player> getPlaying() {
        return playing;
    }

    public void setPlaying(List<Player> playing) {
        this.playing = playing;
    }

    public List<Card> getMurderCards() {
        return murderCards;
    }

    public void setMurderCards(List<Card> murderCards) {
        this.murderCards = murderCards;
    }

    public List<Card> getAllCards() {
        return allCards;
    }

    public void setAllCards(List<Card> allCards) {
        this.allCards = allCards;
    }

    public List<Card> getCardsToHandOut() {
        return cardsToHandOut;
    }

    public void setCardsToHandOut(List<Card> cardsToHandOut) {
        this.cardsToHandOut = cardsToHandOut;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Weapon> getWeapons() {
        return weapons;
    }

    public void setWeapons(List<Weapon> weapons) {
        this.weapons = weapons;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<RoomEntranceCell> getRoomEntranceCells() {
        return RoomEntranceCells;
    }

    public void setRoomEntranceCells(List<RoomEntranceCell> roomEntranceCells) {
        RoomEntranceCells = roomEntranceCells;
    }

    public boolean isGameOverStatus() {
        return gameOverStatus;
    }

    public void setGameOverStatus(boolean gameOverStatus) {
        this.gameOverStatus = gameOverStatus;
    }

    public Room getKitchen() {
        return Kitchen;
    }

    public void setKitchen(Room kitchen) {
        Kitchen = kitchen;
    }

    public Room getBallRoom() {
        return ballRoom;
    }

    public void setBallRoom(Room ballRoom) {
        this.ballRoom = ballRoom;
    }

    public Room getConservatory() {
        return Conservatory;
    }

    public void setConservatory(Room conservatory) {
        Conservatory = conservatory;
    }

    public Room getDiningRoom() {
        return diningRoom;
    }

    public void setDiningRoom(Room diningRoom) {
        this.diningRoom = diningRoom;
    }

    public Room getCellar() {
        return Cellar;
    }

    public void setCellar(Room cellar) {
        Cellar = cellar;
    }

    public Room getBilliardRoom() {
        return billiardRoom;
    }

    public void setBilliardRoom(Room billiardRoom) {
        this.billiardRoom = billiardRoom;
    }

    public Room getLibrary() {
        return Library;
    }

    public void setLibrary(Room library) {
        Library = library;
    }

    public Room getLounge() {
        return Lounge;
    }

    public void setLounge(Room lounge) {
        Lounge = lounge;
    }

    public Room getHall() {
        return Hall;
    }

    public void setHall(Room hall) {
        Hall = hall;
    }

    public Room getStudy() {
        return Study;
    }

    public void setStudy(Room study) {
        Study = study;
    }
}
