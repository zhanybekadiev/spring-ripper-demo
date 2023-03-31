package com.example.lecture1.bpp;

import com.example.common.Reflections;
import com.example.lecture1.annotation.InjectRandomInt;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class InjectRandomIntAnnotationBeanPostProcessor implements BeanPostProcessor {


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        var fields = Reflections.getFieldsAnnotatedBy(bean.getClass(), InjectRandomInt.class);

        if (fields.isEmpty()) {
            return bean;
        }

        var rand = ThreadLocalRandom.current();

        for (Field field : fields) {
            InjectRandomInt annotation = field.getAnnotation(InjectRandomInt.class);

            int min = annotation.min();
            int max = annotation.max();
            int randomInt = min + rand.nextInt(max - min);

            Reflections.setFieldValue(bean, field, randomInt);
        }

        return bean;
    }
}
