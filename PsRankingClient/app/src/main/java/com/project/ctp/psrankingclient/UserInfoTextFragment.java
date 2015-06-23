package com.project.ctp.psrankingclient;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;

import java.util.ArrayList;


public class UserInfoTextFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    private String[] jsonName = {"stu_id", "stu_name", "boj_id", "solved", "total", "join_year",
             "accept", "wrong", "timelimit", "memorylimit",
            "outputlimit", "runtimeerr", "compileerr", "univ_name"};
    private String[] info = {"학번", "이름", "아이디", "푼 문제", "제출", "맞았습니다", "틀렸습니다", "시간 초과", "메모리 초과", "출력 초과", "런타임 에러", "컴파일 에러", "학교"};
    private TextView tv_name;
    private TextView tv_id;
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
        return v;
    }
    public void onActivityCreated(Bundle savedState)
    {
        super.onActivityCreated(savedState);
        tv_name = (TextView) getActivity().findViewById(R.id.tv_name);
        tv_id = (TextView) getActivity().findViewById(R.id.tv_id);
        setList(R.id.list_userInfo);
    }
    private void setList(int listName) {
        Crawler craw = new Crawler("http://eb-django-env.elasticbeanstalk.com/ctp/hakgb11/?format=json");
        craw.run();
        JParser jparser = new JParser(jsonName);
        try {
            String testStr = "{\"user\":[{\"boj_id\":\"hakgb11\",\"stu_id\":12142380,\"stu_name\":\"이강호\",\"join_year\":14,\"solved\":381,\"total\":921,\"accept\":282,\"wrong\":381,\"timelimit\":293,\"memorylimit\":483,\"outputlimit\":471,\"runtimeerr\":482,\"compileerr\":383,\"univ_name\":\"Inha\"}]}";
            jparser.Parsing(testStr, "user");
            //jparser.Parsing(craw.getStream(), "user");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String[][] parseredData = jparser.getParseredData();

        //상단 id 및 이름 설정
        tv_name.setText(parseredData[0][1]);
        tv_id.setText(parseredData[0][2]);

        ArrayList<UserInfo> arUserInfo;
        arUserInfo = new ArrayList<UserInfo>();
        UserInfo user;


        for(int i=3 ; i<info.length ; i++) {
            Log.e("why", info[i]);
            user = new UserInfo(info[i], parseredData[0][i]);
            arUserInfo.add(user);
        }

        UserAdapter adapter = new UserAdapter(getActivity(), R.layout.custom_userinfo_textlist, arUserInfo);

        ListView list;
        list = (ListView)v.findViewById(listName);
        list.setAdapter(adapter);
        list.setDividerHeight(2);
    }


    private class UserInfo{
        String title;
        String value;

        UserInfo(String title, String value){
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
            name.setText(arUserInfo.get(position).value);

            return convertView;
        }
    }

}
