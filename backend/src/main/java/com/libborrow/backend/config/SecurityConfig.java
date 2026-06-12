package com.libborrow.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
// 🛡️ TAMBAHAN IMPORT UNTUK CORS
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter; // 1. Suntikkan Satpam JWT yang baru saja kita buat

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // 🛡️ TAMBAHAN: Aktifkan CORS kustom agar tidak diblokir oleh browser browser
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            
            .csrf(csrf -> csrf.disable()) 
            
            // 2. SET STATELESS: Beritahu Spring Boot jangan membuat session di server (wajib untuk REST API + JWT)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            
            .exceptionHandling(ex -> ex.authenticationEntryPoint((request, response, authException) -> {
                System.out.println("❌ ERROR: Akses ditolak! Penyebab: " + authException.getMessage());
                response.sendError(403, "Forbidden: " + authException.getMessage());
            }))

            .authorizeHttpRequests(auth -> auth
                // Endpoint register & login boleh diakses siapa saja tanpa token
                .requestMatchers("/api/users/register", "/api/users/login").permitAll() 
                // Request sisanya (Buku, Peminjaman, Denda) WAJIB membawa token valid!
                .anyRequest().authenticated() 
            )
            
            // 3. DAFTARKAN FILTER: Pasang JwtFilter tepat SEBELUM filter login bawaan Java
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // 🛡️ TAMBAHAN BEAN: Konfigurasi Aturan CORS untuk GitHub Pages Kamu
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        
        // Mengizinkan asal (Origin) Frontend GitHub Pages kamu mengakses API backend
        configuration.setAllowedOrigins(List.of("https://arif-dp.github.io")); 
        
        // Mengizinkan metode/fungsi HTTP yang digunakan oleh frontend
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        
        // WAJIB mengizinkan Header Authorization agar token JWT bisa lewat lolos pemeriksaan
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type", "Accept"));
        
        // Izinkan pengiriman kredensial (seperti token/cookie) jika diperlukan
        configuration.setAllowCredentials(true);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Terapkan ke semua URL API
        return source;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}