package com.example.lecture1.prototypeinsingleton;

import com.example.common.constant.CustomScope;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
@Scope(CustomScope.INTERVAL)
public class RandIntHolder3 implements RandIntHolder {
    private final int randomInteger;

    public RandIntHolder3() {
        this.randomInteger = ThreadLocalRandom.current().nextInt(Integer.MIN_VALUE, Integer.MIN_VALUE + 10);
    }

    @Override
    public int getInteger() {
        return randomInteger;
    }
}
