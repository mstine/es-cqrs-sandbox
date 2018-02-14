package io.pivotal.escqrs.apiservice.widget;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * @author Matt Stine
 */
@Data
@RequiredArgsConstructor
public class Widget {
    @NonNull private Long id;
    @NonNull private String name;
}
