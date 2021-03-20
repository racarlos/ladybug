package application;
import javafx.scene.media.AudioClip;
public class MusicClass {
	private AudioClip soundSelect;
	private AudioClip wakawa;
	
	public final static AudioClip bgMusic=  new AudioClip("file:src/application/Opening.mp3");
	
	MusicClass(){
		this.soundSelect = new AudioClip(this.getClass().getResource("Hakdog.wav").toString());
		this.wakawa = new AudioClip(this.getClass().getResource("wakawaka.mp3").toString());
		MusicClass.bgMusic.setCycleCount(AudioClip.INDEFINITE);
	}
	
	void playAudio() {
		if(MusicClass.bgMusic.isPlaying()) {
			MusicClass.bgMusic.stop();
			MusicClass.bgMusic.play();
		}else {
			MusicClass.bgMusic.play();
		}
	}
	
void SelectorSound(){
		if(this.soundSelect.isPlaying()) {
			this.soundSelect.stop();
			this.soundSelect.play();
		}else {
			this.soundSelect.play();
		}
	}

void wakawakad(){
	if(this.wakawa.isPlaying()) {
		this.wakawa.stop();
		this.wakawa.play();
	}else {
		this.wakawa.play();
	}
}
}

