package com.daniel.upload.api.exception;

public class XmlConverterException extends RuntimeException {	
	
	private static final long serialVersionUID = -6391198300829394898L;

	public XmlConverterException(String msg) {
        super(msg);
    }

	public XmlConverterException(Throwable cause) {
        super(cause);
    } 
	
    public XmlConverterException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
