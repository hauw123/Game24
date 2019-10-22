package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class operatorbutton {
    int angka;
    Sprite sprite;
    Texture texture;
    float w,h,x,y;
    int status = 0;

    public int getAngka() {
        return angka;
    }

    public void setAngka(int angka) {
        this.angka = angka;
    }

    public Sprite getSprite() {
        return sprite;
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

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
