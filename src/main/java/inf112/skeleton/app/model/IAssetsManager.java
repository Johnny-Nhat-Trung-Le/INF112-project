package inf112.skeleton.app.model;

public interface IAssetsManager {

    /**
     * Plays music given a key
     * if there is no music then the music stops,
     * if there is already a music playing it resumes and
     * if there is a new music stops last music and play the new one
     *
     * @param key for retrieving music
     */
    void playMusic(String key);

    /**
     * Stops music currently playing
     */
    void stopMusic();

    /**
     * Pause music currently playing
     */
    void pauseMusic();

    /**
     * Resume music being played
     */
    void resumeMusic();

    /**
     * Plays sound effect
     *
     * @param key for retrieving sound effect
     */
    void playSoundEffect(String key);
}

