package com.valentine.gram;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
 import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner; 


@RunWith(SpringRunner.class)
@SpringBootTest
public class BackendGramApplicationTests {

 @Test
 //@Ignore
 public void contextLoads() {
  Assert.assertEquals(1,1);
 }

}
