package fragment;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.KFCBETA.hjeaimreus.chikan.R;

public class NewsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_news, container, false);


        TextView textView = (TextView)view.findViewById(R.id.new_headLine);

        Typeface font = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Roboto-Black.ttf");
        textView.setTypeface(font);

        return view;
    }

}
