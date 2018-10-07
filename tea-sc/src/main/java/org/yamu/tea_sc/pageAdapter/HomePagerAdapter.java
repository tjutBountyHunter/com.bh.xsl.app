package org.yamu.tea_sc.pageAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.yamu.tea_core.delegate.TeaDelegate;
import org.yamu.tea_sc.hunterMaket.HunterMaketDelegate;
import org.yamu.tea_sc.taskHall.TaskHallDelegate;

public class HomePagerAdapter extends FragmentPagerAdapter {
    public HomePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public TeaDelegate getItem(int position) {
        TeaDelegate fragment = null;
        switch (position) {
            case 0:
                fragment = TaskHallDelegate.newInstance();
                break;
            case 1:
                fragment = HunterMaketDelegate.newInstance();
                break;
            default:
                break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return ContentPage.SIZE;
    }

    public enum ContentPage {
        Item1(0),
        Item2(1);
        public static final int SIZE = 2;
        private final int position;

        ContentPage(int pos) {
            position = pos;
        }

        public static ContentPage getPage(int position) {
            switch (position) {
                case 0:
                    return Item1;
                case 1:
                    return Item2;
                default:
                    return Item1;
            }
        }

        public int getPosition() {
            return position;
        }
    }
}
