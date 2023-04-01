package com.example.lecture1.bfpp;

import com.example.common.constant.CustomScope;
import com.example.lecture1.scope.IntervalCreationScope;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class CustomScopeRegistrationBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        beanFactory.registerScope(CustomScope.INTERVAL, new IntervalCreationScope());
    }
}
