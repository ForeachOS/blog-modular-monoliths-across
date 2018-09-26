package com.example.demo.application;

import com.example.demo.modules.SomeEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ApplicationComponent
{
	public ApplicationComponent() {
		LOG.info( "Component created: {}", getClass() );
	}

	@EventListener
	public void receive( SomeEvent event ) {
		event.add( getClass().getSimpleName() );
	}
}