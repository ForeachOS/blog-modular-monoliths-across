package com.example.demo.modules.two;

import com.foreach.across.core.AcrossModule;
import com.foreach.across.core.annotations.AcrossDepends;
import com.foreach.across.core.context.configurer.ApplicationContextConfigurer;
import com.foreach.across.core.context.configurer.ComponentScanConfigurer;

import java.util.Set;

@AcrossDepends(required = "ModuleOne")
public class ModuleTwo extends AcrossModule
{
	@Override
	public String getName() {
		return "ModuleTwo";
	}

	@Override
	protected void registerDefaultApplicationContextConfigurers( Set<ApplicationContextConfigurer> contextConfigurers ) {
		contextConfigurers.add( ComponentScanConfigurer.forAcrossModule( ModuleTwo.class ) );
	}
}
