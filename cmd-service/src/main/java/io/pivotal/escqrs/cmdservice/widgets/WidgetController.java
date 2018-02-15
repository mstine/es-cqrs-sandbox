package io.pivotal.escqrs.cmdservice.widgets;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * @author Matt Stine
 */
@RestController
@RequestMapping("/widgets")
public class WidgetController {

	@PostMapping
	public ResponseEntity<Widget> create(@RequestBody Widget widget) {
		URI uri = MvcUriComponentsBuilder.fromController(getClass()).path("/{id}")
				.buildAndExpand(widget.getId()).toUri();

		return ResponseEntity.created(uri).body(widget);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Widget> put(@PathVariable Long id, @RequestBody Widget widget) {
		URI selfLink = URI.create(ServletUriComponentsBuilder.fromCurrentRequest()
				.toUriString());
		return ResponseEntity.created(selfLink).body(widget);
	}
}
