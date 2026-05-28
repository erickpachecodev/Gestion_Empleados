public class EmpleadoPorHora extends Empleado{

    private int HorasTrabajadas;
    private double TarifaPorHora;

    public EmpleadoPorHora(String nombre, String apellidos, String dui, Double salario,
                           int HorasTabajadas,
                           double TarifaPorHora){
        super(nombre, apellidos, dui, salario);
        this.HorasTrabajadas = HorasTabajadas;
        this.TarifaPorHora = TarifaPorHora;
    }

    public int getHorasTrabajadas(){
        return HorasTrabajadas;
    }

    public void setHorasTrabajadas(int horasTrabajadas) {
        this.HorasTrabajadas = horasTrabajadas;
    }

    public double getTarifaPorHora(){
        return TarifaPorHora;
    }

    public void setTarifaPorHora(double tarifaPorHora) {
        TarifaPorHora = tarifaPorHora;
    }

    @Override
    public Double calcularSalario() {
        return HorasTrabajadas * TarifaPorHora;
    }


    @Override
    public String toString() {
        return "EmpleadoPorHoras{" +
                super.toString() +
                ", horasTrabajadas=" + horasTrabajadas +
                ", tarifaPorHora=" + tarifaPorHora +
                ", salarioCalculado=" + calcularSalario() +
                '}';
    }

}
