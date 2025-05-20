package com.proyecto.model;

public enum Categoria {
    NINOS("Niños"),
    ADULTOS("Adultos"),
    SALUD("Salud"),
    ALIMENTOS("Alimentos"),
    MASCOTAS("Mascotas"),
    DECORACION("Decoración"),
    LIBROS("Libros"),
    JOYAS_Y_BISUTERIA("Joyas y Bisutería"),
    MODA_Y_ACCESORIOS("Moda y Accesorios"),
    JUGUETES_Y_JUEGOS("Juguetes y Juegos");

    private final String label;

    Categoria(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return label;
    }
}