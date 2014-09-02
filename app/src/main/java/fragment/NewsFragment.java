package fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.KFCBETA.hjeaimreus.chikan.NewsViewPager;
import com.KFCBETA.hjeaimreus.chikan.R;

public class NewsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        LinearLayout linearLayout_1 = (LinearLayout)view.findViewById(R.id.new_1_box);
        LinearLayout linearLayout_2 = (LinearLayout)view.findViewById(R.id.new_2_box);
        LinearLayout linearLayout_3 = (LinearLayout)view.findViewById(R.id.new_3_box);
        LinearLayout linearLayout_4 = (LinearLayout)view.findViewById(R.id.new_4_box);
        LinearLayout linearLayout_5 = (LinearLayout)view.findViewById(R.id.new_5_box);
        LinearLayout linearLayout_6 = (LinearLayout)view.findViewById(R.id.new_6_box);

        linearLayout_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt("position",1);
                bundle.putInt("file_count",6);

                intent.putExtras(bundle);
                intent.setClass(getActivity(), NewsViewPager.class);

                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });
        linearLayout_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt("position",2);
                bundle.putInt("file_count",6);

                intent.putExtras(bundle);
                intent.setClass(getActivity(), NewsViewPager.class);

                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });
        linearLayout_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt("position",3);
                bundle.putInt("file_count",6);

                intent.putExtras(bundle);
                intent.setClass(getActivity(), NewsViewPager.class);

                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });
        linearLayout_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt("position",4);
                bundle.putInt("file_count",6);

                intent.putExtras(bundle);
                intent.setClass(getActivity(), NewsViewPager.class);

                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });
        linearLayout_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt("position",5);
                bundle.putInt("file_count",6);

                intent.putExtras(bundle);
                intent.setClass(getActivity(), NewsViewPager.class);

                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });
        linearLayout_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt("position",6);
                bundle.putInt("file_count",6);

                intent.putExtras(bundle);
                intent.setClass(getActivity(), NewsViewPager.class);

                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });

        return view;
    }

}
