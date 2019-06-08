package com.rabhareit.tailing.configration;

import com.rabhareit.tailing.inplan.HttpComponentsClientHttpRequestFactoryAddBean;
import com.rabhareit.tailing.repository.TailingSocialUserRepository;
import com.rabhareit.tailing.service.TemporaryAccountDetailServiceImple;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletContextInitializer;
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

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.SessionTrackingMode;
import javax.sql.DataSource;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Autowired
  TailingSocialUserRepository socialUserRepository;

  @Autowired
  TemporaryAccountDetailServiceImple accountDetailServiceImple;

  //@Autowired
  //SocialUserDetailsServiceImplTest socialUserDetailsServiceImpl;

  @Autowired
  DataSource dataSource;

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("resources/**");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/auth/twitter").permitAll()
        .antMatchers("/api/**").authenticated()
        .and()
          .apply(springSocialConfigurer()
                 .connectionAddedRedirectUrl("/home")
                 .defaultFailureUrl("/signin?error=twitter"))
        .and()
          .formLogin()
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
    //auth.userDetailsService(socialUserDetailsServiceImpl).passwordEncoder(passwordEncoder());
    auth.jdbcAuthentication()
        .dataSource(dataSource)
        .usersByUsernameQuery("select username, password, true from Account where username = ?")
        .authoritiesByUsernameQuery("select username, 'ROLE_USER' from Account where username = ?")
        .passwordEncoder(passwordEncoder());

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


  /* *
   *
  @Autowired
  public ServletContextInitializer servletContextInitializer(@Value("${secure_cookie}")boolean secure) {

    ServletContextInitializer servletContextInitializer = new ServletContextInitializer() {
      @Override
      public void onStartup(ServletContext servletContext) throws ServletException {
        servletContext.getSessionCookieConfig().setHttpOnly(true);
        servletContext.getSessionCookieConfig().setSecure(secure);
        servletContext.setSessionTrackingModes(Collections.singleton(SessionTrackingMode.COOKIE));
      }
    };
    return servletContextInitializer;
  }

   */


}

