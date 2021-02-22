/**
 * Project Name:Final_cluedo
 * File Name:GameSetUp.java
 * Package Name:game
 * Date:4:43:49 pm
 * Copyright (c) 2020, bluemobi All Rights Reserved.
 *
*/

package game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import gui.GameController;

/**
 * Description: <br/>
 * Responsiblie for the game start setUp feature.For instance,setUp all the
 * cells of data,their initial cell position, ask the number of players, their
 * character/names etc
 *
 * @author Yun Zhou 300442776
 * @version 1.0
 */
public class GameSetUp {
    /**
     * the current game data
     */
    private GameData gameData_current;

    /**
     * 
     * A constructor. It construct a new instance of GameSetUp. SetUp all the
     * cells of data,their initial cell position, ask the number of players,
     * their character/names etc
     * 
     * @param gameData_current
     *            the current game data.
     */
    public GameSetUp(GameData gameData_current) {
        this.gameData_current = gameData_current;
        // do the initialization of Cells
        initializeCells();

        // ask the details of players including how many players, their nick
        // name and which character they are playing. Finally return the number
        // of players

        int nplayers = getPlayerNumbers();
        // every game will have random Murder Cards before game starts
        randomSelectMurderCards();

        /*
         * hand out remaining cards to Players evenly Some players may end up
         * with more cards than others
         */
        distributeCards(nplayers);
    }

    /**
     *
     * Description: <br/>
     * Do the initialization things
     *
     * @author Yun Zhou && Ruiyang Zhang
     */
    private void initializeCells() {
        gameData_current.setCells(setCells());
        gameData_current.setAllCards(new ArrayList<Card>());
        // setPlayers
        gameData_current.setPlayers(setPlayers(gameData_current.getCells()));

        // setWeapons
        gameData_current.setWeapons(setWeapons(gameData_current.getCells()));

        // setRooms
        gameData_current.setRooms(setRooms());

        // setCards
        gameData_current.setCardsToHandOut(
                setCards(gameData_current.getPlayers(), gameData_current.getWeapons(),
                        gameData_current.getRooms()));
        for (Card c : gameData_current.getCardsToHandOut()) {
            gameData_current.getAllCards().add(c);
        }

        // RoomEntranceCells
        gameData_current.setRoomEntranceCells(setRoomEntranceCells());
        // set field to true in Cells to hold a RoomEntranceCell
        for (RoomEntranceCell roomEntranceCell : gameData_current.getRoomEntranceCells()) {
            int x1 = roomEntranceCell.getCellEntrance().getXPos();
            int y1 = roomEntranceCell.getCellEntrance().getYPos();
            int x2 = roomEntranceCell.getCellWalkway().getXPos();
            int y2 = roomEntranceCell.getCellWalkway().getYPos();
            gameData_current.getCells()[x1][y1].setRoomEntranceCell();
            gameData_current.getCells()[x2][y2].setRoomEntranceCell();
        }
        // set field of players and weapons in Cells to hold the respective
        // objects
        for (Player player : gameData_current.getPlayers()) {
            gameData_current.getCells()[player.getPosition().getXPos()][player.getPosition()
                    .getYPos()]
                            .setPlayer(player);
        }
        for (Weapon weapon : gameData_current.getWeapons()) {
            gameData_current.getCells()[weapon.getPosition().getXPos()][weapon.getPosition()
                    .getYPos()]
                            .setWeapon(weapon);
        }

    }

    /**
     *
     * Description: <br/>
     * This part is for allocating the murder cards, which is: what is the
     * murder weapon, where the murder room is and who is the killer
     *
     * we are doing this by using the random number from the range. And for our
     * programme, the index for the room is card.get(0..8), players are
     * card.get(9..14), weapons are card.get(15..20)
     *
     * @author Yun Zhou && Ruiyang Zhang
     */
    public void randomSelectMurderCards() {

        gameData_current.setMurderCards(new ArrayList<Card>());
        // do the random selection by using the integers
        // integers are the corresponding index
        int roomNum = (int) (Math.random() * 9);// index 0-8
        int personNum = (int) (Math.random() * 6) + 9;// index 9-15
        int weaponNum = (int) (Math.random() * 6) + 15;// index 15-21

        gameData_current.getMurderCards().add(gameData_current.getCardsToHandOut().get(roomNum));
        gameData_current.getMurderCards().add(gameData_current.getCardsToHandOut().get(personNum));
        gameData_current.getMurderCards().add(gameData_current.getCardsToHandOut().get(weaponNum));
        // remove them from the whole card list, so that these murder cards will
        // not be handed out to the players
        gameData_current.getCardsToHandOut().remove(weaponNum);
        gameData_current.getCardsToHandOut().remove(personNum);
        gameData_current.getCardsToHandOut().remove(roomNum);

        // print it out to see the murder cards, for debuging
        System.out.println("Murder Cards are:\n");
        for (Card murderCard : gameData_current.getMurderCards()) {
            System.out.println(murderCard.toString() + "\n");
        }

    }

