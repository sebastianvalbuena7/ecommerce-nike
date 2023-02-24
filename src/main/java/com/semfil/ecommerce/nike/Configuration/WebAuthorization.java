package com.semfil.ecommerce.nike.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@EnableWebSecurity
@Configuration
public class WebAuthorization {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/sendEmail", "/api/newClient").permitAll()
                .antMatchers("/products.html").hasAuthority("CLIENT")
                .antMatchers(HttpMethod.GET, "/api/getClients").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/deleteProduct/{id}", "/deleteClient/{id}").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET,  "/api/getProducts").permitAll()
                .antMatchers("/**/**").permitAll();

        httpSecurity.formLogin()
                .usernameParameter("email")
                .passwordParameter("password")
                .loginPage("/api/login");

        httpSecurity.logout().logoutUrl("/api/logout").deleteCookies("JSESSIONID");

        httpSecurity.sessionManagement().maximumSessions(1);

        httpSecurity.csrf().disable();

        httpSecurity.headers().frameOptions().disable();

        // si el usuario no está autenticado, simplemente envíe una respuesta de falla de autenticación
        httpSecurity.exceptionHandling().authenticationEntryPoint((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

        httpSecurity.exceptionHandling().authenticationEntryPoint((req, res, exc) -> res.sendRedirect("/web/index.html"));

        // si el inicio de sesión es exitoso, simplemente borre las banderas que solicitan autenticación
        httpSecurity.formLogin().successHandler((req, res, exc) -> clearAuthenticationAttributes(req));

        // si el inicio de sesión falla, simplemente envíe una respuesta de falla de autenticación
        httpSecurity.formLogin().failureHandler((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

        // si el cierre de sesión es exitoso, simplemente envíe una respuesta exitosa
        httpSecurity.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());

        return httpSecurity.build();
    }

    // eliminar las marcas que Spring establece cuano un usuario no autenticado intenta acceder a algún recurso.
    private void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        }
    }
}
