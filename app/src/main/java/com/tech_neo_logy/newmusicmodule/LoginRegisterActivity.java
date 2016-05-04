package com.tech_neo_logy.newmusicmodule;

import android.graphics.Color;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LoginRegisterActivity extends AppCompatActivity implements SignUpFragment.OnFragmentInteractionListener ,SignInFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout_signup);
        tabLayout.addTab(tabLayout.newTab().setText("Sign Up"));
        tabLayout.addTab(tabLayout.newTab().setText("Sign in"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final ViewPager pager =(ViewPager) findViewById(R.id.loginpager);
        final SigninPagerAdapter adapter = new SigninPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);
        tabLayout.setTabTextColors(Color.GRAY,Color.DKGRAY);
        tabLayout.setSelectedTabIndicatorHeight(6);


        tabLayout.setSelectedTabIndicatorColor(Color.rgb(97,97,97));


        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }





    @Override
    public void onFragmentInteraction(Uri uri) {
    }
}
