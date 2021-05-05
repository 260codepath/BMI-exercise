package com.example.bmmo;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Exercise")
public class Exercise extends ParseObject {

    public static final String KEY_USER = "user";
    public static final String KEY_CREATED_AT = "createdAt";
    public static final String KEY_WORKOUT = "workout";

    public ParseUser getUser(){
        return getParseUser(KEY_USER);
    }
    public ParseObject getWorkout(){
        return getParseObject(KEY_WORKOUT);
    }

    public void setUser(ParseUser user){
        put(KEY_USER,user);
    }
    public void setWorkout(ParseObject workout){ put(KEY_WORKOUT,workout); }
}
