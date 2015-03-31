package com.example.jnbl.main;

import java.util.Comparator;
import java.util.Map;

/**
 * Created by Pain on 2015/3/31.
 */
public class MapCPByDis implements Comparator {
    public int compare(Object arg0, Object arg1) {
        Map<String, Object> user0=(Map<String, Object>)arg0;
        Map<String, Object> user1=(Map<String, Object>)arg1;


        int flag = user0.get("distance").toString().compareTo(user1.get("distance").toString());
        if(flag==0){
            return user0.get("time").toString().compareTo(user1.get("time").toString());
        }else{
            return flag;
        }
    }

}


