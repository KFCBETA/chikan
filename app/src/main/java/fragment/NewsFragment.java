package fragment;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.KFCBETA.hjeaimreus.chikan.R;

import org.w3c.dom.Text;

public class NewsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        TextView textView = (TextView)view.findViewById(R.id.new_headLine);
        Animation animation = AnimationUtils.loadAnimation(getActivity(),R.anim.rotate);
        textView.startAnimation(animation);

        return view;
    }

}
