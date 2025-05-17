public abstract class Usuario 
{
  private int idUsuario;
  private String nombre;
  private String correo;
  private String password;

  public Usuario(int idUsuario, String nombre, String correo, String password)
  {
    this.idUsuario = idUsuario;
    this.nombre = nombre;
    this.correo = correo;
    this.password = password;
  }

  public int getIdUsuario() 
  { 
    return idUsuario;
  }
  public void setIdUsuario(int idUsuario)
  { 
    this.idUsuario = idUsuario;
  }

  public String getNombre()
  { 
    return nombre;
  }

  public void setNombre(String nombre)
  { 
    this.nombre = nombre;
  }

  public String getCorreo() 
  {
     return correo;
  }

  public void setCorreo(String correo)
  { 
    this.correo = correo;
  }

  public String getPassword() 
  { 
    return password;
  }
  public void setPassword(String password) 
  {
     this.password = password;
  }

  public abstract void iniciarSesion();
  public abstract void cerrarSesion();
}
