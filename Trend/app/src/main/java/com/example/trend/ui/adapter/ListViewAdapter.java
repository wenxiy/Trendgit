package com.example.trend.ui.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trend.R;
import com.example.trend.service.model.Developers;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.InnerHolder> {
    private List<Developers> mdeveloper;

    public ListViewAdapter(List<Developers> developers) {
        mdeveloper = developers;
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //传入条目
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_list, parent, false);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        //设置数据
        holder.setData(mdeveloper.get(position));
    }

    @Override
    public int getItemCount() {
        if (mdeveloper != null) {
            return mdeveloper.size();
        }
        return 0;
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView maver;
        private TextView mitem_t1;
        private TextView mitem_t2;

        public InnerHolder(View itemView) {
            super(itemView);
            maver = itemView.findViewById(R.id.aver);
            mitem_t1 = itemView.findViewById(R.id.item_t1);
            mitem_t2 = itemView.findViewById(R.id.item_t2);
        }

        public void setData(Developers developer) {
            Uri uri = Uri.parse(developer.getAvatar());
            maver.setImageURI(uri);
            mitem_t1.setText(developer.getUsername());
            mitem_t2.setText(developer.getName());
        }
    }
}

