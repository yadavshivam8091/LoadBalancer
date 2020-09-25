package io.ys.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;

import io.ys.services.Request;
import io.ys.services.Server;
import io.ys.services.ServerAdapter;

@RestController
public class Routes {

	@Autowired
	@Qualifier("loadbalancer.serveradapter")
	private ServerAdapter serverAdapter;

	@RequestMapping("/**")
	public ResponseEntity mirrorRest(@RequestBody(required = false) String body, HttpMethod method,
			HttpServletRequest request, HttpServletResponse response) throws URISyntaxException, IOException {
		
		try {
			Server server = serverAdapter.getServer();
			Request Request = new Request(request, method, server.get(), body);
			return Request.execute();
		} catch (HttpStatusCodeException e) {
			return ResponseEntity.status(e.getRawStatusCode()).headers(e.getResponseHeaders())
					.body(e.getResponseBodyAsString());
		} catch (Exception e) {
			System.out.println("error encountered"+e.getMessage() + ">>>>>>"+e.getCause().getMessage());
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}
}
