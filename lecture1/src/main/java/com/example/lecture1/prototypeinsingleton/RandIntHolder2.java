package com.example.lecture1.prototypeinsingleton;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
@Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RandIntHolder2 implements RandIntHolder {
    private final int randomInteger;

    public RandIntHolder2() {
        this.randomInteger = ThreadLocalRandom.current().nextInt(10);
    }

    @Override
    public int getInteger() {
        return randomInteger;
    }
}
