package test;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.DisplayMetrics;
import android.util.FloatMath;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;
import android.widget.ImageView;

public class TouchTest {

    private static final int NONE = 0;// 初始狀態
    private static final int DRAG = 1;// 拖曳狀態
    private static final int ZOOM = 2;// 縮放狀態
    private int mode = NONE;

    private ImageView imageView;
    private Matrix matrix = new Matrix();
    private Matrix savedMatrix = new Matrix();
    private Bitmap bitmap;
    private DisplayMetrics dm;

    public TouchTest(DisplayMetrics dm, ImageView imageView, Bitmap bitmap){

        this.dm = dm;
        this.imageView = imageView;
        this.bitmap = bitmap;

    }

    private float minScaleR;

    public void ZoomIn() {

        minScaleR = Math.min( (float) dm.widthPixels / (float) bitmap.getWidth(), (float) dm.heightPixels / (float) bitmap.getHeight());

        if (minScaleR < 1.0) {
            matrix.postScale(minScaleR+1f, minScaleR+1f);
        }
        else{
            matrix.postScale(minScaleR, minScaleR);
        }
    }

    public void ZoomOut(){
        minScaleR = Math.max(
                (float) dm.widthPixels / (float) bitmap.getWidth(),
                (float) dm.heightPixels / (float) bitmap.getHeight());
        if (minScaleR > 1.0) {
            matrix.postScale((minScaleR-(int)minScaleR),(minScaleR-(int)minScaleR));
        }
        else{
            matrix.postScale(minScaleR, minScaleR);
        }
    }

    private PointF prev = new PointF();
    private PointF mid = new PointF();
    private float dist = 1f;

    public float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return FloatMath.sqrt(x * x + y * y);
    }

    public void midPoint(PointF point, MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        point.set(x / 2, y / 2);
    }

    public void setImageSize(){

        imageView.setOnTouchListener(new OnTouchListener(){

            @Override
            public boolean onTouch(View arg0, MotionEvent event) {
                switch (event.getAction() & MotionEvent.ACTION_MASK) {

                    case MotionEvent.ACTION_DOWN:
                        savedMatrix.set(matrix);
                        prev.set(event.getX(), event.getY());
                        mode = DRAG;
                        break;

                    case MotionEvent.ACTION_POINTER_DOWN:
                        dist = spacing(event);
                        // 如果兩點距離超過10, 就判斷為多點觸控模式 即為縮放模式
                        if (spacing(event) > 10f) {
                            savedMatrix.set(matrix);
                            midPoint(mid, event);
                            mode = ZOOM;
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_POINTER_UP:
                        mode = NONE;
                        break;
                    case MotionEvent.ACTION_MOVE:
                      /*  if (mode == DRAG) {
                            matrix.set(savedMatrix);
                            matrix.postTranslate(event.getX() - prev.x, event.getY()
                                    - prev.y);
                        } else */if (mode == ZOOM) {
                            float newDist = spacing(event);//偵測兩根手指移動的距離
                            if (newDist > 10f) {
                                matrix.set(savedMatrix);
                                float tScale = newDist / dist;
                                matrix.postScale(tScale, tScale, mid.x, mid.y);
                            }

                        }
                        break;
                }
                imageView.setImageMatrix(matrix);
                return true;
            }

        });
    }

    private Bitmap getViewBitmap(View v) {
        v.clearFocus();
        v.setPressed(false);

        boolean willNotCache = v.willNotCacheDrawing();
        v.setWillNotCacheDrawing(false);

        // Reset the drawing cache background color to fully transparent
        // for the duration of this operation
        int color = v.getDrawingCacheBackgroundColor();
        v.setDrawingCacheBackgroundColor(0);

        if (color != 0) {
            v.destroyDrawingCache();
        }
        v.buildDrawingCache();
        Bitmap cacheBitmap = v.getDrawingCache();
        if (cacheBitmap == null) {
            return null;
        }

        Bitmap bitmap = Bitmap.createBitmap(cacheBitmap);

        // Restore the view
        v.destroyDrawingCache();
        v.setWillNotCacheDrawing(willNotCache);
        v.setDrawingCacheBackgroundColor(color);

        return bitmap;
    }
}
