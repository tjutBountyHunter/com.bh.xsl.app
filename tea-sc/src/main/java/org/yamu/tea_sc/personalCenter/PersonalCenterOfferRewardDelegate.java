package org.yamu.tea_sc.personalCenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.yamu.tea_core.delegate.TeaDelegate;
import org.yamu.tea_sc.R;
import org.yamu.tea_sc.R2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class PersonalCenterOfferRewardDelegate extends TeaDelegate {
    private static PersonalCenterOfferRewardDelegate fragment;

    @BindView(R2.id.recy_offer_reward)
    RecyclerView recyclerView;
    List<OfferReward> offerRewardList;
    @BindView(R2.id.rabt_group)
    RadioGroup radioGroup;


    public static Fragment newInstance() {
        if (fragment == null)
            fragment = new PersonalCenterOfferRewardDelegate();
        return fragment;
    }

    public static PersonalCenterOfferRewardDelegate getInstance() {
        if (fragment == null)
            fragment = new PersonalCenterOfferRewardDelegate();
        return fragment;
    }

    private void initView() {
        radioGroup.check(R.id.rabt_all);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        offerRewardList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            offerRewardList.add(new OfferReward());
        }
        recyclerView.setAdapter(new OfferRewardRecyclerAdapter(R.layout.item_offer_reward_card, offerRewardList));


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Toast.makeText(getContext(), checkedId + "", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void setTopBarcheckedItem(int topBarcheckedItem) {
        int id;
        switch (topBarcheckedItem) {
            case 0:
                id = R.id.rabt_all;
                break;
            case 1:
                id = R.id.rabt_yifa;
                break;
            case 2:
                id = R.id.rabt_yijie;
                break;
            case 3:
                id = R.id.rabt_daiwanchen;
                break;
            case 4:
                id = R.id.rabt_wanchen;
                break;
            default:
                id = R.id.rabt_all;
                break;
        }
        radioGroup.check(id);
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_personal_center_offer_reward;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initView();
    }
}
