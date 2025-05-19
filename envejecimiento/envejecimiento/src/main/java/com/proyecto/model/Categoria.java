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
    JUGUETES_Y_JUEGOS("Juguetes y Juegos");  // ← Sin coma aquí

    private final String label;

    // Constructor correcto
    Categoria(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}