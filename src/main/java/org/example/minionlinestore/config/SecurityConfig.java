package org.example.minionlinestore.config;


import org.example.minionlinestore.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserService userService() {
        return new UserService();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        AuthenticationManagerBuilder builder =
                http.getSharedObject(AuthenticationManagerBuilder.class);

        builder.userDetailsService(userService())
                .passwordEncoder(passwordEncoder());
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/profile"
                        ).authenticated()
                        .anyRequest().permitAll()
                )

                .formLogin(form -> form
                        .loginPage("/sign-in-page")
                        .loginProcessingUrl("/to-enter")
                        .defaultSuccessUrl("/profile", true)
                        .failureUrl("/sign-in-page?autherror")
                        .usernameParameter("user_email")
                        .passwordParameter("user_password")
                        .permitAll()
                )

                .logout(logout -> logout
                        .logoutUrl("/sign-out")
                        .logoutSuccessUrl("/sign-in-page")
                        .permitAll()
                )

                .csrf(csrf -> csrf.disable());
        return http.build();
    }
}
