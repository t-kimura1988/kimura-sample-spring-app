package daiku.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.switchuser.SwitchUserFilter;

import daiku.app.security.GoenAuthenticationEntryPoint;
import daiku.app.security.OAuth2Filter;

@Configuration
@EnableWebSecurity
public class CustomWebSecurity {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, OAuth2Filter oAuth2Filter) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/actuator/**").permitAll()
                .anyRequest().authenticated());
        http.oauth2ResourceServer(oauth2 -> oauth2
                .authenticationEntryPoint(new GoenAuthenticationEntryPoint())
                .jwt(Customizer.withDefaults()));

        http.addFilterAfter(oAuth2Filter, SwitchUserFilter.class);
        return http.build();
    }
}
