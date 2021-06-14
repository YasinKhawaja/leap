package edu.ap.group10.leapwebapp.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public JWTAuthorizationFilter(AuthenticationManager authManager) {
        super(authManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        String header = req.getHeader(SecurityConstraints.HEADER_STRING);

        if (header == null || !header.startsWith(SecurityConstraints.TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

        StringBuilder sb = new StringBuilder();
        sb.append("User: " + authentication.getPrincipal() + " tried to: " + req.getMethod() + req.getRequestURI());

        Enumeration<String> paramNames = req.getParameterNames();
        if (paramNames.hasMoreElements()) {
            sb.append("/?");
        }
        while (paramNames.hasMoreElements()) {
            String param = paramNames.nextElement();
            sb.append(param);
            String[] paramValues = req.getParameterValues(param);
            for (String value : paramValues) {
                sb.append("=" + value);
            }
            if (paramNames.hasMoreElements()) {
                sb.append("&");
            }
        }
        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(req);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        try {
            chain.doFilter(wrappedRequest, res);
        } finally {
            byte[] buf = wrappedRequest.getContentAsByteArray();
            if (buf.length > 0) {
                try {
                    String requestBody = new String(buf, 0, buf.length, wrappedRequest.getCharacterEncoding());
                    sb.append("\nRequest body:" + requestBody);
                } catch (Exception e) {
                    log.error("Error while trying to read request body", e);
                }
            }
            log.trace(sb.toString());
        }
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(SecurityConstraints.HEADER_STRING);

        if (token != null) {
            String user = JWT.require(Algorithm.HMAC512(SecurityConstraints.SECRET.getBytes())).build()
                    .verify(token.replace(SecurityConstraints.TOKEN_PREFIX, "")).getSubject();

            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
            }

            return null;
        }

        return null;
    }
}