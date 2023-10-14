package br.com.marketupdate.service;

import br.com.marketupdate.entity.dto.ProdutoDTO;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
public class JsonSerializationService implements SerializationService<List<ProdutoDTO>> {
    private final Gson gson;
    public JsonSerializationService() {
        this.gson = new Gson();
    }
    @Override
    public String serializationToJson(List<ProdutoDTO> products) {
        return gson.toJson(products);
    }
    public void sendJsonResponse(HttpServletResponse response, List<ProdutoDTO> produtosDTO, JsonSerializationService service) throws IOException {
        String produtosJson = service.serializationToJson(produtosDTO);
        response.setContentType("application/json");
        response.getWriter().write(produtosJson);
    }
}
