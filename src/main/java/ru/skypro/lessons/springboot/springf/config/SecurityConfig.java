package ru.skypro.lessons.springboot.springf.config;


import io.swagger.v3.oas.models.PathItem;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;


@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    @Autowired
    private UserDetailsService userDetailsService;
    private AuthUser user;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean

    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }




    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeHttpRequests(this::customizeRequest);

        return http.build();
    }

    private void customizeRequest(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
        try {
//            registry.requestMatchers(HttpMethod.GET, "/employee/**").hasAnyRole("USER", "ADMIN")
//                    .requestMatchers(HttpMethod.POST, "/employee/**").hasRole("ADMIN")
//                    .requestMatchers(HttpMethod.PUT, "/employee/**").hasRole("ADMIN")
//                    .requestMatchers(HttpMethod.DELETE, "/employee/**").hasRole("ADMIN")

            registry.requestMatchers(HttpMethod.GET, "/admin/**").hasAnyRole("USER", "ADMIN")
                    .requestMatchers(HttpMethod.POST, "/admin/**").hasAnyRole("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/admin/**").hasAnyRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/admin/**").hasAnyRole("ADMIN")

                    .and()
                    .formLogin().permitAll()
                    .and()
                    .logout().logoutUrl("/logout");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
//       1  Создайте таблицы пользователей с хешированными паролями.

//       2  Добавьте в веб-приложение вызов окна для аутентификации пользователя.

//       3  Создайте роли USER и ADMIN. Настройте права доступа так, чтобы создание, модификация и удаление данных позволялось только пользователю с ролью ADMIN,
//        а пользователю с ролью USER были доступны только эндпойнты на чтение данных.