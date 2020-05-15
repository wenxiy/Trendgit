package com.example.trend.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trend.R;
import com.example.trend.service.entity.Developers;
import java.util.List;
import java.util.Objects;

import static android.view.View.inflate;

public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.InnerHolder> {
    private final List<Developers> mdeveloper;

    public ListViewAdapter(List<Developers> developers) {
        this.mdeveloper = developers;
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //传入条目
    View view=View.inflate(parent.getContext(), R.layout.item_list,null);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        //设置数据
        holder.setData(mdeveloper.get(position));
    }

    @Override
    public int getItemCount() {
        if (mdeveloper!=null)
        {
            return mdeveloper.size();
        }
        return 0;
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        public InnerHolder(View itemView) {
            super(itemView);
            
        }
        public void setData(Developers data){

        }
    }
}

