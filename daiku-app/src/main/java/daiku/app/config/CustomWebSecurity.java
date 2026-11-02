package daiku.app.config;

import daiku.app.security.GoenAuthenticationEntryPoint;
import daiku.app.security.OAuth2Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.switchuser.SwitchUserFilter;

@EnableWebSecurity
public class CustomWebSecurity extends WebSecurityConfigurerAdapter {
    @Autowired
    private OAuth2Filter oAuth2Filter;
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/actuator/**").permitAll().anyRequest().authenticated()
                .and()
                .oauth2ResourceServer()
                .authenticationEntryPoint(new GoenAuthenticationEntryPoint())
                .jwt();

        http.addFilterAfter(oAuth2Filter, SwitchUserFilter.class);
    }
}
