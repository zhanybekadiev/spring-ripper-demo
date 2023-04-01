package com.example.lecture1.bfpp;

import com.example.common.Reflections;
import com.example.lecture1.annotation.Replaced;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class ReplaceImplBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            var beanDefinition = beanFactory.getBeanDefinition(beanDefinitionName);
            var beanClassName = beanDefinition.getBeanClassName();

            if (beanClassName == null) {
                continue;
            }

            Class<?> beanClass = Reflections.classForName(beanClassName);
            var replaced = beanClass.getAnnotation(Replaced.class);

            if (replaced == null) {
                continue;
            }

            var newImpl = replaced.newImpl();
            beanDefinition.setBeanClassName(newImpl.getName());
        }
    }
}
