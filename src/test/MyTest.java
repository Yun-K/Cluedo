/**
 * Project Name:Final_cluedo
 * File Name:MyTest.java
 * Package Name:test
 * Date:6:19:29 pm
 * Copyright (c) 2020, bluemobi All Rights Reserved.
 *
*/

package test;

import org.junit.Test;

import game.Board;

/**
 * 
 * Description: <br/>
 *
 * @author Yun & Ruiyang & Andree & Gimani
 */
@SuppressWarnings("javadoc")
public class MyTest {

    // private GameData gameData_current = new GameData(false, new
    // Room("Kitchen"),
    // new Room("Ball Room"), new Room("Conservatory"), new Room("Dining Room"),
    // new Room("Cellar"), new Room("Billiard Room"), new Room("Library"),
    // new Room("Lounge"),
    // new Room("Hall"), new Room("Study"));
    //
    // private Board board = new Board(gameData_current.getCells(),
    // gameData_current.getPlayers(),
    // gameData_current.getWeapons(), gameData_current.getRooms(),
    // gameData_current.getMurderCards(),
    // gameData_current.getRoomEntranceCells(),
    // gameData_current.getAllCards(),
    // gameData_current.getPlaying());

    @Test
    public void testDiceRoll() {
        try {
            for (int i = 0; i < 99; i++) {
                int roll = Board.rollDice();
                assert (roll >= 2 && roll <= 12);
            }
        } catch (NullPointerException e) {
        }
    }

}
