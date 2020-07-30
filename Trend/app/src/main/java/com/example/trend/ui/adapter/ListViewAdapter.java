package com.example.trend.ui.adapter;

import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trend.R;
import com.example.trend.service.model.Developers;
import com.example.trend.service.model.Repositories;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.InnerHolder> {
    private List<Developers> mdeveloper;
    private OnItemClickListener monItemClickListener;

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
        holder.setData(mdeveloper.get(position), position);
    }

    @Override
    public int getItemCount() {
        if (mdeveloper != null) {
            return mdeveloper.size();
        }
        return 0;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        //设置监听回调接口
        this.monItemClickListener = listener;
    }

    /*
    编写回调的步骤
    1. 创建一个接口
    2. 定义接口内部的方法
    3. 内部设置接口
    4. 接口方法的调用
     */
    public interface OnItemClickListener {
        void OnItemClick(int position);
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView maver;
        private TextView user_name;
        private TextView repo_name;
        private int mPosition;

        public InnerHolder(View itemView) {
            super(itemView);
            maver = itemView.findViewById(R.id.aver);
            user_name = itemView.findViewById(R.id.user_name);
            repo_name = itemView.findViewById(R.id.repo_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (monItemClickListener != null) {
                        monItemClickListener.OnItemClick(mPosition);
                    }
                }
            });
        }

        public void setData(Developers developer, int position) {
            this.mPosition = position;
          //  Log.d("TAG",developer.getRepo().getDescription());
            Uri uri = Uri.parse(developer.getAvatar());
            maver.setImageURI(uri);
            user_name.setText(developer.getAuthor());
            repo_name.setText(developer.getName());
        }
    }
}

