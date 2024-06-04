package com.example.installmgmt.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.GetMapping;


//verify docs for new way to EnableGlobalMethodSecurity
//
//merge imgmt endpoint branch with master then create new branch method authorization rules then copy this to imgmt
//is it possible to obtain some authorities and role info from the RS instead of just Authz server

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

  @Value("${jwksUri}")
  private String authzServPubKeyUri;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    //JwtAuthenticationToken
    
    //lesson 14 20min
    //to get uri -> call http://localhost:8080/.well-known/openid-configuration and look for jwks_uri
    http.oauth2ResourceServer(
      r -> r.jwt().jwkSetUri(authzServPubKeyUri)
        );

    http
      .authorizeHttpRequests(
          authorize -> authorize.anyRequest().authenticated()
          )
      .httpBasic(Customizer.withDefaults())
      .formLogin(Customizer.withDefaults());
      
      return http.build();
  }
  
}


/*
  public class ControllerA{
  
  @GetMapping("/demo")
  
  @PreAuthorize("hasAuthority('read')")
  public void dothings(){
  
  }
  }
  
  n2c J
  4157 @PreAuthorize(ʺ#userId == principal.idʺ)
  1 public void doSomething(@PathVariable String userId);
  
  
  - create CustomJwtAuthenticationTokenConverter that takes principal id and looks up the
  user in the DB to get roles
  - create view in teh DB to hold roles. | viewCols = user/principalId,
  isAdmin, installerRole (asst, lead, none)+installerId, memberRole+memberId
    -- could just be the status_id since the app has enums for the status name
        strings
    -- may not need member and installer ids; review while setting the method
        authorization rules
      --- because e.g. to view Node -> anyone, to edit Node -> admin|lead, nothing
        else would need to be done on a Node (member could request support but that
        would be responsibility of admin or lead to edit Node). To request support,
        would need to check whether the Node owns the an Install who's InstallRequest
        was made by the member clicking the button (button should not be visible
        unless that condition is met)
  
  
 */
