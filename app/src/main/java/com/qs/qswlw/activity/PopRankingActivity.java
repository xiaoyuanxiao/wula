package com.qs.qswlw.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qs.qswlw.R;
import com.qs.qswlw.activity.PersonalCenter.BaseInfoActivity;
import com.qs.qswlw.fragment.PopRankingCenterFragment;
import com.qs.qswlw.fragment.PopRankingLeftFragment;

import java.util.ArrayList;

import static com.qs.qswlw.R.id.ll_container;

/**
 * Created by xiaoyu on 2017/8/28.
 */

public class PopRankingActivity extends BaseInfoActivity {
    private RadioGroup fg_unionranking;
    private RelativeLayout day_ranking, week_ranking, month_ranking;
    private TextView tv_ranking_left, tv_ranking_right;
    private String content;
    private ArrayList<Fragment> fragments;
    private FragmentManager fragmentManager;
    private String role = "";
    private String time_slot = "";
    private PopRankingLeftFragment popRankingLeftFragment, popRankingLeftFragment2;
    private PopRankingCenterFragment popRankingCenterFragment;
    private String city;
    private FragmentManager manager;

    @Override
    public View setConetnView() {
        View inflate = View.inflate(this, R.layout.activity_mainranking, null);
        fg_unionranking = (RadioGroup) inflate.findViewById(R.id.fg_unionranking);
        day_ranking = (RelativeLayout) inflate.findViewById(R.id.day_ranking);
        week_ranking = (RelativeLayout) inflate.findViewById(R.id.week_ranking);
        month_ranking = (RelativeLayout) inflate.findViewById(R.id.month_ranking);
        tv_ranking_left = (TextView) inflate.findViewById(R.id.tv_ranking_left);
        tv_ranking_right = (TextView) inflate.findViewById(R.id.tv_ranking_right);
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
        //   fragmentManager = getSupportFragmentManager();
        initFragment();
    }

    /**
     * 初始化所有基fragment
     */
    private void initFragment() {
        manager = getSupportFragmentManager();
         FragmentTransaction transaction= manager.beginTransaction();
        popRankingLeftFragment = new PopRankingLeftFragment(role, time_slot);
        transaction.add(ll_container, popRankingLeftFragment);
        transaction.commit();
//        fragments = new ArrayList<Fragment>();
//        popRankingLeftFragment = new PopRankingLeftFragment(role,time_slot);
//        fragments.add(popRankingLeftFragment);
//        showFragment(fragments.get(0));

    }

    /**
     * 显示fragment
     *
     * @param fragment 要显示的fragment
     */
    private void showFragment(Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragment(transaction);
        if (fragment.isAdded()) {
            transaction.show(fragment);
        } else {
            transaction.add(ll_container, fragment, fragment.getClass().getName());
        }
        transaction.commit();
    }

    /**
     * 隐藏其他fragment
     *
     * @param transaction 控制器
     */
    private void hideFragment(FragmentTransaction transaction) {
        for (int i = 0; fragments.size() > i; i++) {
            if (fragments.get(i).isVisible()) {
                transaction.hide(fragments.get(i));
            }
        }
    }

    @Override
    public void setOnclick() {
        super.setOnclick();
        day_ranking.setOnClickListener(this);
        week_ranking.setOnClickListener(this);
        month_ranking.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.day_ranking:
                content = "日排名";
                time_slot = "ri";
                showDialog(content);
                break;
            case R.id.week_ranking:
                content = "周排名";
                time_slot = "zhou";
                showDialog(content);
                break;
            case R.id.month_ranking:
                content = "月排名";
                time_slot = "yue";
                showDialog(content);
                break;
        }


    }

    private void showDialog(final String content) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final String[] sex = {"省代", "市代", "区代"};
        /**
         *  设置一个单项选择下拉框
         */
        builder.setSingleChoiceItems(sex, 1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tv_ranking_left.setText(content);
                city = sex[which];
                tv_ranking_right.setText(sex[which].substring(0, 1) + "管理中心");
            }
        });
        builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });
        builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {



            @Override
            public void onClick(DialogInterface dialog, int which) {
                if ("省代".equals(city)) {
                    role = "";
                }
                if ("市代".equals(city)) {
                    role = "shi";
                }
                if ("区代".equals(city)) {
                    role = "qu";
                }
                if ("日排名".equals(content)) {
                    FragmentTransaction transaction= manager.beginTransaction();
                    popRankingLeftFragment = new PopRankingLeftFragment(role, time_slot);
                    transaction.add(R.id.ll_container, popRankingLeftFragment);
                    transaction.commit();

                    //     showFragment(fragments.get(0));
                } else if ("周排名".equals(content)) {
                    FragmentTransaction transaction= manager.beginTransaction();
                    popRankingCenterFragment = new PopRankingCenterFragment(role, time_slot);
                    transaction.replace(R.id.ll_container, popRankingCenterFragment);

                    transaction.commit();
                    //       showFragment(fragments.get(1));
                } else if ("月排名".equals(content)) {
                    FragmentTransaction transaction= manager.beginTransaction();
                    popRankingLeftFragment2 = new PopRankingLeftFragment(role, time_slot);
                    transaction.replace(R.id.ll_container, popRankingLeftFragment2);

                    transaction.commit();
                }
                dialog.dismiss();


            }
        });
        builder.show();
    }


}
