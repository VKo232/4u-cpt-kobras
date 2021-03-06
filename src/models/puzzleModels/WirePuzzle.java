package models.puzzleModels;

import helpers.Content;
import helpers.Drawer;
import helpers.Mouse;
import helpers.TextOutput;
import models.Dialogue;
import models.ImgObj;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/*
PORTIAAAA
change of plans
so no extra class cuz its useless
to rotate
you have an int array that you keep ttrack of whatever
to change it look under handle input
 */
public class WirePuzzle extends Puzzle {
    ImgObj[] pipes = new ImgObj[12];
    ImgObj pstart = Content.images.get(50);
    ImgObj pend = Content.images.get(63);

    // see wireList.txt
    // the numbers in the bottom right of each picture equals index + 1

    int[] rot = {1, 0, 3, 2, 2, 1, 0, 0, 2, 2, 3, 0};
    boolean[] correct = {false, true, false, false, false, false, true, true, false, false, false, true};

    public WirePuzzle(int id, int dialogue) {
        super(id, dialogue);
    }

    @Override
    public void init() {
        isCompleted = false;
        for (int i = 0; i < pipes.length; i++){
            pipes[i] = Content.images.get(51+i);
        }
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.fillRect(0,0 ,600 ,600 );
        //g.setColor(Color.BLACK);
        TextOutput.s ="Find a path for the wires to connect from\nstart to end";
        Drawer.draw(g, pstart);
        Drawer.draw(g, pend);
        for(int i = 0; i < pipes.length; i++) {
            Drawer.draw(g, pipes[i]);
        }
    }

    @Override
    public boolean passed() {
        for (int i = 0; i < rot.length; i++){
            if ((rot[i] % 4) != 0) {
                correct[i] = false;
            } else {
                correct[i] = true;
            }
        }

        // for the two straight pipes
        if (rot[0]%2 == 0)
             correct[0] = true;
        if (rot[5]%2 == 0)
            correct[5] = true;

        for (int i = 0; i < correct.length; i++){
            if (!correct[i]){
                return false;
            }
        }

        return true;
    }



    // oh right
    // theres also this
    // find image of back button
    // make in img obj of it
    // do the drawer.draw thing for the back button
    // copy mouse isCollided and add the backbutton img where specified
    // then...
    @Override
    public boolean quit() {
        return false;
    }


    // this updates frequently
    @Override
    public void handleInput() {
        if(Mouse.isClicked()) {
            for(int i = 0; i < pipes.length; i++) {
                // this part is where you set whatever you need to set
                if(Mouse.isCollided(pipes[i])) {
                    pipes[i].img =rotateCw(pipes[i].img);
//                    Graphics2D g2 = pipes[i].img.createGraphics();
//                    g2.rotate(Math.toRadians(90));
                    rot[i]++;
                    // these must always be correct (the unused pipes)
                    rot[6] = 0;
                    rot[11] = 0;
                }
            }
        }
    }
    public static BufferedImage rotateCw( BufferedImage img )
    {
        int         width  = img.getWidth();
        int         height = img.getHeight();
        BufferedImage   newImage = new BufferedImage( height, width, img.getType() );

        for( int i=0 ; i < width ; i++ )
            for( int j=0 ; j < height ; j++ )
                newImage.setRGB( height-1-j, i, img.getRGB(i,j) );

        return newImage;
    }
    @Override
    public boolean isCompleted() {
        return passed();
    }

}
