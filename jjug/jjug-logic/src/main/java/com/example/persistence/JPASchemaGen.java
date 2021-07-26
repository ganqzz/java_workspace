package com.example.persistence;

import javax.persistence.Persistence;

public class JPASchemaGen {

    private static final String PERSISTENCE_UNIT_NAME = "jjugGenPU";

    public static void main(String[] args) {
        Persistence.generateSchema(PERSISTENCE_UNIT_NAME, null);
        System.out.println("DDL have been generated");
    }
}
