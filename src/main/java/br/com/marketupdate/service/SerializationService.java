package br.com.marketupdate.service;

public interface SerializationService<T> {
    String serializationToJson(T object);
}
