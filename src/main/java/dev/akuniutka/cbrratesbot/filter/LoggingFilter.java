package dev.akuniutka.cbrratesbot.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

@Component
@Slf4j
public class LoggingFilter extends OncePerRequestFilter {
    private static final List<MediaType> SUPPORTED_MEDIA_TYPES = Arrays.asList(
            MediaType.valueOf("text/*"),
            MediaType.APPLICATION_JSON
    );

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        if (isAsyncDispatch(request)) {
            filterChain.doFilter(request, response);
        } else {
            ContentCachingRequestWrapper requestWrapper = wrapRequest(request);
            ContentCachingResponseWrapper responseWrapper = wrapResponse(response);
            String prefix = requestWrapper.getRemoteAddr() + "|>";
            try {
                if (log.isInfoEnabled()) {
                    logRequestLineAndHeaders(requestWrapper, prefix);
                }
                filterChain.doFilter(requestWrapper, responseWrapper);
            } finally {
                if (log.isInfoEnabled()) {
                    logRequestBody(requestWrapper, prefix);
                    logResponse(responseWrapper, prefix);
                }
                responseWrapper.copyBodyToResponse();
            }
        }
    }

    private void logRequestLineAndHeaders(ContentCachingRequestWrapper request, String prefix) {
        String queryString = request.getQueryString();
        if (queryString == null) {
            log.info("{} {} {}", prefix, request.getMethod(), request.getRequestURI());
        } else {
            log.info("{} {} {}?{}", prefix, request.getMethod(), request.getRequestURI(), queryString);
        }
        Collections.list(request.getHeaderNames()).forEach(
                headerName -> Collections.list(request.getHeaders(headerName)).forEach(
                        headerValue -> log.info("{} {} {}", prefix, headerName, headerValue)
                )
        );
        log.info("{}", prefix);
    }

    private void logRequestBody(ContentCachingRequestWrapper request, String prefix) {
        byte[] content = request.getContentAsByteArray();
        if (content.length > 0) {
            logContent(content, request.getContentType(), request.getCharacterEncoding(), prefix);
            log.info("{}", prefix);
        }
    }

    private void logResponse(ContentCachingResponseWrapper response, String prefix) {
        int status = response.getStatus();
        log.info("{} {} {}", prefix, status, HttpStatus.valueOf(status).getReasonPhrase());
        response.getHeaderNames().forEach(
                headerName -> response.getHeaders(headerName).forEach(
                        headerValue -> log.info("{} {} {}", prefix, headerName, headerValue)
                )
        );
        log.info("{}", prefix);
        byte[] content = response.getContentAsByteArray();
        if (content.length > 0) {
            logContent(content, response.getContentType(), response.getCharacterEncoding(), prefix);
            log.info("{}", prefix);
        }
    }

    private void logContent(byte[] content, String contentType, String contentEncoding, String prefix) {
        MediaType mediaType = MediaType.valueOf(contentType);
        if (SUPPORTED_MEDIA_TYPES.stream().anyMatch(supportedType -> supportedType.includes(mediaType))) {
            try {
                String contentString = new String(content, contentEncoding);
                Stream.of(contentString.split("\n\r|\r\n")).forEach(line -> log.info("{} {}", prefix, line));
            } catch (UnsupportedEncodingException e) {
                log.info("{} [{} bytes content]", prefix, content.length);
            }
        } else {
            log.info("{} [{} bytes content]", prefix, content.length);
        }
    }

    private ContentCachingRequestWrapper wrapRequest(HttpServletRequest request) {
        if (request instanceof ContentCachingRequestWrapper) {
            return (ContentCachingRequestWrapper) request;
        } else {
            return new ContentCachingRequestWrapper(request);
        }
    }

    private ContentCachingResponseWrapper wrapResponse(HttpServletResponse response) {
        if (response instanceof ContentCachingResponseWrapper) {
            return (ContentCachingResponseWrapper) response;
        } else {
            return new ContentCachingResponseWrapper(response);
        }
    }
}
