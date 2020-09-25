package io.ys.services;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class Request {

	private URI uri;
	private HttpEntity<String> httpEntity;
	private HttpHeaders headers;
	private HttpMethod method;
	private String newUri;

	public Request(HttpServletRequest request, HttpMethod method, JSONObject newUri, String body) {
		
		this.method = method;
		this.headers = BuildHeader(request);
		this.httpEntity = new HttpEntity<String>(body, headers);
		this.uri = BuidUri(request.getRequestURI(), newUri, 3000, request.getQueryString());

	}

	public ResponseEntity execute() throws HttpStatusCodeException,Exception{
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.exchange(uri, method, httpEntity, String.class);
	}

	private URI BuidUri(String requestUrl, JSONObject server, int port, String queryString) {
		try {
			URI uri = new URI("http", null, server.getString("IP"), server.getInt("PORT"), null, null, null);
//			uri = new URI(Server);
			uri = UriComponentsBuilder.fromUri(uri).path(requestUrl).query(queryString).build(true).toUri();
			return uri;
		} catch (URISyntaxException e) {
			return null;
		}

	}

	private HttpHeaders BuildHeader(HttpServletRequest request) {
		HttpHeaders headers = new HttpHeaders();
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			headers.set(headerName, request.getHeader(headerName));
		}
		return headers;
	}
}
