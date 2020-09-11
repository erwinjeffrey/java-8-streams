package org.javainaction.lambdas.models;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ProductFactory {

    static Map<String, Supplier<Product>> map = new HashMap<>();
    static {
        map.put("loan", Loan::new);
        map.put("Stock", Stock::new);
        map.put("bond", Bond::new);
    }

    public static Product createProduct(String name){
       Supplier<Product> p = map.get(name);
       if(p != null) return  p.get();

        throw new IllegalStateException("No such product " + name);
    }
}
