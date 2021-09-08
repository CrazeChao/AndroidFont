package com.android.font;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Solution {

    HashMap<String,Integer> romainAnalyzeMap = new HashMap<String, Integer>();

    {   
        romainAnalyzeMap.put("I",1);
        romainAnalyzeMap.put("V",5);
        romainAnalyzeMap.put("X",10);
        romainAnalyzeMap.put("L",50);
        romainAnalyzeMap.put("C",100);
        romainAnalyzeMap.put("D",500);
        romainAnalyzeMap.put("M",1000);
        romainAnalyzeMap.put("IV",4);
        romainAnalyzeMap.put("IX",9);
        romainAnalyzeMap.put("XL",40);
        romainAnalyzeMap.put("XC",90);
        romainAnalyzeMap.put("CD",400);
        romainAnalyzeMap.put("CM",900);
    }

    public boolean isNotGroup(Character ch){
       return  "VLD".contains(ch.toString());
    }


    public int romanToInt(String s) {
        //拆解
        int total = 0;
        for (int i = 0; i < s.length(); i++) {
            Character pre = (i == 0 ? null : s.charAt(i - 1));
            if (pre == null) continue;
            Character cur = s.charAt(i);

            Integer integer = romainAnalyzeMap.get(pre.toString() + cur.toString());
            if (integer == null) {
               total+= romainAnalyzeMap.get(pre)+romainAnalyzeMap.get(cur);
            }
            if (integer != null){
                total +=integer;
            }
            i++;
        }
        return total;
    }

    public int romanToInt2(String s) {
        if (s == null || s.length() == 0)return 0;
        int total = 0;
        char[] characters = s.toCharArray();

        for (int i = 0; i < characters.length; i++) {
            Character c = characters[i];
            Character pre = (i == 0 ? null : (characters[i - 1] == 0)? null: characters[i - 1]);
            if (pre == null && isNotGroup(c)){
               total+=romainAnalyzeMap.get(c.toString());
               characters[i] = 0;
               continue;
            }
            if (pre != null) {
                Integer integer = romainAnalyzeMap.get(pre.toString() + c.toString());
                if (integer == null) {
                    integer = romainAnalyzeMap.get(pre.toString());
                }else {
                    characters[i]=0;
                }
                characters[i - 1] = 0;
                total += integer;
            }
        }
        Integer integer = romainAnalyzeMap.get(characters[characters.length-1]+"");
        if (integer != null){
            total+= integer;
        }
        return total;
    }
    /**
     * 性能优化
     * */
    public int romanToInt3(String s){
        if (s == null || s.length() == 0)return 0;
        int total = 0;
        for (int i = 0; i < s.length(); i++) {
            Integer value = null;
            if (i + 1 < s.length()) {
                value = romainAnalyzeMap.get(s.substring(i, i + 2));
            }
            if (value == null) {
                value = romainAnalyzeMap.get(s.substring(i, i + 1));
            }
            total += value;
        }
        return total;

    }


}
