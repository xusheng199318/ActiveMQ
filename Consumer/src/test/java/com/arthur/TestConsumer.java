package com.arthur;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * Created by xusheng on 2019/3/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-activemq.xml")
public class TestConsumer {

    @Test
    public void testConsumer() throws IOException {
        System.in.read();
    }
}
