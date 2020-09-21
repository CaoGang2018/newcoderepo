package com.cao.bird.element;

import com.cao.bird.Data;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author admin_cg
 * @data 2020/9/21 20:49
 */
public class Ground {
    public BufferedImage image;
    public int x, y;
    public int width, height;

    public Ground() throws IOException {
        image = ImageIO.read(Data.groundUrl);
        width = image.getWidth();
        height = image.getHeight();
        x = 0; y = 500;
    }
    public void step () {
        x--;
        if(x == -109)
            x = 0;
    }
}
