package adruino_adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.devqt.idea.project.R;

import java.util.ArrayList;

public class ArdAdap extends BaseAdapter {

    ArrayList<ArdL> ardLs=new ArrayList<>();
    Context context;

    public ArdAdap(Context context,ArrayList<ArdL> art){
        ardLs=art;
        this.context=context;
    }
    @Override
    public int getCount() {
        return ardLs.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.list_adap_ard,parent,false);

        TextView tv_name= (TextView) view.findViewById(R.id.nameSpace);

        ArdL ardL=ardLs.get(position);

        tv_name.setText(ardL.getName());

        return view;
    }
}