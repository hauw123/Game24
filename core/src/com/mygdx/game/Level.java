package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import java.awt.Button;
import java.util.ArrayList;
import java.util.List;

public class Level implements Screen {
    List<levelbutton> buttonlevel;//list class levelbutton
    int levelloop;
    float x,y,x1,y1;//posisi kotak level dan posisi font
    float w,h;// ukuran kotak level
    final Game24 game;
    int count;//count untuk baris level di latar
    Texture texture;
    Sprite spriteback;
    Texture texturenext;
    Sprite spritenext;
    levelbutton buttonA;
    int leveltemp,idtemp;//Simpan level dan id dari user
    String usernametemp;//Simpan username user
    int tambah;//Untuk level


    int id,soal1,soal2,soal3,soal4;
    Net.HttpRequest request;
    Net.HttpResponseListener response;
    Json json;
    String jsonString;

    public Level(Game24 game,int id,String username,int level,int tambah1) {
        this.game = game;
        tambah=tambah1;
        leveltemp = level;
        idtemp = id;
        usernametemp = username;
        texture = new Texture("back.png");
        spriteback = new Sprite(texture);
        spriteback.setPosition(50,50);
        spriteback.setSize(200,200);
        texturenext = new Texture("next.png");
        spritenext = new Sprite(texturenext);
        spritenext.setPosition(900,50);
        spritenext.setSize(200,200);
        buttonlevel = new ArrayList<levelbutton>();

        x = 75;
        y = 700;
        w = 200;
        h = 200;
        x1 =165;
        y1 = 825;
        levelloop = 1;
        count = 0;

        setlevel();


    }

    public void setlevel(){
        for(int i = 0+tambah;i<8+tambah;i++) {
            if (count < 4) {
                buttonA = new levelbutton();
                if (i+1>leveltemp){

                    buttonA.setGambar("buttonlevel2.png");
                    buttonA.setLevel(levelloop+tambah);
                    buttonA.setPosition(x, y);
                    buttonA.setukuran(w, h);
                    buttonA.setPositionText(x, y);
                    buttonA.setLevelangka(levelloop+tambah);
                    buttonA.setX1(x1);
                    buttonA.setY1(y1);
                    buttonA.setStatus(0);
                    buttonlevel.add(buttonA);
                }
                else {

                    buttonA.setGambar("buttonlevel.png");
                    buttonA.setLevel(levelloop+tambah);
                    buttonA.setPosition(x, y);
                    buttonA.setukuran(w, h);
                    buttonA.setPositionText(x, y);
                    buttonA.setLevelangka(levelloop+tambah);
                    buttonA.setX1(x1);
                    buttonA.setY1(y1);
                    buttonA.setStatus(1);
                    buttonlevel.add(buttonA);
                }
                levelloop++;
                x += 250;
                x1 +=250;
                count++;

            }
            else{
                y-= 325;
                x = 75;
                x1 =165;
                y1 -= 325;
                count =0;
                i-=1;
            }
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(game.camera.combined);

        game.batch.begin();
        game.batch.draw(game.bg, 0, 0);
        for (int i = 0 ; i < buttonlevel.size();i++){

            buttonlevel.get(i).getSprite().draw(game.batch);
            game.font.draw(game.batch,String.valueOf(buttonlevel.get(i).getLevelangka()),buttonlevel.get(i).getX1(),buttonlevel.get(i).getY1());

        }
        spritenext.draw(game.batch);
        spriteback.draw(game.batch);
        game.batch.end();

        if (Gdx.input.justTouched()){
            Vector3 tmp = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.camera.unproject(tmp);
            Rectangle rec = new Rectangle(tmp.x-10, tmp.y-10, 20, 20);
        for(int i = 0 ; i < buttonlevel.size();i++) {
        if (buttonlevel.get(i).getSprite().getBoundingRectangle().overlaps(rec)) {
            game.sound.play(2.0f);
            if (buttonlevel.get(i).getStatus()==1) {


                json = new Json();
                request = new Net.HttpRequest(Net.HttpMethods.GET);
                response = new Net.HttpResponseListener() {
                    @Override
                    public void handleHttpResponse(Net.HttpResponse httpResponse) {
                        String responseJson = httpResponse.getResultAsString();
                        JsonValue temp = new JsonReader().parse(responseJson);
                        id = Integer.parseInt(temp.getString("id"));
                        soal1 = Integer.parseInt(temp.getString("soal1"));
                        soal2 = Integer.parseInt(temp.getString("soal2"));
                        soal3 = Integer.parseInt(temp.getString("soal3"));
                        soal4 = Integer.parseInt(temp.getString("soal4"));

                        game.setScreen(new inGame(game,id,soal1,soal2,soal3,soal4,idtemp,leveltemp,usernametemp,0));
                    }

                    @Override
                    public void failed(Throwable t) {
                        System.out.println(t);
                    }

                    @Override
                    public void cancelled() {

                    }
                };
                request.setUrl("localhost:8000/public/soal/"+buttonlevel.get(i).getLevelangka());//Ganti sesuai ip address lewat cmd
                Gdx.net.sendHttpRequest(request,response);


            }
    }
}
        }

        if (Gdx.input.justTouched()){
            Vector3 tmp = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.camera.unproject(tmp);
            Rectangle rec = new Rectangle(tmp.x-10, tmp.y-10, 20, 20);

            if(spriteback.getBoundingRectangle().overlaps(rec)){

                if (tambah > 0) {
                    game.sound.play(2.0f);

                    game.setScreen(new Level(game,idtemp,usernametemp,leveltemp,tambah-8));
                }
                else {
                    game.sound.play(2.0f);

                    game.setScreen(new menu(game, idtemp, usernametemp, leveltemp,1));
                }
            }
        }

        if (Gdx.input.justTouched()){
            Vector3 tmp = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.camera.unproject(tmp);
            Rectangle rec = new Rectangle(tmp.x-10, tmp.y-10, 20, 20);

            if(spritenext.getBoundingRectangle().overlaps(rec)){
                game.sound.play(2.0f);

                game.setScreen(new Level(game,idtemp,usernametemp,leveltemp,tambah+8));
                System.out.println("AAAA");
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