    /**
     * 
     * Description: <br/>
     * Ask the details of players including how many players, their nick name
     * and which character they are playing. Finally return the number of
     * players who is involved.
     * 
     * @author Yun & Ruiyang & Andree & Gimani
     * @return number of players who is involved
     */
    private int getPlayerNumbers() {

        // playing role
        gameData_current.setPlaying(new ArrayList<>());
        // nick name of players
        List<String> temp_name_list = new ArrayList<>();
        // index of the role list
        List<Integer> selections = new ArrayList<>();

        /*
         * Below is the game set up
         */
        int nplayers = GameController.getNumPlayers();

        // get the player name and the role
        for (int i = 0; i < nplayers; i++) {
            String nickname = GameController.getPlayerNickName("");
            while (temp_name_list.contains(nickname)) {
                nickname = GameController.getPlayerNickName("The name cannot be the same!\n");
            }
            temp_name_list.add(nickname);

            // choose a unique player
            int selection = GameController.getCharacterChoice("");

            while (selections.contains(selection)) {
                selection = GameController.getCharacterChoice("Must be a unique character!\n");
            }
            selections.add(selection);

            gameData_current.getPlayers().get(selection).setPlayer(nickname);
            gameData_current.getPlaying().add(gameData_current.getPlayers().get(selection));

        }

        return nplayers;
    }

    /**
     * 
     * Description: <br/>
     * Hand out the remaining cards to the player evenly
     * 
     * @author Yun & Ruiyang & Andree & Gimani
     * @param nplayers
     *            players who get remaining cards
     */
    private void distributeCards(int nplayers) {
        int nextPlayerToGetCard = 0;// (int) Math.random() * nplayers + 1;
        while (!gameData_current.getCardsToHandOut().isEmpty()) {
            // out of the index, so return to the first player
            if (nextPlayerToGetCard > nplayers - 1) {
                nextPlayerToGetCard = 0;// 1;
            }
            // set the random index of the card
            int randomCardIndex = (int) (Math.random()
                    * (gameData_current.getCardsToHandOut().size() - 1));

            /*
             * it's bidirectional,the card needs to know the owner and the
             * Player need to know hand cards
             */
            Player currentPlayer = gameData_current.getPlaying().get(nextPlayerToGetCard);
            Card toAddCard = gameData_current.getCardsToHandOut().get(randomCardIndex);

            // NOT A GOOD CODE, should be upgrade it when everything is done and
            // have enough time
            currentPlayer.addToHand(toAddCard);
            toAddCard.setPlayer(currentPlayer);

            gameData_current.getCardsToHandOut().remove(randomCardIndex);
            nextPlayerToGetCard++;
        }

    }

    /**
     * Description: <br/>
     * Add each Room into the List
     *
     * @author Yun Zhou
     * @return the list of Rooms
     */
    public List<Room> setRooms() {
        List<Room> rooms = new ArrayList<Room>();
        // rooms.add(Cellar);
        rooms.add(gameData_current.getHall());
        rooms.add(gameData_current.getLounge());
        rooms.add(gameData_current.getKitchen());
        rooms.add(gameData_current.getBallRoom());
        rooms.add(gameData_current.getConservatory());
        rooms.add(gameData_current.getBilliardRoom());
        rooms.add(gameData_current.getLibrary());
        rooms.add(gameData_current.getStudy());
        rooms.add(gameData_current.getDiningRoom());

        return rooms;
    }

