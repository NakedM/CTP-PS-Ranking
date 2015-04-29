package com.project.ctp.psrankingclient;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class SearchProblemActivity extends ActionBarActivity {

    private Button btn_selectOldOrder;
    private Button btn_selectYoungOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_problem);

        btn_selectOldOrder = (Button)findViewById(R.id.btn_selectOldOrder);
        btn_selectYoungOrder = (Button)findViewById(R.id.btn_selectYoungOrder);

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
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);

        if(v==btn_selectOldOrder) //오래된 기수를 선택했을때 group_id is 0
        {
            menu.setHeaderTitle("오래된 기수");
            menu.add(0,0,0,"전체");
            menu.add(0,1,0,"2011/1");
            menu.add(0,2,0,"2011/2");
            menu.add(0,3,0,"2012/1");
            menu.add(0,4,0,"2012/2");
            menu.add(0,5,0,"2013/1");
            menu.add(0,6,0,"2013/2");
            menu.add(0,7,0,"2014/1");
            menu.add(0,8,0,"2014/2");
            menu.add(0,9,0,"2015/1");
            menu.add(0,10,0,"2015/2");
            menu.add(0,11,0,"2016/1");
            menu.add(0,12,0,"2016/2");
            menu.add(0,13,0,"2017/1");
            menu.add(0,14,0,"2017/2");
            menu.add(0,15,0,"2018/1");
            menu.add(0,16,0,"2018/2");
            menu.add(0,17,0,"2019/1");
            menu.add(0,18,0,"2019/2");
            menu.add(0,19,0,"2020/1");
            menu.add(0,20,0,"2020/2");
        }
        else if(v==btn_selectYoungOrder) //최근 기수를 선택했을때 group_id is 1
        {
            menu.setHeaderTitle("최근 기수");
            menu.add(1, 0, 0, "전체");
            menu.add(1, 1, 0, "2011/1");
            menu.add(1, 2, 0, "2011/2");
            menu.add(1, 3, 0, "2012/1");
            menu.add(1, 4, 0, "2012/2");
            menu.add(1, 5, 0, "2013/1");
            menu.add(1, 6, 0, "2013/2");
            menu.add(1, 7, 0, "2014/1");
            menu.add(1, 8, 0, "2014/2");
            menu.add(1, 9, 0, "2015/1");
            menu.add(1, 10, 0, "2015/2");
            menu.add(1, 11, 0, "2016/1");
            menu.add(1, 12, 0, "2016/2");
            menu.add(1, 13, 0, "2017/1");
            menu.add(1, 14, 0, "2017/2");
            menu.add(1, 15, 0, "2018/1");
            menu.add(1, 16, 0, "2018/2");
            menu.add(1, 17, 0, "2019/1");
            menu.add(1, 18, 0, "2019/2");
            menu.add(1, 19, 0, "2020/1");
            menu.add(1, 20, 0, "2020/2");
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) //컨텍스트 아이템을 선택했을때(입력 확인용)
    {
        if(item.getGroupId() == 0)
        {
            /*switch(item.getItemId()) 스위치문 나중에 사용 예정
            {
                case 1:
            }*/
            btn_selectOldOrder.setText(item.getTitle()); // 버튼 내용 변경
        }
        else if(item.getGroupId() == 1)
        {
            /*switch(item.getItemId()) 스위치문 나중에 사용 예정
            {
                case 1:
            }*/
            btn_selectYoungOrder.setText(item.getTitle());
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_problem, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
