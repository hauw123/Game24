package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class gamebutton {
    int angka;
    Sprite sprite;
    Texture texture;
    float w,h,x,y;
    int status = 0;
    float x1,y1;

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setGambar(String textureA){
        this.texture = new Texture(textureA);
        this.sprite = new Sprite(this.texture);
    }


    public void setukuran(float w1,float h1){
        w = w1;
        h = h1;
        sprite.setSize(w,h);
    }

    public void setpost(float x1,float y1){
        x = x1;
        y = y1;
        sprite.setPosition(x,y);
    }

    public int getAngka() {
        return angka;
    }

    public void setAngka(int angka) {
        this.angka = angka;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public Texture getTexture() {
        return texture;
    }



    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public float getX1() {
        return x1;
    }

    public float getY1() {
        return y1;
    }




    public void setPositionText(float x2 , float y2){
        x1 = x2;
        y1 = y2;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
    public void setX1(float x1) {
        this.x1 = x1;
    }

    public void setY1(float y1) {
        this.y1 = y1;
    }

    public void remove(Sprite sprite){

    }
}
