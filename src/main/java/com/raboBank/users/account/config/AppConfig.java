package com.raboBank.users.account.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class AppConfig extends WebSecurityConfigurerAdapter {

    @Value("${app.username}")
    private String username;

    @Value("${app.password}")
    private String password;

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * Configures the authentication manager to use the custom UserDetailsService and PasswordEncoder.
     *
     * @param auth AuthenticationManagerBuilder object to configure
     * @throws Exception if an error occurs during authentication configuration
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    /**
     * Configures the HTTP security settings for the application.
     *
     * @param http HttpSecurity object to configure
     * @throws Exception if an error occurs during security configuration
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/users/allTransactions", "/api/users/balance").permitAll() // Allow access to GET endpoints
                .antMatchers("/api/users/withdraw", "/api/users/transfer").authenticated() // Require authentication for POST endpoints
                .and().httpBasic()
                .and().csrf().disable();
    }

    /**
     * Creates a UserDetailsService bean with a single user (admin) loaded from the configuration properties.
     *
     * @return UserDetailsService bean
     */
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username(username)
                .password(passwordEncoder().encode(password))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user);
    }

    /**
     * Creates a PasswordEncoder bean for encoding passwords.
     *
     * @return PasswordEncoder bean
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
