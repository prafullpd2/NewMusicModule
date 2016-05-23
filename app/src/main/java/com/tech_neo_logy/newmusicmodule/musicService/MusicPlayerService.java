package com.tech_neo_logy.newmusicmodule.musicService;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.Parcel;
import android.provider.Settings;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;

import com.tech_neo_logy.newmusicmodule.MusicFragment;
import com.tech_neo_logy.newmusicmodule.Navigation;
import com.tech_neo_logy.newmusicmodule.R;

import java.io.IOException;

public class MusicPlayerService extends Service implements MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener,
        MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnErrorListener,
        MediaPlayer.OnSeekCompleteListener, MediaPlayer.OnInfoListener {
    private MediaPlayer mediaPlayer = new MediaPlayer();
    NotificationManager notificationManager;
    int notificID = 33;

    private String musicUrl;

    public MusicPlayerService() {

    }

    void playMedia() {
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
    }

    void stopMedia() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    @Override
    public void onCreate() {

        mediaPlayer.setOnBufferingUpdateListener(this);
        mediaPlayer.setOnCompletionListener(this);
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.setOnErrorListener(this);
        mediaPlayer.setOnSeekCompleteListener(this);
        mediaPlayer.setOnInfoListener(this);
        mediaPlayer.reset();
    }

    @Override
    public void onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
            mediaPlayer.stop();
        }
        mediaPlayer.release();
        mediaPlayer = null;
        cancelNotification();
    }

    private void cancelNotification() {

        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(notificID);

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        musicUrl = intent.getExtras().getString("musicUrl");
        mediaPlayer.reset();
        //setting up media player with musicUrl
        if (!mediaPlayer.isPlaying()) {
            try {
                mediaPlayer.setDataSource(musicUrl);//set data source
                mediaPlayer.prepareAsync();//preperation of media player

            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        initNotification();
        return START_STICKY;
    }

    //create notification
    private void initNotification() {
        String notifacationService = Context.NOTIFICATION_SERVICE;
        notificationManager = (NotificationManager) getSystemService(notifacationService);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext());
        notificationBuilder.setSmallIcon(R.mipmap.notification)
                .setTicker("ticker")
                .setContentTitle("Click Me! title")
                .setContentText("This is Neo Dawn text")
                .setOngoing(true)
                .setWhen(System.currentTimeMillis());
        Intent moreNotification = new Intent(getApplicationContext(), MusicFragment.class);
        TaskStackBuilder tstackBuilder = TaskStackBuilder.create(getApplicationContext());
        tstackBuilder.addParentStack(Navigation.class);
        tstackBuilder.addNextIntent(moreNotification);
        PendingIntent pendingIntent = PendingIntent.getActivities(getApplicationContext(), 0, new Intent[]{moreNotification}, PendingIntent.FLAG_UPDATE_CURRENT);
        notificationBuilder.setContentIntent(pendingIntent);
        notificationManager.notify(notificID, notificationBuilder.build());

    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {

    }

    @Override
    public void onCompletion(MediaPlayer mp) {

        stopMedia();
        stopSelf();
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        switch (what) {
            case MediaPlayer.MEDIA_ERROR_SERVER_DIED:
                Toast.makeText(MusicPlayerService.this, "MEDIA_ERROR_SERVER_DIED " + extra, Toast.LENGTH_SHORT).show();
                break;
            case MediaPlayer.MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK:
                Toast.makeText(MusicPlayerService.this, "MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK " + extra, Toast.LENGTH_SHORT).show();
                break;
            case MediaPlayer.MEDIA_ERROR_UNKNOWN:
                Toast.makeText(MusicPlayerService.this, "MEDIA_ERROR_UNKNOWN " + extra, Toast.LENGTH_SHORT).show();
                break;
            case MediaPlayer.MEDIA_ERROR_UNSUPPORTED:
                Toast.makeText(MusicPlayerService.this, "MEDIA_ERROR_UNSUPPORTED " + extra, Toast.LENGTH_SHORT).show();
                break;
        }

        return false;
    }

    @Override
    public boolean onInfo(MediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {

        playMedia();
    }

    @Override
    public void onSeekComplete(MediaPlayer mp) {

    }
}
