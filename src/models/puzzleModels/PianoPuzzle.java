package models.puzzleModels;

import helpers.Content;
import helpers.Drawer;
import helpers.Mouse;
import helpers.TextOutput;
import models.ImgObj;
import states.LocationState;

import java.awt.*;

public class PianoPuzzle extends Puzzle {
    int index = 0;
    boolean allCorrect = false;
    ImgObj p1 = Content.images.get(31);
    ImgObj p2 = Content.images.get(32);
    ImgObj p3 = Content.images.get(33);
    ImgObj p4 = Content.images.get(34);
    ImgObj p5 = Content.images.get(35);
    ImgObj p6 = Content.images.get(36);
    // ImgObj exit = Content.images.get(-5);
    ImgObj[] corrects = {Content.images.get(41), Content.images.get(42),Content.images.get(43),Content.images.get(44),Content.images.get(45),Content.images.get(46)};

    int[] sequence = {2, 1, 5, 5, 3, 6};
    int[] played = new int[6];

    boolean showSeq = true;
    boolean showIncorrect = false;

    public PianoPuzzle(int id, int dialogue) {
        super(id, dialogue);
    }

    @Override
    public void init() {
        for (int i = 0; i < played.length; i++) {
            played[i] = 0;
        }
    }

    @Override
    public void draw(Graphics2D g) {
        Drawer.draw(g,LocationState.location.getBackground());
        g.setColor(Color.BLACK);
        Drawer.draw(g, p1);
        Drawer.draw(g, p2);
        Drawer.draw(g, p3);
        Drawer.draw(g, p4);
        Drawer.draw(g, p5);
        Drawer.draw(g, p6);
        try {
            Drawer.draw(g, corrects[played[index - 1] - 1]);
        } catch(Exception e) {

        }
        if (showSeq) {
            TextOutput.s = "Play this sequence: 2, 1, 5, 5, 3, 6";
//            g.setColor(Color.WHITE);
//            g.fillRect(245,395 , 300,40 );
//            g.setColor(Color.BLACK);
//            g.drawString("Play this sequence: 2, 1, 5, 5, 3, 6", 250, 400);
        }
        else {
            // cover sequence
            TextOutput.s = "";
//            g.setColor(Color.WHITE);
//            g.fillRect(245, 395, 300, 40);
//            g.setColor(Color.BLACK);
        }

        if (showIncorrect) {
            TextOutput.s += "\nNope! Try again.";
//            g.setColor(Color.WHITE);
//            g.fillRect(295, 445, 200, 40);
//            g.setColor(Color.BLACK);
//            g.drawString("Nope! Try again.", 300, 450);
        }
        else {
            // cover incorrect warning
//            g.setColor(Color.WHITE);
//            g.fillRect(295, 445, 200, 40);
//            g.setColor(Color.BLACK);
        }
    }

    @Override
    public boolean passed() {
        return allCorrect;
    }


    @Override
    public boolean quit() {
        return false;
    }


    // this updates frequently
    @Override
    public void handleInput() {
        if (Mouse.isClicked()) {
            showSeq = false;
            if (Mouse.isCollided(p1)) {
                played[index] = 1;
            } else if (Mouse.isCollided(p2)) {
                played[index] = 2;
            } else if (Mouse.isCollided(p3)) {
                played[index] = 3;
            } else if (Mouse.isCollided(p4)) {
                played[index] = 4;
            } else if (Mouse.isCollided(p5)) {
                played[index] = 5;
            } else if (Mouse.isCollided(p6)) {
                played[index] = 6;
            } else {
                played[index] = 0;
            }
            index++;
            for(int i = 0; i < index;i++) {
                if(played[i] != sequence[i]) {
                    index = 0;
                    showSeq = true;
                    showIncorrect = true;
                    return;
                }
            }
            showIncorrect = false;
            if(index == 6) {
                allCorrect = true;
            }
        }
    }

    @Override
    public boolean isCompleted() {
        return isCompleted;
    }

}
