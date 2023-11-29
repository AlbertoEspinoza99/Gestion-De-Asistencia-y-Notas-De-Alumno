package com.colegio.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.colegio.utils.RSAKeyProperties;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;


import static org.springframework.security.config.Customizer.withDefaults;

import java.time.Duration;
import java.util.Arrays;

@Configuration
public class SecurityConfiguration {
	
	
	
	
	
	 private final RSAKeyProperties keys;

	    public SecurityConfiguration(RSAKeyProperties keys){
	        this.keys = keys;
	    }
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new  BCryptPasswordEncoder();
	}
	
	
	 @Bean
	    public AuthenticationManager authManager(UserDetailsService detailsService){
	        DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();
	        daoProvider.setUserDetailsService(detailsService);
	        daoProvider.setPasswordEncoder(passwordEncoder());
	        return new ProviderManager(daoProvider);
	    }
	
	 
	@Bean
	public SecurityFilterChain filterchain(HttpSecurity http) throws Exception{
		
	          http
	            .cors(withDefaults())
				.csrf(csrf->csrf.disable())
				.authorizeHttpRequests(auth->{
				auth.requestMatchers("/auth/**","/menu/**","/matricula/**","/asistencia/**","/curso/**","/ALUMNO/**","/PROFESOR/**","/upload/**","/email/**").permitAll();
				//auth.requestMatchers("/PROFESOR/**").hasRole("PROFESOR");
				//auth.requestMatchers("/ALUMNO/**").hasRole("ALUMNO");
				auth.anyRequest().authenticated();
				});		        
				 http.oauth2ResourceServer()
	                .jwt()
	                .jwtAuthenticationConverter(jwtAuthenticationConverter());
	        http.sessionManagement(
	                session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	            );
	
	        return http.build();
	}
	
	
	
	@Bean
    public JwtDecoder jwtDecoder(){
        return NimbusJwtDecoder.withPublicKey(keys.getPublicKey()).build();
    }

    @Bean
    public JwtEncoder jwtEncoder(){
        JWK jwk = new RSAKey.Builder(keys.getPublicKey()).privateKey(keys.getPrivateKey()).build();
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter(){
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("roles");
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
        JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
        jwtConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return jwtConverter;
    }
	
	
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
     CorsConfiguration cc = new CorsConfiguration();
                   cc.setAllowedHeaders(Arrays.asList("Origin,Accept", "X-Requested-With", "Content-Type", "Access-Control-Request-Method", "Access-Control-Request-Headers","Authorization"));
                   cc.setExposedHeaders(Arrays.asList("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));                
     cc.setAllowedOrigins(Arrays.asList("/*"));
     cc.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "PUT","PATCH","DELETE"));
                   cc.addAllowedOrigin("http://localhost:4200");
                   cc.setMaxAge(Duration.ZERO);
                   cc.setAllowCredentials(Boolean.TRUE);
     UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
     source.registerCorsConfiguration("/**", cc);
     return source;
    }
	
    
    //email
    
    
    
    
	

}
