package com.mygdx.game;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.mygdx.game.Game24;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		AdapterAndroid adapterAndroid = new AdapterAndroid(this);
		Game24 game = new Game24();


		game.setNotificationHandler(adapterAndroid);

		initialize(game, config);
//		initialize(new Game24(), config);
	}
}
