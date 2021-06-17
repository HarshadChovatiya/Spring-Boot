package com.sample.app.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.sample.app.helper.JwtUtil;
import com.sample.app.service.impl.CustomUserDetailsService;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String requestTokenHeader = request.getHeader("Authorization");
		String username=null;
		String jwtToken=null;
		//  && requestTokenHeader.startsWith("Bearer ")
		if(requestTokenHeader!=null) {
//			jwtToken = requestTokenHeader.substring(7);
			jwtToken = requestTokenHeader;
			try {
				username=this.jwtUtil.extractUsername(jwtToken);
			} catch(Exception e) {
				logger.error(e.getMessage());
			}
			
			try {
				UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(username);
				if(username!=null &&
						SecurityContextHolder.getContext().getAuthentication()==null) {
					
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
					new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
					
					usernamePasswordAuthenticationToken.setDetails(
							new WebAuthenticationDetailsSource().buildDetails(request));
					
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
					
				}
			} catch(UsernameNotFoundException e) {
				logger.error(e.getMessage());
			}
		}
		
		filterChain.doFilter(request, response);
	
	}

}