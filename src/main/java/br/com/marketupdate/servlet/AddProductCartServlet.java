package br.com.marketupdate.servlet;

import br.com.marketupdate.configuration.CORSFilter;
import br.com.marketupdate.service.ProductService;
import br.com.marketupdate.service.TokenService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
@WebServlet("/market/cart/add")
public class AddProductCartServlet extends HttpServlet {
    private ProductService productService;
    private  TokenService tokenService;
    @Override
    public void init() throws ServletException {
        productService = new ProductService();
        tokenService = new TokenService();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException{
        String authorizationHeader = req.getHeader("Authorization");
        try {
            var rootNode = readJsonFromRequest(req);
            int productId = rootNode.get("productId").asInt();

            if (authorizationHeader != null) {
                Claims claims = tokenService.decodeToken(authorizationHeader);

                productService.addToCart(claims.getSubject(), productId);
                resp.setStatus(HttpServletResponse.SC_OK);
            } else {
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) {
        CORSFilter.configureResponse(resp, "POST");
    }

    public static JsonNode readJsonFromRequest(HttpServletRequest req) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = req.getReader();
        String line;
        while ((line = reader.readLine()) != null) {sb.append(line);}
        return mapper.readTree(sb.toString());
    }


}
