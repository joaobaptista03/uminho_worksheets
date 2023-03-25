public class Lampada
{
    public enum Modo {
        ON, 
        OFF, 
        ECO
    }
    
    private Modo mode;
    private double consumo_on_ms; // Consumo por milissegundo modo ON
    private double consumo_eco_ms; // Consumo por milissegundo modo ON
    private double consumo_total;
    private double consumo_periodo;
    private long init_mili; // Millisegundo inicial
    
    public Lampada() {
        this.mode = Modo.OFF;
        this.consumo_on_ms = 1;
        this.consumo_eco_ms = 2;
        this.consumo_total = 0;
        this.consumo_periodo = 0;
        this.init_mili = System.currentTimeMillis();
    }
    
    public Lampada(Modo mode, double consumo_on_ms, double consumo_eco_ms, 
                   double consumo_total, double consumo_periodo, long init_mili) {
        this.mode = mode;
        this.consumo_on_ms = consumo_on_ms;
        this.consumo_eco_ms = consumo_eco_ms;
        this.consumo_total = consumo_total;
        this.consumo_periodo = consumo_periodo;
        this.init_mili = init_mili;
    }
    
    public Lampada(Lampada e) {
        this.mode = e.get_mode();
        this.consumo_on_ms = e.get_consumo_on();
        this.consumo_eco_ms = e.get_consumo_eco();
        this.consumo_total = e.get_consumo_total();
        this.consumo_periodo = e.get_consumo_periodo();
        this.init_mili = e.get_mili();
    }
    
    public Modo get_mode() {
        return this.mode;
    }
    
    public void set_mode(Modo mode) {
        this.mode = mode;
    }
    
    public double get_consumo_on() {
        return this.consumo_on_ms;
    }
    
    public void set_consumo_on(double consumo_ms_on) {
        this.consumo_on_ms = consumo_ms_on;
    }
    
    public double get_consumo_eco() {
        return this.consumo_eco_ms;
    }
    
    public void set_consumo_eco(double consumo_eco_ms) {
        this.consumo_eco_ms = consumo_eco_ms;
    }
    
    public double get_consumo_total() {
        return this.consumo_total;
    }
    
    public void set_consumo_total(double consumo_total) {
        this.consumo_total = consumo_total;
    }
    
    public double get_consumo_periodo() {
        return this.consumo_periodo;
    }
    
    public void set_consumo_periodo(double consumo_periodo) {
        this.consumo_periodo = consumo_periodo;
    }
    
    public long get_mili() {
        return this.init_mili;
    }
    
    public void set_mili(long init_mili) {
        this.init_mili = init_mili;
    }
    
    public void atualiza_consumo() {
        long periodo = System.currentTimeMillis() - this.init_mili;
        
        if (this.mode == Modo.ON) {
            this.consumo_total += periodo * this.consumo_on_ms;
            this.consumo_periodo += periodo * this.consumo_on_ms;
        }
        if (this.mode == Modo.ECO) {
            this.consumo_total += periodo * this.consumo_eco_ms;
            this.consumo_periodo += periodo * this.consumo_eco_ms;
        }
        
        this.init_mili = System.currentTimeMillis();
    }
    
    public void lampON() {
        atualiza_consumo();
        this.mode = Modo.ON;
    }
    
    public void lampOFF() {
        atualiza_consumo();
        this.mode = Modo.OFF;
    }
    
    public void lampECO() {
        atualiza_consumo();
        this.mode = Modo.ECO;
    }
    
    public double totalConsumo() {
        atualiza_consumo();
        return this.consumo_total;
    }
    
    public double periodoConsumo() {
        atualiza_consumo();
        return this.consumo_periodo;
    }
    
    public void reset_consumo_periodo() {
        this.consumo_periodo = 0;
    }
}
