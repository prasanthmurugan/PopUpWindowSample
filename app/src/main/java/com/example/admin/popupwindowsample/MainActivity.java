package com.example.admin.popupwindowsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerView;

public class MainActivity extends YouTubeBaseActivity {
    TextView mTxtHelloWorld;
    YouTubePlayerView mYoutubePlayerView;
    public static final String API = "AIzaSyDkbWa_9FM3-scsgBCfrUxbCYD7NNwi7E0";
    public static final String YOU_TUBE_ID = "kF4H11JlAtk";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setUpDefaults();
        setUpEvents();
    }

    private void init() {
        mTxtHelloWorld = (TextView)findViewById(R.id.hello_world);
    }

    private void setUpDefaults() {
        mYoutubePlayerView =(YouTubePlayerView)findViewById(R.id.you_tube);
        mYoutubePlayerView.initialize(API, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, final YouTubePlayer youTubePlayer, boolean b) {
                Log.e("loading: ", "" + b);
                if (!b) {
                    Log.e("loading: ",""+b);
//                    youTubePlayer.cueVideo(YOU_TUBE_ID);
                      youTubePlayer.loadVideo(YOU_TUBE_ID);
                }
                youTubePlayer.setPlayerStateChangeListener(new YouTubePlayer.PlayerStateChangeListener() {
                    @Override
                    public void onLoading() {
                        Log.e("came:","loading");
                        youTubePlayer.pause();
                    }

                    @Override
                    public void onLoaded(String s) {
                        Log.e("came:","onLoaded");
                        youTubePlayer.play();
                    }

                    @Override
                    public void onAdStarted() {
                        Log.e("came:","onAdStarted");
                    }

                    @Override
                    public void onVideoStarted() {
                        Log.e("came:","onVideoStarted");
                    }

                    @Override
                    public void onVideoEnded() {
                        Log.e("came:","onVideoEnded");
                    }

                    @Override
                    public void onError(YouTubePlayer.ErrorReason errorReason) {
                        Log.e("came:","onError");
                    }
                });
                youTubePlayer.setPlaybackEventListener(new YouTubePlayer.PlaybackEventListener() {
                    @Override
                    public void onPlaying() {
                        Log.e("came:","onPlaying");
                    }

                    @Override
                    public void onPaused() {
                        Log.e("came:","onPaused");
                    }

                    @Override
                    public void onStopped() {
                        Log.e("came:","onStopped");
                    }

                    @Override
                    public void onBuffering(boolean b) {
                        Log.e("came:","onBuffering");
                    }

                    @Override
                    public void onSeekTo(int i) {
                        Log.e("came:","onSeekTo");
                    }
                });
            }


            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });

    }

    private void setUpEvents() {
        mTxtHelloWorld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                LayoutInflater inflater1 = LayoutInflater.from(getApplicationContext());
                View view = inflater.inflate(R.layout.pop_up_layout,null);
                PopupWindow popupWindow = new PopupWindow(view, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
                popupWindow.showAtLocation(mTxtHelloWorld, Gravity.CENTER_VERTICAL, 0, 0);
//                YouTubePlayerFragment youTubePlayerFragment = new YouTubePlayerFragment();
//                youTubePlayerFragment.initialize(API, new YouTubePlayer.OnInitializedListener() {
//                    @Override
//                    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
//                        Log.e("loading: ",""+b);
//                        if (!b) {
//                            Log.e("loading: ",""+b);
//                            youTubePlayer.cueVideo(YOU_TUBE_ID);
//                        }
//                    }
//
//                    @Override
//                    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
//
//                    }
//                });
            }
        });
    }


}
