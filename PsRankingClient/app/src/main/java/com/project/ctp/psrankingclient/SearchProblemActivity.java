package com.project.ctp.psrankingclient;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.ContextMenu;
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
            setContexMenu(menu, 0);

        }
        else if(v==btn_selectYoungOrder) //최근 기수를 선택했을때 group_id is 1
        {
            menu.setHeaderTitle("최근 기수");
            setContexMenu(menu, 1);
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

    public void setContexMenu(ContextMenu menu, int GID)
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
