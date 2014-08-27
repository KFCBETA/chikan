package fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
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

import com.KFCBETA.hjeaimreus.chikan.InternationalViewPager;
import com.KFCBETA.hjeaimreus.chikan.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class InternationalFragment extends Fragment {


    private ListView international_ListView;
    private String[] international_string;
    private Drawable[] international_image;

    CustomAdapter tmp_adapter;


    //the number of article
    private int file_count = 2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_international,container,false);


        //read section title from the file
        international_image = new Drawable[file_count];
        international_string = new String[file_count];


        for(int i=0;i<file_count;i++)
        {
            int size;
            byte[] buffer;
            String title_number;
            InputStream file_in_title = null;
            title_number = Integer.toString(i+1) + "_title.txt";

            try {
                file_in_title = getResources().getAssets().open(title_number);
                size = file_in_title.available();
            } catch (IOException e) {
                size = 1;
                e.printStackTrace();
            }
            buffer = new byte[size];
            try {
                if(file_in_title != null)
                {
                    file_in_title.read(buffer);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            String tmp_title = new String(buffer);

            international_string[i] = tmp_title;
        }

        tmp_adapter = new CustomAdapter(getActivity(),R.layout.list_layout,international_string);

        international_ListView = (ListView)view.findViewById(R.id.international_listview);

        international_ListView.setAdapter(tmp_adapter);

        international_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent();
                Bundle bundle = new Bundle();

                bundle.putInt("position",i);
                bundle.putInt("file_count",file_count);

                intent.putExtras(bundle);
                intent.setClass(getActivity(), InternationalViewPager.class);

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
            title.setText(international_string[position]);
            ImageView image = (ImageView)row.findViewById(R.id.list_image);

            image.setImageResource(R.drawable.ic_action_accept);

            return row;
        }
    }
}
