package com.jisutudy.forStudy.singleton;

public class StatefulService {

    private String msg; // 상태를 유지하는 필드
    public void send(String msg) {
        System.out.println("msg = " + msg);
        this.msg = msg; // 여기가 문제!
    }

    public String getMsg() {
        return msg;
    }
}
