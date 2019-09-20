package com.example.csv2json.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ProcessorAspect {

    @Before("execution(* * com.example.csv2json.processor.*.process(..))")
    public void process() {
      log.info(">> Processing ");
    }

}
