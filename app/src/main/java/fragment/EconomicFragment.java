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

import com.KFCBETA.hjeaimreus.chikan.DataBaseHelper;
import com.KFCBETA.hjeaimreus.chikan.EconomicViewPager;
import com.KFCBETA.hjeaimreus.chikan.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class EconomicFragment extends Fragment {

    final private String LISTIMAGE = "image";
    final private String LISTTITLE = "title";

    private ListView economic_ListView;
    private String[] economic_string;
    private List<HashMap<String, String>> economic_list;
    private SimpleAdapter economic_adapter;
    private int[] economic_image;
    private RelativeLayout economic_layout;

    private ArrayList<ArrayList<String>> economicFragmentInput;
    private DataBaseHelper dataBaseHelper;

    //the number of article
    private int file_count;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_economic,container,false);

        dataBaseHelper = new DataBaseHelper(getActivity());
        economicFragmentInput = dataBaseHelper.getIntNews();

        file_count = economicFragmentInput.get(0).size();

        //read section title from the file
        economic_image = new  int[file_count];
        economic_string = new String[file_count];

        for(int i=0;i<file_count;i++)
        {
            economic_image[i] = R.drawable.ic_action_accept;
            economic_string[i] = economicFragmentInput.get(0).get(i);
        }

        economic_list = new ArrayList<HashMap<String, String>>();
        for(int i=0;i<file_count;i++)
        {
            HashMap<String, String> tmp = new HashMap<String, String>();
            tmp.put(LISTIMAGE, Integer.toString(economic_image[i]));
            tmp.put(LISTTITLE,economic_string[i]);
            economic_list.add(tmp);
        }

        String[] from = {LISTIMAGE, LISTTITLE};
        int[] to = {R.id.list_image, R.id.list_title};

        economic_adapter = new SimpleAdapter(getActivity(),economic_list,R.layout.list_layout,from,to);

        economic_ListView = (ListView)view.findViewById(R.id.economic_listview);

        economic_ListView.setAdapter(economic_adapter);

        economic_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent();

                Bundle bundle = new Bundle();
                bundle.putInt("position",i);
                bundle.putInt("file_count",file_count);

                intent.putExtras(bundle);
                intent.setClass(getActivity(), EconomicViewPager.class);

                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });

        return view;
    }

}