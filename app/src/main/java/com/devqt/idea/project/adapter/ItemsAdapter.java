package com.devqt.idea.project.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.devqt.idea.project.R;

import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemsViewHolder> {

    private List<ItemsModel> list;

    public ItemsAdapter(List<ItemsModel> list) {
        this.list = list;
    }

    @Override
    public ItemsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.elements_row, parent, false));
    }

    @Override
    public void onBindViewHolder(final ItemsViewHolder holder, int position) {

        ItemsModel itemsModel = list.get(position);
        holder.name.setText(itemsModel.name);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ItemsViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        /*TextView description
        ImageView icon;
        VideoView video;*/

        public ItemsViewHolder(View itemView){
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.nameTxt);
            /*description = (TextView) itemView.findViewById(R.id.description);
            icon = (ImageView) itemView.findViewById(R.id.img);
            video = (VideoView) itemView.findViewById(R.id.videoApp);*/


        }

    }
}
