package pt.gois.algaworks.algamoneyapi.token;

import org.apache.catalina.util.ParameterMap;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Gustavo on 05/09/2017.
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RefreshTokenCookiePreProcessorFilter implements Filter{

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        if("/oauth/token".equalsIgnoreCase(req.getRequestURI())
                && "refresh_token".equals(req.getParameter("grant_type"))
                && req.getCookies() != null
                ) {

            for(Cookie cookie: req.getCookies()) {
                if(cookie.getName().equals("refreshToken")) {
                    String refreshToken = cookie.getValue();

                    // Seria bom se pudéssemos inserir o refresh token no request. Mas depois que ele está pronto
                    // não conseguimos mais alterar seu mapa de parâmetros... Por isso criamos MyServletRequestWrapper

                    req = new MyServletRequestWrapper(req, refreshToken);
                }
            }
        }

        filterChain.doFilter(req, servletResponse);

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException { }

    @Override
    public void destroy() { }

    static class MyServletRequestWrapper extends HttpServletRequestWrapper {

        private String refreshToken;

        public MyServletRequestWrapper(HttpServletRequest request, String refreshToken) {
            super(request);
            this.refreshToken = refreshToken;
        }

        @Override
        public Map<String, String[]> getParameterMap() {
            ParameterMap<String, String[]> map = new ParameterMap<>(getRequest().getParameterMap());
            map.put("refresh_token", new String[] { refreshToken });
            map.setLocked(true);
            return map;
        }
    }


}
