package com.bit2016.myapp.core.service;

import com.bit2016.myapp.core.domain.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kickscar on 2016-12-01.
 */

public class UserService {
    public List<User> fetchUserList() {

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
}
