package inf112.lilBro.app.view;

import inf112.lilBro.app.model.PlayerAction;

public interface IAssetsManager {

    /**
     * Plays {@linkplain com.badlogic.gdx.audio.Music} given a key on loop.
     * <p> If there is no music given the key the music stops.
     * <p> If there is already a music playing with the same key it resumes if it is paused.
     * <p> If key is different from the last and it has a music it stops the last music and play the new one.
     *
     * @param key for retrieving music
     */
    void playMusic(String key);

    /**
     * Stops the music that is currently playing.
     */
    void stopMusic();

    /**
     * Pauses the music that is currently playing.
     */
    void pauseMusic();

    /**
     * Resumes the music that is being played.
     */
    void resumeMusic();

    /**
     * Plays a sound effect based on the specified key.
     * <p> If there is no sound matching the key nothing will happen.
     *
     * @param key for retrieving sound effect
     */
    void playSoundEffect(PlayerAction key);
}

