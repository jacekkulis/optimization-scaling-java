package pl.dmcs.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ReflectionDemo {

    public static void main(String[] args) {
        Object obj = new Test();
        Class<?> reflectionClass = obj.getClass();

        Field[] fields = reflectionClass.getFields();
        System.out.println(getFieldNames(fields));


        try {
            Method method = reflectionClass.getMethod("getPrivateField");
            System.out.println(method.invoke(obj));

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        Method[] methods = reflectionClass.getMethods();
        System.out.println(getMethodNames(methods));

        try {
            Method method = reflectionClass.getMethod("test");
            method.invoke(obj);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private static List<String> getFieldNames(Field[] fields) {
        List<String> fieldNames = new ArrayList<>();
        for (Field field : fields)
            fieldNames.add(field.getName());
        return fieldNames;
    }

    private static List<String> getMethodNames(Method[] methods) {
        List<String> methodNames = new ArrayList<>();
        for (Method method : methods)
            methodNames.add(method.getName());
        return methodNames;
    }
}
