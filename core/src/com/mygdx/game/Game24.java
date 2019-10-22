package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Game24 extends Game {
	SpriteBatch batch;
	Texture bg;
	Camera camera;
	Viewport viewport;
	FreeTypeFontGenerator generator;
	FreeTypeFontGenerator.FreeTypeFontParameter parameter,parameterangka,parametermenu;
	BitmapFont font,fontangka,fontmenu;
	Music music;
	Sound sound;
	public NotificationHandler notificationHandler;

	@Override
	public void create () {
		batch = new SpriteBatch();
		bg = new Texture("menu.jpg");

		camera = new OrthographicCamera();
		viewport = new StretchViewport(bg.getWidth(), bg.getHeight(), camera);
		viewport.apply();
		camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0);





		generator = new FreeTypeFontGenerator(Gdx.files.internal("font/Lumberjack-Regular.ttf"));

		parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.color = Color.WHITE;
		parameter.size = 70;

		parameterangka = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameterangka.color = Color.WHITE;
		parameterangka.size = 140;

		parametermenu = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parametermenu.color = Color.WHITE;
		parametermenu.size = 140;

		font = new BitmapFont();
		font = generator.generateFont(parameter);
		fontangka = generator.generateFont(parameterangka);
		fontmenu = generator.generateFont(parameterangka);

		generator.dispose();

		sound = Gdx.audio.newSound(Gdx.files.internal("sound/klik.mp3"));
		music = Gdx.audio.newMusic(Gdx.files.internal("sound/music.mp3"));
		music.setLooping(true);
		this.setScreen(new login_register(this,1));
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		batch.dispose();
		bg.dispose();
	}

	@Override
	public void resize(int x, int y){
		viewport.update(x, y);
	}

	public void setNotificationHandler(NotificationHandler handler) {
		this.notificationHandler = handler;
	}

	public void ShowWin(){
		notificationHandler.showNotification("Notification", "The Chosen One");
	}

}
