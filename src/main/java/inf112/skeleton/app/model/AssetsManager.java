package inf112.skeleton.app.model;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

import java.util.HashMap;
import java.util.Map;


// maybe just make a play/ pause/ stop/ resume for every single one ;__;
public class AssetsManager implements IAssetsManager {
    private Map<String, String> musicMap;
    private Map<String, String> soundEffectMap;
    private Music nowPlaying;
    public AssetsManager() {
        musicMap = new HashMap<>();
        soundEffectMap = new HashMap<>();

        // ADD MUSIC AND SOUNDS
        addMusic("MAIN", "assets/MiiPlaza.mp3");
        addMusic("BACKGROUND", "assets/shop.mp3");
        addMusic("DEAD","assets/dead.mp3");
    }

    private void addMusic(String key, String filePath) {
        musicMap.put(key, filePath);
    }

    private void addSoundEffect(String key, String filePath) {
        soundEffectMap.put(key, filePath);
    }

    @Override
    public void playMusic(String key) {
        nowPlaying = Gdx.audio.newMusic(Gdx.files.internal(musicMap.get(key)));
        if (nowPlaying != null && !nowPlaying.isPlaying()) {
            nowPlaying.setVolume(0.5f);
            nowPlaying.setLooping(true);
            nowPlaying.play();
        }
    }

    @Override
    public void stopMusic() {
        this.nowPlaying.stop();
    }

    @Override
    public void pauseMusic() {
        if (nowPlaying != null && nowPlaying.isPlaying()) {
            nowPlaying.pause();
        }
    }

    @Override
    public void resumeMusic() {
        if (nowPlaying != null && !nowPlaying.isPlaying()) {
            nowPlaying.play();
        }
    }

    @Override
    public void playSoundEffect(String key) {

    }
}