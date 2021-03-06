package com.sevenbluedogs.Herramientas;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class ServicioMusica extends Service {

    MediaPlayer miReproductor;

    public void onCreate(){

        super.onCreate();

        miReproductor=MediaPlayer.create(this,R.raw.wii);

        miReproductor.setLooping(true);

        miReproductor.setVolume(100,100);

    }

    public int onStartCommand(Intent intent, int flags, int startId){

        miReproductor.start();

            return START_STICKY;

    }

    public void onDestroy(){

        super.onDestroy();

        if(miReproductor.isPlaying()) miReproductor.stop();

        miReproductor.release();

        miReproductor=null;

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
