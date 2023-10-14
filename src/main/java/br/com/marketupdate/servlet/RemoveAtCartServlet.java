package br.com.marketupdate.servlet;

import br.com.marketupdate.configuration.CORSFilter;
import br.com.marketupdate.service.CartService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
@WebServlet("/market/cart/home/remove")
public class RemoveAtCartServlet extends HttpServlet {
    private final CartService cartService;
    public RemoveAtCartServlet(CartService cartService) {
        this.cartService = cartService;
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        try {
            String authorizationHeader = request.getHeader("Authorization");
            BufferedReader reader = request.getReader();
            String json = reader.readLine();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(json);
            Long productId = rootNode.get("produtoId").asLong();

            cartService.removeProductFromCart(authorizationHeader, productId);
            response.setStatus(HttpServletResponse.SC_OK);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }catch (JsonSyntaxException | JsonIOException | IOException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) {
        CORSFilter.configureResponse(resp, "POST");
    }
}
