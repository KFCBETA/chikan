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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.KFCBETA.hjeaimreus.chikan.DataBaseHelper;
import com.KFCBETA.hjeaimreus.chikan.EconomicViewPager;
import com.KFCBETA.hjeaimreus.chikan.ParseCategories;
import com.KFCBETA.hjeaimreus.chikan.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class EconomicFragment extends Fragment {

    private ListView economic_ListView;
    private String[] economic_string;
    private Drawable[] economic_image;

    private ArrayList<ArrayList<String>> economicFragmentInput;
    private DataBaseHelper dataBaseHelper;

    //the number of article
    private int file_count;

    private Drawable drawableInput;
    ParseCategories parseCategories;

    CustomAdapter tmp_adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        //For testing
        parseCategories = new ParseCategories();
        View view = inflater.inflate(R.layout.fragment_economic,container,false);

        dataBaseHelper = new DataBaseHelper(getActivity());
        economicFragmentInput = dataBaseHelper.getIntNews();

        file_count = economicFragmentInput.get(0).size();

        //read section title from the file
        economic_image = new Drawable[file_count];
        economic_string = new String[file_count];

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                drawableInput = parseCategories.intoDrawable(parseCategories.getIntNewsPics(),getActivity());
//            }
//        }).start();
//        while(drawableInput == null){
//        }

        for(int i=0;i<file_count;i++)
        {
            economic_image[i] = drawableInput;
            economic_string[i] = economicFragmentInput.get(0).get(i);
        }

        economic_ListView = (ListView)view.findViewById(R.id.economic_listview);

        tmp_adapter = new CustomAdapter(getActivity(),R.layout.list_layout,economic_string);

        economic_ListView.setAdapter(tmp_adapter);

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
            title.setText(economic_string[position]);
            ImageView image = (ImageView)row.findViewById(R.id.list_image);

            image.setImageDrawable(drawableInput);

            return row;
        }
    }
}
