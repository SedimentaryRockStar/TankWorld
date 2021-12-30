package com.stevewen.tank;

import java.awt.*;

public class Bullet {
    private static final int SPEED= 10;
    public static final int WIDTH= ResourceMgr.BulletD.getWidth(), HEIGHT= ResourceMgr.BulletD.getHeight();

    private int x, y;
    private Dir dir;
    TankFrame tf= null;

    Rectangle rect= new Rectangle();

    private boolean living = true;
    private Group group= Group.BAD;


    public Bullet(int x, int y, Dir dir, TankFrame tf, Group group) {
        this.x= x;
        this.y= y;
        this.dir= dir;
        this. tf= tf;
        this.group= group;

        rect.x= this.x;
        rect.y= this.y;
        rect.width= WIDTH;
        rect.height= HEIGHT;

        tf.bullets.add(this);
    }

    public void paint(Graphics g) {
        if (!living){
            tf.bullets.remove(this);
        }
        switch(dir){
            case LEFT:
                g.drawImage(ResourceMgr.BulletL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.BulletR, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.BulletD, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.BulletU, x, y, null);
                break;
        }
        move();
    }
    private void move() {
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

        rect.x= this.x;
        rect.y= this.y;

        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) living = false;
    }




    private void die(){
        this.living= false;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }


    public void collideWith(Tank tank){
        if( this.group== tank.getGroup() ) return;

        if(rect.intersects(tank.rect)){
            tank.die();
            this.die();
            int eX= tank.getX()+ Tank.WIDTH/2- Explode.WIDTH/2;
            int ey= tank.getY()+ Tank.HEIGHT/2- Explode.HEIGHT/2;
            tf.explodes.add(new Explode(eX, ey, tf));
        }

    }
}
