package innotech.com.configuration;

import org.springframework.boot.autoconfigure.security.servlet.SpringBootWebSecurityConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler.Builder;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{

	@Bean 
	public BCryptPasswordEncoder passwordEncoder() {
		 return new BCryptPasswordEncoder();
	 }
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		PasswordEncoder encoder = passwordEncoder();
		
		UserBuilder users = User.builder().passwordEncoder( password -> {
			return encoder.encode(password);
		});
		
		auth.inMemoryAuthentication()
		.withUser(users.username("admin").password("12345").roles("ADMIN","USER"))
		.withUser(users.username("cavalos").password("12345").roles("USER"))
		;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

      http.authorizeRequests().antMatchers("/","/css/**", "/js/**", "/image/**","/cliente/listar/**").permitAll()
      .antMatchers("/empresa/listar/**").hasAnyRole("USER")
      .antMatchers("/periodo/listar/**").hasAnyRole("USER")
      .antMatchers("/declaracion/listar/**").hasAnyRole("USER")
     
      .antMatchers("/uploads/**").hasAnyRole("ADMIN")
      .antMatchers("/empresa/**").hasAnyRole("ADMIN")
      .antMatchers("/cliente/**").hasAnyRole("ADMIN")
      .antMatchers("/declaracion/**").hasAnyRole("ADMIN")
      .antMatchers("/compra/**").hasAnyRole("ADMIN")
      
      .anyRequest().authenticated()
      .and()
	      .formLogin().loginPage("/login")
	      .permitAll()
      .and()
      .logout().permitAll()
      .and()
      .exceptionHandling().accessDeniedPage("/error_403")
      
      ;
	}
	
	

}
