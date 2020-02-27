package com.example.signup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewpagerAdapter_ngo extends FragmentPagerAdapter {



    private final List<Fragment> fragmentsList= new ArrayList<>();
    private final List<String> fragmentTitle= new ArrayList<>();


    public ViewpagerAdapter_ngo(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentsList.get(position);
    }


    @Override
    public int getCount() {
        return fragmentTitle.size();
    }


    public void addFragment(Fragment fragment,String title){
        fragmentsList.add(fragment);
        fragmentTitle.add(title);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitle.get(position);
    }

}