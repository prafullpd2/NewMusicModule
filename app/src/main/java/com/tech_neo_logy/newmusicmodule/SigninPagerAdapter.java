package com.tech_neo_logy.newmusicmodule;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Prafull on 15-Mar-16.
 */
public class SigninPagerAdapter extends FragmentStatePagerAdapter {

    int noOfTabs;


    public SigninPagerAdapter(FragmentManager fm) {
        super(fm);


    }

    @Override
    public Fragment getItem(int position) {
        if(position==0){
            SignUpFragment tab1 = new SignUpFragment();
            return tab1;
        }

        if(position==1){
            SignInFragment tab2 = new SignInFragment();
            return tab2;
        }
        else
            return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        //this is where you set the titles
        switch(position) {
            case 0:
                return "Sign Up";

            case 1:
                return "Sign In";

        }
        return null;
    }
}
