package pl.dmcs.dynamicproxy;

import pl.dmcs.reflection.Testable;

import java.lang.reflect.Proxy;

public class DynamicProxyDemo {

    public static void main(String[] args) {
        Testable proxyInstance = (Testable) Proxy.newProxyInstance(
                DynamicProxyDemo.class.getClassLoader(), new Class[]{Testable.class}, new Test1());

        Testable proxyInstance1 = (Testable) Proxy.newProxyInstance(
                DynamicProxyDemo.class.getClassLoader(), new Class[]{Testable.class}, new Test2());

        proxyInstance.test();
        proxyInstance1.test();

    }
}
