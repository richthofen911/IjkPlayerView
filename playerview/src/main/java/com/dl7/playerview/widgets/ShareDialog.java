package com.dl7.playerview.widgets;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import com.dl7.playerview.R;

/**
 * Created by long on 2016/11/17.
 */

public class ShareDialog extends DialogFragment {

    private ImageView mIvScreenshot;

    private OnDialogClickListener mClickListener;
    private OnDialogDismissListener mDismissListener;
    private Bitmap mBitmap;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setWindowAnimations(R.style.AnimateDialog);
//        window.setGravity(Gravity.BOTTOM);
//        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View view = inflater.inflate(R.layout.dialog_share, container);
        mIvScreenshot = (ImageView) view.findViewById(R.id.iv_screenshot_photo);
        if (mBitmap != null) {
            mIvScreenshot.setImageBitmap(mBitmap);
        }
        view.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        view.findViewById(R.id.btn_share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mClickListener != null) {
                    mClickListener.onShare(mIvScreenshot.getDrawingCache(), null);
                }
                dismiss();
            }
        });
        return view;
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if (mDismissListener != null) {
            mDismissListener.onDismiss();
        }
    }

    public void setScreenshotPhoto(Bitmap bitmap) {
        mBitmap = bitmap;
    }

    public void setClickListener(OnDialogClickListener clickListener) {
        mClickListener = clickListener;
    }

    public void setDismissListener(OnDialogDismissListener dismissListener) {
        mDismissListener = dismissListener;
    }

    public interface OnDialogClickListener {
        void onShare(Bitmap bitmap, Uri uri);
    }

    public interface OnDialogDismissListener {
        void onDismiss();
    }
}