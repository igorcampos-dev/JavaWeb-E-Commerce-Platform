package br.com.marketupdate.servlet;

import br.com.marketupdate.configuration.CORSFilter;
import br.com.marketupdate.configuration.ResponseObject;
import br.com.marketupdate.entity.Usuario;
import br.com.marketupdate.service.TokenService;
import br.com.marketupdate.service.UserService;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet("/market/login")
public class LoginServlet extends HttpServlet {
    private UserService userService;
    private TokenService tokenService;
    private Gson gson;
    @Override
    public void init() {
        userService = new UserService();
        tokenService = new TokenService();
        gson = new Gson();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        try {
            Usuario user = gson.fromJson(req.getReader(), Usuario.class);
            PrintWriter out = resp.getWriter();
            String responseObject;
            Usuario usuario = userService.getUserByEmailAndPassword(user.getEmail(), user.getPassword());

            if (usuario != null) {
                String id = userService.getFindIdToEmail(user.getEmail());
                String token = tokenService.authenticateAndGetToken(id);
                 responseObject = new ResponseObject(true, token, null).toJson();
                out.write(responseObject);
            } else {
                 responseObject = new ResponseObject(null, "Credenciais inválidas").toJson();
                out.write(responseObject);
            }
        } catch (JsonSyntaxException | JsonIOException | IOException e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp){
        CORSFilter.configureResponse(resp, "POST");

    }
}
