package com.example.lecture1.service;

import com.example.lecture1.annotation.InjectRandomInt;
import com.example.lecture1.annotation.OnStartup;
import com.example.lecture1.annotation.Benchmark;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service("dummySpeakerImpl")
public class DummySpeakerImpl implements Speaker {
    @InjectRandomInt(min = 10, max = 100)
    private int repeat;

    @OnStartup
    @Benchmark
    @Override
    public void sayHelloRepeated() {
        for (int i = 0; i < repeat; i++) {
            log.info("Hello world!!! - " + (i + 1));
        }
    }

    @Override
    public void sayHello() {
        log.info("Hello without repeat");
    }

    @OnStartup
    @Override
    public void sayGoodBye() {
        log.info("Good bye");
    }
}
