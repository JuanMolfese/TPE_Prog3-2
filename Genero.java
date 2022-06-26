public class Genero {
    private String nombre;
    private Integer valorBusqueda;

    public Genero(String nombre, Integer valorBusqueda) {
        this.nombre = nombre;
        this.valorBusqueda = valorBusqueda;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getValorBusqueda() {
        return valorBusqueda;
    }

    public void setValorBusqueda() {
        this.valorBusqueda = valorBusqueda + 1;
    }


}
