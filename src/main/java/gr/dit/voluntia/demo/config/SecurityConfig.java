package gr.dit.voluntia.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // Δημόσιες σελίδες: login, signup κ.α.
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
                        ).permitAll() // Επιτρέπει σε όλους την πρόσβαση σε αυτές τις σελίδες

                        // Μόνο οι συνδεδεμένοι χρήστες να έχουν πρόσβαση στα dashboards
                        .requestMatchers("/volunteer_dashboard").hasAuthority("ROLE_VOLUNTEER")
                        .requestMatchers("/organization_dashboard").hasAuthority("ROLE_ORGANIZATION")
                        .requestMatchers("/admin_dashboard").hasAuthority("ROLE_ADMIN")

                        // Οποιοδήποτε άλλο αίτημα απαιτεί authentication
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login") // Η σελίδα login
                        .permitAll() // Επιτρέπει σε όλους την πρόσβαση στη φόρμα login
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
        return new BCryptPasswordEncoder(); // Ή οποιοδήποτε άλλο PasswordEncoder
    }
}
