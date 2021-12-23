package com.stevewen.tank;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class T {

    public static void main(String[] args){
        Frame f= new Frame();
        f.setSize(800, 600); // Set the size of the frame
        f.setResizable(false); // Fixed size for continuous experience
        f.setTitle("Tank War");
        f.setVisible(true);

        f.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){ // When pressing the cross, this method was invoked
                System.exit(0); // Exit the system
            }
        });
    }
}
