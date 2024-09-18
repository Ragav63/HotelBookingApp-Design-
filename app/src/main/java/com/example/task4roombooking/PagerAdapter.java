package com.example.task4roombooking;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class PagerAdapter extends FragmentStatePagerAdapter {

    int tabCount;

    public PagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        tabCount=behavior;

    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new DateFragment();

            case 1:
                return new RoomFragment();

            case 2:
                return new ContactDetailsFragment();

            case 3:
                return new ConfirmDetailsFragment();

            case 4:
                return new PaymentFragment();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }


}
