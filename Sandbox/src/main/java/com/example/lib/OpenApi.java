package com.example.lib;

import com.example.internal.InternalObj;

public interface OpenApi {
    InternalObj method ();

    class Factory {
        public InternalObj create() {
            return new InternalObj();
        }
    }
}
