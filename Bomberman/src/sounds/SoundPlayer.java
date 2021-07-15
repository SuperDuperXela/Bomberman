package sounds;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import gamemodel.GameLogic;

public class SoundPlayer {
    
    private GameLogic gameLogic;

    public SoundPlayer(GameLogic gameLogic) {
	this.gameLogic = gameLogic;
    }

    public void playDeathSound() {
	playSound("death");
    }

    public void playBombDropSound() {
	playSound("bombdrop");
    }

    public void playExplosionSound() {
	playSound("explosion");
    }

    public void playPickupSound() {
	playSound("pickup");
    }

    public void playStartSound() {
	playSound("start");
    }

    private void playSound(String url) {
	new Thread(() -> {
	    try {
		Clip clip = AudioSystem.getClip();
		clip.open(gameLogic.getSounds().get(url));
		clip.start();
	    } catch (Exception e) {
		e.printStackTrace();
	    }

	}).start();
    }
}