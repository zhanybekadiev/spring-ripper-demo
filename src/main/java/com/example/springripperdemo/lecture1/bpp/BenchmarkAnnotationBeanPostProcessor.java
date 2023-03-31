package com.example.springripperdemo.lecture1.bpp;

import com.example.springripperdemo.lecture1.annotation.Benchmark;
import com.example.springripperdemo.Reflections;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Proxy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class BenchmarkAnnotationBeanPostProcessor implements BeanPostProcessor {
    private final Map<String, Class<?>> beanOriginClasses = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        var beanClass = bean.getClass();

        if (Reflections.isAnyMethodAnnotatedBy(beanClass, Benchmark.class)) {
            beanOriginClasses.put(beanName, beanClass);
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        var originClass = beanOriginClasses.remove(beanName);

        if (originClass == null) {
            return bean;
        }

        var log = LoggerFactory.getLogger(originClass);

        return Proxy.newProxyInstance(originClass.getClassLoader(), originClass.getInterfaces(), (proxy, method, args) -> {

            var originMethod = originClass.getMethod(method.getName(), method.getParameterTypes());

            if (originMethod.isAnnotationPresent(Benchmark.class)) {
                long start = System.nanoTime();
                var res = method.invoke(bean, args);
                long end = System.nanoTime();
                log.info("{}() - executed in {} ms", method.getName(), TimeUnit.NANOSECONDS.toMillis(end - start));
                return res;
            } else {
                return method.invoke(bean, args);
            }
        });
    }
}
