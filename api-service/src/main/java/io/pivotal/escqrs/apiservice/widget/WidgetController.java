package io.pivotal.escqrs.apiservice.widget;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * @author Matt Stine
 */
@RestController
@RequestMapping("/widgets")
public class WidgetController {

    private WidgetQueryService queryService;

    public WidgetController(WidgetQueryService queryService) {
        this.queryService = queryService;
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
}
