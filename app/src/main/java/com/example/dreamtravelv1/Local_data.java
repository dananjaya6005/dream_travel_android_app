package com.example.dreamtravelv1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.dreamtravelv1.fragment.Travel_Agency;
import com.example.dreamtravelv1.fragment.Travel_Restaurant;
import com.google.android.material.tabs.TabLayout;

public class Local_data extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_data);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);

        tabLayout.addTab(tabLayout.newTab().setText("Travel Agents"));
        tabLayout.addTab(tabLayout.newTab().setText("Restaurants"));


    viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager(),FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        @NonNull
        @Override
        public Fragment getItem(int position) {

            switch(position){
                case 0:
                    Travel_Agency travel_agency = new Travel_Agency();
                    return travel_agency;
                case 1:
                    Travel_Restaurant travel_restaurant  = new Travel_Restaurant();
                    return  travel_restaurant;
                default:
                    return null;

            }

        }

        @Override
        public int getCount() {

            return tabLayout.getTabCount();
        }
    });

    tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            viewPager.setCurrentItem(tab.getPosition());

        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {
            viewPager.setCurrentItem(tab.getPosition());

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    });



    }
}