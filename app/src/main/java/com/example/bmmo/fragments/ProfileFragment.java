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
import androidx.fragment.app.Fragment;

import com.example.bmmo.Exercise;
import com.example.bmmo.LoginActivity;
import com.example.bmmo.PostsAdapter;
import com.example.bmmo.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class ProfileFragment extends Fragment{
    private final int REQUEST_CODE = 20;
    public static final String TAG = "PostsFragment";
    private List<Exercise> allExercises;
    private PostsAdapter adapter;

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


    protected void queryPosts() {
        ParseQuery<Exercise> query = ParseQuery.getQuery(Exercise.class);
        query.include(Exercise.KEY_USER);
        query.setLimit(20);
        query.whereEqualTo(Exercise.KEY_USER, ParseUser.getCurrentUser());
        query.addDescendingOrder(Exercise.KEY_CREATED_AT);
        query.findInBackground(new FindCallback<Exercise>() {
            @Override
            public void done(List<Exercise> posts, ParseException e) {
                if (e != null){
                    Log.e(TAG, "Issue getting posts",e);
                    return;
                }
                for (Exercise exercise: posts){
                    Log.i(TAG,"Post: " + exercise.getUser() + ", username: " + exercise.getUser().getUsername());
                }
                allExercises.addAll(posts);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
