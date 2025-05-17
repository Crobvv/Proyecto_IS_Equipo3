import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario 
{
  private String RFC;
  private String codigoPostal;
  private String direccionEnvio;
  private List<Factura> facturas;
  private List<Pedido> pedidos;

  public Cliente(int idUsuario, String nombre, String correo, String password, String RFC, String codigoPostal, String direccionEnvio) {
    super(idUsuario, nombre, correo, password);
    this.RFC = RFC;
    this.codigoPostal = codigoPostal;
    this.direccionEnvio = direccionEnvio;
    this.facturas = new ArrayList<>();
    this.pedidos = new ArrayList<>();
  }

  public void consultarPerfil() {
    Scanner sc = new Scanner(System.in);
    System.out.print("Introduce tu contraseña para ver el perfil: ");
    String entrada = sc.nextLine();

    if (entrada.equals(getPassword())) {
        System.out.println("Nombre: " + getNombre());
        System.out.println("Correo: " + getCorreo());
        System.out.println("RFC: " + RFC);
        System.out.println("Código Postal: " + codigoPostal);
        System.out.println("Dirección de Envío: " + direccionEnvio);
    } 
    
    else {
        System.out.println("Contraseña incorrecta.");
    }
  }

  public void HistorialCompras() {
     if (pedidos.isEmpty()) {
        System.out.println(getNombre() + " no tiene pedidos.");
    } 
    else {
        System.out.println("Historial de compras de " + getNombre() + ":");
        for (Pedido p : pedidos) {
            System.out.println("- Pedido ID: " + p.getId() + ", Estado: " + p.getEstado());
        }
    }
  }

  public String getRFC()
  { 
    return RFC;
  }

  public void setRFC(String RFC)
  {
    this.RFC = RFC;
  }

  public String getCodigoPostal()
  {
    return codigoPostal;
  }

  public void setCodigoPostal(String codigoPostal)
  {
    this.codigoPostal = codigoPostal;
  }

  public List<Factura> getFacturas()
  {
    return facturas;
  }

  public void setFacturas(List<Factura> facturas)
  {
    this.facturas = facturas;
  }

  public List<Pedido> getPedidos() 
  {
    return pedidos; 
  }

  public void setPedidos(List<Pedido> pedidos)
  { 
    this.pedidos = pedidos;
  }
  
  public String getDireccionEnvio()
  {
      return direccionEnvio;
  }

  public void setDireccionEnvio(String direccionEnvio) 
  {
    this.direccionEnvio = direccionEnvio;
  }
  
  @Override
  public void iniciarSesion()
  {
    System.out.println("Cliente" + getNombre() + "ha iniciado sesión");
  }

  @Override
  public void cerrarSesion()
  {
    System.out.println("Cliente" + getNombre() + "ha cerrado sesión");
  }
}
