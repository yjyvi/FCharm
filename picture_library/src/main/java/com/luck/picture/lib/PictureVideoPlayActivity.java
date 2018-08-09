package com.luck.picture.lib;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

/**
 * @author yjyvi
 */
public abstract class PictureVideoPlayActivity extends PictureBaseActivity implements MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener, View.OnClickListener {
    private String video_path = "";
    private ImageView picture_left_back;
    private MediaController mMediaController;
    private VideoView mVideoView;
    private ImageView iv_play;
    private int mPositionWhenPaused = -1;
    private ProgressBar pb;
    private String goodsDescription;
    private String goodsId;
    private TextView mTvDesc;
    private TextView mTvBuy;
    public LinearLayout mLlVideoGoods;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picture_activity_video_play);


        video_path = getIntent().getStringExtra("video_path");
        goodsDescription = getIntent().getStringExtra("goodsDescription");
        goodsId = getIntent().getStringExtra("goodsId");

        picture_left_back = (ImageView) findViewById(R.id.picture_left_back);
        mVideoView = (VideoView) findViewById(R.id.video_view);
        mVideoView.setBackgroundColor(Color.BLACK);
        iv_play = (ImageView) findViewById(R.id.iv_play);
        pb = (ProgressBar) findViewById(R.id.image_buffer);

        mLlVideoGoods = (LinearLayout) findViewById(R.id.ll_video_goods);
        mTvDesc = (TextView) findViewById(R.id.tv_desc);
        mTvBuy = (TextView) findViewById(R.id.tv_buy);

        if (TextUtils.isEmpty(goodsId)) {
            mLlVideoGoods.setVisibility(View.GONE);
        } else {
            mLlVideoGoods.setVisibility(View.VISIBLE);
            mTvDesc.setText(goodsDescription);
            mTvBuy.setOnClickListener(this);
        }


        mMediaController = new MediaController(this);
        mVideoView.setOnCompletionListener(this);
        mVideoView.setOnPreparedListener(this);
        mVideoView.setMediaController(mMediaController);

        picture_left_back.setOnClickListener(this);
        iv_play.setOnClickListener(this);
        pb.setVisibility(View.VISIBLE);

    }

    public static void start(Context context, String videoPath) {
        Intent starter = new Intent(context, PictureVideoPlayActivity.class);
        starter.putExtra("video_path", videoPath);
        context.startActivity(starter);
    }

    public static void start(Context context, String videoPath, String goodsDescription, String goodsId) {
        Intent starter = new Intent(context, PictureVideoPlayActivity.class);
        starter.putExtra("video_path", videoPath);
        starter.putExtra("goodsDescription", goodsDescription);
        starter.putExtra("goodsId", goodsId);
        context.startActivity(starter);
    }


    @Override
    public void onStart() {
        // Play Video
        mVideoView.setVideoPath(video_path);
        mVideoView.start();
        super.onStart();
    }

    @Override
    public void onPause() {
        // Stop video when the activity is pause.
        mPositionWhenPaused = mVideoView.getCurrentPosition();
        mVideoView.stopPlayback();

        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mMediaController = null;
        mVideoView = null;
        iv_play = null;
        super.onDestroy();
    }

    @Override
    public void onResume() {
        // Resume video player
        if (mPositionWhenPaused >= 0) {
            mVideoView.seekTo(mPositionWhenPaused);
            mPositionWhenPaused = -1;
        }

        super.onResume();
    }

    @Override
    public boolean onError(MediaPlayer player, int arg1, int arg2) {
        return false;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        if (null != iv_play) {
            iv_play.setVisibility(View.VISIBLE);
        }


    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.picture_left_back) {
            finish();
        } else if (id == R.id.iv_play) {
            mVideoView.start();
            iv_play.setVisibility(View.INVISIBLE);
        } else if (id == R.id.tv_buy) {
            mVideoView.stopPlayback();
            goToGoodsDetials(goodsId);
        }
    }

    public abstract void goToGoodsDetials(String goodsId);

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(new ContextWrapper(newBase) {
            @Override
            public Object getSystemService(String name) {
                if (Context.AUDIO_SERVICE.equals(name)) {
                    return getApplicationContext().getSystemService(name);
                }
                return super.getSystemService(name);
            }
        });
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                if (what == MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START) {
                    // video started
                    mVideoView.setBackgroundColor(Color.TRANSPARENT);
                    return true;
                }
                return false;
            }
        });
        pb.setVisibility(View.GONE);
    }
}
