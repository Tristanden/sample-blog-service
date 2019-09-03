package fr.liksi.blog.blogservice.config;

import fr.liksi.blog.connector.ApiClient;
import fr.liksi.blog.connector.iam.AccountApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IAMClientConfig {

    @Value("${iam.url}")
    private String iamUrl;

    @Value("${iam.auth.user}")
    private String iamAuthUser;

    @Value("${iam.auth.password}")
    private String getIamAuthPwd;

    @Bean
    public AccountApi accountApi() {
        ApiClient apiClient = new ApiClient();
        apiClient.setBasePath(iamUrl);
        apiClient.setUsername(iamAuthUser);
        apiClient.setPassword(getIamAuthPwd);
        AccountApi accountApi = new AccountApi(apiClient);
        return accountApi;
    }

}
