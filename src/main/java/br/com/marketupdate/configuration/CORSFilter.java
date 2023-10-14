package br.com.marketupdate.configuration;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class CORSFilter implements Filter {
    private static final String ALLOWED_ORIGIN = "http://localhost:3000";
    private static final String ALLOWED_METHODS = "GET, POST, PUT, DELETE";
    private static final String ALLOWED_HEADERS = "Content-Type, Authorization"; // Adicionada "Authorization"
    private static final String MAX_AGE = "3600"; // 1 hour
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("parar de dar erro sonarlint");
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setHeader("Access-Control-Allow-Origin", ALLOWED_ORIGIN);
        httpResponse.setHeader("Access-Control-Allow-Methods", ALLOWED_METHODS);
        httpResponse.setHeader("Access-Control-Allow-Headers", ALLOWED_HEADERS);
        httpResponse.setHeader("Access-Control-Max-Age", MAX_AGE);
        chain.doFilter(request, response);
    }
    @Override
    public void destroy() {
        System.out.println("parar de dar erro sonarlint");
    }
    public static void configureResponse(HttpServletResponse response, String method) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Access-Control-Allow-Origin", ALLOWED_ORIGIN);
        response.setHeader("Access-Control-Allow-Methods", method);
        response.setHeader("Access-Control-Allow-Headers", ALLOWED_HEADERS);
    }
}
