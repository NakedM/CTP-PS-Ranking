package com.project.ctp.psrankingclient;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Sangyun on 2015-06-14.
 */
public class JParser
{
    String[] jsonName;
    String[][] parseredData;
    JParser(String[] jsonName)
    {
        this.jsonName = jsonName;
    }

    /**
     * Parsing JSON
     * @param stream
     * @param JArrName
     * @throws JSONException
     */
    public void Parsing(String stream, String JArrName) throws JSONException
    {
        JSONObject json = new JSONObject(stream);
        JSONArray jArr = json.getJSONArray(JArrName);
        parseredData = new String[jArr.length()][jsonName.length];

        for(int i=0 ; i<jArr.length(); i++) {
            json = jArr.getJSONObject(i);
            if(json != null) {
                for(int j=0 ; j<jsonName.length ; j++)
                    parseredData[i][j] = json.getString(jsonName[j]);
            }
        }

        for(int i=0 ; i<parseredData.length ; i++) {
            for(int j=0 ; j<parseredData[i].length ; j++) {
                Log.d("JSON" + jsonName[j], parseredData[i][j]);
            }
        }
    }
    public String[][] getParseredData()
    {
        return parseredData;
    }

    public void setJsonName(String[] jsonName)
    {
        this.jsonName = jsonName;
    }
}
