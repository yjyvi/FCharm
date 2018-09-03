package com.whmnrc.mylibrary.utils;

import android.app.Activity;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;


/**
 * 多图选择视频选择utils
 * Created by yonghao zeng on 2017/6/1.
 */

public class ImgVideoPickerUtils {

    /**
     * 头像选取 打开相册
     *
     * @param
     */
    public static void openImgSelect(Activity context, int size) {
        PictureSelector.create(context)
                .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()
                //                            .theme()//主题样式(不设置为默认样式) 也可参考 demo values/styles 下 例如：R.style.picture.white.style
                .maxSelectNum(size)// 最大图片选择数量 int
                .minSelectNum(1)// 最小选择数量 int
                .imageSpanCount(4)// 每行显示个数 int
                .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .previewImage(true)// 是否可预览图片 true or false
                .isCamera(true)// 是否显示拍照按钮 true or false
                .compress(true)// 是否压缩 true or false
                .enableCrop(true)
//                .hideBottomControls(true)
                .showCropFrame(true)
//                .showCropGrid(true)
//                .withAspectRatio(1, 1)// int 裁剪比例 如 16:9 3:2 3:4 1:1 可自定义
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
//                .showCropFrame(true)// 是否显示裁剪矩形边框 圆形裁剪时建议设为 false   true or false
                //                            .selectionMedia()// 是否传入已选图片 List<LocalMedia> list
//                .rotateEnabled(true) // 裁剪是否可旋转图片 true or false
//                .scaleEnabled(true)// 裁剪是否可放大缩小图片 true or false
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调 onActivityResult code

    }


    /**
     * 打开相机
     */
    public static void openCamera(Activity context) {
        PictureSelector.create(context).openCamera(PictureConfig.TYPE_IMAGE)
                .enableCrop(true)
                .circleDimmedLayer(true)
                .scaleEnabled(true)
                .compress(true)
                .forResult(PictureConfig.REQUEST_CAMERA);

    }

    /**
     * 商家入驻打开相机
     */
    public static void openCameraPhoto(Activity context) {
        PictureSelector.create(context)
                .openCamera(PictureMimeType.ofImage())
//                .enableCrop(true)// 是否裁剪 true or false
                .compress(true)// 是否压缩 true or false
//                .withAspectRatio(1, 1)// int 裁剪比例 如 16:9 3:2 3:4 1:1 可自定义
//                .freeStyleCropEnabled(false)// 裁剪框是否可拖拽 true or false
//                .circleDimmedLayer(false)// 是否圆形裁剪 true or false
//                .showCropFrame(true)// 是否显示裁剪矩形边框 圆形裁剪时建议设为 false   true or false
////                            .selectionMedia()// 是否传入已选图片 List<LocalMedia> list
//                .rotateEnabled(true) // 裁剪是否可旋转图片 true or false
//                .scaleEnabled(true)// 裁剪是否可放大缩小图片 true or false
                .forResult(PictureConfig.REQUEST_CAMERA);
    }

    /**
     * 单张图片选择
     */
    public static void openSinglePhoto(Activity context) {
        PictureSelector.create(context)
                .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()
//                            .theme()//主题样式(不设置为默认样式) 也可参考 demo values/styles 下 例如：R.style.picture.white.style
                .maxSelectNum(1)// 最大图片选择数量 int
                .minSelectNum(1)// 最小选择数量 int
                .imageSpanCount(4)// 每行显示个数 int
                .selectionMode(PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .previewImage(true)// 是否可预览图片 true or false
                .isCamera(true)// 是否显示拍照按钮 true or false
                .enableCrop(true)// 是否裁剪 true or false
                .compress(true)// 是否压缩 true or false
                .withAspectRatio(1, 1)// int 裁剪比例 如 16:9 3:2 3:4 1:1 可自定义
//                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
                .showCropGrid(true)
                .rotateEnabled(false)
                .isDragFrame(true)
                .cropWH(500, 500)
                .showCropFrame(true)// 是否显示裁剪矩形边框 圆形裁剪时建议设为 false   true or false
//                            .selectionMedia()// 是否传入已选图片 List<LocalMedia> list
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调 onActivityResult code
    }


    /**
     * 发布文章，发布鱼市 聊天发布的图片
     *
     * @param context
     * @param maxImg
     */
    public static void openPhoto(Activity context, int maxImg) {
        PictureSelector.create(context)
                .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()
                .maxSelectNum(maxImg)// 最大图片选择数量 int
                .minSelectNum(1)// 最小选择数量 int
                .imageSpanCount(4)// 每行显示个数 int
                .previewImage(true)
                .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .previewImage(true)// 是否可预览图片 true or false
                .isCamera(true)// 是否显示拍照按钮 true or false
                .compress(true)// 是否压缩 true or false
                .minimumCompressSize(1024)
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调 onActivityResult code
    }

    /**
     * 发布文章，发布鱼市 聊天发布视频
     *
     * @param context
     */
    public static void openVideo(Activity context) {
        PictureSelector.create(context)
                .openGallery(PictureMimeType.ofVideo())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()
//                            .theme()//主题样式(不设置为默认样式) 也可参考 demo values/styles 下 例如：R.style.picture.white.style
                .maxSelectNum(1)// 最大图片选择数量 int
                .minSelectNum(1)// 最小选择数量 int
                .videoMaxSecond(30)
                .recordVideoSecond(30)
                .imageSpanCount(4)// 每行显示个数 int
                .selectionMode(PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .isCamera(true)// 是否显示拍照按钮 true or false
//                .compress(true)// 是否压缩 true or false
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调 onActivityResult code
    }


}
