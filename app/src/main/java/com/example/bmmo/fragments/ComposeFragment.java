package com.example.bmmo.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bmmo.Exercise;
import com.example.bmmo.MainActivity;
import com.example.bmmo.Exercise;
import com.example.bmmo.R;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class ComposeFragment extends Fragment {

    public static final String TAG = "ComposeFragment";
    public static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 42;
    private EditText etDescription;
    private Button btnCaptureImage;
    private ImageView ivPostImage;
    private Button btnSubmit;
    private Button btnStop;
    private File photoFile;
    private String photoFileName = "photo.jpg";

    private long pause;
    //    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
    private Spinner spinner;
    private String workout = null;
    private static final String[] paths = {"Pushups", "Squats", "Lunges","Glute bridge"};

   private Chronometer timer;
   private boolean checker;


    public ComposeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_exercise, container, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated (view, savedInstanceState);

//        btnCaptureImage = view.findViewById(R.id.btnCaptureImage);
        //ivPostImage = view.findViewById(R.id.ivPostImage);
        super.onCreate (savedInstanceState);
        timer = view.findViewById (R.id.timer);

        spinner = (Spinner) view.findViewById (R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String> (((MainActivity) getActivity ()),

                android.R.layout.simple_spinner_item, paths);

        adapter.setDropDownViewResource (android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter (adapter);

        spinner.setOnItemSelectedListener (new AdapterView.OnItemSelectedListener () {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Log.v ("item", (String) parent.getItemAtPosition (position));
                workout = (String) parent.getItemAtPosition (position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        //START
        btnSubmit = view.findViewById (R.id.btnSubmit);
        //        queryPosts();
        btnSubmit.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                if (workout != null) {
                    Log.v ("item", workout);

                }
                spinner.setEnabled(false);
                spinner.setClickable(false);
                if (!checker) {
                    timer.setBase(SystemClock.elapsedRealtime ()-pause);
                    timer.start ();
                    checker = true;
                }


//                ParseUser currentUser = ParseUser.getCurrentUser();
//                savePost(description,currentUser,photoFile);
            }
        });

        //STOP
        btnStop = view.findViewById (R.id.btnStop);
        btnStop.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                if (workout != null) {
                    Log.v ("item", workout);
                }
                timer.setBase (SystemClock.elapsedRealtime ());
                pause=0;
                spinner.setEnabled(true);
                spinner.setClickable(true);
                if (checker) {
                    timer.stop ();
                    pause=SystemClock.elapsedRealtime () - timer.getBase ();
                    checker = false;
                }

//                ParseUser currentUser = ParseUser.getCurrentUser();
//                savePost(description,currentUser,photoFile);
            }
        });


    }



//    private void saveExercsise(ParseUser currentUser, int time) {
//        Exercise exercise = new Exercise();
//        post.setDescription(description);
//
//        post.setUser(currentUser);
//        post.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//                if (e!=null){
//                    Log.e(TAG,"Error saving",e);
//                    Toast.makeText(getContext(),"Error saving",Toast.LENGTH_SHORT).show();
//                }
//                Log.i(TAG,"Saved");
//                etDescription.setText("");
//                ivPostImage.setImageResource(0);
//            }
//        });
//    }




}



//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment ComposeFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static ComposeFragment newInstance(String param1, String param2) {
//        ComposeFragment fragment = new ComposeFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }
//    }