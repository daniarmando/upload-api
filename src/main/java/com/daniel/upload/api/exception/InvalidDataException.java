package com.daniel.upload.api.exception;

public class InvalidDataException extends RuntimeException {	
	
	private static final long serialVersionUID = -6391198300829394898L;

	public InvalidDataException(String msg) {
        super(msg);
    }

	public InvalidDataException(Throwable cause) {
        super(cause);
    } 
	
    public InvalidDataException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
