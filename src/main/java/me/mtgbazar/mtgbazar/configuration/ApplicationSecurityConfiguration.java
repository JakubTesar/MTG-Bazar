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
                .authorizeHttpRequests(registry -> {
                    registry.requestMatchers(
                            ""
                    ).authenticated().requestMatchers("").permitAll()
                            .anyRequest() // Ostatní stránky jako např. `/articles/**` budou pouze pro přihlášené uživatele
                            .authenticated();

                    try {
                        http.formLogin(formConfigurer -> {}); // Pokud uživatel není přihlášen, přesměrujeme ho na login formulář
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }).build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
