package com.qs.qswlw.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.qs.qswlw.R;

/**
 * Created by xiaoyu on 2017/4/6.
 */

public class JoinPromotionLuckDrawAdapter extends BaseAdapter {
    private Context context;
    private String[] s = {"购买期数：5","购买期数：5","购买期数：5","购买期数：5","购买期数：5","购买期数：5","购买期数：5","购买期数：5",};

    public JoinPromotionLuckDrawAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return s.length;
    }

    @Override
    public Object getItem(int i) {
        return s[i];
    }

    @Override
    public long getItemId(int i) {

        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view =  View.inflate(context, R.layout.item_join_promotionluckdraw,null);
        return view;
    }
}
