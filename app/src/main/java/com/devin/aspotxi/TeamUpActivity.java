package com.devin.aspotxi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class TeamUpActivity extends AppCompatActivity {
    RadioButton infobtn, livebtn, teambtn,lscore, lvideo;
    RelativeLayout infolyt, livelyt, teamlyt,teamAlyt,teamBlyt;
    TextView matchno, date, time;
    DatabaseReference reference;
    LinearLayout mumbai, delhi, kolkata, ahmedabad, chennai, benguluru;
    WebView webView;
    Model model;
    RecyclerView teamArec,teamBrec;
    private String teamAchild,teamBchild;
    TeamAdapter teamAdapter;
    TeamBadapter teamBadapter;
    TextView instabtn;

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_up);

        webView = findViewById(R.id.webearn);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        instabtn=findViewById(R.id.instabtn);
        instabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("");
            }
        });


        teamArec=findViewById(R.id.teamArecV);
        teamBrec=findViewById(R.id.teamBrecV);
        teamAlyt=findViewById(R.id.teamAlyt);
        teamBlyt=findViewById(R.id.teamblyt);

        lscore = findViewById(R.id.lscore);
        lvideo = findViewById(R.id.lvideo);

        infobtn = findViewById(R.id.infobtn);
        livebtn = findViewById(R.id.livebtn);
        teambtn = findViewById(R.id.teambtn);
        infolyt = findViewById(R.id.infom);
        livelyt = findViewById(R.id.earn);
        teamlyt = findViewById(R.id.teamlyt);

        mumbai = findViewById(R.id.Mumbai);
        delhi = findViewById(R.id.Delhi);
        kolkata = findViewById(R.id.Kolkata);
        chennai = findViewById(R.id.Cheanni);
        ahmedabad = findViewById(R.id.Ahemdabad);
        benguluru = findViewById(R.id.Bengloru);


        matchno = findViewById(R.id.matchIntTv);
        date = findViewById(R.id.dates);
        time = findViewById(R.id.times);


        reference = FirebaseDatabase.getInstance().getReference().child("Matches");
        String matchkeys = getIntent().getStringExtra("MatchKey");
        reference.child(matchkeys).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String matchint = snapshot.child("matchint").getValue().toString();
                String dates = snapshot.child("date").getValue().toString();
                String times = snapshot.child("time").getValue().toString();
                String ewarurl = snapshot.child("earnU").getValue().toString();
                webView.loadUrl(ewarurl);
                matchno.setText(matchint);
                date.setText(dates);
                time.setText(times);
                String places = snapshot.child("place").getValue().toString();
                switch (places) {
                    case "Wankhede Stadium, Mumbai":
                        mumbai.setVisibility(View.VISIBLE);
                        delhi.setVisibility(View.GONE);
                        kolkata.setVisibility(View.GONE);
                        chennai.setVisibility(View.GONE);
                        ahmedabad.setVisibility(View.GONE);
                        benguluru.setVisibility(View.GONE);
//                        holder.imgA.setImageResource(R.drawable.csk);
                        break;
                    case "MA Chidambaram Stadium, Chennai":
                        mumbai.setVisibility(View.GONE);
                        delhi.setVisibility(View.GONE);
                        kolkata.setVisibility(View.GONE);
                        chennai.setVisibility(View.VISIBLE);
                        ahmedabad.setVisibility(View.GONE);
                        benguluru.setVisibility(View.GONE);
                        break;
                    case "Narendra Modi Stadium Motera, Ahmedabad":
                        mumbai.setVisibility(View.GONE);
                        delhi.setVisibility(View.GONE);
                        kolkata.setVisibility(View.GONE);
                        chennai.setVisibility(View.GONE);
                        ahmedabad.setVisibility(View.VISIBLE);
                        benguluru.setVisibility(View.GONE);
                        break;
                    case "Arun Jaitley Stadium, Delhi":
                        mumbai.setVisibility(View.GONE);
                        delhi.setVisibility(View.VISIBLE);
                        kolkata.setVisibility(View.GONE);
                        chennai.setVisibility(View.GONE);
                        ahmedabad.setVisibility(View.GONE);
                        benguluru.setVisibility(View.GONE);
                        break;
                    case "M.Chinnaswamy Stadium, Bengaluru":
                        mumbai.setVisibility(View.GONE);
                        delhi.setVisibility(View.GONE);
                        kolkata.setVisibility(View.GONE);
                        chennai.setVisibility(View.GONE);
                        ahmedabad.setVisibility(View.GONE);
                        benguluru.setVisibility(View.VISIBLE);
                        break;
                    case "Eden Gardens, Kolkata":
                        mumbai.setVisibility(View.GONE);
                        delhi.setVisibility(View.GONE);
                        kolkata.setVisibility(View.VISIBLE);
                        chennai.setVisibility(View.GONE);
                        ahmedabad.setVisibility(View.GONE);
                        benguluru.setVisibility(View.GONE);
                        break;

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
//        infolyt.setVisibility(View.VISIBLE);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));

        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_baseline_arrow_back_24));
        final Bundle bundle = getIntent().getExtras();
