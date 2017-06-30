package com.maalgaadi.admin.recyclerviewexample;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 6/29/2017.
 */

class CutsomAdapter extends UltimateViewAdapter<CutsomAdapter.SimpleAdapterViewHolder> {
    List<String> stringlist;
    Context context;
    int lastPosition = -1;
    private ImageView mbackground;


    public CutsomAdapter(List<String> stringlist) {
        this.stringlist = stringlist;
    }

    @Override
    public SimpleAdapterViewHolder getViewHolder(View view) {
        return new SimpleAdapterViewHolder(view, false);
    }

    @Override
    public SimpleAdapterViewHolder onCreateViewHolder(ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        SimpleAdapterViewHolder sv = new SimpleAdapterViewHolder(v, true);
        return sv;
    }

    @Override
    public int getAdapterItemCount() {
        return stringlist.size();
    }


    @Override
    public void onBindViewHolder(SimpleAdapterViewHolder holder, int position) {
        if (position < getItemCount() &&
                (customHeaderView != null ? position <= stringlist.size() : position < stringlist.size()) && (customHeaderView != null ? position > 0 : true))

            ((SimpleAdapterViewHolder) holder)
                    .textViewSample.setText(stringlist.get(position));
     /*   Picasso.with(context).load(R.drawable.demo)
                .into(holder.imageViewSample);*/
        //holder.textViewSample.setOnClickListener((View.OnClickListener) this);

    }


    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder
            (ViewGroup viewGroup) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.stick_header_item, viewGroup, false);
        return new RecyclerView.ViewHolder(view) {
        };
    }


    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
        TextView textView = (TextView) holder.itemView.findViewById(R.id.tv_stick);
        textView.setText(String.valueOf(getItem(position).charAt(0)));
        holder.itemView.setBackgroundColor(Color.parseColor("#AAffffff"));


        //**Glide.with(holder.)*/
        setAnimation(holder.itemView,position);


    }

    private void setAnimation(View viewToAnimate, int position) {
        boolean anim = true;

        if (anim) {
            anim = false;
            Animation animation = AnimationUtils.loadAnimation(context,
                    R.anim.card_flip_left_in);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        } else {

            anim = true;
            Animation animation = AnimationUtils.loadAnimation(context,
                    R.anim.card_flip_left_out);
            viewToAnimate.startAnimation(animation);
             lastPosition = position;

        }
    }


    public void insert(String string, int position) {
        insertInternal(stringlist, string, position);
    }


    public void remove(int position) {
        removeInternal(stringlist, position);
    }


    public void clear() {
        clearInternal(stringlist);
    }

    public void toggleSelection(int pos) {
        super.toggleSelection(pos);
    }

    public void setSelected(int pos) {
        super.setSelected(pos);
    }

    public void clearSelection(int pos) {
        super.clearSelection(pos);
    }

    public void swapPositions(int from, int to) {
        swapPositions(stringlist, from, to);
    }

    public long generateHeaderId(int position) {
        if (getItem(position).length() > 0) {
            return getItem(position).charAt(0);
        } else return -1;

    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        swapPositions(fromPosition, toPosition);
//        notifyItemMoved(fromPosition, toPosition);
        super.onItemMove(fromPosition, toPosition);
    }

    @Override
    public void onItemDismiss(int position) {
        if (position > 0)
            remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
        super.onItemDismiss(position);
    }


    public void setOnDragStartListener(OnStartDragListener
                                               dragStartListener) {
        mDragStartListener = dragStartListener;

    }


    public class SimpleAdapterViewHolder extends UltimateRecyclerviewViewHolder {

        TextView textViewSample;
        ImageView imageViewSample;
        ProgressBar progressBarSample;
        View item_view;

        public SimpleAdapterViewHolder(View itemView, boolean isItem) {
            super(itemView);

            if (isItem) {
                textViewSample = (TextView) itemView.findViewById(
                        R.id.text);
                imageViewSample = (ImageView)
                        itemView.findViewById(R.id.image);
                progressBarSample = (ProgressBar)
                        itemView.findViewById(R.id.progressbar);
                progressBarSample.setVisibility(View.GONE);
                item_view = itemView.findViewById(R.id.itemView);
            }

        }

        @Override
        public void onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY);
        }

        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(0);
        }
    }

    public String getItem(int position) {
        if (customHeaderView != null)
            position--;
        if (position < stringlist.size())
            return stringlist.get(position);
        else return "";
    }
}

