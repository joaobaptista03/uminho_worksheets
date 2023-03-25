import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class CasaInteligente
{
    private List<Lampada> lampadas;
    
    public CasaInteligente() {
        this.lampadas = new ArrayList<>();
    }
    
    public CasaInteligente(CasaInteligente ci) {
        this.lampadas = ci.get_lampadas();
    }
    
    public List<Lampada> get_lampadas() {
        return new ArrayList<>(lampadas);
    }
    
    public void set_lampadas() {
        this.lampadas = new ArrayList<>(lampadas);
    }
    
    public void addLampada(Lampada l) {
        this.lampadas.add(l);
    }
    
    public void ligaLampadaNormal(int index) {
        lampadas.get(index).lampON();
    }
    
    public void ligaLampadaEco(int index) {
        lampadas.get(index).lampECO();
    }
    
    public int qtEmEco() {
        int total = 0;
        
        for (Lampada l : lampadas)
            if (l.get_mode() == Lampada.Modo.ECO) total++;
            
        return total;
    }
    
    public void removeLampada(int index) {
       lampadas.remove(lampadas.get(index));
    }
    
    public void ligaTodasEco() {
        for (Lampada l : lampadas)
            l.lampECO();
    }
    
    public void ligaTodasMax() {
        for (Lampada l : lampadas)
            l.lampON();
    }
    
    public double consumoTotal() {
        double total = 0;
        
        for (Lampada l : lampadas) total += l.get_consumo_total();
        
        return total;
    }
    
    public Lampada maisGastadora() {
        Lampada mais_gastadora = new Lampada();
        
        for (Lampada l : lampadas) 
            if (mais_gastadora.get_consumo_total() < l.get_consumo_total()) 
                mais_gastadora = l;
        
        return mais_gastadora;
    }
    
    public Set<Lampada> lampadasEmModoEco() {
        Set<Lampada> ecoLamps = new HashSet<>();
        
        for (Lampada l : lampadas)
            if (l.get_mode() == Lampada.Modo.ECO) ecoLamps.add(l);
        
        return ecoLamps;
    }
    
    public void reset() {
        for (Lampada l : lampadas) l.reset_consumo_periodo();
    }
    
    public Set<Lampada> podiumEconomia() {
        Lampada lamp1 = new Lampada();
        double consumo1 = Double.MAX_VALUE;
        Lampada lamp2 = new Lampada();
        double consumo2 = Double.MAX_VALUE;
        Lampada lamp3 = new Lampada();    
        double consumo3 = Double.MAX_VALUE;
        
        for (Lampada l : lampadas) {
            if (l.get_consumo_eco() < consumo1) {
                Lampada temp = lamp1;
                lamp1 = l;
                lamp2 = temp;
            }
            else if (l.get_consumo_eco() < consumo2) {
                Lampada temp = lamp2;
                lamp2 = l;
                lamp3 = temp;
            }
            else if (l.get_consumo_eco() < consumo3) lamp3 = l;
        }
        
        Set<Lampada> tres_economicas = new HashSet<>();
        tres_economicas.add(lamp1);
        tres_economicas.add(lamp2);
        tres_economicas.add(lamp3);
        return tres_economicas;
    }
}
