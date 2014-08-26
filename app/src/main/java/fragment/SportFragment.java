package fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;

import com.KFCBETA.hjeaimreus.chikan.DataBaseHelper;
import com.KFCBETA.hjeaimreus.chikan.R;
import com.KFCBETA.hjeaimreus.chikan.SportViewPager;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class SportFragment extends Fragment {

    final private String LISTIMAGE = "image";
    final private String LISTTITLE = "title";

    private ListView sport_ListView;
    private String[] sport_string;
    private List<HashMap<String, String>> sport_list;
    private SimpleAdapter sport_adapter;
    private int[] sport_image;
    private RelativeLayout sport_layout;

    private DataBaseHelper dataBaseHelper;
    private ArrayList<ArrayList<String>> sportFragmentInput;

    //the number of article
    private int file_count = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_sport,container,false);

        dataBaseHelper = new DataBaseHelper(getActivity());
        sportFragmentInput = dataBaseHelper.getIntNews();

        file_count = sportFragmentInput.get(0).size();

        //read section title from the file
        sport_image = new  int[file_count];
        sport_string = new String[file_count];

        for(int i=0;i<file_count;i++)
        {
            sport_image[i] = R.drawable.ic_action_accept;
            sport_string[i] = sportFragmentInput.get(0).get(i);
        }

        sport_list = new ArrayList<HashMap<String, String>>();
        for(int i=0;i<sport_string.length;i++)
        {
            HashMap<String, String> tmp = new HashMap<String, String>();
            tmp.put(LISTIMAGE, Integer.toString(sport_image[i]));
            tmp.put(LISTTITLE,sport_string[i]);
            sport_list.add(tmp);
        }

        String[] from = {LISTIMAGE, LISTTITLE};
        int[] to = {R.id.list_image, R.id.list_title};

        sport_adapter = new SimpleAdapter(getActivity(),sport_list,R.layout.list_layout,from,to);

        sport_ListView = (ListView)view.findViewById(R.id.sport_listview);

        sport_ListView.setAdapter(sport_adapter);

        sport_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent();

                Bundle bundle = new Bundle();
                bundle.putInt("position",i);
                bundle.putInt("file_count",file_count);

                intent.putExtras(bundle);
                intent.setClass(getActivity(), SportViewPager.class);

                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });

        return view;
    }

}
