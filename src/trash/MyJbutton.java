/**
 * Project Name:Final_cluedo
 * File Name:MyJbutton.java
 * Package Name:trash
 * Date:1:46:24 pm
 * Copyright (c) 2020, bluemobi All Rights Reserved.
 *
*/

package trash;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

/**
 * Description: <br/>
 *
 *
 * @author Yun Zhou 300442776
 * @version 1.0
 */
@SuppressWarnings("javadoc")
public class MyJbutton extends JFrame implements ActionListener {
    /**
     * serialVersionUID:
     */
    private static final long serialVersionUID = 3853577437968935052L;

    JRadioButton rb1, rb2;

    JButton b;

    public MyJbutton() {

        rb1 = new JRadioButton("Male");
        rb1.setBounds(100, 50, 100, 30);
        rb2 = new JRadioButton("Female");
        rb2.setBounds(100, 100, 100, 30);
        ButtonGroup bg = new ButtonGroup();
        bg.add(rb1);
        bg.add(rb2);
        b = new JButton("click");
        b.setBounds(100, 150, 80, 30);
        b.addActionListener(this);
        add(rb1);
        add(rb2);
        add(b);
        setSize(300, 300);
        setLayout(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (rb1.isSelected()) {
            JOptionPane.showMessageDialog(this, "You are Male.");
        }
        if (rb2.isSelected()) {
            JOptionPane.showMessageDialog(this, "You are Female.");
        }
    }

    // public static void main(String args[]) {
    // new MyJbutton();
    // }
}
