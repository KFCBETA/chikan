package fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;

import com.KFCBETA.hjeaimreus.chikan.InternationalViewPager;
import com.KFCBETA.hjeaimreus.chikan.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class InternationalFragment extends Fragment {

    final private String LISTIMAGE = "image";
    final private String LISTTITLE = "title";

    private ListView international_ListView;
    private String[] international_string;
    private List<HashMap<String, String>> international_list;
    private SimpleAdapter international_adapter;
    private int[] international_image = new int[] {R.drawable.international_1, R.drawable.ic_action_accept, R.drawable.ic_action_accept, R.drawable.ic_action_accept,
            R.drawable.ic_action_accept, R.drawable.ic_action_accept};
    private RelativeLayout international_layout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_international,container,false);

        international_string = new String[] {"Russian aid convoy into Ukraine called 'direct invasion'", "Report: At least 38 killed after tourist buses crash in Egypt",
                "international_3", "international_4", "international_5", "international_6"};

        international_list = new ArrayList<HashMap<String, String>>();
        for(int i=0;i<international_string.length;i++)
        {
            HashMap<String, String> tmp = new HashMap<String, String>();
            tmp.put(LISTIMAGE, Integer.toString(international_image[i]));
            tmp.put(LISTTITLE,international_string[i]);
            international_list.add(tmp);
        }

        String[] from = {LISTIMAGE, LISTTITLE};
        int[] to = {R.id.list_image, R.id.list_title};

        international_adapter = new SimpleAdapter(getActivity(),international_list,R.layout.list_layout,from,to);

        international_ListView = (ListView)view.findViewById(R.id.international_listview);

        international_ListView.setAdapter(international_adapter);

        international_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent();

                Bundle bundle = new Bundle();
                bundle.putInt("position",i);

                intent.putExtras(bundle);
                intent.setClass(getActivity(), InternationalViewPager.class);

                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });

        return view;
    }




}
