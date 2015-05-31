package com.project.ctp.psrankingclient;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AllFragment extends Fragment{
    private List<String> mDataSourceList = new ArrayList<String>();
    private List<FragmentTransaction> mBackStackList = new ArrayList<FragmentTransaction>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //add data to ListView
        for(int i=0, count=20; i<count; i++){
            mDataSourceList.add("列表数据" + i);
        }


        ArrayList<User> arUser;
        arUser = new ArrayList<User>();
        User user;

        user = new User("1","pppgod", "이상윤");
        arUser.add(user);
        user = new User("2","dodo3371", "김정은");
        arUser.add(user);

        UserAdapter adapter = new UserAdapter(getActivity(), R.layout.custom_rank_list, arUser);

        ListView list;
        list = (ListView) getActivity().findViewById(R.id.list_all);
        list.setAdapter(adapter);
        list.setDividerHeight(2);

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), UserInfoActivity.class);
                startActivity(intent);
                return false;
            }
        });
    }

    private void showTost(String msg){
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
    }
    private class User
    {
        String rank;
        String id;
        String name;

        User(String crank, String cid, String cname) {
            rank = crank;
            id = cid;
            name = cname;
        }
    }

    private class UserAdapter extends BaseAdapter
    {
        Context con;
        LayoutInflater inflater;
        ArrayList<User> arD;
        int layout;

        public UserAdapter(Context context, int alayout, ArrayList<User> aarD) {
            con = context;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            arD = aarD;
            layout = alayout;
        }
        @Override
        public int getCount() {
            return arD.size();
        }

        @Override
        public Object getItem(int position) {
            return arD.get(position).name;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override //보여지는 함수
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater.inflate(layout, parent, false);
            }

            TextView rank = (TextView) convertView.findViewById(R.id.rank);
            rank.setText(arD.get(position).rank);

            TextView id = (TextView) convertView.findViewById(R.id.id);
            id.setText(arD.get(position).id);

            TextView name = (TextView) convertView.findViewById(R.id.name);
            name.setText(arD.get(position).name);

            return convertView;
        }
    }
}