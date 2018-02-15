package io.pivotal.escqrs.apiservice.widget;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

/**
 * @author Matt Stine
 */
public class FakeWidgetQueryService implements WidgetQueryService {
	@Override
	public Collection<Widget> getAll() {
		return Arrays.asList(new Widget(1L, "Larry"),
				new Widget(2L, "Moe"),
				new Widget(3L, "Curly"));
	}

	@Override
	public Optional<Widget> get(Long id) {
		if (id.equals(5L)) return Optional.empty();
		return Optional.of(new Widget(1L, "Larry"));
	}
}
