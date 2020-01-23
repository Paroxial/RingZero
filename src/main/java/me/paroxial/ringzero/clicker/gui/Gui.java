package me.paroxial.ringzero.clicker.gui;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.Color;
import java.util.concurrent.atomic.AtomicInteger;

public class Gui extends JFrame {
    private AtomicInteger clicks = new AtomicInteger(1);
    
	// TODO - Add buttons for clicker settings and sliders for min/max CPS values
    public Gui(){
        setBackground(Color.WHITE);
        setBounds(100, 100, 250, 250);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setVisible(true);
    }
}
