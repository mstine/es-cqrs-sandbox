package io.pivotal.escqrs.queryservice.widgets;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * @author Matt Stine
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Widget {
	@NonNull private Long id;
	@NonNull private String name;
}
