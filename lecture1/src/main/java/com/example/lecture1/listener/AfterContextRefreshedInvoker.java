package com.example.lecture1.listener;

import com.example.common.Reflections;
import com.example.lecture1.annotation.OnStartup;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Slf4j
@Component
@RequiredArgsConstructor
public class AfterContextRefreshedInvoker {
    private final ConfigurableListableBeanFactory beanFactory;

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        var ctx = event.getApplicationContext();

        for (var beanDefinitionName : ctx.getBeanDefinitionNames()) {
            var beanDef = beanFactory.getBeanDefinition(beanDefinitionName);
            var beanClassName = beanDef.getBeanClassName();

            if (beanClassName == null) {
                continue;
            }

            var originalClass = Reflections.classForName(beanClassName);

            for (Method toInvoke : Reflections.getMethodsAnnotatedBy(originalClass, OnStartup.class)) {
                var bean = beanFactory.getBean(beanDefinitionName);
                var beanMethod = Reflections.getMethod(bean.getClass(), toInvoke.getName(), toInvoke.getParameterTypes());
                Reflections.invokeMethod(beanMethod, bean);
            }
        }
    }
}
