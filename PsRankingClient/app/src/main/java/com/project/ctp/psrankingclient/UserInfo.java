package com.project.ctp.psrankingclient;

/**
 * Created by Sangyun on 2015-05-27.
 */
public class UserInfo
{
    private String mem_id;
    private String stu_id;
    private String stu_name;
    private String join_year;
    private String boj_id;
    private String total;
    private String accept;
    private String wrong_answer;
    private String time_limit;
    private String run_error;
    private String compile_error;
    private String etc;

    public String getMem_id()
    {   return mem_id;  }
    public String getStu_id()
    {   return stu_id;  }
    public String getStu_name()
    {   return stu_name;    }
    public String getJoin_year()
    {   return join_year;   }
    public String getBoj_id()
    {   return boj_id;  }
    public String getTotal()
    {   return total;   }
    public String getAccept()
    {   return accept;  }
    public String getWrong_answer()
    {   return wrong_answer;    }
    public String getTime_limit()
    {   return time_limit;  }
    public String getRun_error()
    {   return run_error;   }
    public String getCompile_error()
    {   return compile_error;   }
    public String getEtc()
    {   return etc; }
}
