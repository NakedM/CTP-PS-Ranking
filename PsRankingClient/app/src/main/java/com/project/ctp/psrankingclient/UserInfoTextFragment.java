package com.project.ctp.psrankingclient;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class UserInfoTextFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";

    View v;
    public static UserInfoTextFragment newInstance(int sectionNumber) {
        UserInfoTextFragment fragment = new UserInfoTextFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v =  inflater.inflate(R.layout.fragment_user_info_text, container, false);
        setList(R.id.list_userInfo);
        return v;
    }

    private void setList(int listName) {
        ArrayList<UserInfo> arUserInfo;
        arUserInfo = new ArrayList<UserInfo>();
        UserInfo user;
        user = new UserInfo("pppgod", 1234);
        arUserInfo.add(user);
        user = new UserInfo("dodo3371", 4444);
        arUserInfo.add(user);

        UserAdapter adapter = new UserAdapter(getActivity(), R.layout.custom_userinfo_textlist, arUserInfo);

        ListView list;
        list = (ListView)v.findViewById(listName);
        list.setAdapter(adapter);
        list.setDividerHeight(2);
    }


    private class UserInfo{
        String title;
        int value;

        UserInfo(String title, int value){
            this.title = title;
            this.value = value;
        }
    }

    private class UserAdapter extends BaseAdapter
    {
        Context context;
        LayoutInflater inflater;
        ArrayList<UserInfo> arUserInfo;
        int layout;

        public UserAdapter(Context context, int layout, ArrayList<UserInfo> arUserInfo) {
            this.context = context;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            this.arUserInfo = arUserInfo;
            this.layout = layout;
        }

        @Override
        public int getCount(){
            return arUserInfo.size();
        }

        @Override
        public Object getItem(int position){
            return arUserInfo.get(position).value;
        }

        @Override
        public long getItemId(int position){
            return position;
        }

        @Override //보여지는 함수
        public View getView(final int position, View convertView, ViewGroup parent){
            if(convertView == null){
                convertView = inflater.inflate(layout, parent, false);
            }

            TextView id = (TextView) convertView.findViewById(R.id.tv_infoTitle);
            id.setText(arUserInfo.get(position).title);

            TextView name = (TextView) convertView.findViewById(R.id.tv_infoValue);
            name.setText(String.valueOf(arUserInfo.get(position).value));

            return convertView;
        }
    }

}
