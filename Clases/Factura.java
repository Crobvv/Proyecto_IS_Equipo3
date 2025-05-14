import java.util.Date;
public class Factura {
    private int idFactura;
    private double montoTotal;
    private Date fechaEmision;
    private String actividad;

    // Constructor
    public Factura(int idFactura, double montoTotal, Date fechaEmision, String actividad) {
        this.idFactura = idFactura;
        this.montoTotal = montoTotal;
        this.fechaEmision = fechaEmision;
        this.actividad = actividad;
    }

    // Métodos
    public void generarFactura() {
        // Lógica para generar la factura
    }

    public void enviarFactura() {
        // Lógica para enviar la factura
    }

    public void consultarFactura() {
        // Lógica para consultar la factura
    }

    // Getters y Setters
    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }
}
