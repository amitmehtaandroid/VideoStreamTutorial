package com.androidbegin.videostreamtutorial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;



public class LoginActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private static final int RECOVERY_REQUEST = 1;
    private YouTubePlayerView youTubeView;
    private YouTubePlayer player;

    private MyPlayerStateChangeListener playerStateChangeListener;
    private MyPlaybackEventListener playbackEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        playerStateChangeListener = new MyPlayerStateChangeListener();
        playbackEventListener = new MyPlaybackEventListener();

        youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        youTubeView.initialize(Config.YOUTUBE_API_KEY, this);


        final EditText seekToText = (EditText) findViewById(R.id.seek_to_text);
        Button seekToButton = (Button) findViewById(R.id.seek_to_button);
        seekToButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                int skipToSecs = Integer.valueOf(seekToText.getText().toString());
                player.seekToMillis(skipToSecs * 1000);
            }
        });
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(Config.YOUTUBE_API_KEY, this);
        }
    }

    protected Provider getYouTubePlayerProvider() {
        return youTubeView;
    }

//    public void onInitializationSuccess(Provider provider, YouTubePlayer youTubePlayer, boolean b) {
//
//    }
//
//    public void onInitializationFailure(Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
//
//    }





//    public void onInitializationSuccess(Provider provider, YouTubePlayer player, boolean wasRestored) {
//        if (!wasRestored) {
//            player.cueVideo("fhWaJi1Hsfo"); // Plays https://www.youtube.com/watch?v=fhWaJi1Hsfo
//        }
//    }

    public void onInitializationSuccess(Provider provider, YouTubePlayer player, boolean wasRestored) {
        player.setPlayerStateChangeListener(playerStateChangeListener);
        player.setPlaybackEventListener(playbackEventListener);
        this.player = player;

        if (!wasRestored) {
            player.cueVideo("fhWaJi1Hsfo"); // Plays https://www.youtube.com/watch?v=fhWaJi1Hsfo
        }
    }


    public void onInitializationFailure(Provider provider, YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_REQUEST).show();
        } else {
            String error = String.format(getString(R.string.player_error), errorReason.toString());
            Toast.makeText(this, error, Toast.LENGTH_LONG).show();
        }
    }

    private final class MyPlaybackEventListener implements YouTubePlayer.PlaybackEventListener {


        public void onPlaying() {
            // Called when playback starts, either due to user action or call to play().
            showMessage("Playing");
        }


        public void onPaused() {
            // Called when playback is paused, either due to user action or call to pause().
            showMessage("Paused");
        }


        public void onStopped() {
            // Called when playback stops for a reason other than being paused.
            showMessage("Stopped");
        }


        public void onBuffering(boolean b) {
            // Called when buffering starts or ends.
        }


        public void onSeekTo(int i) {
            // Called when a jump in playback position occurs, either
            // due to user scrubbing or call to seekRelativeMillis() or seekToMillis()
        }
    }
    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
    private final class MyPlayerStateChangeListener implements YouTubePlayer.PlayerStateChangeListener {


        public void onLoading() {
            // Called when the player is loading a video
            // At this point, it's not ready to accept commands affecting playback such as play() or pause()
        }


        public void onLoaded(String s) {
            // Called when a video is done loading.
            // Playback methods such as play(), pause() or seekToMillis(int) may be called after this callback.
        }


        public void onAdStarted() {
            // Called when playback of an advertisement starts.
        }


        public void onVideoStarted() {
            // Called when playback of the video starts.
        }


        public void onVideoEnded() {
            // Called when the video reaches its end.
        }


        public void onError(YouTubePlayer.ErrorReason errorReason) {
            // Called when an error occurs.
        }
    }
}

