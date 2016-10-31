package com.example.gsdharmesh.medicare;

/**
 * Created by gsdharmesh on 10/25/16.
 */
public class doctors {
    private String name,spec,status;
    public doctors(){

    }
    public doctors(String a, String b,String c){
        this.name=a;
        this.spec=b;
        this.status=c;
    }
    public String getName(){
        return name;
    }
    public String getSpec(){
        return spec;
    }
    public String getStatus(){
        return status;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setSpec(String spec){
        this.spec=spec;
    }
    public void setStatus(String status){
        this.status=status;
    }
}
