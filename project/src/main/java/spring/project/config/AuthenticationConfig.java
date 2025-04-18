package spring.project.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import spring.project.exception.ErrorCode;
import spring.project.filter.JwtAuthenticationFilter;
import spring.project.service.UserService;

import java.io.IOException;

@Log4j2
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class AuthenticationConfig {

    private final UserService userService;

    @Value("${jwt.secret-key}")
    private String key;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/main")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/user/login")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/user/create")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/workout/list")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/user/logout")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/admin/**")).hasRole("admin")
                                .requestMatchers(new AntPathRequestMatcher("/workout/**")).hasAnyRole("admin", "trainer", "user")
                                .anyRequest().authenticated()
//                                .hasAnyRole("user", "trainer")
                )
                .exceptionHandling(e -> {
                    e.accessDeniedHandler(new AccessDeniedHandler() {
                        @Override
                        public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
                            log.info(ErrorCode.ACCESS_DENIED.getMessage());
                            response.setContentType("text/html;charset=utf-8");
                            response.getWriter().append("<script>alert('접근 권한이 없습니다.');location.href='/main';</script>");
                        }
                    });
                })
                .addFilterBefore(new JwtAuthenticationFilter(key, userService), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
