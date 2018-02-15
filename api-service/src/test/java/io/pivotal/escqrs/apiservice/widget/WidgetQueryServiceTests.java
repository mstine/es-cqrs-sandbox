package io.pivotal.escqrs.apiservice.widget;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author Matt Stine
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureStubRunner(ids = {"io.pivotal.escqrs:query-service:+:stubs:8080"},
				stubsMode = StubRunnerProperties.StubsMode.LOCAL)
@DirtiesContext
public class WidgetQueryServiceTests {

	@Autowired
	private WidgetQueryService queryService;

	@Test
	public void shouldReturnAllWidgets() {
		Collection<Widget> widgets = queryService.getAll();

		assertThat(widgets, contains(Arrays.asList(equalTo(new Widget(1L, "Larry")),
				equalTo(new Widget(2L, "Moe")),
				equalTo(new Widget(3L, "Curly")))));
	}

	@Test
	public void shouldReturnWidgetById() {
		Optional<Widget> widget = queryService.get(1L);

		assertThat(widget.get(), equalTo(new Widget(1L, "Larry")));
	}
}