    /**
     * Description: <br/>
     * Set up and store Player, Weapon and Rooms together as a whole. That is
     * join them together. It's easy to hand out these Cards to every players
     * after the Game starts.
     *
     * @author Yun Zhou & Ruiyang Zhang
     * @param players
     *            players to store
     * @param weapons
     *            weapon to store
     * @param rooms
     *            rooms to store
     * @return Cards which is consist of players,rooms and weapons
     */
    public static List<Card> setCards(List<Player> players, List<Weapon> weapons, List<Room> rooms) {
        List<Card> cards = new ArrayList<Card>();
        for (Room r : rooms) {
            cards.add(new Card(r.getName(), "Room"));
        }
        for (Player p : players) {
            cards.add(new Card(p.getName(), "Player"));
        }
        for (Weapon w : weapons) {
            cards.add(new Card(w.getName(), "Weapon"));
            w.weaponColor = new Color((int) (Math.random() * 255), (int) (Math.random() * 255),
                    (int) (Math.random() * 255));
        }
        return cards;
    }

    /**
     *
     * Description: <br/>
     * Set Player cell and add them into the list
     *
     * @author Yun Zhou & Ruiyang Zhang
     * @param Cells
     *            the game board
     * @return the list of Players
     */
    public static List<Player> setPlayers(Cell[][] Cells) {
        List<Player> players = new ArrayList<Player>();
        players.add(new Player("Miss Scarlett", Cells[7][24], 'S', true));
        players.add(new Player("Colonel Mustard", Cells[0][17], 'M', true));
        players.add(new Player("Mrs. White", Cells[9][0], 'W', true));
        players.add(new Player("Mr. Green", Cells[14][0], 'G', true));
        players.add(new Player("Mrs. Peacock", Cells[23][6], 'P', true));
        players.add(new Player("Professor Plum", Cells[23][19], 'L', true));
        return players;
    }

    /**
     *
     * Description: <br/>
     * 
     * Set weapon cell and add weapons into the list
     *
     * @author Yun Zhou & Ruiyang Zhang
     * @param Cells
     *            the game board
     * @return the list of Players
     */
    public static List<Weapon> setWeapons(Cell[][] Cells) {
        List<Weapon> weapons = new ArrayList<Weapon>();
        weapons.add(new Weapon("Candlestick", Cells[18][22], 'c'));
        weapons.add(new Weapon("Dagger", Cells[3][3], 'd'));
        weapons.add(new Weapon("Lead Pipe", Cells[22][2], 'l'));
        weapons.add(new Weapon("Revolver", Cells[4][11], 'v'));
        weapons.add(new Weapon("Rope", Cells[20][8], 'r'));
        weapons.add(new Weapon("Spanner", Cells[1][22], 's'));
        return weapons;
    }

    /**
     * MODEL METHOD
     *
     * Description: <br/>
     * Set up the data information for the cells. Briefly allocate the space for
     * 9 Rooms AND which cell can be accessed by Players. However, this method
     * does NOT implements the Entrance Cell of each Room.
     *
     * @author Yun Zhou & Ruiyang Zhang
     * @return cells of the map
     */
    public Cell[][] setCells() {
        Cell[][] Cells = new Cell[24][25];
        // initialize Cells
        for (int x = 0; x < 24; x++) {
            for (int y = 0; y < 25; y++) {
                // inaccessible cells
                if ((x < 9 && y == 0) || (x > 9 && x < 14 && y == 0) || (x > 14 && y == 0)
                        || (x == 6 && y == 1) || (x == 17 && y == 1)
                        || ((x == 0) && (y == 6 || y == 8)) ||
                        ((x == 23) && (y == 5 || y == 7)) || (x > 9 && x < 15 && y > 9 && y < 17)
                        || (x == 23 && y > 12 && y < 15) || (x == 0 && (y == 16 || y == 18)) ||
                        (x == 23 && (y == 18 || y == 20))
                        || ((x == 6 || x == 8 || x == 15 || x == 17) && y == 24)) {
                    Cells[x][y] = new Cell("Inaccessible", x, y);
                }
                // Below are the ROOM cells
                else if (x < 6 && y < 7) {
                    Cells[x][y] = new Cell("Kitchen", x, y);
                    gameData_current.getKitchen().setCells(Cells[x][y]);

                } else if ((x >= 8 && x < 16 && y >= 2 && y < 8) || (x >= 10 && x < 14 && y == 1)) {
                    Cells[x][y] = new Cell("Ball Room", x, y);
                    gameData_current.getBallRoom().setCells(Cells[x][y]);
                } else if ((x >= 18 && y < 5) || (x >= 19 && y == 5)) {
                    Cells[x][y] = new Cell("Conservatory", x, y);
                    gameData_current.getConservatory().setCells(Cells[x][y]);
                } else if ((x < 5 && y == 9) || (x < 8 && y > 9 && y < 16)) {
                    Cells[x][y] = new Cell("Dining Room", x, y);
                    gameData_current.getDiningRoom().setCells(Cells[x][y]);
                } else if (x > 9 && x < 15 && y > 9 && y < 17) {
                    Cells[x][y] = new Cell("Cellar", x, y);
                    gameData_current.getCellar().setCells(Cells[x][y]);
                } else if (x > 17 && y > 7 && y < 13) {
                    Cells[x][y] = new Cell("Billiard Room", x, y);
                    gameData_current.getBilliardRoom().setCells(Cells[x][y]);
                } else if ((x > 17 && y > 13 && y < 19) || (x == 17 && y > 14 && y < 18)) {
                    Cells[x][y] = new Cell("Library", x, y);
                    gameData_current.getLibrary().setCells(Cells[x][y]);
                } else if (x < 7 && y > 18) {
                    Cells[x][y] = new Cell("Lounge", x, y);
                    gameData_current.getLounge().setCells(Cells[x][y]);
                } else if (x > 8 && x < 15 && y > 17) {
                    Cells[x][y] = new Cell("Hall", x, y);
                    gameData_current.getHall().setCells(Cells[x][y]);
                } else if (x > 16 && y > 20) {
                    Cells[x][y] = new Cell("Study", x, y);
                    gameData_current.getStudy().setCells(Cells[x][y]);
                }
                // walkway means player can access
                else {
                    Cells[x][y] = new Cell("Walkway", x, y);
                }
            }
        }
        return Cells;
    }

