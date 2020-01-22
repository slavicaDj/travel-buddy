package util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.FacesConverter;

@FacesConverter("customDateTimeConverter")
public class CustomDateTimeConverter extends DateTimeConverter {

    public CustomDateTimeConverter() {
        setPattern("dd.MM.yyyy HH:mm");
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.length() != getPattern().length()) {
            throw new ConverterException("Neispravan format datuma");
        }

        return super.getAsObject(context, component, value);
    }

}