package io.pivotal.escqrs.apiservice.widget;

import java.util.Collection;
import java.util.Optional;

/**
 * @author Matt Stine
 */
public interface WidgetQueryService {
    Collection<Widget> getAll();
    Optional<Widget> get(Long id);
}
