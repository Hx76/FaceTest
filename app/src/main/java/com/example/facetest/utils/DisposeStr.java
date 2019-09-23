package com.example.facetest.utils;

public class DisposeStr {
    public static String findclassid(String str){
        int index=0;
        String temp="";
        if(str.contains("A")){
            temp="A";
        }else if(str.contains("B")){
            temp="B";
        }else if(str.contains("C")){
            temp="C";
        }
        for(int i=str.indexOf(temp)+1;i<str.length();i++){
            if(str.charAt(i)>='0'&&str.charAt(i)<='9'){
                index=i;
            }else{
                break;
            }
        }
        str=str.substring(str.indexOf(temp), index+1);
        return str;
    }
    public static String findID(String str){
        str=str.replaceAll("\n", "");
        str=str.substring(str.length()-11, str.length());
        return str;
    }
}
