package org.javainaction.lambdas.anotation;

import java.lang.annotation.Repeatable;

@Repeatable(Authors.class)
@interface Author{ String name(); }
public @interface Authors {
    Author[] value();

}
