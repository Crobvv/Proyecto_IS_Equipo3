package com.proyecto.model;

public enum Festividad {
    ANO_NUEVO("Año nuevo"),
    DIA_DE_LA_INDEPENDENCIA("Día de la independencia"),
    DIA_DE_LA_MADRE("Día de la madre"),
    DIA_DE_LOS_MUERTOS("Día de los muertos"),
    DIA_DEL_PADRE("Día del padre"),
    HALLOWEEN("Halloween"),
    NAVIDAD("Navidad"),
    SAN_VALENTIN("San Valentín");

    private final String label;

    Festividad(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}