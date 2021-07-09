package sounds;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundPlayer {
	
	public static void playDeathSound() {
			playSound("death");
	}
	
	public static void playBombDropSound() {
		playSound("bombdrop");
	}
	
	public static void playExplosionSound() {
		playSound("explosion");
	}
	
	public static void playPickupSound() {
		playSound("pickup");
	}
	
	public static void playStartSound() {
		playSound("start");
	}
	
	private static void playSound(String url) {
		new Thread(new Runnable() {
			  // The wrapper thread is unnecessary, unless it blocks on the
			  // Clip finishing; see comments.
			    public void run() {
			      try {
			        Clip clip = AudioSystem.getClip();
			        AudioInputStream inputStream = AudioSystem.getAudioInputStream(
			          SoundPlayer.class.getResourceAsStream("/media/" + url + ".wav"));
			        clip.open(inputStream);
			        clip.start(); 
			      } catch (Exception e) {
			        System.err.println(e.getMessage());
			      }
			    }
			  }).start();
	}
}