package io.pivotal.escqrs.apiservice.widget;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

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
				.buildAndExpand(4L).toUri();
		return ResponseEntity.created(uri).body(widget);
	}
}
