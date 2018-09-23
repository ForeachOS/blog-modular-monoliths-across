package com.example.demo;

import com.example.demo.modules.one.ModuleOne;
import com.example.demo.modules.three.ModuleThree;
import com.example.demo.modules.two.ModuleTwo;
import com.foreach.across.config.AcrossApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;

import java.util.Collections;

@AcrossApplication(
		modules = {

		}
)
public class DemoApplication
{
	@Bean
	public ModuleOne moduleOne() {
		return new ModuleOne();
	}

	@Bean
	public ModuleTwo moduleTwo() {
		return new ModuleTwo();
	}

	@Bean
	public ModuleThree moduleThree() {
		return new ModuleThree();
	}

	public static void main( String[] args ) {
		SpringApplication springApplication = new SpringApplication( DemoApplication.class );
		springApplication.setDefaultProperties( Collections.singletonMap( "spring.config.location", "${user.home}/dev-configs/demo-application.yml" ) );
		springApplication.run( args );
	}
}