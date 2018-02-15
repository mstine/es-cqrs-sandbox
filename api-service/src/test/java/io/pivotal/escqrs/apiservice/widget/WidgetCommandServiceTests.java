package io.pivotal.escqrs.apiservice.widget;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * @author Matt Stine
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureStubRunner(ids = {"io.pivotal.escqrs:cmd-service:+:stubs:8080"},
		stubsMode = StubRunnerProperties.StubsMode.LOCAL)
@DirtiesContext
public class WidgetCommandServiceTests {

	@Autowired
	private WidgetCommandService commandService;

	@Test
	public void shouldCreateWidget() {
		Widget widget = new Widget(4L, "Walter");

		Widget createdWidget = commandService.create(widget);

		assertEquals(widget, createdWidget);
	}

	@Test
	public void shouldDeleteWidget() {
		commandService.delete(new Widget(1L, "Larry"));
	}

	@Test
	public void shouldUpdateWidget() {
		Widget widget = new Widget(1L, "Jesse");

		Widget updatedWidget = commandService.update(widget);

		assertEquals(widget, updatedWidget);
	}
}
