package pl.dmcs.dynamicproxy;

import pl.dmcs.reflection.Test;
import pl.dmcs.reflection.Testable;

public class Test2 extends Test implements Testable {


    public Test2() {
        super();

    }

    @Override
    public void test() {

        System.out.println("test2()");
    }
}
