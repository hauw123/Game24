package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class menu implements Screen {
    final Game24 game;
    Texture texture;
    Texture textureexit;
    Sprite sprite;
    Sprite spriteexit;
    LoginScreen loginscreen1;
    int leveltemp,idtemp;
    String usernametemp;
    int screentemp;

    public menu(Game24 game,int id,String username,int level,int countscreen) {
        this.game = game;
        screentemp = countscreen;
        leveltemp = level;
        idtemp = id;
        usernametemp = username;
        texture = new Texture("buttonplay.png");
        textureexit = new Texture("buttonexit.png");
        sprite = new Sprite(texture);
        spriteexit = new Sprite(textureexit);
        sprite.setPosition(165,650);
        sprite.setSize(750,250);
        spriteexit.setPosition(165,300);
        spriteexit.setSize(750,250);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        if (screentemp == 0){
            game.setScreen(new menu(game,idtemp,usernametemp,leveltemp,1));
        }

        float xGrav = Gdx.input.getAccelerometerX();
        float yGrav = Gdx.input.getAccelerometerY();
        float zGrav = Gdx.input.getAccelerometerZ();

        float gForce = (float) Math.sqrt((xGrav * xGrav)+(yGrav*yGrav)+(zGrav*zGrav));

        if (gForce>30){
            game.setScreen(new menu(game,idtemp,usernametemp,leveltemp,1));
        }
        Gdx.gl.glClearColor(0, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(game.camera.combined);
        game.batch.begin();
        game.batch.draw(game.bg, 0, 0);
        sprite.draw(game.batch);
        spriteexit.draw(game.batch);
        game.batch.end();

        if (Gdx.input.justTouched()){
            Vector3 tmp = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.camera.unproject(tmp);
            Rectangle rec = new Rectangle(tmp.x-10, tmp.y-10, 20, 20);
            if(sprite.getBoundingRectangle().overlaps(rec)){
                game.sound.play(2.0f);
                game.setScreen(new Level(game,idtemp,usernametemp,leveltemp,0));
            }
        }
        if (Gdx.input.justTouched()){
            Vector3 tmp = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.camera.unproject(tmp);
            Rectangle rec = new Rectangle(tmp.x-10, tmp.y-10, 20, 20);
            if(spriteexit.getBoundingRectangle().overlaps(rec)){
                game.sound.play(2.0f);
                System.exit(1);
            }
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
