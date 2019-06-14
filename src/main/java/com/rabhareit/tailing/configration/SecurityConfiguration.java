package com.rabhareit.tailing.configration;

import com.rabhareit.tailing.inplan.HttpComponentsClientHttpRequestFactoryAddBean;
import com.rabhareit.tailing.service.TemporaryAccountDetailServiceImple;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.savedrequest.NullRequestCache;
import org.springframework.social.security.SpringSocialConfigurer;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Autowired
  TemporaryAccountDetailServiceImple accountDetailServiceImple;

  @Autowired
  DataSource dataSource;

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("resources/**");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.formLogin()
            .loginPage("/signin")
            .loginProcessingUrl("/signin/authenticate")
            .defaultSuccessUrl("/home")
            .failureUrl("/signin?param.error=bad_credentials")
            .usernameParameter("username")
            .passwordParameter("passwd")
            .permitAll()
        .and()
          .logout()
            .logoutUrl("/signout")
            .logoutSuccessUrl("/")
            .deleteCookies("JSESSIONID")
            .permitAll()
        .and()
          .authorizeRequests()
            .antMatchers("/resources/**","/favicon.ico","/auth/**","/signin/**","/signup/**","/sig/**").permitAll()
            .anyRequest().authenticated()
        .and()
          .headers().frameOptions().disable()  //for h2
        .and()
          .requestCache().requestCache(new NullRequestCache())
        .and()
          .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
        .and()
          .rememberMe()
          .useSecureCookie(true)
        .and()
          .csrf().disable();
  }


  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(accountDetailServiceImple).passwordEncoder(passwordEncoder());
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  SpringSocialConfigurer springSocialConfigurer() {
    return new SpringSocialConfigurer();
  }

  @Bean
  public RestTemplate restTemplate() {

    HttpClient httpClient = HttpClients.custom()
        .setDefaultRequestConfig(RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build())
        .build();

    RequestConfig requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build();
    HttpComponentsClientHttpRequestFactoryAddBean factory = new HttpComponentsClientHttpRequestFactoryAddBean();
    factory.mergeRequestConfiguration(requestConfig);
    factory.setHttpClient(httpClient);
    return new RestTemplate(factory);
  }

}

