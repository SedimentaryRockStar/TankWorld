package com.stevewen.tank;



public class Main {

    public static void main(String[] args) throws InterruptedException{
        TankFrame tf= new TankFrame();

        //Initialize all the enemy tanks
        for(int i= 0; i< 5; i++){
            tf.tanks.add(new Tank(50+ i* 80, 200, Dir.DOWN, tf, Group.BAD));
        }

        while(true){
            Thread.sleep(50);
            tf.repaint();
        }

    }
}
