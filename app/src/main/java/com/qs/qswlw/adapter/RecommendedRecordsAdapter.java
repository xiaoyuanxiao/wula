package com.qs.qswlw.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qs.qswlw.R;
import com.qs.qswlw.bean.RecommendedRecordsBean;
import com.qs.qswlw.utils.TimeUtils;

import java.util.List;

/**
 * Created by xiaoyu on 2017/4/19.
 */

public class RecommendedRecordsAdapter extends BaseListAdapter<RecommendedRecordsBean.ResultBean> {
    private Context context;

    public RecommendedRecordsAdapter(Context context, List<RecommendedRecordsBean.ResultBean> data) {
        super(context, data);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        RecommendedRecordsBean.ResultBean resultBean = data.get(i);
        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            view =  View.inflate(context, R.layout.item_recommendedrecords,null);
            holder.tv_recommendrecord_name = (TextView) view.findViewById(R.id.tv_recommendrecord_name);
            holder.tv_recommendrecord_role = (TextView) view.findViewById(R.id.tv_recommendrecord_role);
            holder.tv_recommendrecord_data = (TextView) view.findViewById(R.id.tv_recommendrecord_data);
            holder.iv_recommendrecord_avater = (ImageView) view.findViewById(R.id.iv_recommendrecord_avater);
            view.setTag(holder);

        }else{
            holder = (ViewHolder) view.getTag();
        }
        holder.tv_recommendrecord_name.setText(resultBean.getNickname());
        String role = resultBean.getRole();
        if(role.equals("0")){
            holder.tv_recommendrecord_role.setText("（消费天使）");
        }else if(role.equals("10")){
            holder.tv_recommendrecord_role.setText("（s商家）");
        }
        holder.tv_recommendrecord_data.setText(TimeUtils.getStrTime(resultBean.getReg_time()));
        //Glide.with(context).load(ReHttpUtils.getBaseUrl() + resultBean.get()).into(holder.iv_union_ranking);
        return view;
    }

    class ViewHolder{
        TextView tv_recommendrecord_name;
        TextView tv_recommendrecord_role;
        TextView tv_recommendrecord_data;
        ImageView iv_recommendrecord_avater;

    }
}
