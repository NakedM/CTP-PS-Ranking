package com.project.ctp.psrankingclient;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * JSON 파싱 클래스
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
     * @param stream 전체 JSON 문자열
     * @param JArrName 파싱할 JSON 배열의 이름
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

    /**
     * @return String[][] 파싱한 결과
     */
    public String[][] getParseredData()
    {
        return parseredData;
    }

    /**
     * jsonName 재설정
     * @param jsonName
     */
    public void setJsonName(String[] jsonName)
    {
        this.jsonName = jsonName;
    }
}
