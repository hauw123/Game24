package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.awt.Button;
import java.util.ArrayList;
import java.util.List;

public class levelbutton {
    Texture texture;
    Sprite sprite;
    int level;
    float x,y,x1,y1;
    float w,h;
    String levelangka;
    int status;
    public levelbutton(){

    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setGambar(String textureA){
        this.texture = new Texture(textureA);
        this.sprite = new Sprite(this.texture);
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setPosition(float x1 , float y1){
        x = x1;
        y = y1;
        sprite.setPosition(x,y);
    }

    public void setukuran(float w1,float h1){
        w = w1;
        h = h1;
        sprite.setSize(w,h);
    }

    public void setLevelangka(int levelangka) {
        this.levelangka = String.valueOf(levelangka);
    }

    public String getLevelangka() {
        return levelangka;
    }
    public void setPositionText(float x2 , float y2){
        x1 = x2;
        y1 = y2;
    }

    public float getX1() {
        return x1;
    }

    public void setX1(float x1) {
        this.x1 = x1;
    }

    public float getY1() {
        return y1;
    }

    public void setY1(float y1) {
        this.y1 = y1;
    }
}
