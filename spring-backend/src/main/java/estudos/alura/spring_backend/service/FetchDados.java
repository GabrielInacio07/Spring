package estudos.alura.spring_backend.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface FetchDados {

    <T> T obterDados(String json, Class<T> classe) throws JsonProcessingException;
    <T>String converterToJson(Object objeto) throws JsonProcessingException;
    <T>T converterToObject(String json, Class<T> classe) throws JsonProcessingException;
}
