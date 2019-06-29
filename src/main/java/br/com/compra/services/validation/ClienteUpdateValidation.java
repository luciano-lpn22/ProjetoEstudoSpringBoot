package br.com.compra.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import br.com.compra.domain.Cliente;
import br.com.compra.dto.ClienteDTO;
import br.com.compra.repositories.ClienteRepository;
import br.com.compra.resources.exception.FieldMessage;

public class ClienteUpdateValidation implements ConstraintValidator<ClienteUpdate, ClienteDTO>{

	@Autowired
	private ClienteRepository repo;
	
	@Autowired
	private HttpServletRequest request;
	
	@Override
	public void initialize(ClienteUpdate constraintAnnotation) {}
	
	@Override
	public boolean isValid(ClienteDTO value, ConstraintValidatorContext context) {
		
		Map<String,String> map=(Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		
		List<FieldMessage> list = new ArrayList<FieldMessage>();
			
		Cliente aux= repo.findByEmail(value.getEmail());
		if (aux!=null && aux.getId()!=Integer.parseInt(map.get("id"))) {
			list.add(new FieldMessage("email","Email já Existe"));
		}
		
		for (FieldMessage c : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(c.getMessage())
					.addPropertyNode(c.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}

	
}
