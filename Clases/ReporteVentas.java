import java.util.Date; 
public class ReporteVentas {

    // Atributos privados
    private int idReporte;
    private Date fechaGeneracion;
    private double ventasTotales;

    // Constructor
    public Pago(int idPago, double monto, String metodo, String estado) {
        this.idPago = idPago;
        this.monto = monto;
        this.metodo = metodo;
        this.estado = estado;
    }

    // Getters y Setters
    public int getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(int idReporte) {
        this.idReporte = idReporte;
    }

    public Date getfechaGeneracion() {
        return fechaGeneracion;
    }

    public void setfechaGeneracion(Date date) {
        this.fechaGeneracion = date;
    }

    public double getventasTotales() {
        return ventasTotales;
    }

    public void setventasTotales(double ventasTotales) {
        this.ventasTotales = ventasTotales;
    }

    // Métodos del diagrama
    public void generarReporte() {
       // logica generar reporte
    }

    public void exportarDoc() {
        //logica exportar documento
    }

    public void visualizarHistorial() {
        //logica visualizar historial
    }

    
}
