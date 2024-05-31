package it.uniroma2.exceptions;

public class DAOException extends Exception{
    public DAOException(){}
    public DAOException(String message){
        super(message);
    }
}
