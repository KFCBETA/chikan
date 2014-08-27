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
import com.KFCBETA.hjeaimreus.chikan.EntertainmentViewPager;
import com.KFCBETA.hjeaimreus.chikan.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class EntertainmentFragment extends Fragment {

    private ListView entertainment_ListView;
    private String[] entertainment_string;
    private Drawable[] entertainment_image;


    private ArrayList<ArrayList<String>> entertainmentFragmentInput;
    private DataBaseHelper dataBaseHelper;

    //the number of article
    private int file_count = 0;

    CustomAdapter entertainment_adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_entertainment,container,false);

        dataBaseHelper = new DataBaseHelper(getActivity());
        entertainmentFragmentInput = dataBaseHelper.getIntNews();

        file_count = entertainmentFragmentInput.get(0).size();

        //read section title from the file
        entertainment_image = new Drawable[file_count];
        entertainment_string = new String[file_count];

        for(int i=0;i<file_count;i++)
        {
            //entertainment_image[i] = ;
            entertainment_string[i] = entertainmentFragmentInput.get(0).get(i);
        }

        entertainment_adapter = new CustomAdapter(getActivity(),R.layout.list_layout,entertainment_string);

        entertainment_ListView = (ListView)view.findViewById(R.id.entertainment_listview);
        entertainment_ListView.setAdapter(entertainment_adapter);
        entertainment_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent();

                Bundle bundle = new Bundle();
                bundle.putInt("position",i);
                bundle.putInt("file_count",file_count);

                intent.putExtras(bundle);
                intent.setClass(getActivity(), EntertainmentViewPager.class);

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
            title.setText(entertainment_string[position]);
            ImageView image = (ImageView)row.findViewById(R.id.list_image);

            image.setImageResource(R.drawable.ic_action_accept);

            return row;
        }
    }
}
