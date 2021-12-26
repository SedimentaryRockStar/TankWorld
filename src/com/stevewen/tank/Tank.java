package com.stevewen.tank;

import java.awt.*;

public class Tank {
    private int x, y;
    private Dir dir= Dir.DOWN;
    final int SPEED= 5;

    private TankFrame tf= null;

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    private boolean moving= false;

    public Tank(int x, int y, Dir dir, TankFrame tf){
        super();
        this.x= x;
        this.y= y;
        this.dir= dir;
        this.tf= tf;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public void paint(Graphics g) {
        Color c= g.getColor();
        g.setColor(Color.yellow);
        g.fillRect(x, y, 50, 60);
        g.setColor(c);

        move();


    }

    private void move(){
        if(!moving) return;

        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
        }
    }

    public void fire(){
        tf.bullets.add(new Bullet(this.x, this.y, this.dir, this.tf));
    }
}
