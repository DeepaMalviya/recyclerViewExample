package com.maalgaadi.admin.recyclerviewexample;

import android.graphics.Color;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;

import com.marshalchen.ultimaterecyclerview.UltimateDifferentViewTypeAdapter;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.animators.FlipInLeftYAnimator;
import com.marshalchen.ultimaterecyclerview.animators.LandingAnimator;
import com.marshalchen.ultimaterecyclerview.animators.SlideInLeftAnimator;
import com.marshalchen.ultimaterecyclerview.itemTouchHelper.SimpleItemTouchHelperCallback;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    UltimateRecyclerView ultimateRecyclerView;
    LinearLayoutManager linearLayoutManager;
    CutsomAdapter adapter;
    List<String> stringList = new ArrayList<>();
    int moreNum = 2;
    private ItemTouchHelper mItemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ultimateRecyclerView = (UltimateRecyclerView) findViewById(R.id.recyclerView);
        ultimateRecyclerView.setHasFixedSize(true);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        ultimateRecyclerView.setItemAnimator(itemAnimator);
        ultimateRecyclerView.setItemAnimator(new LandingAnimator());
        ultimateRecyclerView.setRefreshing(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        ultimateRecyclerView.setLayoutManager(linearLayoutManager);

        adapter = new CutsomAdapter(getarrayList());
        ultimateRecyclerView.setAdapter(adapter);

        ultimateRecyclerView.enableLoadmore();
        addCustomLoaderView();

        ultimateRecyclerView.
                setRecylerViewBackgroundColor(Color.parseColor("#ffffff"));

        swipeRefresh();
        dragList();
        infiniteInsertlist();

    }

    private void infiniteInsertlist() {
        ultimateRecyclerView.setOnLoadMoreListener(new UltimateRecyclerView.OnLoadMoreListener() {
            @Override
            public void loadMore(int itemsCount, final int maxLastVisiblePosition) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.insert("more" + moreNum++, adapter.getAdapterItemCount());
                        adapter.insert("More" + moreNum++, adapter.getAdapterItemCount());
                       /* adapter.insert("More" + moreNum++, adapter.getAdapterItemCount());
                        adapter.insert("More" + moreNum++, adapter.getAdapterItemCount());*/


                    }
                }, 1000);

            }
        });
    }

    private void dragList() {
        ItemTouchHelper.Callback callback =
                new SimpleItemTouchHelperCallback(adapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(ultimateRecyclerView.mRecyclerView);
        adapter.setOnDragStartListener
                (new CutsomAdapter.OnStartDragListener() {
                    @Override
                    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
                        mItemTouchHelper.startDrag(viewHolder);
                    }
                });


    }

    private void swipeRefresh() {
        ultimateRecyclerView.setDefaultOnRefreshListener
                (new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                adapter.insert(moreNum++ + "  Refresh things", 0);
                                ultimateRecyclerView.setRefreshing(false);
                                linearLayoutManager.scrollToPosition(0);

                            }
                        }, 1000);
                    }
                });


    }


    private void addCustomLoaderView() {
        adapter.setCustomLoadMoreView(LayoutInflater.from(this).inflate(R.layout.custom_bottom_progressbar, null));

    }

    public List<String> getarrayList() {
        stringList.add("1111");
        stringList.add("222");
        stringList.add("333");
        stringList.add("444");
        stringList.add("555");
        stringList.add("666");
        stringList.add("777");
        stringList.add("888");
        stringList.add("222");
        stringList.add("333");
        stringList.add("444");
        stringList.add("555");
        stringList.add("666");
        stringList.add("777");
        stringList.add("888");
        return stringList;
    }


}

