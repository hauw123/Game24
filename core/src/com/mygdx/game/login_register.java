package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class login_register implements Screen {
    final Game24 game;
    Texture texturelogin,textureregis,textureexit;
    Sprite spritelogin,spriteregis,spriteexit;
    int tempscreen;



    public login_register(Game24 game, int countscreen) {
        this.game = game;
        tempscreen = countscreen;
        this.game.music.play();
        texturelogin = new Texture("buttonlogin.png");
        textureregis = new Texture("buttonregister.png");
        textureexit = new Texture("buttonexit.png");

        spritelogin = new Sprite(texturelogin);
        spriteregis = new Sprite(textureregis);
        spriteexit = new Sprite(textureexit);

        spritelogin.setPosition(165,1000);
        spritelogin.setSize(750,250);

        spriteregis.setPosition(165,650);
        spriteregis.setSize(750,250);

        spriteexit.setPosition(165,300);
        spriteexit.setSize(750,250);


    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if (tempscreen == 0){
            game.setScreen(new login_register(game,1));
        }
        Gdx.gl.glClearColor(0, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(game.camera.combined);
        game.batch.begin();
        game.batch.draw(game.bg, 0, 0);
        game.fontmenu.draw(game.batch,"Game 24",190,1600);
        spritelogin.draw(game.batch);
        spriteregis.draw(game.batch);
        spriteexit.draw(game.batch);

        game.batch.end();

        if (Gdx.input.justTouched()){
            Vector3 tmp = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.camera.unproject(tmp);
            Rectangle rec = new Rectangle(tmp.x-10, tmp.y-10, 20, 20);
            if(spritelogin.getBoundingRectangle().overlaps(rec)){
                game.sound.play(2.0f);

                game.setScreen(new LoginScreen(game));
            }
        }
        if (Gdx.input.justTouched()){
            Vector3 tmp = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.camera.unproject(tmp);
            Rectangle rec = new Rectangle(tmp.x-10, tmp.y-10, 20, 20);
            if(spriteregis.getBoundingRectangle().overlaps(rec)){
                game.sound.play(2.0f);
                game.setScreen(new RegisScreen(game));
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
