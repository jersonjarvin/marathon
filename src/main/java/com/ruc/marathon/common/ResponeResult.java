package com.ruc.marathon.common;

public class ResponeResult <T>{
    public T data;
    public boolean success;
    public String message;
    public  ResponeResult(boolean success, String message, T data){
        this.data = data;
        this.success = success;
        this.message = message;
    }
    public  ResponeResult(boolean success, String message){
        this.success = success;
        this.message = message;
    }
    public ResponeResult(){

    }
}
