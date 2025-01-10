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
                                "/login/vol",
                                "/login/org",
                                "/login/admin",
                                "/signup",
                                "/signup/vol",
                                "/signup/org",
                                "/signup/admin"
                        ).permitAll()

                        // Dashboards accessible only from the authority role users
                        .requestMatchers("/volunteer_dashboard").hasAuthority("ROLE_VOLUNTEER")
                        .requestMatchers("/organization_dashboard").hasAuthority("ROLE_ORGANIZATION")
                        .requestMatchers("/admin_dashboard").hasAuthority("ROLE_ADMIN")

                        // Any other request requires authentication
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")                    // login page
                        .loginProcessingUrl("/login")           // Managing/processing path for login
                        .defaultSuccessUrl("/dashboard", true)  // Redirect after success
                        .permitAll() // All users can have access to login page
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")       // logout URL
                        .logoutSuccessUrl("/")      // Redirect to home page after logout
                        .permitAll());              // logout is Public

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // BCrypt password encoder
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customUserDetailsService);       // We use our CustomUserDetailsService
        provider.setPasswordEncoder(passwordEncoder());                 // Configure BCrypt password encoder, for password safety
        return provider;
    }
}
