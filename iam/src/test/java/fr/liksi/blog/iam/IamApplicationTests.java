package fr.liksi.blog.iam;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IamApplicationTests {

    @LocalServerPort
    int randomServerPort;

    @Test
    public void getSwaggerJsonFile() throws IOException {

        String swagger = new RestTemplate().getForObject("http://localhost:" + randomServerPort + "/v2/api-docs", String.class);
        Path path = Paths.get(System.getProperty("user.dir"), "target", "iam.json");

        FileCopyUtils.copy(swagger.getBytes(StandardCharsets.UTF_8),  path.toFile());
    }

}
