package fragment;

import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.KFCBETA.hjeaimreus.chikan.R;

public class InternationalFragment extends Fragment {

    View view_test;
    ScrollView test;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view_test = inflater.inflate(R.layout.fragment_international, container, false);

        view_test.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN)
                {
                    Log.w("log","action_down");
                    return true;
                }
                return false;
            }
        });

        // Inflate the layout for this fragment
        return view_test;
    }

}
