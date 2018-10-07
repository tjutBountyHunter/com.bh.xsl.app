package org.yamu.tea_sc.personalCenter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PersonalCenterPagerAdapter extends FragmentPagerAdapter {


    public PersonalCenterPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = PersonalCenterHomeDelegate.newInstance();
                break;
            case 1:
                fragment = PersonalCenterOfferRewardDelegate.newInstance();
                break;
            case 2:
                fragment = PersonalCenterCollectionDelegate.newInstance();
                break;
            default:
                fragment = PersonalCenterHomeDelegate.newInstance();
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
        Item2(1),
        Item3(2);

        public static final int SIZE = 3;
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
                case 2:
                    return Item3;
                default:
                    return Item1;
            }
        }

        public int getPosition() {
            return position;
        }
    }
}