package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.KFCBETA.hjeaimreus.chikan.InternationalViewPager;
import com.KFCBETA.hjeaimreus.chikan.R;


public class InternationalFragment extends Fragment {


    private ListView international_ListView;
    private String[] international_string;
    private ArrayAdapter<String> international_adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        international_string = new String[] {"international_1", "international_2", "international_3", "international_4",
                "international_5", "international_6", "international_7"};

        View view = inflater.inflate(R.layout.fragment_international,container,false);

        international_ListView = (ListView)view.findViewById(R.id.international_listview);
        international_adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,international_string);
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
