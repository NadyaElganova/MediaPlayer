package com.example.mediaplayer;

import android.media.MediaPlayer;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyMusicService extends Service {
    private MediaPlayer mediaPlayer;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String songName = intent.getStringExtra("songName");
        playSong(songName);
        return START_STICKY;
    }

    private void playSong(String songName) {
        try {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                mediaPlayer.reset();
            }
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(songName);
            mediaPlayer.prepare(); // Подготовка медиафайла к воспроизведению
            mediaPlayer.start(); // Начало воспроизведения
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

}
