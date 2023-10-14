package br.com.marketupdate.exceptions;

public class UserCartNotFoundException extends RuntimeException {
    public UserCartNotFoundException(String message){
        super(message);
    }
}
