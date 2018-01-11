package org.spring.springboot;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.spring.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import lombok.Getter;
import lombok.Setter;

/** 
 * token的校验 
 * 该类继承自BasicAuthenticationFilter，在doFilterInternal方法中， 
 * 从http头的Authorization 项读取token数据，然后用Jwts包提供的方法校验token的合法性。 
 * 如果校验通过，就认为这是一个取得授权的合法请求 
 * @author zhaoxinguo on 2017/9/13. 
 */  
@Getter
@Setter
public class JWTAuthenticationFilter extends BasicAuthenticationFilter {  
	@Autowired
    private UserDetailsService userDetailsService;
	 private UserService userService;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager ,UserService userService) {  
        super(authenticationManager); 
        this.userService = userService;
        
    }  
  
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {  
        String header = request.getHeader("Authorization");  
  
        if (header == null || !header.startsWith("Bearer ")) {  
            //chain.doFilter(request, response);  
            try {
				throw new Exception();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
            return;
        }  
  
        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);  
  
        SecurityContextHolder.getContext().setAuthentication(authentication);  
        chain.doFilter(request, response);  
  
    }  
  
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {  
        String token = request.getHeader("Authorization");  
        if (token != null) {  
            // parse the token.  
            String user = Jwts.parser()  
                    .setSigningKey("MyJwtSecret")  
                    .parseClaimsJws(token.replace("Bearer ", ""))  
                    .getBody()  
                    .getSubject(); 
            if (user != null) {  
                return new UsernamePasswordAuthenticationToken(user, this.userService.getUserByName(user).getPassword());  
            }  
            return null;  
        }  
        return null;  
    }  
  
}  
