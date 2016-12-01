package com.bit2016.myapp.ui.user;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bit2016.myapp.R;
import com.bit2016.myapp.core.domain.User;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by kickscar on 2016-12-01.
 */

public class UserListArrayAdapter extends ArrayAdapter<User> {
    private LayoutInflater layoutInflater;
    DisplayImageOptions displayImageOption = new DisplayImageOptions.Builder()
            // .showImageOnLoading( R.drawable.ic_default_profile )// resource or drawable
            .showImageForEmptyUri( R.drawable.ic_default_profile )// resource or drawable
            .showImageOnFail( R.drawable.ic_default_profile )// resource or drawable
             //.resetViewBeforeLoading( false )// default
            .delayBeforeLoading( 0 )
            //.cacheInMemory( false )// default
            .cacheOnDisc( true )// false is default
            //.preProcessor(...)
            //.extraForDownloader(...)
            //.considerExifParams( false )// default
            //.imageScaleType( ImageScaleType.IN_SAMPLE_POWER_OF_2 )// default
            //.bitmapConfig( Bitmap.Config.ARGB_8888 )// default
            //.decodingOptions(...)
            //.displayer( new SimpleBitmapDisplayer() )// default
            //.handler( new Handler() )// default
            .build();

    public UserListArrayAdapter( Context context ) {
        super( context, R.layout.row_user_list );
        layoutInflater = LayoutInflater.from( context );
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if( view == null ) {
            view = layoutInflater.inflate( R.layout.row_user_list, parent, false );
        }

        User user = getItem( position );

        TextView textView = (TextView)view.findViewById( R.id.name );
        textView.setText( user.getName() );

        ImageLoader.getInstance().displayImage( user.getProfilePic(), (ImageView)view.findViewById( R.id.profile ), displayImageOption );

        return view;
    }

    public void add( List<User> list ) {
        if( list == null ) {
            return;
        }

        for( User user : list ) {
            add( user );
        }
    }
}
