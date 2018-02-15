package io.pivotal.escqrs.apiservice.widget;

import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author Matt Stine
 */
@ControllerAdvice(annotations = RestController.class)
public class WidgetControllerAdvice {

	private final MediaType vndErrorMediaType = MediaType.parseMediaType("application/vnd.error+json");

	@ExceptionHandler(WidgetNotFoundException.class)
	public ResponseEntity<VndErrors> notFoundException(WidgetNotFoundException e) {
		return this.error(e, HttpStatus.NOT_FOUND, e.getWidgetId() + "");
	}

	private <E extends Exception> ResponseEntity<VndErrors> error(E error,
																  HttpStatus httpStatus,
																  String logref) {
		String msg = Optional.of(error.getMessage()).orElse(
				error.getClass().getSimpleName());
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(this.vndErrorMediaType);
		return new ResponseEntity<>(new VndErrors(logref, msg), httpHeaders,
				httpStatus);
	}
}
