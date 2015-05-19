package com.project.ctp.psrankingclient;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class StudentNumberFragment extends Fragment{
    private Button btn_studentNum;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("onCreateView", "start");
        View view = inflater.inflate(R.layout.fragment_student_number, container, false);
        Log.d("onCreateView", "end");
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedState)
    {
        super.onActivityCreated(savedState);
        btn_studentNum = (Button) getActivity().findViewById(R.id.btn_studentNum);
        btn_studentNum.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                registerForContextMenu(v);
                getActivity().openContextMenu(v);
                unregisterForContextMenu(v);
            }
        });
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        Log.d("onCreateContextMenu", "start");
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("오래된 기수");
        setContextMenu(menu, 0);
        Log.d("onCreateContextMenu", "end");
    }
    private void setContextMenu(ContextMenu menu, int GID)
    {
        menu.add(GID, 0, 0, "전체");
        menu.add(GID, 1, 0, "2011/1");
        menu.add(GID, 2, 0, "2011/2");
        menu.add(GID, 3, 0, "2012/1");
        menu.add(GID, 4, 0, "2012/2");
        menu.add(GID, 5, 0, "2013/1");
        menu.add(GID, 6, 0, "2013/2");
        menu.add(GID, 7, 0, "2014/1");
        menu.add(GID, 8, 0, "2014/2");
        menu.add(GID, 9, 0, "2015/1");
        menu.add(GID, 10, 0, "2015/2");
        menu.add(GID, 11, 0, "2016/1");
        menu.add(GID, 12, 0, "2016/2");
        menu.add(GID, 13, 0, "2017/1");
        menu.add(GID, 14, 0, "2017/2");
        menu.add(GID, 15, 0, "2018/1");
        menu.add(GID, 16, 0, "2018/2");
        menu.add(GID, 17, 0, "2019/1");
        menu.add(GID, 18, 0, "2019/2");
        menu.add(GID, 19, 0, "2020/1");
        menu.add(GID, 20, 0, "2020/2");
    }

}