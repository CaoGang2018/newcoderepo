package com.cao.bird;

import com.cao.bird.element.Bird;
import com.cao.bird.element.Column;
import com.cao.bird.element.Ground;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.rmi.server.ExportException;

/**
 * @author admin_cg
 * @data 2020/9/21 20:42
 */
public class BirdPanel extends JPanel {
    BufferedImage background;

    BufferedImage startImage;
    BufferedImage gameOverImage;

    Ground ground;

    Column column1, column2;

    Bird bird;

    int score;

    int state;

    public static final int START = 0; //开始
    public static final int RUNNING = 1; //运行
    public static final int GAME_OVER = 2; //结束

    public BirdPanel() throws IOException {
        this.background = ImageIO.read(Data.bgUrl);
        startImage = ImageIO.read(Data.startUrl);
        gameOverImage = ImageIO.read(Data.gameOverUrl);

        ground = new Ground();
        column1 = new Column(1);
        column2 = new Column(2);
        bird = new Bird();

        score = 0;

        state = START;
    }
    public void paint(Graphics g){
        g.drawImage(background, 0, 0, null);

        g.drawImage(ground.image, ground.x, ground.y, null);

        g.drawImage(column1.image, column1.x - column1.width / 2, column1.y - column1.height / 2, null);
        g.drawImage(column2.image, column2.x - column2.width / 2, column2.y - column2.height / 2, null);

        Graphics2D g2 = (Graphics2D) g;
        g2.rotate(-bird.alpha, bird.x, bird.y);
        g.drawImage(bird.image, bird.x - bird.width / 2, bird.y - bird.height / 2, null);
        g2.rotate(bird.alpha, bird.x, bird.y);

        Font f = new Font(Font.SANS_SERIF, Font.BOLD, 40);
        g.setFont(f);
        g.drawString("" + score, 40, 60);
        g.setColor(Color.WHITE);
        g.drawString("" + score, 40-3, 60-3);

        switch (state) {
            case START -> g.drawImage(startImage, 0, 0, null);
            case GAME_OVER -> g.drawImage(gameOverImage, 0, 0, null);
        }
    }

    public void action() throws InterruptedException {
        MouseAdapter l = new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                try{
                    switch (state) {
                        case START -> state = RUNNING;
                        case RUNNING -> bird.flappy();
                        case GAME_OVER -> {
                            column1 = new Column(1);
                            column2 = new Column(2);
                            bird = new Bird();
                            score = 0;
                            state = START;
                        }
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        };
        addMouseListener(l);
        while (true){
            switch (state) {
                case START -> {
                    bird.fly();
                    ground.step();
                }
                case RUNNING -> {
                    ground.step();
                    column1.step();
                    column2.step();
                    bird.fly();
                    bird.step();
                    if (bird.x == column1.x || bird.x == column2.x)
                        score++;
                    if (bird.hit(ground) || bird.hit(column1) || bird.hit(column2))
                        state = GAME_OVER;
                }
            }
            repaint();
            Thread.sleep(1000 / 60);
        }
    }
}
