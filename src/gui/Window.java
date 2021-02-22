/**
 * Project Name:ASSIGNMENT2_CLUEDO_UPDATED.zip_expanded
 * File Name:Test.java
 * Package Name:test
 * Date:下午5:38:00
 * Copyright (c) 2020, bluemobi All Rights Reserved.
 *
*/

package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

/**
 * Description: <br/>
 * Extend the JFrame and add a new feature that is show the confirmation dialog
 * when user try to close the window
 *
 * @author Yun Zhou 300442776
 * @version 1.0
 */
public class Window extends JFrame implements ActionListener {

    /**
     * serialVersionUID:
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     * A constructor. It construct a new instance of Window. Extend the JFrame
     * and add a new feature that is show the confirmation dialog when user try
     * to close the window
     *
     * @param title
     *            the title for the frame
     */
    public Window(String title) {
        super(title);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        // add a window listener to show the JOptionPane
        // to ask the user whether they want to close the windows
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int option = JOptionPane.showConfirmDialog(
                        Window.this, "Do you want to exit the game? ", "Hint",
                        JOptionPane.YES_NO_CANCEL_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    if (e.getWindow() == Window.this) {
                        System.exit(0);
                    } else {
                        return;
                    }
                }
            }
        });

    }

    /**
     * 
     * Description: <br/>
     * Add the JMenu stuff on the JFrame
     * 
     * @author Yun & Ruiyang & Andree & Gimani
     */
    public void addJMenu() {
        // menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu gameMenu = new JMenu("Cluedo");
        menuBar.add(gameMenu);

        JMenuItem restart = new JMenuItem("Restart");
        restart.addActionListener(
                (ActionEvent e) -> restartGame());

        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(
                (ActionEvent e) -> close_window());

        // gameMenu.add(restart);
        gameMenu.add(exit);
        setJMenuBar(menuBar);

    }

    /**
     * 
     * Description: <br/>
     * Restart game dialog
     * 
     * @author Yun & Ruiyang & Andree & Gimani
     */
    private void restartGame() {
        int option = JOptionPane.showConfirmDialog(
                Window.this, "Do you want to restart the game? ", "Hint",
                JOptionPane.YES_NO_CANCEL_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            System.out.println("Unexpected Error has occur");
            // new Game();
        }
    }

    /**
     * 
     * Description: <br/>
     * Double check whether the player want to exit the game.
     * 
     * @author Yun & Ruiyang & Andree & Gimani
     */
    public void close_window() {
        int option = JOptionPane.showConfirmDialog(
                Window.this, "Do you want to exit the game? ", "Hint",
                JOptionPane.YES_NO_CANCEL_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    /**
     * Radio button
     */
    JRadioButton three_player, four_player, five_player, six_player;

    /**
     * button
     */
    JButton b;

    /**
     * 
     * Description: <br/>
     * Ask the number of player use jbutton
     * 
     * @author Yun & Ruiyang & Andree & Gimani
     */
    public void ask_number_player() {

        three_player = new JRadioButton("3");
        three_player.setBounds(100, 50, 100, 30);
        four_player = new JRadioButton("4");
        four_player.setBounds(100, 70, 100, 30);
        five_player = new JRadioButton("5");
        five_player.setBounds(100, 90, 100, 30);
        six_player = new JRadioButton("6");
        six_player.setBounds(100, 110, 100, 30);

        ButtonGroup bg = new ButtonGroup();
        bg.add(three_player);
        bg.add(four_player);
        bg.add(five_player);
        bg.add(six_player);

        b = new JButton("player number");
        b.setBounds(60, 150, 140, 40);
        b.addActionListener(this);
        add(three_player);
        add(four_player);
        add(five_player);
        add(six_player);
        add(b);
        setSize(300, 300);
        setLayout(null);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (three_player.isSelected()) {
            JOptionPane.showMessageDialog(this,
                    "There are 3 players in total.\nPlease choose the character to play and enter the nickname");
            GameController.player_number = 3;
        } else if (four_player.isSelected()) {
            JOptionPane.showMessageDialog(this,
                    "There are 4 players in total.\nPlease choose the character to play and enter the nickname");
            GameController.player_number = 4;
        } else if (five_player.isSelected()) {
            JOptionPane.showMessageDialog(this,
                    "There are 5 players in total.\nPlease choose the character to play and enter the nickname");
            GameController.player_number = 5;
        } else if (six_player.isSelected()) {
            JOptionPane.showMessageDialog(this,
                    "There are 6 players in total.\nPlease choose the character to play and enter the nickname");
            GameController.player_number = 6;
        }

    }
    //
    // public static void main(String[] args) {
    // Window window = new Window("Test");
    // window.ask_number_player();
    // }
}
