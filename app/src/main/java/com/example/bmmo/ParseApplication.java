package com.example.bmmo;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {


    // Initializes Parse SDK as soon as the application is created
    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Post.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("osLAZyLCE8sYhkQj9rOLCEjIiiq2w2sCxr2tUtHj")
                .clientKey("229SUGPKKsxhNMpi1QeDoFJpU3NafRwFKxQDPGdA")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
