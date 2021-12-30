package com.stevewen.tank;

public class FourDirFireStrategy implements FireStrategy{

    @Override
    public void fire(Tank t){

        int bX= t.getX() + Tank.WIDTH/2;
        int by= t.getY() + Tank.HEIGHT/2;

        switch(t.getDir()){
            case UP:
                bX-= Bullet.WIDTH/2;
                by-= Bullet.HEIGHT*2;
                break;
            case DOWN:
                bX-= Bullet.WIDTH/2;
                by+= Bullet.HEIGHT;
                break;
            case RIGHT:
                bX+= Bullet.WIDTH;
                by-= Bullet.HEIGHT*10/60;
                break;
            case LEFT:
                bX-= Bullet.WIDTH*2;
                by-= Bullet.HEIGHT/4;
                break;
        }

        Dir[] dirs= Dir.values();
        for(Dir dir : dirs){
            new Bullet(bX, by, dir, t.tf, t.getGroup());
        }

    }
}
