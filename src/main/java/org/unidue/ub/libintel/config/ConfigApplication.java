package org.unidue.ub.libintel.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.security.Security;

@Configuration
@EnableConfigServer
@SpringBootApplication
public class ConfigApplication extends WebSecurityConfigurerAdapter {

	/**
	 * starts te config server, setting the higher crypto policy in order to use the extended cryptographic key file
	 * @param args run arguments
	 */
	public static void main(String[] args) {
		Security.setProperty("crypto.policy", "unlimited");
		SpringApplication.run(ConfigApplication.class, args);
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.csrf()
				.disable()
				.httpBasic()
				.and()
				.authorizeRequests()
				.antMatchers("/**").authenticated();
	}
}
