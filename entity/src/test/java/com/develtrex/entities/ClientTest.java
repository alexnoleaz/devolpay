package com.develtrex.entities;

import com.develtrex.config.EntityConfig;
import lombok.extern.log4j.Log4j2;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


@Log4j2
public class ClientTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }
    @Test
    public void contextSpring(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(EntityConfig.class);

        Client oClient = context.getBean(Client.class);
        oClient.setNames("Alexander");
        System.out.println("oClient name: " + oClient.getNames());

        Client oClient2 = context.getBean(Client.class);
        oClient2.setNames("Cristhian");

        System.out.println("oClient2 name: " + oClient2.getNames());
    }
}
