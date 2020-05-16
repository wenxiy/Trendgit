package com.example.trend.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trend.R;
import com.example.trend.service.entity.Developers;
import com.facebook.drawee.view.SimpleDraweeView;

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
        private SimpleDraweeView maver;
        private TextView mitem_t1;
        private TextView mitem_t2;
        public InnerHolder(View itemView) {
            super(itemView);
            maver=(SimpleDraweeView) itemView.findViewById(R.id.aver);
            mitem_t1=(TextView)itemView.findViewById(R.id.item_t1);
            mitem_t2=(TextView)itemView.findViewById(R.id.item_t2);
        }
        public void setData(Developers data){
                maver.setImageURI(data.getAvatar());
                mitem_t1.setText(data.);
        }
    }
}

