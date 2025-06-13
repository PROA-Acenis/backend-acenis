package com.example.backend.model.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class NomeCategoriaConverter implements AttributeConverter<NomeCategoria, String> {

    @Override
    public String convertToDatabaseColumn(NomeCategoria categoria) {
        if (categoria == null) {
            return null;
        }
        return categoria.getDescricao();
    }

    @Override
    public NomeCategoria convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return NomeCategoria.fromDescricao(dbData);
    }
}