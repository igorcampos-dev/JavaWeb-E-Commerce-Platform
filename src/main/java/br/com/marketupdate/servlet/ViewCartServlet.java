package br.com.marketupdate.servlet;

import br.com.marketupdate.configuration.CORSFilter;
import br.com.marketupdate.entity.dto.ProdutoDTO;
import br.com.marketupdate.service.CartService;
import br.com.marketupdate.service.JsonSerializationService;
import br.com.marketupdate.service.TokenService;
import io.jsonwebtoken.Claims;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@WebServlet("/market/home/cart")
public class ViewCartServlet extends HttpServlet {
    private JsonSerializationService service;
    private TokenService tokenService;
    private CartService cartService;
    @Override
    public void init() throws ServletException {
        service = new JsonSerializationService();
        tokenService = new TokenService();
        cartService = new CartService();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        try {
            String authorizationHeader = request.getHeader("Authorization");
            Claims claims = tokenService.decodeToken(authorizationHeader);
            String usuarioId = claims.getSubject();
            if (usuarioId == null || usuarioId.isEmpty()) response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            List<ProdutoDTO> produtosDTO = cartService.getProdutosFromCart(usuarioId);
            service.sendJsonResponse(response, produtosDTO, service);
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) {
        CORSFilter.configureResponse(resp, "POST");
    }
}
