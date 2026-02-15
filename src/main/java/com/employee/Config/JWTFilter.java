package com.employee.Config;


import com.employee.Entity.Employee;
import com.employee.Repo.EmpRepository;
import com.employee.Service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class JWTFilter extends OncePerRequestFilter {
    private final JWTService jwtService;
    private final EmpRepository empRepository;

    public JWTFilter(JWTService jwtService, EmpRepository empRepository) {
        this.jwtService = jwtService;
        this.empRepository = empRepository;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws RuntimeException, ServletException, IOException {
        String token=httpServletRequest.getHeader("Authorization");
        if(token != null && token.startsWith("Bearer ")){
            String tokenValue=token.substring(8,token.length()-1);
            String  username=jwtService.getUsername(tokenValue);
            Optional<Employee> optionalEmployee=empRepository.findByUsername(username);
            if(optionalEmployee.isPresent()){
                Employee employee=optionalEmployee.get();
                UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(employee,null,null);
                authenticationToken.setDetails(new WebAuthenticationDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            }
        }

          filterChain.doFilter(httpServletRequest,httpServletResponse);


    }


}
