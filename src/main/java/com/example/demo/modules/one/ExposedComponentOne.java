package com.example.demo.modules.one;

import com.foreach.across.core.annotations.Exposed;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
@Exposed
public class ExposedComponentOne implements Supplier<String>
{
	@Override
	public String get() {
		return "hello from module one";
	}
}
