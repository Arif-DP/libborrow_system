package com.libborrow.backend.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 🛡️ PERBAIKAN 1: IZINKAN REQUEST "OPTIONS" (PRE-FLIGHT CORS)
        // Browser selalu mengirimkan OPTIONS sebelum request asli (GET/POST)
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            filterChain.doFilter(request, response);
            return;
        }
        
        // 1. Ambil header bernama "Authorization" dari request frontend
        String authHeader = request.getHeader("Authorization");

        // 2. Cek apakah token ada dan formatnya benar
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7); 

            try {
                // 3. Validasi keaslian token
                if (jwtUtil.validateToken(token)) {
                    String email = jwtUtil.extractEmail(token);
                    String role = jwtUtil.extractRole(token);

                    SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role.toUpperCase());

                    UsernamePasswordAuthenticationToken authToken = 
                            new UsernamePasswordAuthenticationToken(email, null, Collections.singletonList(authority));

                    // 5. Beri tahu Spring Security bahwa request ini SAH
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            } catch (Exception e) {
                // Token tidak valid atau kadaluwarsa, jangan lakukan apa-apa
                // Biarkan filterChain melanjutkan, nanti Spring Security akan menolak akses (403)
            }
        }

        // Lanjutkan request ke API
        filterChain.doFilter(request, response);
    }
}