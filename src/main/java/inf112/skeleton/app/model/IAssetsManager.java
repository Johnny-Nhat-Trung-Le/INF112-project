package inf112.skeleton.app.model;

public interface IAssetsManager {

    void playMusic(String key);

    void stopMusic();

    void pauseMusic();

    void resumeMusic();

    void playSoundEffect(String key);
}

