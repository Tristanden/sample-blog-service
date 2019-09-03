package fr.liksi.blog.blogservice.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String[] SWAGGER_RESOURCES = {
            "/v2/api-docs",
            "/configuration/ui",
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/webjars/**" };

    @Value("${basicauth.password:pwd}")
    private String password;

    @Value("${basicauth.user:user}")
    private String user;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(SWAGGER_RESOURCES);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // If we let CSRF enabled, every non-GET request will require a CSRF Token and return 403 if not provided
        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/csrf").permitAll()
                .antMatchers("/").permitAll()
                //.and().httpBasic()
                .anyRequest().authenticated()
        .and().httpBasic();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser(user).password(encoder().encode(password)).roles("ADMIN");
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
