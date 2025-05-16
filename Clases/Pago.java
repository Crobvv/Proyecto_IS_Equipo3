public class Pago {

    // Atributos privados
    private int idPago;
    private double monto;
    private String metodo;
    private String estado;

    // Constructor
    public Pago(int idPago, double monto, String metodo, String estado) {
        this.idPago = idPago;
        this.monto = monto;
        this.metodo = metodo;
        this.estado = estado;
    }

    // Getters y Setters
    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    // Métodos del diagrama
    public void procesarPago() {
        if (estado.equalsIgnoreCase("pendiente")) {
            estado = "procesado";
            System.out.println("Pago procesado exitosamente.");
        } else {
            System.out.println("El pago ya fue procesado o está en un estado no válido.");
        }
    }

    public void confirmarPago() {
        if (estado.equalsIgnoreCase("procesado")) {
            estado = "confirmado";
            System.out.println("Pago confirmado exitosamente.");
        } else {
            System.out.println("El pago no se puede confirmar. Verifica su estado.");
        }
    }

    // Método toString para mostrar la información del pago
    @Override
    public String toString() {
        return "Pago [idPago=" + idPago + ", monto=" + monto + ", metodo=" + metodo + ", estado=" + estado + "]";
    }
}
