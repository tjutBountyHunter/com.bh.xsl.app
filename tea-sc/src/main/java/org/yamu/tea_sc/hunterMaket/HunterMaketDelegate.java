package org.yamu.tea_sc.hunterMaket;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import org.yamu.tea_core.delegate.TeaDelegate;
import org.yamu.tea_sc.R;
import org.yamu.tea_sc.R2;
import org.yamu.tea_sc.hunterMaket.Entity.HunterMaketCard;
import org.yamu.tea_sc.hunterMaket.Entity.HunterMaketClass;
import org.yamu.tea_sc.hunterMaket.adapter.MultipleItem;
import org.yamu.tea_sc.hunterMaket.adapter.MultipleItemQuickAdapter;
import org.yamu.tea_sc.hunterMaket.adapter.MyHunterRecyclerAdapter;
import org.yamu.tea_sc.hunterMaket.adapter.TagHunterRecyclerAdapter;
import org.yamu.tea_sc.widget.LineButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by mjt89 on 2018/10/4 0004
 */

public class HunterMaketDelegate extends TeaDelegate {

    @BindView(R2.id.relv_tag)
    RecyclerView mTagRecycler = null;
    @BindView(R2.id.relv_my_hunter)
    RecyclerView mMyHunterRecycle = null;
    @BindView(R2.id.relv_hot_hunter)
    RecyclerView mHotHunterRecycle = null;
    @BindView(R2.id.line_bt_my_hunter)
    LineButton mMyHunterButton = null;
    @BindView(R2.id.line_bt_hot_hunter)
    LineButton mHotHunterButton = null;

    private final MultipleItemQuickAdapter mHotAdapter = new MultipleItemQuickAdapter(null);
    private final List<HunterMaketClass> items = new ArrayList<>();
    private final TagHunterRecyclerAdapter mTagAdapter = new TagHunterRecyclerAdapter(R.layout.item_hunter_maket_class, null);
    private final MyHunterRecyclerAdapter mMyAdapter = new MyHunterRecyclerAdapter(R.layout.item_hunter_my, null);

    private static HunterMaketDelegate delegate = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_hunter_market;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mHotHunterButton.setLeftText("");
        mHotHunterButton.setTitleText("热门猎人");
    }

    public static TeaDelegate newInstance() {
        if (delegate == null) {
            delegate = new HunterMaketDelegate();
        }
        return delegate;
    }

    private final List<MultipleItem> mHotHunters = new ArrayList<>();
    private final List<HunterMaketCard> mMyHunters = new ArrayList<>();

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

        mTagRecycler.setLayoutManager(new GridLayoutManager(getContext(), 4));
        mTagRecycler.setAdapter(mTagAdapter);

        HunterMaketClass item = null;
        for (int i = 0; i < 6; i++) {
            item = new HunterMaketClass();
            item.setTitle(i);
            item.setImageResId(i);
            items.add(item);
        }
        mTagAdapter.addData(items);

        mMyHunterRecycle.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mMyHunterRecycle.setAdapter(mMyAdapter);
        HunterMaketCard mMyHunter;
        for (int i = 0; i < 3; i++) {
            mMyHunter = new HunterMaketCard();
            mMyHunter.setId(i);
            mMyHunters.add(mMyHunter);
        }
        mMyAdapter.addData(mMyHunters);

        mHotHunterRecycle.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mHotHunterRecycle.setAdapter(mHotAdapter);
        MultipleItem item2 = null;
        for (int i = 0; i < 6; i++) {
            item2 = new MultipleItem(MultipleItem.HOT_HUNTER);
            mHotHunters.add(item2);
        }
        mHotAdapter.addData(mHotHunters);
    }
}
