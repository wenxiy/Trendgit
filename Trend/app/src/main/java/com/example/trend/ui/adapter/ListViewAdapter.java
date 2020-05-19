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
            //debug   将aver与mitem换成自定义的资源
                maver.setImageURI(data.getAvatar());
          //  maver.setImageURI("data:image/jpeg;base64,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");
          //  mitem_t1.setText("111");
                mitem_t1.setText(data.getUsername());
         //  mitem_t2.setText("111");
            mitem_t2.setText(data.getName());
        }
    }
}

