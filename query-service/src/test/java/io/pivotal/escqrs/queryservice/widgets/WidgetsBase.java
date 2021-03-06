package io.pivotal.escqrs.queryservice.widgets;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;

/**
 * @author Matt Stine
 */
public class WidgetsBase {
	@Before
	public void setup() {
		RestAssuredMockMvc.standaloneSetup(new WidgetController());
	}
}
