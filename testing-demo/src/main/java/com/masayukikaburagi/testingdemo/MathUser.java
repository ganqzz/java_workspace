package com.masayukikaburagi.testingdemo;

public class MathUser {

    private SimpleMath math;

    public MathUser(SimpleMath math){
        this.math = math;
    }

    public void doSomeMath(){
        math.add(4,5);
    }

    public int doSomeMultiply(){
        return math.multiply(4,5);
    }
}
