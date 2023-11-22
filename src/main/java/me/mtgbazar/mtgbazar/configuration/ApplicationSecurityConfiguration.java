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
        return http
                .authorizeHttpRequests(
                        registry -> {
                            registry.requestMatchers("/users", "/users/detail")
                                    .authenticated()
                                    .requestMatchers("/cards", "/access/register", "/access/login", "/access/logout")
                                    .permitAll()
                                    .anyRequest() // Ostatní stránky jako např. `/articles/**` budou pouze pro přihlášené uživatele
                                    .authenticated();
                            try {
                                //form.loginPage("/access/login").loginProcessingUrl("/access/login").successForwardUrl("/cards");
                                // po tomhle nefunguje login. Proč? To ví jen bůh! ☺
                                http.formLogin(form -> {});
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }).logout((logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/cards"))).build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}