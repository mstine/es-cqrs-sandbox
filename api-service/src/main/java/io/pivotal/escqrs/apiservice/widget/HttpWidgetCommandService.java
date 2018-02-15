package io.pivotal.escqrs.apiservice.widget;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * @author Matt Stine
 */
@Service
public class HttpWidgetCommandService implements WidgetCommandService {

	private RestTemplate restTemplate = new RestTemplate();

	@Override
	public Widget create(Widget widget) {
		ResponseEntity<Widget> response = restTemplate.postForEntity("http://localhost:8080/widgets", widget, Widget.class);
		return response.getBody();
	}

	@Override
	public void delete(Widget w) {
		restTemplate.delete("http://localhost:8080/widgets/{id}", w.getId());
	}

	@Override
	public Widget update(Widget widget) {
		URI uri = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/widgets/{id}").buildAndExpand(widget.getId()).toUri();

		ResponseEntity<Widget> response = restTemplate.exchange(RequestEntity.put(uri)
				.accept(MediaType.APPLICATION_JSON)
				.body(widget), Widget.class);

		return response.getBody();
	}
}
