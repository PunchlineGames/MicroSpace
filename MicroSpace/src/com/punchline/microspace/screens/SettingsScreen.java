package com.punchline.microspace.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.punchline.javalib.BaseGame;
import com.punchline.javalib.states.screens.MenuScreen;
import com.punchline.javalib.utils.SoundManager;

public class SettingsScreen extends MenuScreen {

	private CheckBox sounds;
	private CheckBox music;
	
	public SettingsScreen(BaseGame game) {
		super(game, Gdx.files.internal("data/Skin/uiskin.json"), "Settings", null);
	}

	@Override
	protected void initialize() {
		super.initialize();
		
		sounds = new CheckBox(" Sound Effects", skin);
		music = new CheckBox(" Music ", skin);
		
		ClickListener listener = new ClickListener() {
			
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Preferences pref = Gdx.app.getPreferences("settings");
				
				float soundVol = pref.getBoolean("Sound", true) ? 1f : 0f;
				float musicVol = pref.getBoolean("Music", true) ? 1f : 0f;
				
				SoundManager.setSoundVolume(soundVol);
				SoundManager.setMusicVolume(musicVol);
			}	
			
		};
		
		sounds.addListener(listener);
		music.addListener(listener);
		
		TextButton backButton = new TextButton("Back", skin);
		backButton.addListener(new ClickListener() {

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				game.setScreen(new MainMenuScreen(game));
			}	
			
		});
		
		window.add(sounds);
		window.row();
		window.add(music);
		window.row();
		window.add(backButton);
	}

	@Override
	public void show() {
		readSettings();
		
		super.show();
	}

	@Override
	public void hide() {
		writeSettings();
		
		super.hide();
	}
	
	private void readSettings() {
		
		Preferences prefs = Gdx.app.getPreferences("settings");
		
		boolean soundVol = prefs.getBoolean("Sound", true);
		boolean musicVol = prefs.getBoolean("Music", true);
		
		sounds.setChecked(soundVol); 
		music.setChecked(musicVol);
		
	}
	
	private void writeSettings() {
		
		Preferences prefs = Gdx.app.getPreferences("settings");
		
		prefs.putBoolean("Sound", sounds.isChecked());
		prefs.putBoolean("Music", music.isChecked());
		
		prefs.flush();
		
	}
	
}