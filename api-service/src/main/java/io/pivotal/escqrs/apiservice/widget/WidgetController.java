package io.pivotal.escqrs.apiservice.widget;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

/**
 * @author Matt Stine
 */
@RestController
@RequestMapping("/widgets")
public class WidgetController {

	private WidgetQueryService queryService;
	private WidgetCommandService commandService;

	public WidgetController(WidgetQueryService queryService,
							WidgetCommandService commandService) {
		this.queryService = queryService;
		this.commandService = commandService;
	}

	@GetMapping
	public ResponseEntity<Collection<Widget>> getAll() {
		return ResponseEntity.ok(this.queryService.getAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Widget> getOne(@PathVariable Long id) {
		return this.queryService.get(id).map(ResponseEntity::ok)
				.orElseThrow(() -> new WidgetNotFoundException(id));
	}

	@PostMapping
	public ResponseEntity<Widget> create(@RequestBody Widget w) {
		Widget widget = this.commandService.create(w);

		URI uri = MvcUriComponentsBuilder.fromController(getClass()).path("/{id}")
				.buildAndExpand(w.getId()).toUri();
		return ResponseEntity.created(uri).body(widget);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		return this.queryService.get(id)
				.map(w -> {
					commandService.delete(w);
					return ResponseEntity.noContent().build();
				}).orElseThrow(() -> new WidgetNotFoundException(id));
	}

	@PutMapping("/{id}")
	ResponseEntity<Widget> update(@PathVariable Long id, @RequestBody Widget w) {
		return this.queryService.get(id)
				.map(existing -> {
					Widget widget = commandService.update(new Widget(w.getId(), w.getName()));
					URI selfLink = URI.create(ServletUriComponentsBuilder.fromCurrentRequest()
						.toUriString());
					return ResponseEntity.created(selfLink).body(widget);
				}).orElseThrow(() -> new WidgetNotFoundException(id));
	}
}
