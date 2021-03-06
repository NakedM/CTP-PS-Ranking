package com.project.ctp.psrankingclient;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
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
import android.widget.Toast;

import org.json.JSONException;

import java.util.ArrayList;

public class SearchProblemActivity extends ActionBarActivity
{
    private Button btn_selectOldOrder;
    private Button btn_selectYoungOrder;
    private Button btn_search;
    private String strOld = "전체";
    private String strYoung = "전체";
    private String[] jsonName = {"stu_id", "boj_id", "stu_name", "join_year", "solved",
            "total", "accept", "wrong", "timelimit", "memorylimit",
            "outputlimit", "runtimeerr", "compileerr", "univ_name"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_problem);

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());

        btn_selectOldOrder = (Button) findViewById(R.id.btn_selectOldOrder);
        btn_selectYoungOrder = (Button) findViewById(R.id.btn_selectYoungOrder);

        btn_selectOldOrder.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                registerForContextMenu(v);
                openContextMenu(v);
                unregisterForContextMenu(v);
            }
        });
        btn_selectYoungOrder.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                registerForContextMenu(v);
                openContextMenu(v);
                unregisterForContextMenu(v);
            }
        });

        btn_search = (Button) findViewById(R.id.btn_search);
        btn_search.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                //strOld < strYoung인 경우
                if (strOld.compareTo(strYoung) < 0) {
                    String temp;
                    temp = strYoung;
                    strYoung = strOld;
                    strOld = temp;
                }

                setList(R.id.list_solve);
                setList(R.id.list_solveNot);
            }
        });

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo
            menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        if (v == btn_selectOldOrder) //오래된 기수를 선택했을때 group_id is 0
        {
            menu.setHeaderTitle("오래된 기수");
            setContextMenu(menu, 0);

        } else if (v == btn_selectYoungOrder) //최근 기수를 선택했을때 group_id is 1
        {
            menu.setHeaderTitle("최근 기수");
            setContextMenu(menu, 1);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) //컨텍스트 아이템을 선택했을때(입력 확인용)
    {
        if (item.getGroupId() == 0) {
            /*switch(item.getItemId()) 스위치문 나중에 사용 예정
            {
                case 1:
            }*/
            strOld = item.getTitle().toString();
            btn_selectOldOrder.setText(item.getTitle()); // 버튼 내용 변경
        } else if (item.getGroupId() == 1) {
            /*switch(item.getItemId()) 스위치문 나중에 사용 예정
            {
                case 1:
            }*/
            strYoung = item.getTitle().toString();
            btn_selectYoungOrder.setText(item.getTitle());
        }
        return true;
    }

    private void setContextMenu(ContextMenu menu, int GID) {
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
        Log.i("setList", "jparsing end");
        String[][] parseredData = jparser.getParseredData();

        Log.i("setList", "add list begin");
        ArrayList<User> arUser;
        arUser = new ArrayList<User>();
        User user;
        for(int i=0 ; i<parseredData.length ; i++) {
            Log.d("setList", parseredData[i][1]);
            Log.d("setList", parseredData[i][2]);
            user = new User(parseredData[i][1], parseredData[i][2]);
            arUser.add(user);
        }
        Log.i("setList", "add list end");
        UserAdapter adapter = new UserAdapter(this, R.layout.custom_user_list, arUser);

        ListView list;
        list = (ListView) findViewById(listName);
        list.setAdapter(adapter);
        list.setDividerHeight(2);

        //리스트 아이템 클릭시 발생하는 함수
        //short click
        list.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(SearchProblemActivity.this, "좀 더 길게 누르세요", Toast.LENGTH_SHORT).show();
            }
        });

        //long click
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(AdapterView<?> av, View v, int position, long id) {

                //눌려진 사용자 아이디 전송

                Intent intent = new Intent(SearchProblemActivity.this, UserInfoActivity.class);
                startActivity(intent);
                return true;
            }
        });
        Log.i("setList", "end");
    }

    private class User
    {
        String id;
        String name;

        User(String cid, String cname) {
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

            TextView id = (TextView) convertView.findViewById(R.id.id);
            id.setText(arD.get(position).id);

            TextView name = (TextView) convertView.findViewById(R.id.name);
            name.setText(arD.get(position).name);

            return convertView;
        }
    }
}
