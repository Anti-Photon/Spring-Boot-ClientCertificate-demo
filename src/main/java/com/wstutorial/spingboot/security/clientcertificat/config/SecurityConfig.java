package com.wstutorial.spingboot.security.clientcertificat.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AppConfig appConfig;

    @Autowired
    private ServerProperties serverProperties;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        if (appConfig.getEnabled() && serverProperties.getSsl().isEnabled()) {
            System.out.println("Enabled");
            http.authorizeRequests()
                    .antMatchers("/").permitAll()
                    .antMatchers("/protected").hasRole("USER")
                    .antMatchers("/admin").hasRole("ADMIN")
//                    .anyRequest().authenticated()
                    .and().x509()
                    .subjectPrincipalRegex("CN=(.*?)(?:,|$)")
                    .userDetailsService(userDetailsService())
                    .and().requiresChannel().antMatchers("/hello").requiresInsecure()
                    .and().requiresChannel().antMatchers("/protected").requiresSecure();
        }
        else {
            System.out.println("Disabled");
            http
                .requiresChannel()
                .anyRequest()
                .requiresInsecure();
        }
    }

    @Bean
    public UserDetailsService userDetailsService() {
        System.out.println("Filter::");
        return username -> {
            System.out.println("Username:" + username + " at " + java.util.Calendar.getInstance().getTime());
            for(String whitelistedCN : appConfig.getWhitelist()) {
                if (username.equals(whitelistedCN)) {
                    System.out.println(whitelistedCN);
                    return new User(username, "",
                            AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));
                }
            }
            System.out.println("Error: cert mismatch");
            throw new UsernameNotFoundException("User:" + username + " not found");
        };
    }

}

