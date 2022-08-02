package br.com.system.cors;

import br.com.system.property.SistemaApiProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {

    @Autowired
    private SistemaApiProperty sistemaApiProperty;

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

        // Convertendo de Server para Servlet
        HttpServletRequest resquest = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        //seta estes Headers para qualquer requisição
        response.setHeader("Access-Control-Allow-Origin", sistemaApiProperty.getOriginPermitida());
        response.setHeader("Access-Control-Allow-Credentials", "true");

        // Se requisicao é do tipo OPTIONS (cors) e é de Origin Permitida então seta os headers
        if ("OPTIONS".equals(resquest.getMethod()) && sistemaApiProperty.getOriginPermitida().equals(resquest.getHeader("Origin"))) {
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT, OPTIONS");
            response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, Accept");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(req, resp);
        }

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}