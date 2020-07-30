package com.example.trend.ui.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trend.R;
import com.example.trend.service.model.Repositories;
import com.example.trend.service.model.Repository;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.InnerHolder>  {
    private List<Repository> repositories;
    private OnItemClickListener monItemClickListener;
    int expandPosition = -1;
    public ListViewAdapter(List<Repository> repositories) {
        this.repositories = repositories;
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
        holder.setData(repositories.get(position), position);
        holder.description.setVisibility(position == expandPosition ? View.VISIBLE : View.GONE);
        holder.languages.setVisibility(position == expandPosition ? View.VISIBLE : View.GONE);
        holder.star.setVisibility(position == expandPosition ? View.VISIBLE : View.GONE);
        holder.fork.setVisibility(position == expandPosition ? View.VISIBLE : View.GONE);
        holder.lauguages_icon.setVisibility(position == expandPosition ? View.VISIBLE : View.GONE);
        holder.star_icon.setVisibility(position == expandPosition ? View.VISIBLE : View.GONE);
        holder.fork_icon.setVisibility(position == expandPosition ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        if (repositories != null) {
            return repositories.size();
        }
        return 0;
    }

    public void togglePosition(int position) {
        if (expandPosition != position) {//如果闪屏就用notifydatasetchange
            notifyItemChanged(expandPosition);
            expandPosition = position;
        } else {
            expandPosition = -1;
        }
        notifyItemChanged(position);
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
        private TextView description;
        private TextView languages;
        private TextView star;
        private TextView fork;
        private ImageView star_icon;
        private ImageView lauguages_icon;
        private ImageView fork_icon;
        public InnerHolder(View itemView) {
            super(itemView);
            maver = itemView.findViewById(R.id.aver);
            user_name = itemView.findViewById(R.id.user_name);
            repo_name = itemView.findViewById(R.id.repo_name);
            description = itemView.findViewById(R.id.description);
            languages = itemView.findViewById(R.id.languages);
            star = itemView.findViewById(R.id.star);
            fork = itemView.findViewById(R.id.fork);
            star_icon = itemView.findViewById(R.id.star_icon);
            lauguages_icon = itemView.findViewById(R.id.languages_icon);
            fork_icon = itemView.findViewById(R.id.fork_icon);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (monItemClickListener != null) {
                        monItemClickListener.OnItemClick(mPosition);
                        togglePosition(mPosition);
                    }
                }
            });
        }

        public void setData(Repository developer, int position) {
            this.mPosition = position;
            Uri uri = Uri.parse(developer.getAvatar());
            maver.setImageURI(uri);
            user_name.setText(developer.getAuthor());
            repo_name.setText(developer.getName());
            description.setText(developer.getDescription());
            languages.setText(developer.getLanguage());
            star.setText(String.valueOf(developer.getStars()));
            fork.setText(String.valueOf(developer.getForks()));
        }
    }
}

