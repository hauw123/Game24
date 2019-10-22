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
import com.badlogic.gdx.utils.JsonWriter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class inGame implements Screen {

    final Game24 game;
    int countmove=0;//Cek banyak move
    int coba=3;//Coba angka tanpa database
    int count=0;//Count baris kotak di layar
    int cek1;//cek button yang telah di click
    int array[] = new int[4];//array menyimpan angka,index button yangg di click
    int arraycount =0;//count untuk array
    float posisigambarx,posisigambary;//redraw sprite dengan posisi temporary
    int hapus[] = new int[3];//untuk hapus sprite yang sudah di operator
    int temphasil;//menyimpan hasil untuk di cek
    int cek2=0;//Cek operator terhadap button yang di click

    int counthapus=0;
    List<gamebutton> buttongame; //<.....class.....>
    gamebutton button1;
    List<operatorbutton> operatorgame;
    operatorbutton Operator1;//Operator +
    operatorbutton Operator2;//Operator -
    operatorbutton Operator3;//Operator :
    operatorbutton Operator4;//Operator x

    Texture texture;
    Sprite sprite;
    int hasil ;
    float x,y,x1,y1;
    float w,h;
    float tempx,tempy;
    int arraysoal[] = new int[4];
    int idtemp;
    int idgametemp;
    int leveltemp;
    String usernametemp;
    int screentemp;

    Net.HttpRequest request;
    Net.HttpResponseListener response;
    Json json;
    String jsonString;

    Net.HttpRequest request1;
    Net.HttpResponseListener response1;
    Json json1;
    String jsonString1;

    public inGame(Game24 game,int idgame,int soal1,int soal2,int soal3,int soal4,int id, int level, String username, int countscreen) {
        this.game = game;
        screentemp = countscreen;
        hapus[0] = -1;
        hapus[1] = -1;
        hapus[2] = -1;
        x=100;
        y=1200;
        w=400;
        h=400;
        y=1200;
        x1=260;
        y1=1455;

        texture = new Texture("back.png");
        sprite = new Sprite(texture);

        arraysoal[0] = soal1;
        arraysoal[1] = soal2;
        arraysoal[2] = soal3;
        arraysoal[3] = soal4;
        idtemp = id;
        leveltemp = level;
        idgametemp= idgame;
        usernametemp = username;
        sprite.setPosition(20,1650);
        sprite.setSize(200,200);

        buttongame = new ArrayList<gamebutton>();

        for (int i = 0 ; i < 4 ; i++){
            if (count<2) {
                button1 = new gamebutton();
                button1.setGambar("buttongame.png");
                button1.setpost(x, y);
                button1.setukuran(w, h);
                button1.setAngka(arraysoal[i]);
                button1.setX1(x1);
                button1.setY1(y1);
                buttongame.add(button1);
                coba += 2;
                count++;
                x+=500;
                x1+=500;

            }
            else{
                count=0;
                y-=500;
                x=100;
                x1=260;
                y1-=500;
                i-=1;
            }
        }

        Operator1 = new operatorbutton();
        Operator2 = new operatorbutton();
        Operator3 = new operatorbutton();
        Operator4 = new operatorbutton();

        Operator1.setGambar("tambahputih.png");
        Operator2.setGambar("minusputih.png");
        Operator3.setGambar("bagiputih.png");
        Operator4.setGambar("kaliputih.png");

        Operator1.setpost(0,100);
        Operator1.setukuran(250,250);

        Operator2.setpost(280,100);
        Operator2.setukuran(250,250);

        Operator3.setpost(560,100);
        Operator3.setukuran(250,250);

        Operator4.setpost(840,100);
        Operator4.setukuran(250,250);

    }

    public void tambahlevel(int id,int level){
        json = new Json();
        request = new Net.HttpRequest(Net.HttpMethods.PUT);
        response = new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                System.out.println("AAAAA");
            }

            @Override
            public void failed(Throwable t) {
                System.out.println(t);
            }

            @Override
            public void cancelled() {

            }
        };
        json.setOutputType(JsonWriter.OutputType.json);
        classlevel levelpass = new classlevel();
        levelpass.level = level;
        jsonString = json.toJson(levelpass);

        request.setUrl("localhost:8000/public/user/"+id);//Ganti sesuai ip address lewat cmd
        request.setContent(jsonString);
        request.setHeader("Content-Type","application/json");
        Gdx.net.sendHttpRequest(request,response);
    }

    public void cekrank(final int levelrank){
        System.out.println("Test pertama");
        json1 = new Json();
        request1 = new Net.HttpRequest(Net.HttpMethods.GET);
        response1 = new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                String responseJson = httpResponse.getResultAsString();
                JsonValue temp = new JsonReader().parse(responseJson);
                if (levelrank > Integer.parseInt(temp.getString("MAX(level)"))){
                    game.ShowWin();
                    System.out.println("Test123");
                }

            }

            @Override
            public void failed(Throwable t) {
                System.out.println(t);
            }

            @Override
            public void cancelled() {

            }
        };
        request1.setUrl("localhost:8000/public/ranking");//Ganti sesuai ip address lewat cmd
        Gdx.net.sendHttpRequest(request1,response1);
    }



    @Override
    public void show() {
        
    }

    @Override
    public void render(float delta) {

        if (screentemp==0){
            game.setScreen(new inGame(game,idgametemp,arraysoal[0],arraysoal[1],arraysoal[2],arraysoal[3],idtemp, leveltemp, usernametemp,1));
        }
        float xGrav = Gdx.input.getAccelerometerX();
        float yGrav = Gdx.input.getAccelerometerY();
        float zGrav = Gdx.input.getAccelerometerZ();

        float gForce = (float) Math.sqrt((xGrav * xGrav)+(yGrav*yGrav)+(zGrav*zGrav));

        if (gForce>30){
            game.setScreen(new inGame(game,idgametemp,arraysoal[0],arraysoal[1],arraysoal[2],arraysoal[3],idtemp, leveltemp, usernametemp,1));
        }
        Gdx.gl.glClearColor(0, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(game.camera.combined);
        game.batch.begin();
        game.batch.draw(game.bg, 0, 0);

        for (int i = 0; i < 4; i++) {

            if (i != hapus[0] && i != hapus[1] && i != hapus[2] ){
                buttongame.get(i).getSprite().draw(game.batch);
                game.fontangka.draw(game.batch, String.valueOf(buttongame.get(i).getAngka()), buttongame.get(i).getX1(), buttongame.get(i).getY1());
            }


        }


        Operator1.getSprite().draw(game.batch);
        Operator2.getSprite().draw(game.batch);
        Operator3.getSprite().draw(game.batch);
        Operator4.getSprite().draw(game.batch);
        sprite.draw(game.batch);
        game.batch.end();
// Back
        if (Gdx.input.justTouched()){
            Vector3 tmp = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.camera.unproject(tmp);
            Rectangle rec = new Rectangle(tmp.x-10, tmp.y-10, 20, 20);
            if(sprite.getBoundingRectangle().overlaps(rec)){
                game.sound.play(2.0f);
                game.setScreen(new Level(game,idtemp,usernametemp,leveltemp,0));
            }
        }

        if (Gdx.input.justTouched()) {
            Vector3 tmp = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.camera.unproject(tmp);
            Rectangle rec = new Rectangle(tmp.x - 10, tmp.y - 10, 20, 20);
//            if(Operator1.getSprite().getBoundingRectangle().overlaps(rec)){
//                game.setScreen(new Level(game));
//            }
            for (int i = 0; i < 4; i++) {
                if (buttongame.get(i).getSprite().getBoundingRectangle().overlaps(rec)) {
                    game.sound.play(2.0f);
                    if (cek1 < 2) {
                        System.out.println(buttongame.get(i).getStatus());

                        if (buttongame.get(i).getStatus() == 0) {
                            posisigambarx = buttongame.get(i).getX();
                            posisigambary = buttongame.get(i).getY();
                            buttongame.get(i).setGambar("buttongame2.png");
                            buttongame.get(i).setpost(posisigambarx, posisigambary);
                            buttongame.get(i).setukuran(400, 400);

                            game.batch.begin();
                            buttongame.get(i).getSprite().draw(game.batch);
                            game.batch.end();
                            cek1++;
                            cek2++;
                            buttongame.get(i).setStatus(1);
                            array[arraycount] = buttongame.get(i).getAngka();
                            arraycount++;
                            array[arraycount] = i;
                            arraycount++;
                            System.out.println(buttongame.get(i).getStatus());
                        } else if (buttongame.get(i).getStatus() == 1) {
                            posisigambarx = buttongame.get(i).getX();
                            posisigambary = buttongame.get(i).getY();
                            buttongame.get(i).setGambar("buttongame.png");
                            buttongame.get(i).setpost(posisigambarx, posisigambary);
                            buttongame.get(i).setukuran(400, 400);
                            arraycount -= 2;
                            game.batch.begin();
                            buttongame.get(i).getSprite().draw(game.batch);
                            game.batch.end();
                            cek1--;
                            cek2--;
                            buttongame.get(i).setStatus(0);
                            System.out.println(buttongame.get(i).getStatus());
                        }
                    } else {
                        if (buttongame.get(i).getStatus() == 1) {
                            posisigambarx = buttongame.get(i).getX();
                            posisigambary = buttongame.get(i).getY();
                            buttongame.get(i).setGambar("buttongame.png");
                            buttongame.get(i).setpost(posisigambarx, posisigambary);
                            buttongame.get(i).setukuran(400, 400);

                            game.batch.begin();
                            buttongame.get(i).getSprite().draw(game.batch);
                            game.batch.end();
                            cek1--;
                            cek2--;
                            buttongame.get(i).setStatus(0);
                            System.out.println(buttongame.get(i).getStatus());
                            arraycount -= 2;
                        }
                    }
                }
            }
        }//Tutup click button game

    if (cek2==2) {
        if (Gdx.input.justTouched()) {
            Vector3 tmp = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.camera.unproject(tmp);
            Rectangle rec = new Rectangle(tmp.x - 10, tmp.y - 10, 20, 20);
            if (Operator1.getSprite().getBoundingRectangle().overlaps(rec)) {
                game.sound.play(2.0f);

                hasil = array[0] + array[2];
                System.out.println(hasil);
                hapus[counthapus] = array[1];
                counthapus++;
                arraycount = 0;
                cek1 = 0;
                for (int i = 0; i < 4; i++) {
                    buttongame.get(i).setStatus(0);
                }
                array[0] = hasil;

                tempx = buttongame.get(array[3]).getX();
                tempy = buttongame.get(array[3]).getY();

                buttongame.get(array[3]).setGambar("buttongame.png");
                buttongame.get(array[3]).setpost(tempx, tempy);
                buttongame.get(array[3]).setukuran(400, 400);
                buttongame.get(array[3]).setAngka(array[0]);
                countmove++;
                if (countmove == 3) {
                    temphasil = hasil;
                    if (temphasil == 24) {

                        if (idgametemp<leveltemp){System.out.println("Win");
                            game.setScreen(new Level(game,idtemp,usernametemp,leveltemp,0));

                        }
                        else{
                        System.out.println("Win");
                        System.out.println(leveltemp+1);
                            cekrank(leveltemp+1);
                        tambahlevel(idtemp,leveltemp+1);


                            game.setScreen(new Level(game,idtemp,usernametemp,leveltemp+1,0));

                        }
                    } else {Gdx.input.vibrate(1000);
                        System.out.println("Retry");
                    }
                }
                hasil = 0;
                cek2=0;
            }

        }

        if (Gdx.input.justTouched()) {
            Vector3 tmp = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.camera.unproject(tmp);
            Rectangle rec = new Rectangle(tmp.x - 10, tmp.y - 10, 20, 20);
            if (Operator2.getSprite().getBoundingRectangle().overlaps(rec)) {
                game.sound.play(2.0f);

                hasil = array[0] - array[2];
                System.out.println(hasil);
                hapus[counthapus] = array[1];
                counthapus++;
                arraycount = 0;
                cek1 = 0;
                for (int i = 0; i < 4; i++) {
                    buttongame.get(i).setStatus(0);
                }
                array[0] = hasil;

                tempx = buttongame.get(array[3]).getX();
                tempy = buttongame.get(array[3]).getY();

                buttongame.get(array[3]).setGambar("buttongame.png");
                buttongame.get(array[3]).setpost(tempx, tempy);
                buttongame.get(array[3]).setukuran(400, 400);
                buttongame.get(array[3]).setAngka(array[0]);
                countmove++;
                if (countmove == 3) {
                    temphasil = hasil;
                    if (temphasil == 24) {
                        if (idgametemp<leveltemp){System.out.println("Win");

                            game.setScreen(new Level(game,idtemp,usernametemp,leveltemp,0));

                        }
                        else{
                            System.out.println("Win");
                            System.out.println(leveltemp+1);
                            cekrank(leveltemp+1);
                            tambahlevel(idtemp,leveltemp+1);

                            game.setScreen(new Level(game,idtemp,usernametemp,leveltemp+1,0));

                        }
                    } else {Gdx.input.vibrate(1000);
                        System.out.println("Retry");
                    }
                }
                hasil = 0;
                cek2=0;
            }

        }

        if (Gdx.input.justTouched()) {
            Vector3 tmp = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.camera.unproject(tmp);
            Rectangle rec = new Rectangle(tmp.x - 10, tmp.y - 10, 20, 20);
            if (Operator3.getSprite().getBoundingRectangle().overlaps(rec)) {
                game.sound.play(2.0f);

                hasil = array[0] / array[2];
                System.out.println(hasil);
                hapus[counthapus] = array[1];
                counthapus++;
                arraycount = 0;
                cek1 = 0;
                for (int i = 0; i < 4; i++) {
                    buttongame.get(i).setStatus(0);
                }
                array[0] = hasil;

                tempx = buttongame.get(array[3]).getX();
                tempy = buttongame.get(array[3]).getY();

                buttongame.get(array[3]).setGambar("buttongame.png");
                buttongame.get(array[3]).setpost(tempx, tempy);
                buttongame.get(array[3]).setukuran(400, 400);
                buttongame.get(array[3]).setAngka(array[0]);
                countmove++;
                if (countmove == 3) {
                    temphasil = hasil;
                    if (temphasil == 24) {
                        if (idgametemp<leveltemp){System.out.println("Win");

                            game.setScreen(new Level(game,idtemp,usernametemp,leveltemp,0));

                        }
                        else{
                            System.out.println("Win");
                            System.out.println(leveltemp+1);
                            cekrank(leveltemp+1);
                            tambahlevel(idtemp,leveltemp+1);

                            game.setScreen(new Level(game,idtemp,usernametemp,leveltemp+1,0));

                        }
                    } else {Gdx.input.vibrate(1000);
                        System.out.println("Retry");
                    }
                }
                hasil = 0;
                cek2=0;
            }

        }

        if (Gdx.input.justTouched()) {
            Vector3 tmp = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.camera.unproject(tmp);
            Rectangle rec = new Rectangle(tmp.x - 10, tmp.y - 10, 20, 20);
            if (Operator4.getSprite().getBoundingRectangle().overlaps(rec)) {
                game.sound.play(2.0f);

                hasil = array[0] * array[2];
                System.out.println(hasil);
                hapus[counthapus] = array[1];
                counthapus++;
                arraycount = 0;
                cek1 = 0;
                for (int i = 0; i < 4; i++) {
                    buttongame.get(i).setStatus(0);
                }
                array[0] = hasil;

                tempx = buttongame.get(array[3]).getX();
                tempy = buttongame.get(array[3]).getY();

                buttongame.get(array[3]).setGambar("buttongame.png");
                buttongame.get(array[3]).setpost(tempx, tempy);
                buttongame.get(array[3]).setukuran(400, 400);
                buttongame.get(array[3]).setAngka(array[0]);
                countmove++;
                if (countmove == 3) {
                    temphasil = hasil;
                    if (temphasil == 24) {
                        if (idgametemp<leveltemp){System.out.println("Win");

                            game.setScreen(new Level(game,idtemp,usernametemp,leveltemp,0));

                        }
                        else{
                            System.out.println("Win");
                            System.out.println(leveltemp+1);
                            cekrank(leveltemp+1);
                            tambahlevel(idtemp,leveltemp+1);

                            game.setScreen(new Level(game,idtemp,usernametemp,leveltemp+1,0));

                        }
                    } else {
                        Gdx.input.vibrate(1000);
                        System.out.println("Retry");
                    }
                }
                hasil = 0;
                cek2=0;
            }

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
