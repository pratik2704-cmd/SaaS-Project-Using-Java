package com.Saas.configuration;

import java.io.IOException;

import java.nio.charset.StandardCharsets;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.*;

public class JwtValidator extends OncePerRequestFilter {

    // Replace with your real secret (32+ characters)
    private static final String SECRET = "change-this-to-a-long-random-string-of-32-chars";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String jwt = request.getHeader(JwtConstant.JWT_HEADER);

        if (jwt != null) {
            jwt = jwt.substring(7);
            
            try {
            	SecretKey key = Keys.hmacShaKeyFor(JwtConstant.JWT_SECRET.getBytes());
            	Claims claims = Jwts.parser()
            			.verifyWith(key)
            			.build()
            			.parseSignedClaims(jwt)
            			.getPayload();
            	
            	String email = String.valueOf(claims.get("email"));
            	
            	String authorities = String.valueOf(claims.get("authorities"));
            	
            	
            	List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList(
            			authorities
            			);
            	
            	Authentication auth = new UsernamePasswordAuthenticationToken(email, null , auths);
            	
            	SecurityContextHolder.getContext().setAuthentication(
            			auth
            			);
            }
            catch (Exception e) {
				throw new BadCredentialsException("Invalid JWT...");
			}
        }
        filterChain.doFilter(request, response);
    }
}