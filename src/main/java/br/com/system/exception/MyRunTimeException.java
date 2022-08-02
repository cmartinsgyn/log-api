package br.com.system.exception;

public class MyRunTimeException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public MyRunTimeException(String msg) {
        super(msg);
    }
}
