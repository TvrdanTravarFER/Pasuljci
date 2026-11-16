package hr.krvnaveza.domain.converter;

import hr.krvnaveza.domain.enums.KrvnaGrupa;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class KrvnaGrupaConverter implements AttributeConverter<KrvnaGrupa, String> {

    @Override
    public String convertToDatabaseColumn(KrvnaGrupa attribute) {
        if (attribute == null)
            return null;

        return attribute.getDbVal();
    }

    @Override
    public KrvnaGrupa convertToEntityAttribute(String dbData) {
        if(dbData == null)
            return null;

        return KrvnaGrupa.convertDBValue(dbData);
    }
}
