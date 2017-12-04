package com.paradygmaty;


class A {
    static {System.out.println("Loading class A");} //static initializer block
    public A() {System.out.println( "Object constructor of class A "); }
    {System.out.println( "Creating object of class A "); } //initializer block
}

class B {
    static {System.out.println("Loading class B");} //static initializer block
    {System.out.println( "Creating object of class B "); } //initializer block
    public B() {System.out.println( "Object constructor of class B "); }
}

public class Init
{
    public static void main(String[] args)
    {
        System.out.println("Inside main");
        System.out.println("Before the first object of class A");
        new A();
        System.out.println("Before the second object of class A");
        System.out.println("I'm an instance of class "  + new A().getClass().getSimpleName());
        try {
            System.out.println("Before calling: Class.forName(\"B\");");
            Class.forName("com.paradygmaty.B");
        } catch(Exception e)
        {System.out.println("Can't find class B");}
        System.out.println("After calling: Class.forName(\"B\");");
    }
}