package br.com.compra.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.compra.domain.enums.TipoCliente;
import br.com.compra.dto.ClienteNewDTO;
import br.com.compra.resources.exception.FieldMessage;
import br.com.compra.services.validation.utils.BR;

public class ClienteInsertValidation implements ConstraintValidator<ClienteInsert,ClienteNewDTO>{

	@Override
	public void initialize(ClienteInsert constraintAnnotation) {
	}
	@Override	
	public boolean isValid(ClienteNewDTO value, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<FieldMessage>();
		if (value.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCpf(value.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "Cpf Invalido"));
		}
		if (value.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCnpj(value.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "Cnpj Invalido"));
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
