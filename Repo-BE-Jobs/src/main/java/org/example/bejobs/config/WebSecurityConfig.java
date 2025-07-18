package org.example.bejobs.config;

import lombok.RequiredArgsConstructor;
import org.example.bejobs.filter.JwtTokenFilter;
import org.example.bejobs.model.user.RoleEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.http.HttpMethod.*;

@Configuration
//@EnableMethodSecurity
@EnableWebSecurity
@EnableWebMvc
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final JwtTokenFilter jwtTokenFilter;
    @Value("${api.prefix}")
    private String apiPrefix;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
//                .cors(Customizer.withDefaults())
//                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(request -> {
                    request
                            .requestMatchers(
                                    String.format("%s/public/application/**", apiPrefix)
                            ).permitAll()

                            .requestMatchers(POST,
                                    String.format("%s/login/company", apiPrefix)
                            ).permitAll()

                            .requestMatchers(POST,
                                    String.format("%s/login/user", apiPrefix)
                            ).permitAll()

                            .requestMatchers(GET,
                                    String.format("%s/public/company-info", apiPrefix),
                                    String.format("%s/job-offers/search", apiPrefix)
                            ).permitAll()

                            .requestMatchers(POST,
                                    String.format("%s/user/register", apiPrefix),
                                    String.format("%s/apply/*", apiPrefix),
                                    String.format("%s/job-offers/search", apiPrefix)
                            ).permitAll()

                            .requestMatchers(POST,
                                    String.format("%s/company/register", apiPrefix)
                            ).permitAll()

                            .requestMatchers(GET,
                                    String.format("%s/public/job-offer", apiPrefix)
                            ).permitAll()

                            .requestMatchers(GET,
                                    String.format("%s/info-user/**", apiPrefix)
                            ).permitAll()

                            .requestMatchers(GET,
                                    String.format("%s/public/job-offer/detail", apiPrefix)
                            ).permitAll()
                            .requestMatchers(GET,
                                    String.format("%s/public/level", apiPrefix),
                                    String.format("%s/public/status", apiPrefix),
                                    String.format("%s/public/work-type", apiPrefix)
                            ).permitAll()
                            .requestMatchers(POST,
                                    String.format("%s/public/company-info/create", apiPrefix),
                                    String.format("%s/public/job-offer/create", apiPrefix)
                            ).hasAnyRole(RoleEntity.COMPANY)
//                            .requestMatchers(
//                                    String.format("%s/users/list-role", apiPrefix),
//                                    String.format("%s/users/delete-user/**", apiPrefix)
//                                    ).hasRole(RoleEntity.COMPANY)

                            .requestMatchers(PUT,
                                    String.format("%s/public/company-info/update/**", apiPrefix)
                            ).hasAnyRole(RoleEntity.COMPANY)
                            .requestMatchers(PUT,
                                    String.format("%s/public/job-offer/update/**", apiPrefix)
                            ).hasAnyRole(RoleEntity.COMPANY)

                            .requestMatchers(DELETE,
                                    String.format("%s/public/job-offer/**", apiPrefix),
                                    String.format("%s/user/**", apiPrefix)
                            ).hasRole(RoleEntity.COMPANY)
                            .anyRequest().authenticated();
                })
                .csrf(AbstractHttpConfigurer::disable);
        http.cors(new Customizer<CorsConfigurer<HttpSecurity>>() {
//            @Bean
            @Override
            public void customize(CorsConfigurer<HttpSecurity> httpSecurityCorsConfigurer) {
                CorsConfiguration configuration = new CorsConfiguration();
                configuration.setAllowedOrigins(List.of("*"));
                configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
                configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));
                configuration.setExposedHeaders(List.of("x-auth-token"));
                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                source.registerCorsConfiguration("/**", configuration);
                httpSecurityCorsConfigurer.configurationSource(source);
            }
        });

        return http.build();
    }
}
