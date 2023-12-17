package cm;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

// https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
// https://www.baeldung.com/spring-deprecated-websecurityconfigureradapter

@Configuration
public class SecurityConfiguration {

    @Bean 
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        .csrf( csrf -> {
            csrf.ignoringRequestMatchers( new AntPathRequestMatcher("/contributor_auth", "POST") );
        })
        .authorizeHttpRequests
            ( authz -> authz
            .requestMatchers("/projects").permitAll()
            .requestMatchers("/contributor_auth").permitAll()
            .anyRequest().authenticated()
            )
        .httpBasic( withDefaults() );

        return http.build();

    }



}