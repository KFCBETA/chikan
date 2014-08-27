package fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

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
    private Drawable[] sport_image;

    private DataBaseHelper dataBaseHelper;
    private ArrayList<ArrayList<String>> sportFragmentInput;

    //the number of article
    private int file_count = 0;

    CustomAdapter sport_adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_sport,container,false);

        dataBaseHelper = new DataBaseHelper(getActivity());
        sportFragmentInput = dataBaseHelper.getIntNews();

        file_count = sportFragmentInput.get(0).size();

        //read section title from the file
        sport_image = new Drawable[file_count];
        sport_string = new String[file_count];

        for(int i=0;i<file_count;i++)
        {
            //sport_image[i] = ;
            sport_string[i] = sportFragmentInput.get(0).get(i);
        }

        sport_adapter = new CustomAdapter(getActivity(),R.layout.list_layout,sport_string);

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
    public class CustomAdapter extends ArrayAdapter<String> {

        public CustomAdapter(Context context, int resource, String[] objects) {
            super(context, resource, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            LayoutInflater inflater = getActivity().getLayoutInflater();
            View row = inflater.inflate(R.layout.list_layout,parent, false);
            TextView title = (TextView)row.findViewById(R.id.list_title);
            title.setText(sport_string[position]);
            ImageView image = (ImageView)row.findViewById(R.id.list_image);

            image.setImageResource(R.drawable.ic_action_accept);

            return row;
        }
    }
}
