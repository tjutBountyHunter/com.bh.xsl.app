package org.yamu.tea_sc.personalCenter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class PersonalAdapter extends PagerAdapter {
    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, int position) {
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

//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
//        container.removeView((View) object);
//    }
}
