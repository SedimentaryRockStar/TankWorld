package com.stevewen.tank;

import java.awt.*;
import java.util.Random;



public class Tank {


    private int x;
    private int y;
    private Dir dir= Dir.DOWN;
    final int SPEED= 5;
    private boolean living= true;
    public static final int WIDTH= ResourceMgr.GOODtankD.getWidth(), HEIGHT= ResourceMgr.GOODtankD.getHeight();
    TankFrame tf= null;
    private Random random= new Random();
    private boolean moving= true; // TODO: Change the version of the main tank
    private Group group= Group.BAD;

    Rectangle rect= new Rectangle();

    FireStrategy fs;

    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public boolean isMoving() {
        return moving;
    }
    public void setMoving(boolean moving) {
        this.moving = moving;
    }
    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public Tank(int x, int y, Dir dir, TankFrame tf, Group group){
        super();
        this.x= x;
        this.y= y;
        this.dir= dir;
        this.tf= tf;
        this.group= group;

        rect.x= this.x;
        rect.y= this.y;
        rect.width= WIDTH;
        rect.height= HEIGHT;




            try {
                if(group== Group.GOOD) {
                    String goodFsName = (String) PropertyMgr.get("goodFs");
                    fs = (FireStrategy) Class.forName(goodFsName).newInstance();
                }else {
                    String badFsName= (String) PropertyMgr.get("badFs");
                    fs = (FireStrategy) Class.forName(badFsName).newInstance();
                }
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }




    public Dir getDir() {
        return dir;
    }



    public void paint(Graphics g) {

        if(!living) tf.tanks.remove(this);

        switch(dir){
            case LEFT:
                g.drawImage(this.group== Group.GOOD? ResourceMgr.GOODtankL : ResourceMgr.BADtankL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(this.group== Group.GOOD? ResourceMgr.GOODtankR : ResourceMgr.BADtankR, x, y, null);                break;
            case DOWN:
                g.drawImage(this.group== Group.GOOD? ResourceMgr.GOODtankD : ResourceMgr.BADtankD, x, y, null);                break;
            case UP:
                g.drawImage(this.group== Group.GOOD? ResourceMgr.GOODtankU : ResourceMgr.BADtankU, x, y, null);                break;
        }
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

        if(this.group== Group.BAD && random.nextInt(100)> 95) this.fire();

        if(this.group== Group.BAD && random.nextInt(100)> 95) randomdir();

        boundsCheck();

        //Update the rect
        rect.x= this.x;
        rect.y= this.y;

    }

    private void boundsCheck(){
        if(this.x< 2) x= 2;
        if (this.y< 28) y= 28;
        if (this.x> TankFrame.GAME_WIDTH- Tank.WIDTH- 2) this.x= TankFrame.GAME_WIDTH- Tank.WIDTH- 2;
        if (this.y> TankFrame.GAME_HEIGHT- Tank.HEIGHT- 2) this.y= TankFrame.GAME_HEIGHT- Tank.HEIGHT- 2;
    }


    private void randomdir() {
        this.dir= Dir.values()[random.nextInt(4)];
    }
    public Group getGroup() {
        return group;
    }
    public void setGroup(Group group) {
        this.group = group;
    }

    public void fire(){
        fs.fire(this);
    }

    public void die(){
        this.living= false;
    }
}
