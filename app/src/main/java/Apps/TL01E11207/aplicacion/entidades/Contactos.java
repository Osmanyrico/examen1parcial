package Apps.TL01E11207.aplicacion.entidades;

public class Contactos {

    private int id;
    private String nombre;
    private String telefono;
    private String pais;
    private String nota;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    //public String getCorreo_electornico() {
        //return correo_electornico;
    //}

    //public void setCorreo_electornico(String correo_electornico) {
        //this.correo_electornico = correo_electornico;
    //}

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }
}
