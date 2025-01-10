package gr.dit.voluntia.demo.config;

import gr.dit.voluntia.demo.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // Except static views from authentication
                        .requestMatchers(
                                "/css/**",          // CSS files
                                "/js/**",           // JavaScript files
                                "/images/**",       // Images
                                "/favicon.ico"      // Favicon
                        ).permitAll()

                        // Public pages
                        .requestMatchers(
                                "/",
                                "/public",
                                "/auth",
                                "/login",
                                "/signup",
                                "/signup/vol",
                                "/signup/org",
                                "/signup/admin"
                        ).permitAll()

                        // Dashboards accessible only from the authority role users
                        .requestMatchers("/dashboard/vol").hasAuthority("ROLE_VOLUNTEER")
                        .requestMatchers("/dashboard/org").hasAuthority("ROLE_ORGANIZATION")
                        .requestMatchers("/dashboard/admin").hasAuthority("ROLE_ADMIN")

                        // Any other request requires authentication
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/process-login")
                        .successHandler(customSuccessHandler())
                        .failureUrl("/login?error=true")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .permitAll()
                );
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler customSuccessHandler() {
        return (request, response, authentication) -> {
            String role = authentication.getAuthorities().stream()
                    .findFirst()
                    .get()
                    .getAuthority();

            if (role.equals("ROLE_ADMIN")) {
                response.sendRedirect("/dashboard/admin");
            } else if (role.equals("ROLE_ORGANIZATION")) {
                response.sendRedirect("/dashboard/org");
            } else if (role.equals("ROLE_VOLUNTEER")) {
                response.sendRedirect("/dashboard/vol");
            } else {
                response.sendRedirect("/");
            }
        };
    }
}