package com.cao.bird.element;

import com.cao.bird.Data;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @author admin_cg
 * @data 2020/9/21 20:48
 */
public class Column {
    public BufferedImage image;
    public int x, y;
    public int width, height;
    public int gap;
    public int distance;
    public Random random = new Random();

    public Column(int n) throws IOException {
        image = ImageIO.read(Data.columnUrl);
        width = image.getWidth();
        height = image.getHeight();
        gap = 144;
        distance = 245;
        x = 550 + (n - 1) * distance;
        y = random.nextInt(218) + 132;
    }
    public void step(){
        x--;
        if(x == -width / 2){
            x = distance * 2 - width / 2;
            y = random.nextInt(218) + 132;
        }
    }
}
