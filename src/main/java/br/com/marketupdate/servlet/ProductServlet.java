package br.com.marketupdate.servlet;

import br.com.marketupdate.configuration.CORSFilter;
import br.com.marketupdate.service.ProductService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/market/home/*")
public class ProductServlet extends HttpServlet {
    private ProductService productService;
    @Override
    public void init() throws ServletException {
        productService = new ProductService();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException{
        String category = request.getParameter("category");
        try {
            if (category != null && !category.isEmpty()) {
                productService.getProductsByCategory(category, response);
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }



    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp){
        CORSFilter.configureResponse(resp, "GET");
    }
}
