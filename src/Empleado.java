public abstract class Empleado {

    private String nombre;
    private String apellidos;
    private String dui;
    private Double salario;

    public Empleado(String nombre, String apellidos, String dui, Double salario) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dui = dui;
        this.salario = salario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    @Override
    public String toString(){
        return "Empleado [" +
                "DUI: '" + dui + '\'' +
                ", Nombre '" + nombre + " " + apellidos + '\'' +
                ", Salario Base: $" + salario + ']';
    }
    public abstract Double calcularSalario();
}






