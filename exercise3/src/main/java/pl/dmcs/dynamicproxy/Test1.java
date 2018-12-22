package pl.dmcs.dynamicproxy;

import pl.dmcs.reflection.Test;
import pl.dmcs.reflection.Testable;

public class Test1 extends Test implements Testable {

    public Test1() {
        super();
    }

    @Override
    public void test() {
        System.out.println("test1()");
    }
}
