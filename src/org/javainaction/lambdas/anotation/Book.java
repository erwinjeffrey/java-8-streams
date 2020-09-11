package org.javainaction.lambdas.anotation;

import java.util.Arrays;

@Author(name = "Raoul") @Author(name="Mario") @Author(name="Alan")
public class Book {
    public static void main(String[] args) {
        Author[] authors = Book.class.getAnnotationsByType(Author.class);
        Arrays.asList(authors).forEach(a -> {System.out.println(a.name());});
    }
}
