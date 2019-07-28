package com.rabhareit.tailing.configration;

import com.rabhareit.tailing.service.TailingUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Autowired
  TailingUserDetailsService userDetailsService;

  @Autowired
  DataSource dataSource;

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("resources/**");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
          .antMatchers("css/**","js/**","/favicon.ico").permitAll()
          .anyRequest().authenticated()
        .and()
          .formLogin()
            .loginPage("/signin")
            .loginProcessingUrl("/signin/authenticate")
            .defaultSuccessUrl("/home",true)
            .failureUrl("/signin?param.error=bad_credentials")
            .usernameParameter("username")
            .passwordParameter("passwd")
            .permitAll()
        .and()
          .logout()
            .logoutUrl("/signout")
            .logoutSuccessUrl("/")
            .permitAll()
            .deleteCookies("JSESSIONID")
        .and()
          .apply(springSocialConfigurer().postLoginUrl("/home")
                                         .connectionAddedRedirectUrl("/home")
                                         .defaultFailureUrl("/signin?param.error=bad_credentials")
                                         .alwaysUsePostLoginUrl(true)
          );

  }

  @Autowired
  void configureAuthenticationManager(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService)
        .passwordEncoder(passwordEncoder());
  }

  @Bean
  TextEncryptor textEncryptor() {
    return new TextEncryptor() {
      public String encrypt(String plainText) {
        return Encryptors.noOpText().encrypt(plainText);
      }

      public String decrypt(String cipherText) {
        return Encryptors.noOpText().decrypt(cipherText);
      }
    };
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  SpringSocialConfigurer springSocialConfigurer() {
    return new SpringSocialConfigurer();
  }


}

