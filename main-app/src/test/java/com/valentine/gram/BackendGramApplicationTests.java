package com.valentine.gram;

import com.valentine.gram.controller.PostController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BackendGramApplicationTests {

    @Autowired
    private PostController postController;

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    public void contextLoads() throws Exception {
        assertThat(postController).isNotNull();
    }

    @Test
    public void simpleReturnStringTest(){
       assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/test",
            String.class)).contains("Hello World");
    }

}
