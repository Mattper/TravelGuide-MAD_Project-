package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout =findViewById(R.id.tabLayout);
        viewPager =findViewById(R.id.viewPager);

        tabLayout.setupWithViewPager(viewPager);

        FragmentAdapter adapter =new FragmentAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        adapter.addFragment(new HomeFragment(),"Home");
        adapter.addFragment(new HotelFragment(),"Hotels");
        adapter.addFragment(new ArticleFragment(),"Articles");
        adapter.addFragment(new ServiceFragment(),"Services");
        viewPager.setAdapter(adapter);
        

    }

    public void logout(View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),LogInActivity.class));
        finish();
    }


}