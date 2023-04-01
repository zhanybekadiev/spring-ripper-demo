package com.example.lecture1.service;

import com.example.lecture1.annotation.Benchmark;
import com.example.lecture1.annotation.InjectRandomInt;
import com.example.lecture1.annotation.OnStartup;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NewDummySpeakerImpl implements Speaker {
    @InjectRandomInt(min = 1, max = 5)
    private int repeated;


    @OnStartup
    @Benchmark
    @Override
    public void sayHelloRepeated() {
        for (int i = 0; i < repeated; i++) {
            log.info("Hello from Mars - {}", i + 1);
        }
    }

    @Override
    public void sayHello() {
        log.info("hello from Mars");
    }

    @OnStartup
    @Override
    public void sayGoodBye() {
        log.info("Good bye from mars");
    }
}
