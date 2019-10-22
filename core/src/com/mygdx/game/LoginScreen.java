package com.mygdx.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;

import java.awt.Font;

public class LoginScreen implements Screen{
    final Game24 game;
    private Stage stage;
    public TextField txtUser;
    public TextField txtPass;
    private SpriteBatch batch;
    private BitmapFont font;
    FreeTypeFontGenerator generator;
    FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();

    user user1;
    Net.HttpRequest request;
    int id;String username2;int level;
    Net.HttpResponseListener response;
    Json json;
    String jsonString;
    int countscreen;

    public LoginScreen(Game24 game) {
        Net.HttpRequest request;
        user1 = new user();
        this.game = game;

        stage = new Stage(game.viewport);
        Gdx.input.setInputProcessor(stage);

        batch = new SpriteBatch();
        generator = new FreeTypeFontGenerator(Gdx.files.internal("font/Lumberjack-Regular.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.color = Color.WHITE;
        parameter.size = 230;
        font = new BitmapFont();
        font = generator.generateFont(parameter);
        style.font = font;

        Skin skin = new Skin(Gdx.files.internal("font/uiskin.json"));
        TextButton btnLogin = new TextButton("Enter",skin);
        TextButton btnBack = new TextButton("Back",skin);

        TextField.TextFieldStyle styletext = skin.get(TextField.TextFieldStyle.class);



        btnLogin.setPosition(200, 400);
        btnLogin.setSize(750, 150);


        btnBack.setPosition(200, 100);
        btnBack.setSize(750, 150);

        btnLogin.addListener(new ClickListener() {
            @Override
            public void touchUp (InputEvent e,float x, float y, int point, int button){
                btnLoginClicked();
            }
        });

        btnBack.addListener(new ClickListener() {
            @Override
            public void touchUp (InputEvent e,float x, float y, int point, int button){
                btnBackClicked();
            }
        });

        styletext.font.getData().setScale(3f);
        txtUser = new TextField("",styletext);

        txtUser.setPosition(200, 1000);
        txtUser.setSize(750,250);
        txtUser.setAlignment(Align.center);
        stage.addActor(txtUser);

        txtPass = new TextField("",styletext);
        txtPass.setAlignment(Align.center);
        txtPass.setPosition(200,700);
        txtPass.setSize(750,250);

        stage.addActor(txtPass);

        stage.addActor(btnLogin);
        stage.addActor(btnBack);
    }

    public void btnLoginClicked(){
        System.out.println(txtUser.getText());
        System.out.println(txtPass.getText());
        user1.username = txtUser.getText();
        user1.password = txtPass.getText();

        json = new Json();
        request = new Net.HttpRequest(Net.HttpMethods.POST);

        response = new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                String responseJson = httpResponse.getResultAsString();
                JsonValue temp = new JsonReader().parse(responseJson);
                username2 = temp.getString("username");
                id = Integer.parseInt(temp.getString("id"));
                level = Integer.parseInt(temp.getString("level"));
                stage.dispose();
                game.setScreen(new menu(game,id,username2,level,0));
                System.out.println("Test123");
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
        jsonString = json.toJson(user1);
        request.setUrl("localhost:8000/public/login");//Ganti sesuai ip address lewat cmd
        request.setContent(jsonString);
        request.setHeader("Content-Type","application/json");

        Gdx.net.sendHttpRequest(request,response);
    }
    public void btnBackClicked(){
        stage.dispose();
        game.setScreen(new login_register(game,0));
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
        font.draw(game.batch,"LOGIN",100,1600);
        game.batch.end();

        stage.act(delta);
        stage.draw();


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
