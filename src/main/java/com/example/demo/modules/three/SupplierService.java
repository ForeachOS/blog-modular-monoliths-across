package com.example.demo.modules.three;

import com.example.demo.modules.SomeEvent;
import com.foreach.across.core.annotations.RefreshableCollection;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SupplierService
{
	private final ApplicationEventPublisher eventPublisher;

	private Collection<Supplier<String>> suppliers;

	@RefreshableCollection
	public void setSuppliers( Collection<Supplier<String>> suppliers ) {
		this.suppliers = suppliers;
	}

	@EventListener
	@Order(Ordered.HIGHEST_PRECEDENCE)
	public void receive( SomeEvent event ) {
		event.add( getClass().getSimpleName() );
	}

	public Collection<String> getSupplierNames() {
		return suppliers.stream()
		                .map( Object::getClass )
		                .map( Class::getSimpleName )
		                .collect( Collectors.toList() );
	}

	public Collection<String> getEventListeners() {
		SomeEvent event = new SomeEvent();
		eventPublisher.publishEvent( event );
		return event;
	}
}
