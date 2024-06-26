package it.uniroma2.exceptions;

public class DataTooLongException extends Exception{
    public DataTooLongException(){}
    public DataTooLongException(String message){
        super(message);
    }
}
