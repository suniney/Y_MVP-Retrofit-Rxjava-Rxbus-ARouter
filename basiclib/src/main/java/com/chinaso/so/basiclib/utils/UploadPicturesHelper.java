package com.chinaso.so.basiclib.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;

import com.chinaso.so.basiclib.base.BaseApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * @author yanxu
 * @date:2019/1/18
 * @description: 选择拍照，或相机图片，裁剪返回File
 * 使用步骤：1、new UploadPicturesHelper
 * 2、在onActivityResult方法中 uploadPicturesHelper.onActivityResult(requestCode, resultCode, data);
 * 3、在适当的位置调用uploadPicturesHelper.uploadAvatarFromCameraRequest();
 */

public class UploadPicturesHelper {
    public static final int REQUEST_CODE_TAKE_PHOTO = 8881;
    public static final int REQUEST_CODE_ALBUM = 8882;
    public static final int REQUEST_CODE_CROUP_PHOTO = 8883;
    public static final int USER_AVATAR_MAX_SIZE = 100;
    private Activity mActivity;
    private ImageSelectorCallBack imageSelectorCallBack;
    private String imgName;
    private Uri uri;
    private File file;

    public UploadPicturesHelper(Activity activity, String imgName, ImageSelectorCallBack imageSelectorCallBack) {
        this.mActivity = activity;
        this.imgName = imgName;
        this.imageSelectorCallBack = imageSelectorCallBack;
        file = new File(FileUtils.getCachePath(mActivity), System.currentTimeMillis() + "_" + imgName);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            uri = Uri.fromFile(file);
        } else {
            //通过FileProvider创建一个content类型的Uri(android 7.0需要)
            uri = FileProvider.getUriForFile(BaseApplication.getContext(), "com.chinaso.so.file", file);
        }
    }


    public interface ImageSelectorCallBack {
        void onSelected(File imgFile);
    }

    public void setImageSelectorCallBack(ImageSelectorCallBack imageSelectorCallBack) {
        this.imageSelectorCallBack = imageSelectorCallBack;
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            //调用相册后返回
            case REQUEST_CODE_ALBUM:
                if (resultCode == Activity.RESULT_OK) {
                    Uri newUri;
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                        newUri = Uri.parse("file:///" + CropUtils.getPath(mActivity, data.getData()));
                    } else {
                        newUri = data.getData();
                    }
                    if (newUri != null) {
                        cropImageUri(newUri, file);
                    }
                }
                break;
            // 调用相机后返回
            case REQUEST_CODE_TAKE_PHOTO:
                if (resultCode == Activity.RESULT_OK) {
                    startPhotoZoom(uri, file);
                }
                break;
            //调用剪裁后返回
            case REQUEST_CODE_CROUP_PHOTO:
                if (data != null) {
                    if (imageSelectorCallBack != null) {
                        imageSelectorCallBack.onSelected(file);
                    }
                }
                break;
        }
    }


    /**
     * 选择照相机
     */
    public void uploadAvatarFromCameraRequest() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        mActivity.startActivityForResult(intent, REQUEST_CODE_TAKE_PHOTO);
    }

    /**
     * 选择相册
     */
    public void uploadAvatarFromAlbumRequest() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        mActivity.startActivityForResult(photoPickerIntent, REQUEST_CODE_ALBUM);
    }

    /**
     * 调用系统相册裁剪
     *
     * @param orgUri
     */
    public void cropImageUri(Uri orgUri, File file) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(orgUri, "image/*");
        intent.putExtra("crop", "true");// crop=true 有这句才能出来最后的裁剪页面.
        intent.putExtra("aspectX", 1);// 这两项为裁剪框的比例.
        intent.putExtra("aspectY", 1);// x:y=1:1
        intent.putExtra("outputX", USER_AVATAR_MAX_SIZE);//图片输出大小
        intent.putExtra("outputY", USER_AVATAR_MAX_SIZE);
        intent.putExtra("output", Uri.fromFile(file));//裁剪输出的位置
        intent.putExtra("outputFormat", "JPEG");// 返回格式
        mActivity.startActivityForResult(intent, REQUEST_CODE_CROUP_PHOTO);
    }


    /**
     * 裁剪拍照裁剪
     *
     * @param uri
     */
    public void startPhotoZoom(Uri uri, File file) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.putExtra("crop", "true");// crop=true 有这句才能出来最后的裁剪页面.
        intent.putExtra("aspectX", 1);// 这两项为裁剪框的比例.
        intent.putExtra("aspectY", 1);// x:y=1:1
        intent.putExtra("outputX", USER_AVATAR_MAX_SIZE);//图片输出大小
        intent.putExtra("outputY", USER_AVATAR_MAX_SIZE);
        intent.putExtra("output", Uri.fromFile(file));
        intent.putExtra("outputFormat", "JPEG");// 返回格式
        mActivity.startActivityForResult(intent, REQUEST_CODE_CROUP_PHOTO);
    }

    /**
     * 压缩图片工具
     *
     * @param context
     * @param fileSrc
     * @return
     */
    public static File getSmallBitmap(Context context, String fileSrc) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(fileSrc, options);
        options.inSampleSize = calculateInSampleSize(options, 480, 800);
        LogUtil.i("yanxu", "options.inSampleSize-->" + options.inSampleSize);
        options.inJustDecodeBounds = false;
        Bitmap img = BitmapFactory.decodeFile(fileSrc, options);
        LogUtil.i("yanxu", "file size after compress-->" + img.getByteCount() / 256);
        String filename = FileUtils.getCachePath(context) + "/img-" + img.hashCode() + ".jpg";
        saveBitmap2File(img, filename);
        return new File(filename);

    }

    /**
     * 设置压缩的图片的大小设置的参数
     *
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int height = options.outHeight;
        int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            int heightRatio = Math.round(height) / reqHeight;
            int widthRatio = Math.round(width) / reqWidth;
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }


    /**
     * 保存bitmap到文件
     *
     * @param bmp
     * @param filename
     * @return
     */
    public static boolean saveBitmap2File(Bitmap bmp, String filename) {
        Bitmap.CompressFormat format = Bitmap.CompressFormat.JPEG;
        int quality = 50;
        OutputStream stream = null;
        try {
            stream = new FileOutputStream(filename);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return bmp.compress(format, quality, stream);
    }

}
