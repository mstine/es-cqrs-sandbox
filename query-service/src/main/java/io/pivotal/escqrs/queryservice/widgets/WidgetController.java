package io.pivotal.escqrs.queryservice.widgets;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author Matt Stine
 */
@RestController
@RequestMapping("/widgets")
public class WidgetController {

	@GetMapping
	public ResponseEntity<Collection<Widget>> getAll() {
		return ResponseEntity.ok(Arrays.asList(new Widget(1L, "Larry"),
				new Widget(2L, "Moe"),
				new Widget(3L, "Curly")));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Widget> get(@PathVariable Long id) {
		return ResponseEntity.ok(new Widget(1L, "Larry"));
	}
}
