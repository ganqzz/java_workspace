package com.example;

import javax.persistence.Persistence;

public class JPASchemaGen {

    private static final String PU_NAME = "demoGenPU";

    public static void main(String[] args) {
        Persistence.generateSchema(PU_NAME, null);
        System.out.println("DDL have been generated");
    }
}
