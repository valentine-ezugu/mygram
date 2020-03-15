package com.valentine.gram;

import com.valentine.gram.controller.PostController;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner; 


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BackendGramApplicationTests {

    @Autowired
    private PostController postController;

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

}