    /**
     *
     * Description: <br/>
     * Set up the entrance Cells of each Room
     *
     * @author Yun Zhou & Ruiyang Zhang
     * @return the new Game Map which has the Room Entrance
     */
    public static List<RoomEntranceCell> setRoomEntranceCells() {
        List<RoomEntranceCell> RoomEntranceCells = new ArrayList<RoomEntranceCell>();
        RoomEntranceCells
                .add(new RoomEntranceCell(new Cell("Walkway", 4, 7), new Cell("Kitchen", 4, 6)));
        RoomEntranceCells
                .add(new RoomEntranceCell(new Cell("Walkway", 7, 5), new Cell("Ball Room", 8, 5)));
        RoomEntranceCells
                .add(new RoomEntranceCell(new Cell("Walkway", 9, 8), new Cell("Ball Room", 9, 7)));
        RoomEntranceCells
                .add(new RoomEntranceCell(new Cell("Walkway", 14, 8), new Cell("Ball Room", 14, 7)));
        RoomEntranceCells
                .add(new RoomEntranceCell(new Cell("Walkway", 16, 5), new Cell("Ball Room", 15, 5)));
        RoomEntranceCells.add(
                new RoomEntranceCell(new Cell("Walkway", 18, 5), new Cell("Conservatory", 18, 4)));
        RoomEntranceCells.add(
                new RoomEntranceCell(new Cell("Walkway", 17, 9), new Cell("Billiard Room", 18, 9)));
        RoomEntranceCells.add(
                new RoomEntranceCell(new Cell("Walkway", 22, 13), new Cell("Billiard Room", 22, 12)));
        RoomEntranceCells
                .add(new RoomEntranceCell(new Cell("Walkway", 20, 13), new Cell("Library", 20, 14)));
        RoomEntranceCells
                .add(new RoomEntranceCell(new Cell("Walkway", 16, 16), new Cell("Library", 17, 16)));
        RoomEntranceCells
                .add(new RoomEntranceCell(new Cell("Walkway", 17, 20), new Cell("Study", 17, 21)));
        RoomEntranceCells
                .add(new RoomEntranceCell(new Cell("Walkway", 15, 20), new Cell("Hall", 14, 20)));
        RoomEntranceCells
                .add(new RoomEntranceCell(new Cell("Walkway", 12, 17), new Cell("Hall", 12, 18)));
        RoomEntranceCells
                .add(new RoomEntranceCell(new Cell("Walkway", 11, 17), new Cell("Hall", 11, 18)));
        RoomEntranceCells
                .add(new RoomEntranceCell(new Cell("Walkway", 6, 18), new Cell("Lounge", 6, 19)));
        RoomEntranceCells.add(
                new RoomEntranceCell(new Cell("Walkway", 6, 16), new Cell("Dining Room", 6, 15)));
        RoomEntranceCells.add(
                new RoomEntranceCell(new Cell("Walkway", 8, 12), new Cell("Dining Room", 7, 12)));
        return RoomEntranceCells;
    }

}
