package com.devin.aspotxi;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class LiveFragment extends Fragment {

    RecyclerView recyclerView;
    LiveAdapter liveAdapter;
    ShimmerFrameLayout shimmerFrameLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_live, container, false);
        FirebaseRecyclerOptions<LiveModel> options =
                new FirebaseRecyclerOptions.Builder<LiveModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Live"), LiveModel.class)
                        .build();
        liveAdapter=new LiveAdapter(options);
        recyclerView = view.findViewById(R.id.recyViewLive);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        shimmerFrameLayout = view.findViewById(R.id.shimerlyt);
        shimmerFrameLayout.startShimmer();
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                shimmerFrameLayout.stopShimmer();
                shimmerFrameLayout.setVisibility(View.GONE);
                recyclerView.setAdapter(liveAdapter);
            }
        },3000);
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                shimmerFrameLayout.stopShimmer();
                shimmerFrameLayout.setVisibility(View.GONE);
                recyclerView.setAdapter(liveAdapter);
            }
        },3000);
    }
    @Override
    public void onStart() {
        super.onStart();
        liveAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        liveAdapter.stopListening();
    }
}