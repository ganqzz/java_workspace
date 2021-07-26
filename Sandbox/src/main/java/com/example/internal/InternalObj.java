package com.example.internal;

import com.example.lib.OpenApi;

public class InternalObj implements OpenApi {
    @Override
    public InternalObj method() {
        return this;
    }

    public void hoge() {
        System.out.println("Hoge");
    }
}
