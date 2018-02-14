package io.pivotal.escqrs.apiservice.widget;

/**
 * @author Matt Stine
 */
public class FakeWidgetCommandService implements WidgetCommandService {
    @Override
    public Widget create(Widget widget) {
        return widget;
    }
}
