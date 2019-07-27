package br.com.compra.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

	public ValidationError(Long timestamp, Integer status, String error, String message, String path) {
		super(timestamp, status, error, message, path);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private List<FieldMessage> errors= new ArrayList<FieldMessage>();

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addtError(String fieldName,String message) {
		this.errors.add(new FieldMessage(fieldName, message));
	}
	
}
