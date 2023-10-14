package br.com.marketupdate.servlet;

import br.com.marketupdate.configuration.CORSFilter;
import br.com.marketupdate.configuration.ResponseObject;
import br.com.marketupdate.entity.Usuario;
import br.com.marketupdate.service.RegisterService;
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
@WebServlet("/market/register")
public class RegisterServlet extends HttpServlet {
    private UserService userService;
    private Gson gson;
    @Override
    public void init() throws ServletException {
        userService = new UserService();
        gson = new Gson();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException{
        try {
            Usuario user = gson.fromJson(req.getReader(), Usuario.class);
            PrintWriter out = resp.getWriter();
            String responseObject;

            if (!RegisterService.isValidUser(user)) {
                responseObject = new ResponseObject("Verifique as credenciais!!").toJson();
                out.write(responseObject);
            }

            if (userService.processRegistration(user)) {
                userService.saveUser(user);
                responseObject = new ResponseObject("true").toJson();
            } else {
                responseObject = new ResponseObject("Usuario ja existe!").toJson();
            }

            out.write(responseObject);

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
