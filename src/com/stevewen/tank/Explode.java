package com.stevewen.tank;

import java.awt.*;

public class Explode {

    public static final int WIDTH = ResourceMgr.explodes[0].getWidth(), HEIGHT = ResourceMgr.explodes[0].getHeight();

    private int x, y;
    TankFrame tf = null;


    private int step= 0;

    public Explode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
        new Thread(()->new Audio("audio/explode.wav").play()).start();

        //new Audio("audio/explode.wav").start();
    }

    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.explodes[step++], x, y, null );
        if(step>= ResourceMgr.explodes.length) {
            tf.explodes.remove(this);
        }

    }

}