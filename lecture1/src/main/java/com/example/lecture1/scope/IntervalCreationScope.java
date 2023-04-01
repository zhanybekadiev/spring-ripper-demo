package com.example.lecture1.scope;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

import java.util.HashMap;
import java.util.Map;

public class IntervalCreationScope implements Scope {
    private final Map<String, Boolean> flags = new HashMap<>();
    private final Map<String, Object> beanMap = new HashMap<>();


    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        Boolean needsCreation = flags.get(name);

        if (needsCreation == null || needsCreation) {
            flags.put(name, false);
            Object bean = objectFactory.getObject();
            beanMap.put(name, bean);
            return bean;
        } else {
            flags.put(name, true);
            return beanMap.get(name);
        }
    }

    @Override
    public Object remove(String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object resolveContextualObject(String key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getConversationId() {
        throw new UnsupportedOperationException();
    }

}
