package com.core;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class AudioPlayer {

	private float volume = 0.3f; //mim 0.1 max 1.0
//	private boolean songMute, effectMute;

	public static String clickSound = ".//audio//PLEEG.wav";
	public static String attackSong = ".//audio//attack3.wav";
	public static String attackSong2 = ".//audio//attack2.wav";
	public static String battleMusic = ".//audio//TheLoomingBattle.wav";
	public static String audioDamage = ".//audio//impact.6.wav";

	public Clip clip;

	Clip atack;
	Clip music;
	Clip levelUp;
	
	public AudioPlayer() {

//		setFile(battleMusic);
	}

	public void setFile(String soundFileName) {

		try {
			File file = new File(soundFileName);
			AudioInputStream sound = AudioSystem.getAudioInputStream(file);
			clip = AudioSystem.getClip(); // Returns the available clip object
			clip.open(sound);
			setVolume(volume);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public Clip getFile() { return clip; }

	public void play() {
		
//		new AudioPlayer().setFile();
		clip.setFramePosition(0);
		clip.start();
		
	}
	
	public void play( String soundFileName ) {
		setFile(soundFileName);
		clip.setFramePosition(0);
		clip.start();
	}
	
	public void stop() {
		clip.stop();
	}
	
	public boolean isRunning() {
		return clip.isRunning();
	}
	
	public void setVolume(float volume) {
	    if (volume < 0.0f || volume > 1.0f)
	        throw new IllegalArgumentException("Volume not valid: " + volume);
	    FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);        
	    gainControl.setValue(20f * (float) Math.log10(volume));
	}

}

















