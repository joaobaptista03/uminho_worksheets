import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class EncEficiente
{
    private String cliente;
    private int nif;
    private String morada;
    private int nr_encomenda;
    private LocalDate data;
    private List<LinhaEncomenda> linhas;
 
    public EncEficiente() {
        this.cliente = "";
        this.nif = 0;
        this.morada = "";
        this.nr_encomenda = 0;
        this.data = LocalDate.now();
        this.linhas = new ArrayList<>();
    }
    
    public EncEficiente (String cliente, int nif, String morada, int nr_encomenda, LocalDate data, List<LinhaEncomenda> linhas) {
        this.cliente = cliente;
        this.nif = nif;
        this.morada = morada;
        this.nr_encomenda = nr_encomenda;
        this.data = data;
        this.linhas = new ArrayList<>();
    
        for(LinhaEncomenda l : linhas)
            this.linhas.add(l.clone());
    }
    
    public EncEficiente(EncEficiente encomenda) {
        this.cliente = encomenda.get_cliente();
        this.nif = encomenda.get_nif();
        this.morada = encomenda.get_morada();
        this.nr_encomenda = encomenda.get_nr_encomenda();
        this.data = encomenda.get_data();
        this.linhas = encomenda.get_linhas();
    }
    
    public double calculaValorTotal() {
        double total = 0;
        
        for(LinhaEncomenda l : this.linhas)
            total += l.calculaValorLinhaEnc();
            
        return total;
    }
    
    public double calculaValorDesconto() {
        double total = 0;
        
        for(LinhaEncomenda l : this.linhas)
            total += l.calculaValorDesconto();
            
        return total;
    }
    
    public int numeroTotalProdutos() {
        int total = 0;
        
        for(LinhaEncomenda l : this.linhas)
            total += l.getQuantidade();
            
        return total;
    }
    
    public boolean existeProdutoEncomenda(String refProduto) {
        for(LinhaEncomenda l : this.linhas)
            if (refProduto.equals(l.getReferencia())) return true;
            
        return false;
    }
    
    public void adicionaLinha(LinhaEncomenda linha) {
        this.linhas.add(linha);
    }
    
    public void removeProduto(String codProd) {
        /* ERRADO - NÃO SE PODE MODIFICAR ARRAYLIST COM LOOP NORMAL
        for(LinhaEncomenda l : this.linhas)
            if (codProd.equals(l.getReferencia())) this.linhas.remove(l);
            */
           
        Iterator<LinhaEncomenda> it = linhas.iterator();
        
        while (it.hasNext()) {
            LinhaEncomenda l = it.next();
            if (l.getReferencia().equals(codProd)) it.remove();
        }
    }
    
    public String get_cliente() {
        return this.cliente;
    }
    
    public void set_cliente(String cliente) {
        this.cliente = cliente;
    }
    
    public int get_nif() {
        return this.nif;
    }
    
    public void set_nif(int nif) {
        this.nif = nif;
    }
    
    public String get_morada() {
        return this.morada;
    }
    
    public void set_morada(String morada) {
        this.morada = morada;
    }
    
    public int get_nr_encomenda() {
        return this.nr_encomenda;
    }
    
    public void set_nr_encomenda(int nr_encomenda) {
        this.nr_encomenda = nr_encomenda;
    }
    
    public LocalDate get_data() {
        return this.data;
    }
    
    public void set_data(LocalDate data) {
        this.data = data;
    }
    
    public List<LinhaEncomenda> get_linhas() {
        List<LinhaEncomenda> clone_linhas = new ArrayList<>();
        
        for(LinhaEncomenda l : this.linhas)
            clone_linhas.add(l.clone());
        
        return clone_linhas;
    }
    
    public void set_linhas(List<LinhaEncomenda> linhas) {
        this.linhas = new ArrayList<>();
        
        for(LinhaEncomenda l : this.linhas)
            linhas.add(l.clone());
    }
}
