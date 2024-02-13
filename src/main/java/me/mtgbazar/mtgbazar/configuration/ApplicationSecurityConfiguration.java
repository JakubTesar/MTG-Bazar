package me.mtgbazar.mtgbazar.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ApplicationSecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/users", "/users/detail")
                        .authenticated()
                        .requestMatchers("/cards","/cards**", "/access/register", "/access/login","/login", "/access/logout", "/cards/**", "/users")
                        .permitAll()
                        .anyRequest() // Ostatní stránky jako např. `/articles/**` budou pouze pro přihlášené uživatele
                        .authenticated()
                )
                .formLogin((form) -> form
                                .loginPage("/access/login")
                                .loginProcessingUrl("/access/login")
                                .defaultSuccessUrl("/cards", true)
                                .usernameParameter("email")
                                .permitAll()
                )
                .logout((logout -> logout
                        .permitAll()
                        .logoutUrl("/access/logout")
                        .logoutSuccessUrl("/cards")
                        .deleteCookies("JSESSIONID")
                ));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}