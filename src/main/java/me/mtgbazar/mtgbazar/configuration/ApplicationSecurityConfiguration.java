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



/*.authorizeHttpRequests()
                    .requestMatchers("/articles/create", "/articles/edit/**", "/articles/delete/**")
                        .authenticated() // Zde později budeme řešit role (autorizaci)
                    .requestMatchers(
                        "/styles/**", "/images/**", "/scripts/**", "/fonts/**",
                        "/articles", "/", "/skills", "/references", "/contact",
                        "/account/register", "/articles/**"
                    )
                        .permitAll()
                    .anyRequest() // Ostatní stránky jako např. `/articles/**` budou pouze pro přihlášené uživatele
                        .authenticated()
                    .and()
                .formLogin() // Pokud uživatel není přihlášen, přesměrujeme ho na login formulář
                    .loginPage("/account/login") // Přihlašovací URL adresa
                    .loginProcessingUrl("/account/login") // Přihlašovací URL adresa
                    .defaultSuccessUrl("/articles", true) // Nastavení přesměrování po úspěšném přihlášení
                    .usernameParameter("email") // Chceme se přihlašovat pomocí emailu
                    .permitAll() // Povolit vstup na `/account/login` i nepřihlášeným uživatelům
                    .and()
                .logout()
                    .logoutUrl("/account/logout") // Odhlašovací URL adresa
                    .and()
                .build();*/