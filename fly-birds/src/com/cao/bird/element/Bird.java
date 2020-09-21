package com.cao.bird.element;

import com.cao.bird.Data;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author admin_cg
 * @data 2020/9/21 20:48
 */
public class Bird {
    public BufferedImage image;
    public int x, y;
    public int width, height;
    public int size;
    public double g;
    public double t;
    public double v0;
    public double speed;
    public double s;
    public double alpha;

    public BufferedImage[] images;
    public int index;

    public Bird() throws IOException {
        image = ImageIO.read(Data.getIthBody(0));
        width = image.getWidth();
        height = image.getHeight();
        x = 132;
        y = 280;
        size = 40;

        g = 4;
        v0 = 20;
        t = 0.25;
        speed = v0;
        s = 0;
        alpha = 0;

        images = new BufferedImage[8];
        for (int i = 0; i < 8; i++) {
            images[i] = ImageIO.read(Data.getIthBody(i));
        }
        index = 0;
    }

    public void fly(){
        index++;
        image = images[(index / 12) % 8];
    }

    public void step(){
        double v0 = speed;
        s = v0 * t + g * t * t / 2;
        y -= (int) s;
        double v = v0 - g * t;
        speed = v;
        alpha = Math.atan(s / 8);
    }

    public void flappy(){
        speed = v0;
    }

    public boolean hit(Ground ground){
        boolean hit = y + size / 2 > ground.y;
        if(hit){
            y = ground.y - size / 2;
            alpha = Math.PI / 2;
        }
        return hit;
    }

    public boolean hit(Column column){
        if(x > column.x - column.width / 2 + size / 2
        && x < column.x + column.width / 2 + size / 2){
            if(y > column.y - column.gap / 2 + size / 2
            && y < column.y +column.gap / 2 + size / 2)
                return false;
            return true;
        }
        return false;
    }
}
