package com.example.demo.common;

import java.util.HashMap;
import java.util.Map;


// 싱글톤 방식으로 하나의 인스턴스로 TimeMap 관리
public class TimeMap {
    private static Map<String,Integer> map;

    public static TimeMap getInstance() {
        return LazyHolder.INSTANCE;
    }

    public int get(String time){
        return map.get(time);
    }

    private static class LazyHolder {
        private static final TimeMap INSTANCE = new TimeMap();
    }

    private TimeMap(){
        Map<String,Integer> map = new HashMap<>();
        map.put("09:00",0);
        map.put("09:30",1);
        map.put("10:00",2);
        map.put("10:30",3);
        map.put("11:00",4);
        map.put("11:30",5);
        map.put("12:00",6);
        map.put("12:30",7);
        map.put("13:00",8);
        map.put("13:30",9);
        map.put("14:00",10);
        map.put("14:30",11);
        map.put("15:00",12);
        map.put("15:30",13);
        map.put("16:00",14);
        map.put("16:30",15);
        map.put("17:00",16);
        map.put("17:30",17);
        map.put("18:00",18);
        map.put("18:30",19);
        map.put("19:00",20);

        this.map = map;
    }
}
