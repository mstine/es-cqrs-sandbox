package io.pivotal.escqrs.apiservice.widget;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * @author Matt Stine
 */
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Widget {
	@NonNull
	private Long id;
	@NonNull
	private String name;
}
