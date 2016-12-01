package com.bit2016.myapp.core.service;

import android.util.Log;

import com.bit2016.adroid.JSONResult;
import com.bit2016.myapp.core.domain.User;
import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Pack200;

/**
 * Created by kickscar on 2016-12-01.
 */

public class UserService {

    public List<User> fetchMockUserList() {

        List<User> list = new ArrayList<User>();

        // Mock Data
        User user = new User();
        user.setId( 1L );
        user.setName( "안대혁" );
        user.setPhone( "010-4761-6934" );
        user.setEmail( "kickscar@gmail.com" );
        user.setProfilePic("https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/936089_1019758748039064_7187347097932848216_n.jpg?oh=ec46e4053b89bf9ea434f1cf79b6ce43&oe=58B4B3C8" );
        user.setStatus( 1 );
        list.add( user );

        return list;
    }

    public List<User> fetchUserList() {

        String url = "http://192.168.0.2:8088/myapp-api/api/user/list";
        HttpRequest request = HttpRequest.get( url );

        request.contentType( HttpRequest.CONTENT_TYPE_JSON );
        request.accept( HttpRequest.CONTENT_TYPE_JSON );
        request.connectTimeout( 1000 );
        request.readTimeout( 30000 );

        int responseCode = request.code();

        if( responseCode != HttpURLConnection.HTTP_OK ) {
            Log.e( "UserService", "fetchUserList() error : Not 200 OK" );
            return null;
        }

        JSONResultUserList jsonResult = fromJSON( request, JSONResultUserList.class );
        return jsonResult.getData();
    }

    protected <V> V fromJSON( HttpRequest request, Class<V> target ) {
        V v = null;
        try {
            Gson gson = new GsonBuilder().create();

            Reader reader = request.bufferedReader();
            v = gson.fromJson(reader, target);
            reader.close();
        } catch( Exception ex ) {
            Log.e( "UserService", "fromJSON error : " + ex );
            throw new RuntimeException( ex );
        }

        return v;
    }

    private class JSONResultUserList extends JSONResult<List<User>>{};

}