//        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        assert bundle != null;
        Objects.requireNonNull(getSupportActionBar()).setTitle(Objects.requireNonNull(bundle.getString("TeamA")).toUpperCase()
                + " vs " + Objects.requireNonNull(bundle.getString("TeamB")).toUpperCase());
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if(interstitialAd.isLoaded()){
//                    interstitialAd.show();
//                } else {
                TeamUpActivity.super.onBackPressed();
//                }
            }
        });
        String teamAA= Objects.requireNonNull(bundle.getString("TeamA")).toUpperCase();
        String teamBB= Objects.requireNonNull(bundle.getString("TeamB")).toUpperCase();
        lscore.setText(teamAA);
        lvideo.setText(teamBB);
        teamAchild = "Team/"+ teamAA;
        teamBchild = "Team/"+ teamBB;
        teamArec=findViewById(R.id.teamArecV);
        teamArec.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<TeamModel> options =
                new FirebaseRecyclerOptions.Builder<TeamModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child(teamAchild), TeamModel.class)
                        .build();
        teamAdapter=new TeamAdapter(options);

        teamBrec=findViewById(R.id.teamBrecV);
        teamBrec.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<TeamBModel> option =
                new FirebaseRecyclerOptions.Builder<TeamBModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child(teamBchild), TeamBModel.class)
                        .build();
        teamBadapter=new TeamBadapter(option);

    }

    private void gotoUrl(String s) {
        Uri uri=Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }

    public void onRadioClick(View view) {
        boolean isSelected = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.infobtn:
                if (isSelected) {
                    infolyt.setVisibility(View.VISIBLE);
                    livelyt.setVisibility(View.GONE);
                    teamlyt.setVisibility(View.GONE);
                }
                break;
            case R.id.livebtn:
                if (isSelected) {
                    livelyt.setVisibility(View.VISIBLE);
                    infolyt.setVisibility(View.GONE);
                    teamlyt.setVisibility(View.GONE);
                }
                break;
            case R.id.teambtn:
                if (isSelected) {
                    livelyt.setVisibility(View.GONE);
                    infolyt.setVisibility(View.GONE);
                    teamlyt.setVisibility(View.VISIBLE);
                    teamArec.setAdapter(teamAdapter);
                    teamBrec.setAdapter(teamBadapter);
                }
                break;
        }
    }
    public void onliveVRdClick(View view) {
        boolean isSelected=((RadioButton)view).isChecked();
        switch (view.getId()){
            case R.id.lscore:
                if (isSelected){
                    lscore.setTextColor(Color.WHITE);
                    lvideo.setTextColor(Color.RED);
                    teamBlyt.setVisibility(View.GONE);
                    teamAlyt.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.lvideo:
                if (isSelected){
                    lscore.setTextColor(Color.RED);
                    lvideo.setTextColor(Color.WHITE);
                    teamAlyt.setVisibility(View.GONE);
                    teamBlyt.setVisibility(View.VISIBLE);
                }
                break;
        }
    }
    @Override
    public void onStart () {
        super.onStart();
        teamAdapter.startListening();
        teamBadapter.startListening();
    }
    @Override
    public void onStop () {
        super.onStop();
        teamAdapter.stopListening();
        teamBadapter.stopListening();
    }

    @Override
    protected void onResume() {
        super.onResume();
        teamAdapter.startListening();
        teamBadapter.startListening();
    }
}