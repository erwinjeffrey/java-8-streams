package org.javainaction.lambdas.chapter10;

import java.util.Optional;
import java.util.Properties;

public class chapter10Main {

    private static   Optional<Insurance>  optInsurance;

    public static void main(String[] args) {
        /* filtering with Optional */

        Optional<Insurance> optInsurance2 = optInsurance.filter(insurance ->
                "CambrigeInsurance".equals(insurance.getName()));

        Properties props = new Properties();
        props.setProperty("a","5");
        props.setProperty("b","true");
        props.setProperty("c", "-3");


    }
    public String getCarInsuranceName(Optional<Person> person){
        return person.flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown");
    }

    private static Optional<Integer> stringToInt(String s){
        try{
            return Optional.of(Integer.parseInt(s));
        }catch (NumberFormatException e){
            return Optional.empty();
        }
    }

    public int readDuration (Properties props, String name){
        String value = props.getProperty(name);
        if(value != null){
            try {
                int i = Integer.parseInt(value);
                if(i > 0){
                    return i;
                }
            }catch (NumberFormatException nfe){}
        }
        return 0;
    }

    public int readDuration2(Properties props, String name){
        return Optional.ofNullable(props.getProperty(name))
                .flatMap(n-> stringToInt(n))
                .filter(i -> i > 0)
                .orElse(0);
    }


}
