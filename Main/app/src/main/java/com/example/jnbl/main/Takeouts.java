package com.example.jnbl.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Pain on 2015/3/31.
 */
public class Takeouts {
    private static List<Map<String, Object>> list = null ;

    public static List<Map<String, Object>> getData(){
        //用单实例模式来解决问题
        if(list==null){
            list = new ArrayList<Map<String, Object>>();
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("foodName", "鱼香肉丝");
                map.put("time","12:00");
                map.put("payment", "1元外送");
                map.put("address","交通大学主区16号公寓");
                map.put("image_detail", R.drawable.list_1);
                map.put("phone","15652953340");
                map.put("distance","100米");
                list.add(map);
            return list;
        }else{
            return list;
        }
    }
    public static Map<String,Object> getItem(int index){
        if(list.size()>index && index > 0){
            return list.get(index);
        }
        else{
            return null;
        }
    }
    public static boolean push(Map<String,Object> item){
        if(item == null){

            return false;
        }else {
            list.add(item);
            return true;
        }

    }
    public static boolean remove(int index){

        if(list.size()>index && index >= 0){
            list.remove(index);
            return true;
        }
        else{
            return false;
        }
    }


    public static void sort(int way){
        //TODO 0是距离排序，1是时间排序
        switch (way){
            case 0:
                Collections.sort(list, new MapCPByDis());
                break;
            case 1:
                Collections.sort(list, new MapCPByTime());
                break;
        }
    }


}
