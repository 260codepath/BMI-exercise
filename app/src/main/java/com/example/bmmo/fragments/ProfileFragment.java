package com.example.bmmo.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.bmmo.Exercise;
import com.example.bmmo.LoginActivity;
import com.example.bmmo.ExerciseAdapter;
import com.example.bmmo.Profile;
import com.example.bmmo.R;
import com.example.bmmo.StatsAdapter;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment{
    private final int REQUEST_CODE = 20;
    public static final String TAG = "ProfileFragment";
    private List<Exercise> allExercises;
    private List<Profile> profiles;
    private StatsAdapter adapter;
    private ExerciseAdapter exerciseAdapter;
    private SwipeRefreshLayout swipeContainer;
    private RecyclerView rvExercises;
    private RecyclerView rvStats;
    private TextView tvUsername;
    private TextView tvBMO;
    private TextView tvLevel;
    private TextView tvHeight;
    private TextView tvWeight;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.log_out,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragments_profile, container, false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.menu_logout) {
            ParseUser.logOut();
            ParseUser currentUser = ParseUser.getCurrentUser();
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivityForResult(intent,REQUEST_CODE);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvStats = view.findViewById(R.id.rvStats);
        tvBMO = view.findViewById(R.id.tvBMO);
        tvLevel = view.findViewById(R.id.tvLevel);
        tvWeight = view.findViewById(R.id.tvWeight);
        tvHeight = view.findViewById(R.id.tvHeight);
        tvUsername = view.findViewById(R.id.tvUsername);


        rvExercises = view.findViewById(R.id.rvProfileExercise);
        allExercises = new ArrayList<>();
        profiles = new ArrayList<>();
        adapter = new StatsAdapter(getContext(), profiles);
        exerciseAdapter = new ExerciseAdapter(getContext(), allExercises);
        rvStats.setAdapter(adapter);
        rvExercises.setAdapter(exerciseAdapter);
        rvStats.setLayoutManager(new LinearLayoutManager(getContext()));
        rvExercises.setLayoutManager(new LinearLayoutManager(getContext()));
        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer2);
//         Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
//         Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                exerciseAdapter.clear();
                queryPosts();
            }
        });
//        Log.i(TAG,ParseUser.getCurrentUser().getUsername());
        queryPosts();
        queryProfile();
    }

    private void queryProfile(){
        ParseQuery<Profile> query = ParseQuery.getQuery(Profile.class);
        query.include(Profile.KEY_USER);
        query.setLimit(1);
//        query.whereEqualTo(Profile.KEY_USER,ParseUser.getCurrentUser());
//        query.addDescendingOrder(Profile.KEY_CREATED_AT);
        query.findInBackground(new FindCallback<Profile>() {
            @Override
            public void done(List<Profile> profile, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue getting posts", e);
                    return;
                }
                for (Profile prof : profile){
                    Log.i(TAG,String.valueOf(prof.getLevel()!=0));
                    tvBMO.setText("BMI: 23.2");
                    tvWeight.setText("Weight: "+prof.getWeight()+"kg");
                    tvHeight.setText("Height: "+prof.getHeight()+"cm");
                    tvLevel.setText("Level: "+prof.getLevel());
                    tvUsername.setText(ParseUser.getCurrentUser().getUsername());
                }

                profiles.addAll(profile);
//                adapter.addAll(profiles);
//                allExercises.addAll(posts);
                adapter.notifyDataSetChanged();
            }
        });
    }

    protected void queryPosts() {
        ParseQuery<Exercise> query = ParseQuery.getQuery(Exercise.class);
        query.include(Exercise.KEY_USER);
        query.setLimit(20);
//        query.whereEqualTo(Exercise.KEY_USER, ParseUser.getCurrentUser());
//        Log.i(TAG,Exercise.KEY_USER);
//        Log.i(TAG,ParseUser.getCurrentUser().getUsername());
        query.addDescendingOrder(Exercise.KEY_CREATED_AT);
        query.findInBackground(new FindCallback<Exercise>() {
            @Override
            public void done(List<Exercise> posts, ParseException e) {
                if (e != null){
                    Log.e(TAG, "Issue getting posts",e);
                    return;
                }
//                for (Exercise exercise : posts){
//                    Log.i(TAG,exercise.getUser().getUsername());
//                }
                allExercises.addAll(posts);
//                exerciseAdapter.addAll(allExercises);
                exerciseAdapter.notifyDataSetChanged();
                swipeContainer.setRefreshing(false);
            }
        });
    }
}
