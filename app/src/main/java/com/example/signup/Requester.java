package com.example.signup;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;


import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

import io.paperdb.Paper;

public class Requester extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {


    private TabLayout tabLayout;
    private ViewPager viewPager;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requester);
        getSupportActionBar().setTitle(" Requester");

        navigationView = (NavigationView) findViewById(R.id.navigationView_requester);

        navigationView.setNavigationItemSelectedListener(this);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_requester);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.myViewPager);



        setupViewPager(viewPager);

        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_volunteer);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_services);

        //remove shadow from action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setElevation(0);


    }

    private void setupViewPager(ViewPager viewPager) {

        //fragment added

        ViewpagerAdapter_ngo viewPagerAdapter = new ViewpagerAdapter_ngo(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new frag_VolunteerList(), "Volunteer List");
        viewPagerAdapter.addFragment(new frag_service(), "Services");
        viewPager.setAdapter(viewPagerAdapter);
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {

            case R.id.nav_home:
                goToHome();
                break;

            case R.id.nav_profile:
                goToProfile();
                break;

            case R.id.nav_valuePoints:
                //goToValuepts();
                break;

            case R.id.nav_Logout:
                logout();
                break;

        }


        return true;
    }

    private void goToHome() {
        Intent intent = new Intent(getApplicationContext(), Requester.class);
        startActivity(intent);
    }

    private void goToProfile() {
        Intent intent = new Intent(getApplicationContext(), Profile.class);
        startActivity(intent);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Paper.book().destroy();
        Intent intent = new Intent(getApplicationContext(), loginForm.class);
        startActivity(intent);
        finish();

    }


}