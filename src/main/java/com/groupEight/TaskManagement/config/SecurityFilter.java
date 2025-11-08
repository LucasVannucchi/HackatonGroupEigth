package com.groupEight.TaskManagement.config;


import com.groupEight.TaskManagement.models.UsuarioModel;
import com.groupEight.TaskManagement.repository.UsuarioRepository;
import com.groupEight.TaskManagement.services.TokenServices;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenServices tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = recoverToken(request);

        if (token != null){
            var login = tokenService.tokenValidation(token);
            UserDetails userDetails = usuarioRepository.findByEmail(login);

            var authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request,response);
    }


    private String recoverToken(HttpServletRequest request){
        var auth = request.getHeader("Authorization");

        if (auth == null){
            return null;
        }
        return auth.replace("Bearer ", "");
    }
}
