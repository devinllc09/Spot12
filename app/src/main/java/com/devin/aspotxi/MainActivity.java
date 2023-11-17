package com.devin.aspotxi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TableLayout;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.FirebaseDatabase;

import in.dd4you.appsconfig.DD4YouNetReceiver;

public class MainActivity extends AppCompatActivity {
    //        RecyclerView recyclerView;
//        myAdapter adapter;
    private DD4YouNetReceiver dd4YouNetReceiver;
    private ViewGroup myRootView;
    TabLayout tabLayout;
    TabItem upcoming, live, completed;
    ViewPager viewPager;
    PageAdapter pageAdapter;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer=findViewById(R.id.drawer_layout);
        NavigationView navigationView=findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawer,toolbar,
                R.string.open,R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // Get Root View Current Activity
        myRootView = findViewById(R.id.drawer_layout);

        registerBroadcastReceiver();

        tabLayout = findViewById(R.id.tabb);
        upcoming = findViewById(R.id.upcoming);
        live = findViewById(R.id.live);
        completed = findViewById(R.id.completed);
        viewPager = findViewById(R.id.viewpaager);
        pageAdapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

                if (tab.getPosition() == 0 || tab.getPosition() == 1 || tab.getPosition() == 2)
                    pageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


    }

    private void registerBroadcastReceiver() {
        if (dd4YouNetReceiver == null)
            dd4YouNetReceiver = new DD4YouNetReceiver(myRootView,1000);
        dd4YouNetReceiver.register(this.getApplicationContext());
    }
    private void unregisterBroadcastReceiver() {
        if (dd4YouNetReceiver != null)
        {
            dd4YouNetReceiver.unregister(this.getApplicationContext());
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        unregisterBroadcastReceiver();
    }
    @Override
    protected void onResume() {
        super.onResume();
//        registerBroadcastReceiver();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterBroadcastReceiver();
    }
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

}