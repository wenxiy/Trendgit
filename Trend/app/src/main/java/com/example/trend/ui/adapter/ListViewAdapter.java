package com.example.trend.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.trend.service.entity.Developers;
import java.util.List;
import java.util.Objects;

public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.InnerHolder> {
    private final List<Developers> mdeveloper;

    public ListViewAdapter(List<Developers> developers) {
        this.mdeveloper = developers;
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        public InnerHolder(View itemView) {
            super(itemView);
        }
    }
}

