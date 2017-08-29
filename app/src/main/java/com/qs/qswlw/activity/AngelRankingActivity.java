package com.qs.qswlw.activity;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qs.qswlw.Mode.AngelRankingMode;
import com.qs.qswlw.R;
import com.qs.qswlw.activity.PersonalCenter.BaseInfoActivity;
import com.qs.qswlw.bean.AngelRankingBean;
import com.qs.qswlw.okhttp.Iview.IAngelRankingView;
import com.qs.qswlw.okhttp.Presenter.AngelRankingPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoyu on 2017/8/28.
 */

public class AngelRankingActivity extends BaseInfoActivity implements IAngelRankingView {
    private ViewPager viewpager_unionranking;
    private RadioGroup fg_unionranking;
    private List<AngelRankingMode> viewpagedata;
    private RelativeLayout day_ranking, week_ranking, month_ranking;
    public AngelRankingPresenter angelRankingPresenter = new AngelRankingPresenter(this);
    private TextView tv_ranking_left;

    @Override
    public View setConetnView() {
        View inflate = View.inflate(this, R.layout.activity_unionranking, null);
        tv_ranking_left = (TextView) inflate.findViewById(R.id.tv_ranking_left);
        viewpager_unionranking = (ViewPager) inflate.findViewById(R.id.viewpager_unionranking);
        fg_unionranking = (RadioGroup) inflate.findViewById(R.id.fg_unionranking);
        day_ranking = (RelativeLayout) inflate.findViewById(R.id.day_ranking);
        week_ranking = (RelativeLayout) inflate.findViewById(R.id.week_ranking);
        month_ranking = (RelativeLayout) inflate.findViewById(R.id.month_ranking);
        viewpagedata = new ArrayList<>();
        viewpagedata.add(new AngelRankingMode(this, 1));
        viewpagedata.add(new AngelRankingMode(this, 2));
        viewpagedata.add(new AngelRankingMode(this, 3));
        MyViewPagerAdapter adapter = new MyViewPagerAdapter();
        viewpager_unionranking.setAdapter(adapter);
        viewpagedata.get(0).initData();
        return inflate;
    }

    @Override
    public void initfindviewByid() {
        super.initfindviewByid();
        tv_titlebar_center.setText("排名榜");
    }

    @Override
    public void initData() {
        super.initData();
        tv_ranking_left.setText("创业天使创业排名榜");
    }
    @Override
    public void setOnclick() {
        super.setOnclick();
        fg_unionranking.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
        day_ranking.setOnClickListener(this);
        week_ranking.setOnClickListener(this);
        month_ranking.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        int position = 0;
        switch (v.getId()) {

            case R.id.day_ranking:
                position = 0;
                break;
            case R.id.week_ranking:
                position = 1;
                break;
            case R.id.month_ranking:
                position = 2;
                break;
        }
        viewpager_unionranking.setCurrentItem(position);
        viewpagedata.get(position).initData();

    }


    /**
     * 页面切换监听
     */
    class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            int position = 0;
            switch (checkedId) {
                case R.id.day_ranking:
                    position = 0;
                    break;
                case R.id.week_ranking:
                    position = 1;
                    break;
                case R.id.month_ranking:
                    position = 2;
                    break;
            }
            viewpager_unionranking.setCurrentItem(position);
            viewpagedata.get(position).initData();
        }
    }


    public class MyViewPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return viewpagedata.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewpagedata.get(position).view);
            return viewpagedata.get(position).view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView(viewpagedata.get(position).view);
        }
    }


    @Override
    public void setRankMondayWek(List<AngelRankingBean.SalemanBean> list, int recode) {
        switch (recode) {
            case 1:
                viewpagedata.get(0).setdata(list);
                break;
            case 2:
                viewpagedata.get(1).setdata(list);
                break;
            case 3:
                viewpagedata.get(2).setdata(list);
                break;
        }
    }

}