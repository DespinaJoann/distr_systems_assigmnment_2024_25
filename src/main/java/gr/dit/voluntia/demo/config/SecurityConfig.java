package gr.dit.voluntia.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // Εξαίρεση στατικών πόρων από authentication
                        .requestMatchers(
                                "/css/**", // CSS αρχεία
                                "/js/**",  // JavaScript αρχεία
                                "/images/**", // Εικόνες
                                "/favicon.ico" // Favicon
                        ).permitAll()

                        // Δημόσιες σελίδες
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

                        // Dashboards με ειδικά roles
                        .requestMatchers("/volunteer_dashboard").hasAuthority("ROLE_VOLUNTEER")
                        .requestMatchers("/organization_dashboard").hasAuthority("ROLE_ORGANIZATION")
                        .requestMatchers("/admin_dashboard").hasAuthority("ROLE_ADMIN")

                        // Οποιοδήποτε άλλο αίτημα απαιτεί authentication
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login") // Σελίδα login
                        .permitAll() // Πρόσβαση στη φόρμα login σε όλους
                )
                .logout(logout -> logout.permitAll()); // Δημόσιο logout

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
}
