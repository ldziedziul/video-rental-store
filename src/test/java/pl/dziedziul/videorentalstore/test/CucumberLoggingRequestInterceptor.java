package pl.dziedziul.videorentalstore.test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import cucumber.api.Scenario;

class CucumberLoggingRequestInterceptor implements ClientHttpRequestInterceptor {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Scenario scenario;

    CucumberLoggingRequestInterceptor(final Scenario scenario) {
        this.scenario = scenario;
    }

    @Override
    public ClientHttpResponse intercept(final HttpRequest request, final byte[] body, final ClientHttpRequestExecution execution) throws IOException {
        logRequest(request, body);
        ClientHttpResponse response = execution.execute(request, body);
        logResponse(response);
        return response;
    }

    private void logRequest(final HttpRequest request, final byte[] body) {
        log(">>>>>>>>>>>>>>>>>>>>>>>>>>>>REQUEST BEGIN>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        log("{0} {1}", request.getMethod(), request.getURI());
        HttpHeaders headers = request.getHeaders();
        formatHeaders(headers);
        log(prettifyJson(new String(body, StandardCharsets.UTF_8)));
        log(">>>>>>>>>>>>>>>>>>>>>>>>>>>>REQUEST END>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

    private void formatHeaders(final HttpHeaders headers) {
        headers.forEach((key, values) -> values.forEach(value -> log("{0}: {1}", key, value)));
    }

    private void logResponse(final ClientHttpResponse response) throws IOException {
        log("<<<<<<<<<<<<<<<<<<<<<<<<<<<<RESPONSE BEGIN<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        log(response.getStatusCode().toString());
        formatHeaders(response.getHeaders());
        log(prettifyJson(StreamUtils.copyToString(response.getBody(), StandardCharsets.UTF_8)));
        log("<<<<<<<<<<<<<<<<<<<<<<<<<<<<RESPONSE END<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
    }

    private String prettifyJson(final String jsonString) {
        if (jsonString.isBlank()) {
            return jsonString;
        }
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(objectMapper.readValue(jsonString, Object.class));
        } catch (IOException ex) {
            log("Problem with formatting json. Fallbacking to original. Cause", ex);
            return jsonString;
        }
    }

    private void log(final String pattern, final Object args0, final Object... arguments) {
        var actualArguments = new ArrayList<>(Arrays.asList(arguments));
        actualArguments.add(0, args0);
        scenario.write(MessageFormat.format(pattern, actualArguments.toArray()));
    }

    private void log(final Object object) {
        scenario.write(Objects.toString(object));
    }
}
