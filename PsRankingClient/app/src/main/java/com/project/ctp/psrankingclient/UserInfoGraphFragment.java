package com.project.ctp.psrankingclient;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class UserInfoGraphFragment extends Fragment {
    private String dn = "UserInfoGraph";
    public static final String ASSET_PATH = "file:///android_asset/";
    private WebView wv_userInfoGraph;
    private static final String ARG_SECTION_NUMBER = "section_number";

    private int accept;
    private int wrong;
    private int timelimit;
    private int memorylimit;
    private int outputlimit;
    private int runtimeerr;
    private int compileerr;

    public static UserInfoGraphFragment newInstance(int sectionNumber) {
        UserInfoGraphFragment fragment = new UserInfoGraphFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_user_info_graph, container, false);
        wv_userInfoGraph = (WebView) v.findViewById(R.id.wv_userInfoGraph);

        WebSettings ws = wv_userInfoGraph.getSettings();
        ws.setJavaScriptEnabled(true);
        ws.setBuiltInZoomControls(true);
        ws.setJavaScriptCanOpenWindowsAutomatically(true);
        loadChart();
        /*wv_userInfoGraph.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
               view.loadUrl(url);
               loadChart();
               return true;
            }
        });*/
        //wv_userInfoGraph.loadUrl("http://");
        return v;
    }

    private void loadChart() {
        String content = null;
        try {
            AssetManager assetManager = getActivity().getAssets();
            InputStream in = assetManager.open("user_info_graph.html");
            byte[] bytes = readFully(in);
            content = new String(bytes, "UTF-8");
        } catch (IOException e) {
            Log.d(dn,e.getMessage());
        }
        String formattedContent = String.format(content, 1, 2, 3, 4,5,6,7);
        wv_userInfoGraph.loadDataWithBaseURL(ASSET_PATH, formattedContent, "text/html", "utf-8", null);
        //wv_userInfoGraph.requestFocusFromTouch();
    }

    private static byte[] readFully(InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        for (int count; (count = in.read(buffer)) != -1;) {
            out.write(buffer, 0, count);
        }
        return out.toByteArray();
    }


}
