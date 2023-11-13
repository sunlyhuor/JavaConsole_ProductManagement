package co.istad.exception;

public class MenuOptionNotFoundException extends RuntimeException{
    public MenuOptionNotFoundException(String message){
        super(message);
    }
}
