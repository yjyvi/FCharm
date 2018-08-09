package com.whmnrc.feimei.utils;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;

/**
 * Created by yong hao zeng on 2017/12/20.
 */

public class MediaUtils {

    public static Bitmap getVideoThumb(String path) {
        MediaMetadataRetriever media = new MediaMetadataRetriever();
        media.setDataSource(path);
        return media.getFrameAtTime();
    }

}
