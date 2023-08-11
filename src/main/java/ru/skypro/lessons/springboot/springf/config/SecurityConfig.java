package ru.skypro.lessons.springboot.springf.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
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
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean

    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        // Устанавливаем наш созданный экземпляр PasswordEncoder
        // для возможности использовать его при аутентификации.
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public UserDetailsManager UserDetailManager(DataSource dataSource, AuthenticationManager authenticationManager) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.setAuthenticationManager(authenticationManager);
        return jdbcUserDetailsManager;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeHttpRequests(this::customizeRequest);
        return http.build();
    }

    public void customizeRequest(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
        try {
            registry.requestMatchers(new AntPathRequestMatcher("/admin/**"))
                    .hasAnyRole("ADMIN")  // Только для пользователей с ролью ADMIN.
                    .requestMatchers(new AntPathRequestMatcher("/**"))
                    .hasAnyRole("USER")   // Только для пользователей с ролью USER.
                    .and()
                    .formLogin().permitAll()  // Разрешаем всем доступ к форме ввода.
                    .and()
                    .logout().logoutUrl("/logout");  // Устанавливаем URL
            // для выхода из системы.

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
//       1  Создайте таблицы пользователей с хешированными паролями.

//       2  Добавьте в веб-приложение вызов окна для аутентификации пользователя.

//       3  Создайте роли USER и ADMIN. Настройте права доступа так, чтобы создание, модификация и удаление данных позволялось только пользователю с ролью ADMIN,
//        а пользователю с ролью USER были доступны только эндпойнты на чтение данных.