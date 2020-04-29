package org.functionalInterface;

public class MainFunctional {
    public static void main(String[] args) {

        Predicate<String> p = s -> s.length() < 20;

        boolean b = p.test("Hello");
        System.out.println("Hello is shorter than 20 chars: " + b);


        // chaining predicate

        Predicate<String> a = s -> s.length() < 20;
        Predicate<String> a2 = s -> s.length() > 5;

        Predicate<String> a3 = a.and(a2);

        System.out.println("A3 for Yes: " + a3.test("Yes"));
        System.out.println("P3 for Good morning: " + a3.test("Good morning"));
        System.out.println("A3 for Good morning gentlemen: " + a3.test("Good morning gentlemen"));

        Predicate<String> a4 = a.or(a2);
        System.out.println("A4 for Yes: " + a4.test("Yes"));
        System.out.println("A4 for Good morning: " + a4.test("Good morning"));
        System.out.println("A4 for Good morning gentlemen: " + a4.test("Good morning gentlemen"));

        Predicate<String> a5 = Predicate.isEqualsTo("Yes");

        System.out.println("A5 for Yes: " + a5.test("Yes"));
        System.out.println("A5 for No: " + a5.test("No"));

        Predicate<Integer> p6 = Predicate.isEqualsTo(1);
    }
}
