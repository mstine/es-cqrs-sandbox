package io.pivotal.escqrs.apiservice.widget;

/**
 * @author Matt Stine
 */
public interface WidgetCommandService {
	Widget create(Widget widget);

	void delete(Widget w);

	Widget update(Widget widget);
}
