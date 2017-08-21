package com.example.administrator.myapplication;

import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String videoMime = "video/avc";
        int videoWidth = 352;
        int videoHeight = 288;

        MediaFormat format = MediaFormat.createVideoFormat(videoMime, videoWidth, videoHeight);
        format.setInteger(MediaFormat.KEY_STRIDE, videoWidth);
        format.setInteger(MediaFormat.KEY_FRAME_RATE, 30);
        format.setInteger(MediaFormat.KEY_I_FRAME_INTERVAL, 60);
        format.setInteger(MediaFormat.KEY_COLOR_FORMAT, MediaCodecInfo.CodecCapabilities.COLOR_FormatYUV420Planar);
        format.setInteger(MediaFormat.KEY_PROFILE, MediaCodecInfo.CodecProfileLevel.AVCProfileBaseline);
//        format.setInteger(MediaFormat.KEY_BIT_RATE, 125000);

        MediaCodec videoEncoder = null;
        try {
            videoEncoder = MediaCodec.createEncoderByType(videoMime);
        } catch (IOException e) {
            e.printStackTrace();
        }
        videoEncoder.configure(format, null, null, MediaCodec.CONFIGURE_FLAG_ENCODE);

        videoEncoder.start();

        videoEncoder.stop();
        videoEncoder.release();

        Log.v("fuckk", "complete");
    }
}
