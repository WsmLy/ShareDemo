package com.teotw.ui.dialog;

import android.animation.AnimatorSet;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.teotw.R;
import com.teotw.ui.activity.MainActivity;
import com.teotw.ui.activity.ReleaseActivity;
import com.teotw.ui.widgets.ShareView2;

import java.util.Objects;


/**
 * @author wsm on 2018/11/25
 * 文件描述：
 */
public class ReleaseDialog extends Dialog implements View.OnClickListener, ShareView2.OnDownActionListener2  {
    private static final int DURATION = 200;//动画延时，单位为毫秒

    private Context context;
    private ShareView2 shareView;

    public ReleaseDialog(@NonNull Context context) {
        super(context, R.style.MyDialogStyle);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_release);
        setCanceledOnTouchOutside(true);
        Objects.requireNonNull(this.getWindow()).setGravity(Gravity.BOTTOM);

        initView();
    }

    private void initView() {
        findViewById(R.id.context).getLayoutParams().width = ReleaseActivity.width;
        TextView cancelTitle = findViewById(R.id.cancel_title);
        cancelTitle.setOnClickListener(this);
        RelativeLayout linearLayout = findViewById(R.id.context);
        linearLayout.setLayoutTransition(new LayoutTransition());
        LayoutTransition transition = linearLayout .getLayoutTransition();
        transition.enableTransitionType(LayoutTransition.CHANGING);

        shareView = findViewById(R.id.share_view);
//        shareView.getLayoutParams().width = ReleaseActivity.width;
        shareView.setBackgroundColor(0xffff0000);
        shareView.setOnDownActionListener(this);
    }

    @Override
    public void show() {
        super.show();
        showAfter();
    }

    //Dialog延迟显示
    private void showAfter() {
        View view = Objects.requireNonNull(getWindow()).getDecorView();
        //使用透明渐变位移动画，缓解选中项显示跳动问题
        ObjectAnimator alpha = ObjectAnimator.ofFloat(view, "alpha", 0, 1);
        ObjectAnimator translation = ObjectAnimator.ofFloat(view, "translationY", 300, 0);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(alpha, translation);
        animatorSet.setDuration(DURATION);
        animatorSet.setInterpolator(new AccelerateInterpolator());
        animatorSet.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel_title:
                dismiss();
                break;
        }
    }

    @Override
    public void dismiss() {
        ((Activity)context).finish();
        super.dismiss();
    }

    @Override
    public void OnDown(int clickPoint) {
        onclickPointListener.OnClickPoint(clickPoint);
    }
    public void setOnClickPointListener(OnClickPointListener2 onclickPointListener){
        this.onclickPointListener=onclickPointListener;

    }
    public interface OnClickPointListener2 {
        void OnClickPoint(int clickPoint);
    }
    private OnClickPointListener2 onclickPointListener;
}
