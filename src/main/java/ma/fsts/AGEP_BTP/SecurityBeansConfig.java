package ma.fsts.agep_btp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityBeansConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/devis/projet/*").permitAll()
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/api/materiaux").permitAll()
                .requestMatchers("/api/projets").permitAll()
                // .requestMatchers("/api/terrains").permitAll()
                .requestMatchers("/api/devis").permitAll()
                .requestMatchers("/api/employes/*").permitAll()


                    .anyRequest().authenticated()
            );

        return http.build();
}

}
