package com.bit2016.myapp.ui.tabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bit2016.adroid.SafeAsyncTask;
import com.bit2016.myapp.R;
import com.bit2016.myapp.core.domain.User;
import com.bit2016.myapp.core.service.UserService;
import com.bit2016.myapp.ui.user.UserListArrayAdapter;

import java.util.List;

/**
 * Created by kickscar on 2016-12-01.
 */

public class UserListFragment extends ListFragment {

    private UserListArrayAdapter userListArrayAdapter;
    private UserService userService = new UserService();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        userListArrayAdapter = new UserListArrayAdapter( getActivity() );
        setListAdapter( userListArrayAdapter );
        return inflater.inflate( R.layout.fragment_user_list, container, false );
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        new FetchUserListTask().execute();
    }

    private class FetchUserListTask extends SafeAsyncTask<List<User>> {
        @Override
        public List<User> call() throws Exception {
            getActivity().findViewById( R.id.progress ).setVisibility( View.VISIBLE );
            return userService.fetchUserList();
        }

        @Override
        protected void onException(Exception e) throws RuntimeException {
            super.onException(e);
        }

        @Override
        protected void onSuccess(List<User> users) throws Exception {
            if( users.size() == 0 ) {
                return;
            }

            userListArrayAdapter.add( users );
            getActivity().findViewById( R.id.progress ).setVisibility( View.GONE );
        }
    }
}
