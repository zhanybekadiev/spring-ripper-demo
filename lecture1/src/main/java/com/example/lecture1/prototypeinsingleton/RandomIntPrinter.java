package com.example.lecture1.prototypeinsingleton;

import com.example.lecture1.annotation.OnStartup;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
@Scope("singleton")
public abstract class RandomIntPrinter {
    private final RandIntHolder2 newForEachRead;

    public void printRandomInt1() {
        log.info("printRandomInt1(): {}", newForEachRead.getInteger());
    }

    public void printRandomInt2() {
        log.info("printRandomInt2(): {}", lookup().getInteger());
    }

    public void printRandomInt3() {
        log.info("printRandomInt3(): {}", customScopeLookup().getInteger());
    }

    @Lookup
    public abstract RandIntHolder1 lookup();

    @Lookup
    public abstract RandIntHolder3 customScopeLookup();




    @OnStartup
    public void demo() {
        for (int i = 0; i < 5; i++) {
            printRandomInt1();
        }

        for (int i = 0; i < 5; i++) {
            printRandomInt2();
        }

        for (int i = 0; i < 5; i++) {
            printRandomInt3();
        }

    }
}
