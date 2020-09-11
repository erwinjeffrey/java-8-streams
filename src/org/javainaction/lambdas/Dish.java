package org.javainaction.lambdas;

public class Dish {
    private  String name;
    private  int  calories;
    private  boolean vegetarian;
    private  Type type;

    public Dish(String name, boolean vegetarian, int calories,  Type type) {
        this.name = name;
        this.calories = calories;
        this.vegetarian = vegetarian;
        this.type = type;
    }

    public Dish(String name, Integer calories) {
        this.name = name;
        this.calories = calories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                '}';
    }

    public enum Type { MEAT, FISH, OTHER}
}
