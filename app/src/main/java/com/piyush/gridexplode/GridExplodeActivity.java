package com.piyush.gridexplode;

import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.transition.Explode;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GridExplodeActivity extends AppCompatActivity implements RecyclerItemClickListener {

    @BindView(R.id.recycler) RecyclerView mRecyclerView;
    private String[] mDataset;
    private GridRecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_explode);
        ButterKnife.bind(this);
        initDataset();

        mAdapter = new GridRecyclerAdapter(mDataset,this);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initDataset() {
        mDataset = new String[15];
        for (int i = 0; i < 15; i++) {
            mDataset[i] = String.valueOf(i);
        }
    }

    @Override
    public void itemClicked(View v) {
        final Rect viewRect = new Rect();
        v.getGlobalVisibleRect(viewRect);

        Transition explode = new Explode();

        explode.setEpicenterCallback(new Transition.EpicenterCallback() {
            @Override
            public Rect onGetEpicenter(Transition transition) {
                return viewRect;
            }
        });

        explode.setDuration(1000);
        explode.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override
            public void onTransitionEnd(Transition transition) {
                new Handler().postDelayed(() -> {
                    mRecyclerView.setAdapter(mAdapter);
                },1000);
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });
        TransitionManager.beginDelayedTransition(mRecyclerView, explode);
        mRecyclerView.setAdapter(null);
    }


}

