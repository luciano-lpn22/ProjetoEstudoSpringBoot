package br.com.compra.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.AmazonS3Exception;

import br.com.compra.services.exceptions.AuthorizationException;
import br.com.compra.services.exceptions.DataIntegrityException;
import br.com.compra.services.exceptions.FileException;
import br.com.compra.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFaund(ObjectNotFoundException e,HttpServletRequest request){
		StandardError erro= new StandardError(System.currentTimeMillis(),HttpStatus.NOT_FOUND.value(),"Not Found", e.getMessage(),request.getRequestURI());
		return  ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(erro);	
	}

	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException e,HttpServletRequest request){
		StandardError erro= new StandardError(System.currentTimeMillis(),HttpStatus.BAD_REQUEST.value(),"data Integrity" ,e.getMessage(),request.getRequestURI());
		return  ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(erro);
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e,HttpServletRequest request){
		ValidationError erro= new ValidationError(System.currentTimeMillis(),HttpStatus.UNPROCESSABLE_ENTITY.value(), "Erro de Validação", e.getMessage(),request.getRequestURI());
		for(FieldError x : e.getBindingResult().getFieldErrors()) {
			erro.addtError(x.getField(), x.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY.value()).body(erro);
	}
	
	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<StandardError> autorization(AuthorizationException e,HttpServletRequest request){
		StandardError erro= new StandardError(System.currentTimeMillis(),HttpStatus.FORBIDDEN.value(),"ForBidden", e.getMessage(), request.getRequestURI());
		return  ResponseEntity.status(HttpStatus.FORBIDDEN.value()).body(erro);	
	}
	
	@ExceptionHandler(FileException.class)
	public ResponseEntity<StandardError> file(FileException e, HttpServletRequest request){
		StandardError erro= new StandardError(System.currentTimeMillis(),HttpStatus.BAD_REQUEST.value(),"File Integrity",e.getMessage(),request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(erro);
	}
	
	@ExceptionHandler(AmazonServiceException.class)
	public ResponseEntity<StandardError> amazonService(AmazonServiceException e,HttpServletRequest request){
		HttpStatus code =HttpStatus.valueOf(e.getErrorCode());
		StandardError erro= new StandardError(System.currentTimeMillis(),code.value(),"Erro Amazon Service",e.getMessage(),request.getRequestURI());
		return ResponseEntity.status(code.value()).body(erro);
	}
	
	@ExceptionHandler(AmazonS3Exception.class)
	public ResponseEntity<StandardError> amazonS3(AmazonS3Exception e, HttpServletRequest request){
		HttpStatus code=HttpStatus.valueOf(e.getErrorCode());
		StandardError erro= new StandardError(System.currentTimeMillis(),code.value(),"Erro Amazon S3",e.getMessage(),request.getRequestURI());
		return ResponseEntity.status(code.value()).body(erro);
	}
	@ExceptionHandler(AmazonClientException.class)
	public ResponseEntity<StandardError> amazonClient(AmazonClientException e,HttpServletRequest request){
		StandardError erro= new StandardError(System.currentTimeMillis(),HttpStatus.BAD_REQUEST.value(),"Erro Amazon S3",e.getMessage(),request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(erro);
	}
	
}
