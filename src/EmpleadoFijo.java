public class EmpleadoFijo extends Empleado {

    private double salarioMensual;

    public EmpleadoFijo(String nombre, String apellidos, String dui,
                        Double salario,
                        double salarioMensual) {

        super(nombre, apellidos, dui, salario);
        this.salarioMensual = salarioMensual;
    }

    public double getSalarioMensual() {
        return salarioMensual;
    }

    public void setSalarioMensual(double salarioMensual) {
        this.salarioMensual = salarioMensual;
    }

    @Override
    public Double calcularSalario() {
        return salarioMensual;
    }

    @Override
    public String toString() {
        return "EmpleadoFijo{" + super.toString() +
                ", salarioMensual=" + salarioMensual +
                ", salarioCalculado=" + calcularSalario() + '}';
    }
}