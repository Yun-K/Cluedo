package trash;

/**
 * Project Name:code
 * File Name:MenuView.java
 * Package Name:gui
 * Copyright (c) 2020, bluemobi All Rights Reserved.
 *
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * Description: <br/>
 * Create a simple JDialog for testing Button feature
 *
 * @author Yun Zhou
 * @version 1.0
 */
public class MenuView extends JFrame implements ActionListener {

    /**
     * serialVersionUID:
     */
    private static final long serialVersionUID = 1L;

    /**
     * f:frame
     */
    static JFrame f;

    /**
     * 
     * Description: <br/>
     *
     * @author Yun & Ruiyang & Andree & Gimani
     */
    @SuppressWarnings({ "unused", "deprecation" })
    private void temp() {
        // create a new frame
        f = new JFrame("frame");

        // create a object
        MenuView s = new MenuView();

        // create a panel
        JPanel p = new JPanel();

        JButton b = new JButton("click");

        // add actionlistener to button
        b.addActionListener(s);

        // add button to panel
        p.add(b);

        f.add(p);

        // set the size of frame
        f.setSize(400, 400);

        f.show();

    }

    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s.equals("click")) {
            // create a dialog Box
            JDialog d = new JDialog(f, "dialog Box");

            // create a label
            JLabel l = new JLabel("this is a dialog box");

            d.add(l);

            // setsize of dialog
            d.setSize(100, 100);

            // set visibility of dialog
            d.setVisible(true);
        }
    }
}
