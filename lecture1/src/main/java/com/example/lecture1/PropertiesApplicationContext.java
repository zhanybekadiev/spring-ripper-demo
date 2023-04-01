package com.example.lecture1;

import com.example.lecture1.service.Speaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;

@Slf4j
public class PropertiesApplicationContext extends GenericApplicationContext {

    @SuppressWarnings("deprecation")
    public PropertiesApplicationContext(String fileName) {
        PropertiesBeanDefinitionReader reader = new PropertiesBeanDefinitionReader(this);
        int i = reader.loadBeanDefinitions(fileName);
        log.info("found {} beans", i);
        refresh();
    }

    public static void main(String[] args) {
        PropertiesApplicationContext ctx = new PropertiesApplicationContext("context.properties");
        var speaker = ctx.getBean(Speaker.class);
        speaker.sayHello();
    }
}
