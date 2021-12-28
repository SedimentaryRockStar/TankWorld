package com.stevewen.tank;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ResourceMgr {

    public static BufferedImage  GOODtankL, GOODtankU, GOODtankD, GOODtankR;
    public static BufferedImage  BADtankL, BADtankU, BADtankD, BADtankR;
    public static BufferedImage  BulletL, BulletU, BulletD, BulletR;

    public static BufferedImage[] explodes= new BufferedImage[16];

    static {
        try {
            GOODtankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
            GOODtankL = ResourceMgr.rotate(GOODtankU,-90);
            GOODtankR = ResourceMgr.rotate(GOODtankU,90);
            GOODtankD = ResourceMgr.rotate(GOODtankU,180);

            BADtankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
            BADtankL = ResourceMgr.rotate(BADtankU,-90);
            BADtankR = ResourceMgr.rotate(BADtankU,90);
            BADtankD = ResourceMgr.rotate(BADtankU,180);


            BulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/BulletU.png"));
            BulletL = ResourceMgr.rotate(BulletU,-90);
            BulletR = ResourceMgr.rotate(BulletU,90);
            BulletD = ResourceMgr.rotate(BulletU,180);


            for(int i= 0; i< 16; i++){
                explodes[i]= ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/e"+ (i+1)+ ".gif"));
            }

        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    private static BufferedImage rotate(BufferedImage bimg, double angle) {

        int w = bimg.getWidth();
        int h = bimg.getHeight();

        BufferedImage rotated = new BufferedImage(w, h, bimg.getType());
        Graphics2D graphic = rotated.createGraphics();
        graphic.rotate(Math.toRadians(angle), w/2, h/2);
        graphic.drawImage(bimg, null, 0, 0);
        graphic.dispose();
        return rotated;
    }

}
