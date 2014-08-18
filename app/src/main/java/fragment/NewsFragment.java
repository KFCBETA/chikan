package fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.util.FloatMath;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;

import com.KFCBETA.hjeaimreus.chikan.R;


public class NewsFragment extends Fragment {

    private View Test_view;


    private static final int NONE = 0;// 初始狀態
    private static final int DRAG = 1;// 拖曳狀態
    private static final int ZOOM = 2;// 縮放狀態
    private int mode = NONE;

    private float dist = 1f;

    public float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return FloatMath.sqrt(x*x + y*y);
    }
    private ScrollView mScrollView;
    private HorizontalScrollView hoScrollView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Test_view = inflater.inflate(R.layout.fragment_news,container,false);

        mScrollView = (ScrollView)getActivity().findViewById(R.id.Scroll);
        hoScrollView = (HorizontalScrollView)getActivity().findViewById(R.id.hoScroll);

        if(hoScrollView == null)
        {
            Log.w("log","null");
        }
        else
        {
            hoScrollView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    view.getParent().requestDisallowInterceptTouchEvent(true);

                    Log.w("log","hoscroll");
                }
            });
        }


        Test_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                hoScrollView.requestDisallowInterceptTouchEvent(true);
                mScrollView.requestDisallowInterceptTouchEvent(true);

                Log.w("log","clicked");

            }
        });


        return Test_view;
    }



    @Override
    public void onStart()
    {
        super.onStart();

    }

}
