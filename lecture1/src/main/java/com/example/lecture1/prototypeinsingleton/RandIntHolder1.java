package com.example.lecture1.prototypeinsingleton;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
@Scope(value = "prototype")
public class RandIntHolder1 implements RandIntHolder {
    private final int randomInteger;

    public RandIntHolder1() {
        this.randomInteger = ThreadLocalRandom.current().nextInt(-10, 0);
    }

    @Override
    public int getInteger() {
        return randomInteger;
    }
}
