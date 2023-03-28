import java.util.*;
import java.util.stream.Collectors;

public class Parque
{
    private String nome;
    private Map<String,Lugar> lugares;

    public Parque() {
        this.nome = "";
        this.lugares = new HashMap<>();
    }

    public Parque(String nome, Map<String,Lugar> lugares) {
        this.nome = nome;

        this.lugares = new HashMap<>();
        for(Lugar l : lugares.values()) this.lugares.put(l.getMatricula(), l.clone());
    }

    public Parque(Parque p) {
        this.nome = p.getNome();
        this.lugares = p.getLugares();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Map<String,Lugar> getLugares() {
        Map<String,Lugar> new_lugares = new HashMap<>();
        for(Lugar l : this.lugares.values()) new_lugares.put(l.getMatricula(), l.clone());

        return new_lugares;
    }

    public void setLugares(Map<String,Lugar> lugares) {
        this.lugares = new HashMap<>();
        for(Lugar l : lugares.values()) this.lugares.put(l.getMatricula(), l.clone());
    }

    public Set<String> getMatriculasOcupados() {
        return this.lugares.keySet();
    }

    public void novoLugar(Lugar l) {
        this.lugares.put(l.getMatricula(), l.clone());
    }

    public void removeLugar(String matricula) {
        this.lugares.remove(matricula);
    }

    public void alteraTempo(int minutos, String matricula) {
        this.lugares.get(matricula).setMinutos(minutos);
    }

    public int totalMinutos() {
        return this.lugares.values().stream()
                .mapToInt(Lugar::getMinutos)
                .sum();
    }

    public boolean existeLugar(String matricula) {
        return this.lugares.containsKey(matricula);
    }

    public List<String> tempoMaior(int x) {
        return this.lugares.values().stream()
                .filter(l -> l.getMinutos() > x && l.getPermanente())
                .map(Lugar :: getMatricula)
                .collect(Collectors.toList());
    }

    public List<Lugar> copiaLugares() {
        return this.lugares.values().stream()
                .map(Lugar :: clone)
                .collect(Collectors.toList());
    }

    public String infoLugar(String matricula) {
        return this.lugares.get(matricula).toString();
    }
}
