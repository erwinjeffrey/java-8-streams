package org.javainaction.lambdas;

public class Validator {
    private  ValidationStrategy strategy;

    public Validator(ValidationStrategy v){
        this.strategy = v;
    }

    public boolean validate(String s){
        return strategy.execute(s);
    }
}
