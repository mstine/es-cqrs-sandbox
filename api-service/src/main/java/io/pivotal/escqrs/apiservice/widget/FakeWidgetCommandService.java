package io.pivotal.escqrs.apiservice.widget;

/**
 * @author Matt Stine
 */
public class FakeWidgetCommandService implements WidgetCommandService {
	@Override
	public Widget create(Widget widget) {
		return widget;
	}

	@Override
	public void delete(Widget w) {
		// do nothing
	}

	@Override
	public Widget update(Widget widget) {
		return widget;
	}
}
