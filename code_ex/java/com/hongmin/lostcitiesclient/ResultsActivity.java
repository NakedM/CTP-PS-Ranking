package com.hongmin.lostcitiesclient;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 유저의 게임 결과를 보여주는 Activity
 */

public class ResultsActivity extends Activity {

    private Button btGoCenter;
    private TextView tvTotal, tvWins, tvLoses, tvDraws;
    private ListView lvLastPlays;
    private ApplicationClass appclass;
    private ApplicationClass.AppState appstate;
    private ArrayList<LastPlays> list;
    private PlaysAdapter adapter;
    private String dn = "results";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        btGoCenter = (Button)findViewById(R.id.btGoCenter);
        tvTotal = (TextView)findViewById(R.id.tvTotal);
        tvWins = (TextView)findViewById(R.id.tvWins);
        tvLoses = (TextView)findViewById(R.id.tvLoses);
        tvDraws = (TextView)findViewById(R.id.tvDraws);
        lvLastPlays = (ListView)findViewById(R.id.lvLastPlays);

        appclass = (ApplicationClass)getApplicationContext();

        btGoCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        list = new ArrayList<LastPlays>();

        doGetResults();
    }

    public void doGetResults(){
        Log.d(dn,"show results");
        String msg =  appstate.SHOWTOT.val + "/"+appclass.CTS+"/" + appclass.getUsrId() +"/"+appclass.getUsrNum();
        appclass.sender.writeLine(msg);
        String[] msgs = appclass.receiver.getReadMsg().split("/");
        if(Integer.parseInt(msgs[appclass.STAT]) == appclass.SCS){
            String tot = "", wins = "", loses = "", draws="";
            //  flg/stat/total/wins/loses    <- 이 순서
            tot = msgs[2];
            wins = msgs[3];
            loses = msgs[4];
            draws = msgs[5];
            tvTotal.setText(tot);
            tvWins.setText(wins);
            tvLoses.setText(loses);
            tvDraws.setText(draws);
        }
        else{
            Toast.makeText(this,"결과 문제.",Toast.LENGTH_LONG).show();
        }
        Log.d(dn,"show plays");

        Boolean isReverse = false;
        for(int i=0;i<10;i++) {
            msgs = appclass.receiver.getReadMsg().split("/");
            if (Integer.parseInt(msgs[appclass.STAT]) == appclass.SCS) {
                String vsplayer = "", mysco = "", vssco = "";
                String winner = "";

                //  flg/stat/seqnum/vs Player/my score/vs score/ win?    <- 이 순서
                vsplayer = msgs[3];
                mysco = msgs[4];
                vssco = msgs[5];
                winner = msgs[6];
                LastPlays lp = new LastPlays(vsplayer, mysco, vssco, winner);
                list.add(lp);
            } else {
                //총 경기가 10개 미만일 때 Fail을 보내는데 그때부터는 0으로 채워넣음
                if(!isReverse) {
                    Collections.reverse(list);
                    isReverse = true;
                }
                LastPlays lp = new LastPlays("0","0", "0", "0");
                list.add(lp);
                Log.d(dn,"전적이 10개 미만");
            }
        }
        //Log.d(dn,list.toString());
        //최근 경기가 List View에서 위에 오도록 뒤집음
        if(!isReverse) {
            Collections.reverse(list);
            isReverse = true;
        }
        adapter = new PlaysAdapter(this, R.layout.item_last_play, list);
        lvLastPlays.setAdapter(adapter);
    }

    /**
     *  List View에 한줄다 하나의 항목이 아닌 여러 항목을 넣기 위해 Customize를 하고
     *  그러기 위한 Adapter클래스
     */
    class PlaysAdapter extends BaseAdapter{
        private Context context;
        private LayoutInflater inflater;
        private ArrayList<LastPlays> list;
        private int layout;

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position).getVsplayer();
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public PlaysAdapter(Context context, int layout, ArrayList<LastPlays> list){
            this.context = context;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            this.list = list;
            this.layout = layout;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = inflater.inflate(layout,parent,false);
            }
            LastPlays lp = list.get(position);
            Log.d(dn,lp.getMysco());
            if(lp != null){
                TextView tvVsPlayer = (TextView)convertView.findViewById(R.id.tvVsPlayer);
                TextView tvWinner = (TextView)convertView.findViewById(R.id.tvWinner);
                TextView tvVsSco = (TextView)convertView.findViewById(R.id.tvVsScore);
                TextView tvMySco = (TextView)convertView.findViewById(R.id.tvMyScore);

                tvVsPlayer.setText(lp.getVsplayer());
                tvWinner.setText(lp.getWinner());
                tvVsSco.setText(lp.getVssco());
                tvMySco.setText(lp.getMysco());
            }
            return convertView;
        }
    };
}
