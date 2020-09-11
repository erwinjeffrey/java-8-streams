package org.javainaction.parameterization;

public class AppleGreenColorPredicate implements ApplePredicate {

    @Override
    public boolean test(Apple apple) {
       return "green".equals(apple.getColor());
    }
}
