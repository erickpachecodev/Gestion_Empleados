public class EmpleadoFijo extends Empleado {
    private double SalarioMensual;

    public EmpleadoFijo(String nombre, String apellidos, String dui, Double salario, double salarioMensual) {
        super(nombre, apellidos, dui, salario);
        this.SalarioMensual = salarioMensual;
    }

    public double getSalarioMensual() {
        return SalarioMensual;
    }

    public void setSalarioMensual(double salarioMensual) {
        this.SalarioMensual = salarioMensual;
    }

    @Override
    public Double calcularSalario() {
        // Para un empleado fijo, su salario es el salario mensual
        return SalarioMensual;
    }

    @Override
    public String toString() {
        return "EmpleadoFijo{" +
                super.toString() +
                ", salarioMensual=" + SalarioMensual +
                ", salarioCalculado=" + calcularSalario() +
                '}';
    }
}