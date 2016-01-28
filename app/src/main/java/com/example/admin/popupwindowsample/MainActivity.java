package com.example.admin.popupwindowsample;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
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
import com.google.android.youtube.player.YouTubePlayerView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Locale;

public class MainActivity extends YouTubeBaseActivity{
    TextView mTxtHelloWorld;
    YouTubePlayerView mYoutubePlayerView;
    public static final String API = "AIzaSyDkbWa_9FM3-scsgBCfrUxbCYD7NNwi7E0";
    public static final String YOUTUBE_ID = "TRmgMe2STL0";
    public static final String YOUTUBE_URL = "http://crackberry.com/sites/crackberry.com/files/styles/large/public/topic_images/2013/ANDROID.png?itok=xhm7jaxS"/*"https://www.youtube.com/watch?v=TRmgMe2STL0"*/;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
//        setUpDefaults();
//        setUpEvents();
    }

    private void init() {
        mTxtHelloWorld = (TextView)findViewById(R.id.hello_world);
        mTxtHelloWorld.setText(Locale.getDefault().getDisplayLanguage());
    }

    private void setUpDefaults() {
        mYoutubePlayerView =(YouTubePlayerView)findViewById(R.id.you_tube);

//        mYoutubePlayerView.initialize(API,this);
//        mYoutubePlayerView.initialize(API,new YouTubePlayer.OnInitializedListener() {
//            @Override
//            public void onInitializationSuccess(YouTubePlayer.Provider provider, final YouTubePlayer youTubePlayer, boolean b) {
//
//                Log.e("loading: ", "" + b);
//                if (!b) {
//                    Log.e("loading: ",""+b);
//                    youTubePlayer.cueVideo(YOUTUBE_ID);
////                      youTubePlayer.loadVideo(YOUTUBE_ID);
//                }
//
//                youTubePlayer.setPlayerStateChangeListener(new YouTubePlayer.PlayerStateChangeListener() {
//                    @Override
//                    public void onLoading() {
//                        Log.e("came:", "loading");
////                        youTubePlayer.play();
////                        youTubePlayer.pause();
//                    }
//
//                    @Override
//                    public void onLoaded(String s) {
//                        Log.e("came:","onLoaded");
//                        youTubePlayer.play();
//                    }
//
//                    @Override
//                    public void onAdStarted() {
//                        Log.e("came:","onAdStarted");
//                    }
//
//                    @Override
//                    public void onVideoStarted() {
//                        Log.e("came:","onVideoStarted");
//                    }
//
//                    @Override
//                    public void onVideoEnded() {
//                        Log.e("came:","onVideoEnded");
//                    }
//
//                    @Override
//                    public void onError(YouTubePlayer.ErrorReason errorReason) {
//                        Log.e("came:","onError");
//                    }
//                });
//
//                youTubePlayer.setPlaybackEventListener(new YouTubePlayer.PlaybackEventListener() {
//                    @Override
//                    public void onPlaying() {
//                        Log.e("came:","onPlaying");
//                    }
//
//                    @Override
//                    public void onPaused() {
//                        Log.e("came:","onPaused");
//                    }
//
//                    @Override
//                    public void onStopped() {
//                        Log.e("came:","onStopped");
//                    }
//
//                    @Override
//                    public void onBuffering(boolean b) {
//                        Log.e("came:","onBuffering");
//                    }
//
//                    @Override
//                    public void onSeekTo(int i) {
//                        Log.e("came:","onSeekTo");
//                    }
//                });
//
//            }
//
//            @Override
//            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
//            }
//        });

    }

    private void setUpEvents() {
        mTxtHelloWorld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//**From Url To download video
                new DownloadFromUrl().execute(YOUTUBE_URL);

//***inflating the view from the layout
                LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                LayoutInflater inflater1 = LayoutInflater.from(getApplicationContext());
                View view = inflater.inflate(R.layout.pop_up_layout, null);
                PopupWindow popupWindow = new PopupWindow(view, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
                popupWindow.showAtLocation(mTxtHelloWorld, Gravity.CENTER_VERTICAL, 0, 0);

//***Youtube on popup Windows
//YouTubePlayerFragment youTubePlayerFragment = new YouTubePlayerFragment();
//                youTubePlayerFragment.initialize(API, new YouTubePlayer.OnInitializedListener() {
//                    @Override
//                    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
//                        Log.e("loading: ",""+b);
//                        if (!b) {
//                            Log.e("loading: ",""+b);
//                            youTubePlayer.cueVideo(YOUTUBE_ID);
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

    public class DownloadFromUrl extends AsyncTask<String,String,String>
    {
        @Override
        protected String doInBackground(String... params) {
            try {
                File file;
                URL url = new URL(params[0]);
                Log.e("params", "" + params[0]);
                URLConnection urlConnection = url.openConnection();
                urlConnection.setConnectTimeout(5000);
                urlConnection.setReadTimeout(30000);
                long startTime = System.currentTimeMillis();
                Log.e("GetContentLength: ",""+urlConnection.getContentLength());
                InputStream inputStream = urlConnection.getInputStream();
                Log.e("getInputStream: ",""+urlConnection.getInputStream());
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream,1024);
                Log.e("bufferedInputStream: ",""+bufferedInputStream);
                FileOutputStream fileOutputStream = new FileOutputStream(new File(getApplicationContext().getFilesDir(),"myvideo.mp4"));
                Log.e("getFilesDir",""+getApplicationContext().getCacheDir());
                byte[] by = new byte[1024];

                int bufferLength;
                while ((bufferLength=bufferedInputStream.read(by))!=-1)
                {
                    Log.e("by",""+bufferedInputStream.read(by));
                    Log.e("bufferlength",""+bufferLength);
                    Log.e("came","while");
                    fileOutputStream.write(by,0,bufferLength);
                }
                fileOutputStream.flush();
                fileOutputStream.close();
                bufferedInputStream.close();
                Log.e("came","doInBackground");
            } catch(MalformedURLException e) {
               Log.e("Exception",""+e);
            } catch (IOException e) {
                Log.e("Exception", "" + e);
            }
            return null;
        }
    }
}
