public class Lugar
{
    private String matricula;
    private String nome;
    private int minutos;
    private boolean permanente;

    public Lugar() {
        this.matricula = "";
        this.nome = "";
        this.minutos = 0;
        this.permanente = false;
    }

    public Lugar(String matricula, String nome, int minutos, boolean permanente) {
        this.matricula = matricula;
        this.nome = nome;
        this.minutos = minutos;
        this.permanente = permanente;
    }

    public Lugar(Lugar l) {
        this.matricula = l.getMatricula();
        this.nome = l.getNome();
        this.minutos = l.getMinutos();
        this.permanente = l.getPermanente();
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getMinutos() {
        return minutos;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    public boolean getPermanente() {
        return permanente;
    }

    public void setPermanente(boolean permanente) {
        this.permanente = permanente;
    }

    public boolean equals(Lugar l) {
        if (this.matricula.equals(l.getMatricula()) &&
            this.nome.equals(l.getNome()) &&
            this.minutos == l.getMinutos() &&
            this.permanente == l.getPermanente()) return true;
        return false;
    }

    public String toString() {
        return "Lugar{" +
                "matricula='" + matricula + '\'' +
                ", nome='" + nome + '\'' +
                ", minutos=" + minutos +
                ", permanente=" + permanente +
                '}';
    }

    public Lugar clone() {
        return new Lugar(this);
    }
}
