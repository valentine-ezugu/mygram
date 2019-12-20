package com.valentine.utility;

import org.junit.Assert;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = IpLocator.class)
public class UtilityApplicationTests {

	@Test
	//@Ignore
	public void contextLoads() {
        Assert.assertEquals(1,1);
	}

}

