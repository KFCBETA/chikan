package fragment;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.text.method.Touch;
import android.util.DisplayMetrics;
import android.util.FloatMath;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.KFCBETA.hjeaimreus.chikan.ChikanActivity;
import com.KFCBETA.hjeaimreus.chikan.R;

public class NewsFragment extends Fragment {

    private View Test_view;
    private Bitmap bitmap;
    private DisplayMetrics dm;
    private float dist;

    public float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return FloatMath.sqrt(x * x + y * y);
    }

    private static final int NONE = 0;// 初始狀態
    private static final int DRAG = 1;// 拖曳狀態
    private static final int ZOOM = 2;// 縮放狀態
    private int mode = NONE;
    private float zoom;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Test_view = inflater.inflate(R.layout.fragment_news,container,false);

        Test_view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                Log.w("touch","isTouched");

                switch(motionEvent.getAction() & MotionEvent.ACTION_MASK){

                    case MotionEvent.ACTION_POINTER_DOWN:
                        dist = spacing(motionEvent);

                        if(spacing(motionEvent) > 10f)
                        {
                            mode = ZOOM;
                        }
                        break;

                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_POINTER_UP:
                        mode = NONE;
                        break;

                    case MotionEvent.ACTION_MOVE:
                        if(mode == ZOOM)
                        {
                            Log.w("action","Zoom");
                            float newDist = spacing(motionEvent);

                            if(newDist > 10f)
                            {
                                zoom = (float) newDist/dist;
                            }
                        }
                        break;
                }
                return false;
            }
        });


        // Inflate the layout for this fragment
        return Test_view;
    }


}
