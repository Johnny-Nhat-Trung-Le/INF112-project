package inf112.skeleton.app.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

import java.util.HashMap;
import java.util.Map;


public class AssetsManager implements IAssetsManager {
    private static final Map<PlayerAction, String> soundEffectMap = new HashMap<>() {{
        put(PlayerAction.JUMP, "assets/blipp.ogg");
        put(PlayerAction.DAMAGE, "assets/blipp.ogg");
        put(PlayerAction.PICKUP, "assets/blipp.ogg");
    }};
    private static final Map<String, String> musicMap = new HashMap<>() {{
        put("MAIN", "assets/Main.mp3");
        put("BACKGROUND", "assets/Background.mp3");
        put("DEAD", "assets/gameOver.wav");
        put("VICTORY", "assets/Win.mp3");
    }};
    private final Map<PlayerAction, Sound> currentEffects;
    private Music nowPlaying;
    private String lastPlayed;

    public AssetsManager() {
        currentEffects = new HashMap<>();
    }

    @Override
    public void playMusic(String key) {
        if (key.equals(lastPlayed)) {
            resumeMusic();
            return;
        }
        stopMusic();
        if (!musicMap.containsKey(key)) return;
        nowPlaying = Gdx.audio.newMusic(Gdx.files.internal(musicMap.get(key)));
        if (nowPlaying != null && !nowPlaying.isPlaying()) {
            nowPlaying.setVolume(0.5f);
            nowPlaying.setLooping(true);
            nowPlaying.play();
        }
        lastPlayed = key;
    }

    @Override
    public void stopMusic() {
        if (nowPlaying == null) return;
        nowPlaying.stop();
        nowPlaying = null;
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
    public void playSoundEffect(PlayerAction key) {
        if (!soundEffectMap.containsKey(key)) return;
        Sound effect = Gdx.audio.newSound(Gdx.files.internal(soundEffectMap.get(key)));
        if (currentEffects.containsKey(key)) {
            currentEffects.get(key).stop();
        }
        currentEffects.put(key, effect);
        effect.play();
    }

}