package com.project.ctp.psrankingclient;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class UserInfoGraphFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";

    public static UserInfoGraphFragment newInstance(int sectionNumber) {
        UserInfoGraphFragment fragment = new UserInfoGraphFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_info_graph, container, false);
    }


}
