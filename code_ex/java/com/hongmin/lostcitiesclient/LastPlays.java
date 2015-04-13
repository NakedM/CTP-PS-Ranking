package com.hongmin.lostcitiesclient;

/**
 * Customize ListView에 들어갈 항목
 */
public class LastPlays {
    private  String vsplayer = "", mysco = "", vssco="", winner="";

    public LastPlays(String vsplayer, String mysco, String vssco, String winner){
        this.vsplayer = vsplayer;
        this.mysco = mysco;
        this.vssco = vssco;
        this.winner = winner;
    }

    public String getVsplayer() {
        return vsplayer;
    }

    public String getMysco() {
        return mysco;
    }

    public String getVssco() {
        return vssco;
    }

    public String getWinner() {
        return winner;
    }
}
