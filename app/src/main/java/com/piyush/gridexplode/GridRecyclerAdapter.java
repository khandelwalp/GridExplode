package com.piyush.gridexplode;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class GridRecyclerAdapter extends RecyclerView.Adapter<GridRecyclerAdapter.ViewHolder> {

    private String[] mDataSet;
    private RecyclerItemClickListener mListener;

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mTextVw;

        ViewHolder(View v) {
            super(v);
            mTextVw = v.findViewById(R.id.textview);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mListener.itemClicked(v);
        }
    }

    GridRecyclerAdapter(String[] dataSet, RecyclerItemClickListener listener) {
        mDataSet = dataSet;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_list_item, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.mTextVw.setText(mDataSet[position]);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataSet.length;
    }
}
