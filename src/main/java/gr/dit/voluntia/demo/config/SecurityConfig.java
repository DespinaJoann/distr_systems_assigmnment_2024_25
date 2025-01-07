package gr.dit.voluntia.demo.config;

import gr.dit.voluntia.demo.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Autowired
    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    // Ορίζουμε τον AuthenticationManager που θα χρησιμοποιηθεί για την αυθεντικοποίηση
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(customUserDetailsService);
        return authenticationManagerBuilder.build();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Ορίζουμε τις πολιτικές ασφάλειας
        http
                .authorizeHttpRequests(authM -> authM
                        .requestMatchers("/auth/login", "/auth/signup", "/").permitAll()  // Επιτρέπει πρόσβαση στην αρχική σελίδα και στις σελίδες login/signup
                        .requestMatchers("/dashboard/**").authenticated()  // Η σελίδα dashboard απαιτεί αυθεντικοποίηση
                        .anyRequest().permitAll()  // Όλες οι άλλες σελίδες είναι δημόσιες
                )
                .csrf(AbstractHttpConfigurer::disable)  // Απενεργοποιούμε το CSRF για να αποφύγουμε προβλήματα με τις αιτήσεις
                .formLogin(form -> form
                        .loginPage("/auth/login")  // Ορίζουμε τη σελίδα login
                        .defaultSuccessUrl("/dashboard", true)  // Αν ο χρήστης συνδεθεί επιτυχώς, ανακατευθύνεται στο dashboard
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")  // Ορίζουμε το logout URL
                        .logoutSuccessUrl("/")  // Μετά την αποσύνδεση, ανακατευθύνει στην αρχική σελίδα
                        .permitAll()
                );

        return http.build();
    }
}
