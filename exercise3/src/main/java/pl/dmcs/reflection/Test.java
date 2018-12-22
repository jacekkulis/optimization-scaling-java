package pl.dmcs.reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class Test implements Testable, InvocationHandler {

    private String privateField;
    public int publicField;
    public int secondPublicField;

    public Test() {
        this.privateField = "pole";
        this.publicField = 2;
        this.secondPublicField = 3;
    }

    @Override
    public void test() {
        System.out.println("testMain()");
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        test();
        return 44;
    }


    public String getPrivateField() {
        return privateField;
    }
}
