package com.mervetuncelkaya.view.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("com.mervetuncelkaya.phonevalidator")
public class PhoneValidator implements Validator {
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        FacesMessage facesMessage = new FacesMessage();

        if (value != null) {
            value = value.toString().replace("(", "").replace(")", "");
            boolean exp = false;

            if (((String) value).length() < 10) {
                facesMessage = new FacesMessage("Telefon alanı eksik girilmiştir.", "Telefon alanı 10 karakter olmalıdır.");
                exp = true;
            } else if (!((String) value).startsWith("5")) {
                facesMessage = new FacesMessage("Telefon alanına geçersiz giriş yapıldı.", "Telefon alanı 5 ile başlamalıdır.");
                exp = true;
            } else if (!((String) value).startsWith("50") && !((String) value).startsWith("53") && !((String) value).startsWith("54") && !((String) value).startsWith("55")) {
                facesMessage = new FacesMessage("Telefon alanına geçersiz giriş yapıldı.", "Telefon alanı hatalı formatta girilmiştir.");
                exp = true;
            }

            if (exp) {
                facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(facesMessage);
            }
        }
    }
}
