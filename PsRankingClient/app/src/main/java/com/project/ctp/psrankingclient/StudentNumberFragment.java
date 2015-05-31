package com.project.ctp.psrankingclient;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class StudentNumberFragment extends Fragment{
    private Button btn_studentNum;
    private List<String> mDataSourceList = new ArrayList<String>();
    private List<FragmentTransaction> mBackStackList = new ArrayList<FragmentTransaction>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_number, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedState)
    {
        super.onActivityCreated(savedState);
        btn_studentNum = (Button) getActivity().findViewById(R.id.btn_studentNum);
        Log.d("stunum", btn_studentNum.toString());
        btn_studentNum.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                registerForContextMenu(v);
                getActivity().openContextMenu(v);
                unregisterForContextMenu(v);
            }
        });

        ArrayList<User> arUser;
        arUser = new ArrayList<User>();
        User user;

        user = new User("1","pppgod", "이상윤");
        arUser.add(user);
        user = new User("2","dodo3371", "김정은");
        arUser.add(user);

        UserAdapter adapter = new UserAdapter(getActivity(), R.layout.custom_rank_list, arUser);

        ListView list;
        list = (ListView) getActivity().findViewById(R.id.list_studentNum);
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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("학번을 선택하세요");
        setContextMenu(menu, 2);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
       /* if(item.getGroupId() == 2) {
            btn_studentNum = (Button) getActivity().findViewById(R.id.btn_studentNum);
            btn_studentNum.setText(item.getTitle());
        }쓸모가 없는듯 하다*/
        Log.d("stunum", btn_studentNum.toString());
        return true;
    }

    private void setContextMenu(ContextMenu menu, int GID)
    {
        menu.add(GID, 0, 0, "전체");
        menu.add(GID, 1, 0, "08학번");
        menu.add(GID, 2, 0, "09학번");
        menu.add(GID, 3, 0, "10학번");
        menu.add(GID, 4, 0, "11학번");
        menu.add(GID, 5, 0, "12학번");
        menu.add(GID, 6, 0, "13학번");
        menu.add(GID, 7, 0, "14학번");
        menu.add(GID, 8, 0, "15학번");
        menu.add(GID, 9, 0, "16학번");
        menu.add(GID, 10, 0, "17학번");
        menu.add(GID, 11, 0, "18학번");
        menu.add(GID, 12, 0, "19학번");
        menu.add(GID, 13, 0, "20학번");
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