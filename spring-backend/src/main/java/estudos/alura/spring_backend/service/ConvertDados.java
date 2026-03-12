package estudos.alura.spring_backend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertDados implements FetchDados{

    private ObjectMapper mapper = new ObjectMapper();


    @Override
    public <T> T obterDados(String json, Class<T> classe) throws JsonProcessingException {
        return mapper.readValue(json,classe);
    }

    @Override
    public <T> String converterToJson(Object objeto) throws JsonProcessingException {
        return mapper.writeValueAsString(objeto);
    }

    @Override
    public <T> T converterToObject(String json, Class<T> classe) throws JsonProcessingException {
        return mapper.readValue(json,classe);
    }


}
