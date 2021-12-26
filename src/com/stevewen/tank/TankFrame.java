package com.stevewen.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class TankFrame extends Frame {

    Tank myTank= new Tank(200, 200, Dir.DOWN, this);
    ArrayList<Bullet> bullets= new ArrayList<Bullet>();
    Bullet b= new Bullet(200, 300, Dir.DOWN, this);
    static final int GAME_WIDTH= 800, GAME_HEIGHT= 600;


    public TankFrame(){

        setSize(800, 600); // Set the size of the frame
        setResizable(false); // Fixed size for continuous experience
        setTitle("Tank War");
        setVisible(true);

        this.addKeyListener(new MyKeyListener());
        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){ // When pressing the cross, this method was invoked
                System.exit(0); // Exit the system
            }
        });

    }

    //Double Buffer to solve the glowing issue
    Image offScreenImage = null;
    @Override
    public void update(Graphics g){

        if(offScreenImage== null){
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c= gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);

    }

    @Override //When the window is re-graphed, this method is invoked, including opening the window for the first time or resizing
    public void paint(Graphics g){

        myTank.paint(g);
        for (int i= 0; i< bullets.size(); i++){
            bullets.get(i).paint(g);
        }
    }


    class MyKeyListener extends KeyAdapter{

        boolean bL= false;
        boolean bU= false;
        boolean bR= false;
        boolean bD= false;


        @Override
        public void keyPressed(KeyEvent e) {

            int key= e.getKeyCode();
            switch(key){
                case KeyEvent.VK_LEFT :
                case KeyEvent.VK_A :
                    bL= true;
                    break;

                case KeyEvent.VK_UP:
                case KeyEvent.VK_W:
                    bU= true;
                    break;

                case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_D:
                    bR= true;
                    break;

                case KeyEvent.VK_DOWN:
                case KeyEvent.VK_S:
                    bD= true;
                    break;

                default:
                    break;
            }
            //repaint(); // Implicitly invoke paint method every time this method is invoked

            setMainTankDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {

            int key= e.getKeyCode();
            switch(key){
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_A :
                    bL= false;
                    break;
                case KeyEvent.VK_UP:
                case KeyEvent.VK_W:
                    bU= false;
                    break;
                case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_D:
                    bR= false;
                    break;
                case KeyEvent.VK_DOWN:
                case KeyEvent.VK_S:
                    bD= false;
                    break;
                case KeyEvent.VK_SPACE:
                    myTank.fire();
                    break;

                default:
                    break;
            }
            setMainTankDir();
        }


        private void setMainTankDir(){

            if(!bL && !bU && !bR && !bD) {
                myTank.setMoving(false);
            }
            else {
                myTank.setMoving(true);

                if (bL) myTank.setDir(Dir.LEFT);
                if (bU) myTank.setDir(Dir.UP);
                if (bD) myTank.setDir(Dir.DOWN);
                if (bR) myTank.setDir(Dir.RIGHT);
            }
        }


    }


}
