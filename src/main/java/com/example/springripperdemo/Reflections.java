package com.example.springripperdemo;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

@UtilityClass
public class Reflections {
    public List<Field> getFields(Class<?> clazz) {
        return Arrays.asList(clazz.getDeclaredFields());
    }

    public List<Method> getMethods(Class<?> clazz) {
        return Arrays.asList(clazz.getMethods());
    }

    @SneakyThrows(ClassNotFoundException.class)
    public Class<?> classForName(String className) {
        return Class.forName(className);
    }

    @SneakyThrows(NoSuchMethodException.class)
    public Method getMethod(Class<?> clazz, String methodName, Class<?> ... parameterTypes) {
        return clazz.getMethod(methodName, parameterTypes);
    }

    @SneakyThrows({IllegalAccessException.class, InvocationTargetException.class})
    public Object invokeMethod(Method method, Object target, Object ... args) {
        method.setAccessible(true);
        return method.invoke(target, args);
    }

    @SneakyThrows(IllegalAccessException.class)
    public void setFieldValue(Object target, Field f, Object val) {
        f.setAccessible(true);
        f.set(target, val);
    }

    public boolean isAnyMethodAnnotatedBy(Class<?> clazz, Class<? extends Annotation> annotationClass) {
        return getMethods(clazz).stream().anyMatch(m -> m.isAnnotationPresent(annotationClass));
    }

    public List<Method> getMethodsAnnotatedBy(Class<?> clazz, Class<? extends Annotation> annotationClass) {
        return getMethods(clazz).stream()
                .filter(m -> m.isAnnotationPresent(annotationClass))
                .toList();
    }

    public List<Field> getFieldsAnnotatedBy(Class<?> clazz, Class<? extends Annotation> annotationClass) {
        return getFields(clazz).stream()
                .filter(f -> f.isAnnotationPresent(annotationClass))
                .toList();
    }
}
