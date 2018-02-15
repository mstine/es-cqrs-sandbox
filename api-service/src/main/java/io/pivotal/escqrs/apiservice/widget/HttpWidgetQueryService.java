package io.pivotal.escqrs.apiservice.widget;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.Optional;

/**
 * @author Matt Stine
 */
@Service
public class HttpWidgetQueryService implements WidgetQueryService {

	private RestTemplate restTemplate = new RestTemplate();

	@Override
	public Collection<Widget> getAll() {
		ParameterizedTypeReference<Collection<Widget>> widgets =
				new ParameterizedTypeReference<Collection<Widget>>() {
				};

		ResponseEntity<Collection<Widget>> response =
				restTemplate.exchange("http://localhost:8080/widgets",
						HttpMethod.GET, null, widgets);


		return response.getBody();
	}

	@Override
	public Optional<Widget> get(Long id) {
		ResponseEntity<Widget> response = restTemplate.getForEntity("http://localhost:8080/widgets/1",
				Widget.class);

		return Optional.ofNullable(response.getBody());
	}
}
