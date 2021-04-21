package com.example.bmmo.fragments;

import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.bmmo.LoginActivity;
import com.example.bmmo.Post;
import com.example.bmmo.Quiz;
import com.example.bmmo.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class ProfileFragment extends PostsFragment{
    private final int REQUEST_CODE = 20;
    MenuItem men;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.log_out,menu);
//        MenuItem quiz = menu.findItem(R.id.quiz_menu);
//        quiz.setVisible(false);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.menu_logout) {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivityForResult(intent,REQUEST_CODE);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


//    @Override
//    protected void queryPosts() {
//        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
//        query.include(Post.KEY_USER);
//        query.setLimit(20);
//        query.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser());
//        query.addDescendingOrder(Post.KEY_CREATED_AT);
//        query.findInBackground(new FindCallback<Post>() {
//            @Override
//            public void done(List<Post> posts, ParseException e) {
//                if (e != null){
//                    Log.e(TAG, "Issue getting posts",e);
//                    return;
//                }
//                for (Post post: posts){
//                    Log.i(TAG,"Post: " + post.getDescription() + ", username: " + post.getUser().getUsername());
//                }
//                allPosts.addAll(posts);
//                adapter.notifyDataSetChanged();
//            }
//        });
//    }
}
