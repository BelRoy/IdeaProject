package com.devqt.idea.project.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.devqt.idea.project.R;

import java.util.ArrayList;



public class FirebaseHelper extends BaseAdapter {

        ArrayList<Items> items=new ArrayList<>();
        Context context;

        public FirebaseHelper(Context context,ArrayList<Items> art){
                items=art;
                this.context=context;
        }
        @Override
        public int getCount() {
                return items.size();
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
                View view=inflater.inflate(R.layout.content_android_fragment,parent,false);

                TextView txtName= (TextView) view.findViewById(R.id.nameTxt);
             //   TextView icon= (TextView) view.findViewById(R.id.tv_art);

                Items items1=items.get(position);

                txtName.setText(items1.getName());
              //  tv_art.setText(artist.getItems());
                return view;
        }
}