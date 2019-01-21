package teotw.com.shareapplication.ui.widget;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnticipateOvershootInterpolator;

import teotw.com.shareapplication.R;

public class ShareView2 extends View {
    private static final float RADIUS = 100f;//上排距离
    private static final float HEIGHT_VIEW = 150f;
    private static float DISTANCEPOINT = 150;
    private OnDownActionListener2 mDown = null;
    private Point currentPoint1;
    private Point currentPoint2;
    private Point currentPoint3;
    private Point currentPoint4;
    private float startX;
    private float startY;
    private float width;
    private float height;
    private Paint mPaint;
    private Bitmap bitmap1;
    private Bitmap bitmap2;
    private Bitmap bitmap3;
    private Bitmap bitmap4;

    public ShareView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
        // 初始化
        WindowManager wm = (WindowManager) getContext().getSystemService(
                Context.WINDOW_SERVICE);
//        height = wm.getDefaultDisplay().getHeight();
        height = 300;
        width = wm.getDefaultDisplay().getWidth();

        DISTANCEPOINT = width / 5;

        bitmap1 = BitmapFactory.decodeResource(getResources(), R.mipmap.other_icon03);
        bitmap2 = BitmapFactory.decodeResource(getResources(), R.mipmap.other_icon04);
        bitmap3 = BitmapFactory.decodeResource(getResources(), R.mipmap.other_icon05);
        bitmap4 = BitmapFactory.decodeResource(getResources(), R.mipmap.other_icon06);

        startX = (float) (DISTANCEPOINT - bitmap1.getWidth() / 2);
        startY = height - RADIUS;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (currentPoint1 == null) {
            currentPoint1 = new Point(startX, startY);
            currentPoint2 = new Point(startX + DISTANCEPOINT, startY);
            currentPoint3 = new Point(startX + 2 * DISTANCEPOINT, startY);
            currentPoint4 = new Point(startX + 3 * DISTANCEPOINT, startY);
            canvas.drawBitmap(bitmap1, currentPoint1.getX(),
                    currentPoint1.getY(), mPaint);
            canvas.drawBitmap(bitmap2, currentPoint2.getX(),
                    currentPoint2.getY(), mPaint);
            canvas.drawBitmap(bitmap3, currentPoint3.getX(),
                    currentPoint3.getY(), mPaint);
            canvas.drawBitmap(bitmap4, currentPoint4.getX(),
                    currentPoint4.getY(), mPaint);
            startAnimation(1, startX, startY);
            startAnimation(2, startX + DISTANCEPOINT, startY);
            startAnimation(3, startX + 2 * DISTANCEPOINT, startY);
            startAnimation(4, startX + 3 * DISTANCEPOINT, startY);
        } else {
            canvas.drawBitmap(bitmap1, currentPoint1.getX(),
                    currentPoint1.getY(), mPaint);
            canvas.drawBitmap(bitmap2, currentPoint2.getX(),
                    currentPoint2.getY(), mPaint);
            canvas.drawBitmap(bitmap3, currentPoint3.getX(),
                    currentPoint3.getY(), mPaint);
            canvas.drawBitmap(bitmap4, currentPoint4.getX(),
                    currentPoint4.getY(), mPaint);
        }
    }

    @SuppressLint("NewApi")
    private void startAnimation(int flag, float startX, float startY) {
        Point startPoint = new Point(startX, startY);
        Point endPoint = new Point(startX, startY - HEIGHT_VIEW);
        ValueAnimator anim = ValueAnimator.ofObject(new PointEvaluator2(),
                startPoint, endPoint);

        switch (flag) {
            case 1:
                anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @SuppressLint("NewApi")
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        currentPoint1 = (Point) animation.getAnimatedValue();
                        invalidate();
                    }
                });
                anim.setInterpolator(new AnticipateOvershootInterpolator());
                anim.setDuration(1000);
                anim.start();
                break;
            case 2:
                anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @SuppressLint("NewApi")
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        currentPoint2 = (Point) animation.getAnimatedValue();
                        invalidate();
                    }
                });
                anim.setInterpolator(new AnticipateOvershootInterpolator());
                anim.setDuration(1000);
                anim.setStartDelay(50);
                anim.start();
                break;
            case 3:
                anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @SuppressLint("NewApi")
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        currentPoint3 = (Point) animation.getAnimatedValue();
                        invalidate();
                    }
                });
                anim.setInterpolator(new AnticipateOvershootInterpolator());
                anim.setDuration(1000);
                anim.setStartDelay(100);
                anim.start();
                break;
            case 4:
                anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @SuppressLint("NewApi")
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        currentPoint4 = (Point) animation.getAnimatedValue();
                        invalidate();
                    }
                });
                anim.setInterpolator(new AnticipateOvershootInterpolator());
                anim.setDuration(1000);
                anim.setStartDelay(150);
                anim.start();
                break;

            default:
                break;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension((int) width, (int) height);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x = event.getX();
        float y = event.getY();
        if (event.getAction() == MotionEvent.ACTION_UP) {
            System.out.println("up");
        } else if (event.getAction() == MotionEvent.ACTION_DOWN) {

            int result = 0;
            result = isClick(x, y);

            switch (result) {
                case 1:
                    mDown.OnDown(1);
                    return true;
                case 2:
                    mDown.OnDown(2);
                    return true;
                case 3:
                    mDown.OnDown(3);
                    return true;
                case 4:
                    mDown.OnDown(4);
                    return true;

                default:
                    break;
            }
        }
        mDown.OnDown(0);
        return super.onTouchEvent(event);
    }

    private int isClick(float x, float y) {

        if (y > startY - HEIGHT_VIEW
                && y < startY - HEIGHT_VIEW + bitmap1.getHeight() && x > startX
                && x < startX + bitmap1.getWidth()) {
            return 1;
        }
        if (y > startY - HEIGHT_VIEW
                && y < startY - HEIGHT_VIEW + bitmap1.getHeight()
                && x > startX + DISTANCEPOINT
                && x < startX + DISTANCEPOINT + bitmap1.getWidth()) {
            return 2;
        }
        if (y > startY - HEIGHT_VIEW
                && y < startY - HEIGHT_VIEW + bitmap1.getHeight()
                && x > startX + 2 * DISTANCEPOINT
                && x < startX + 2 * DISTANCEPOINT + bitmap1.getWidth()) {
            return 3;
        }
        if (y > startY - HEIGHT_VIEW
                && y < startY - HEIGHT_VIEW + bitmap1.getHeight()
                && x > startX + 3 * DISTANCEPOINT
                && x < startX + 3 * DISTANCEPOINT + bitmap1.getWidth()) {
            return 4;
        }
        return 0;
    }

    // 为每个接口设置监听器
    public void setOnDownActionListener(OnDownActionListener2 down) {
        mDown = down;
    }

    public interface OnDownActionListener2 {
        void OnDown(int clickPoint);
    }

}