package com.valentine.gram;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BackendGramApplicationTests {

    @Test
    public void dummyTest() {

    }

//    @Test
//    public void testAWSCredentials() throws Exception {
//        // Ask for connection
//        AmazonEC2 client = ec2comm.getEC2();
//        assertNotNull(client);
//        assertNotNull(credProvider);
//
//        AWSCredentials credentials = credProvider.getCredentials();
//        assertEquals("access_key", credentials.getAWSAccessKeyId());
//        assertEquals("secret_key", credentials.getAWSSecretKey());
//
//        credProvider.refresh();
//        credentials = credProvider.getCredentials();
//        assertEquals("access_key", credentials.getAWSAccessKeyId());
//        assertEquals("secret_key", credentials.getAWSSecretKey());
//
//    }

}
