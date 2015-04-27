package com.project.ctp.psrankingclient;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
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

        if(v.getId()==R.id.btn_selectOldOrder) //오래된 기수를 선택했을때
        {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.context_order, menu);
            menu.setHeaderTitle("오래된 기수");
        }
        else //최근 기수를 선택했을때
        {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.context_order, menu);
            menu.setHeaderTitle("최근 기수");
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) //컨텍스트 아이템을 선택했을때(입력 확인용)
    {
        SubMenu menu = item.getSubMenu();
        //submenu가 없을 경우 return
        if(menu!=null)
            return false;

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton("확인", null);
        builder.setTitle("선택된 메뉴");
        builder.setMessage(item.getTitle());
        builder.show();

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
