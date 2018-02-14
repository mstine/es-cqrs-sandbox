package io.pivotal.escqrs.apiservice.widget;

/**
 * @author Matt Stine
 */
public class WidgetNotFoundException extends RuntimeException {

    private final Long id;

    public WidgetNotFoundException(Long id) {
        super("widget-not-found-" + id);
        this.id = id;
    }

    public Long getWidgetId() {
        return id;
    }
}
