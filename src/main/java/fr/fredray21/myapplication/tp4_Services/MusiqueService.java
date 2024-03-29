package fr.fredray21.myapplication.tp4_Services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Button;
import android.widget.Toast;

import fr.fredray21.myapplication.R;

public class MusiqueService extends Service {
    MediaPlayer mp;

    @Override
    public void onCreate() {
        Toast.makeText(this, "Service Create", Toast.LENGTH_SHORT).show();
        mp = MediaPlayer.create(this, R.raw.tnt);
        mp.setLooping(true);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Service Started", Toast.LENGTH_SHORT).show();
        if (intent != null && intent.hasExtra("pause")) {
            if (mp.isPlaying()) {
                mp.pause();
            } else {
                mp.start();
            }
        } else {
            mp.start();
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_SHORT).show();
        mp.stop();
    }

    @Override
    public IBinder onBind(Intent intent) {
       return null;
    }
}