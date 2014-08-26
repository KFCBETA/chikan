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
import com.KFCBETA.hjeaimreus.chikan.ScienceViewPager;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ScienceFragment extends Fragment {

    final private String LISTIMAGE = "image";
    final private String LISTTITLE = "title";

    private ListView science_ListView;
    private String[] science_string;
    private List<HashMap<String, String>> science_list;
    private SimpleAdapter science_adapter;
    private int[] science_image;
    private RelativeLayout science_layout;

    private ArrayList<ArrayList<String>> scienceFragmentInput;
    private DataBaseHelper dataBaseHelper;

    //the number of article
    private int file_count = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_science,container,false);

        dataBaseHelper = new DataBaseHelper(getActivity());
        scienceFragmentInput = dataBaseHelper.getIntNews();

        file_count = scienceFragmentInput.get(0).size();

        //read section title from the file
        science_image = new  int[file_count];
        science_string = new String[file_count];
        for(int i=0;i<file_count;i++)
        {
            science_image[i] = R.drawable.ic_action_accept;
            science_string[i] = scienceFragmentInput.get(0).get(i);
        }

        science_list = new ArrayList<HashMap<String, String>>();
        for(int i=0;i<science_string.length;i++)
        {
            HashMap<String, String> tmp = new HashMap<String, String>();
            tmp.put(LISTIMAGE, Integer.toString(science_image[i]));
            tmp.put(LISTTITLE,science_string[i]);
            science_list.add(tmp);
        }

        String[] from = {LISTIMAGE, LISTTITLE};
        int[] to = {R.id.list_image, R.id.list_title};

        science_adapter = new SimpleAdapter(getActivity(),science_list,R.layout.list_layout,from,to);

        science_ListView = (ListView)view.findViewById(R.id.science_listview);

        science_ListView.setAdapter(science_adapter);

        science_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent();

                Bundle bundle = new Bundle();
                bundle.putInt("position",i);
                bundle.putInt("file_count",file_count);

                intent.putExtras(bundle);
                intent.setClass(getActivity(), ScienceViewPager.class);

                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });

        return view;
    }

}
