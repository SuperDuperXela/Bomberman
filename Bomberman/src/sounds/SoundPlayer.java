package sounds;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundPlayer {

    private SoundPlayer() {

    }

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
	new Thread(() -> {
	    try {
		Clip clip = AudioSystem.getClip();
		AudioInputStream inputStream = AudioSystem
			.getAudioInputStream(new File("media/sounds/" + url + ".wav"));
		clip.open(inputStream);
		clip.start();
	    } catch (Exception e) {
		e.printStackTrace();
	    }

	}).start();
    }
}