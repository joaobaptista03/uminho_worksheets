import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class GestaoEncomendas
{
    private Map<Integer, EncEficiente> encomendas;

    public GestaoEncomendas() {
        encomendas = new HashMap<>();
    }
    
    public GestaoEncomendas(Map<Integer, EncEficiente> encomendas) {
        this.encomendas = new HashMap<>();
        
        for(EncEficiente a : encomendas.values()) {
            this.encomendas.put(a.get_nr_encomenda(), a.clone());
        }
    }

    public GestaoEncomendas(GestaoEncomendas ge) {
        this.encomendas = get_encomendas();
    }
    
    public Map<Integer, EncEficiente> get_encomendas() {
        Map<Integer, EncEficiente> new_encomendas = new HashMap<>();
        
        for(EncEficiente a : this.encomendas.values()) {
            new_encomendas.put(a.get_nr_encomenda(), a.clone());
        }
        
        return new_encomendas;
    }
    
    public void set_encomendas(Map<Integer, EncEficiente> encomendas) {
        this.encomendas = new HashMap<>();

        for(EncEficiente a : encomendas.values()) {
            this.encomendas.put(a.get_nr_encomenda(), a.clone());
        }
    }

    public GestaoEncomendas clone() {
        Map<Integer, EncEficiente> new_encomendas = new HashMap<>();

        for(EncEficiente a : this.encomendas.values()) {
            new_encomendas.put(a.get_nr_encomenda(), a.clone());
        }

        return new GestaoEncomendas(new_encomendas);
    }

    public Set<Integer> todosCodigosEnc() {
        return this.encomendas.keySet();
    }

    public void addEncomenda(EncEficiente e) {
        this.encomendas.put(e.get_nr_encomenda(), e.clone());
    }

    public EncEficiente getEncomenda(Integer codEnc) {
        if (!this.encomendas.containsKey(codEnc)) return null;
        return this.encomendas.get(codEnc).clone();
    }

    public void removeEncomenda(Integer codEnc) {
        this.encomendas.remove(codEnc);
    }

    public Integer encomendaComMaisProdutos() {
        Comparator<EncEficiente> c = (a1,a2) -> a2.numeroTotalProdutos() - a1.numeroTotalProdutos();

        TreeSet<EncEficiente> t = new TreeSet<>(c);
        for(EncEficiente e : this.encomendas.values()) t.add(e);

        return t.first().get_nr_encomenda();
    }

    public Set<Integer> encomendasComProduto(String codProd) {
        Set<Integer> new_set = new HashSet<>();

        for(EncEficiente e : this.encomendas.values())
            if (e.existeProdutoEncomenda(codProd)) new_set.add(e.get_nr_encomenda());

        return new_set;

        /*
        return this.encomendas.values().stream()
                .filter(e -> e.existeProdutoEncomenda((codProd)))
                .map(EncEficiente::get_nr_encomenda)
                .collect(Collectors.toSet());
         */
    }

    public Set<Integer> encomendasAposData(LocalDate d) {
        Set<Integer> new_set = new HashSet<>();

        for(EncEficiente e : this.encomendas.values())
            if(e.get_data().isAfter(d)) new_set.add(e.get_nr_encomenda());

        return new_set;

        /*
        return this.encomendas.values().stream()
                .filter(e -> e.get_data().isAfter(d))
                .map(EncEficiente::get_nr_encomenda)
                .collect(Collectors.toSet());
         */
    }

    public EncEficiente encomendaMaiorValor() {
        Comparator<EncEficiente> c = (e1,e2) -> (int) (e2.calculaValorTotal() - e1.calculaValorTotal());

        TreeSet<EncEficiente> t = new TreeSet<>();
        for(EncEficiente e : this.encomendas.values()) t.add(e);

        return t.first().clone();
    }

    public Set<EncEficiente> encomndasOrdenadasPorQtProdutos() {
        return this.encomendas.values().stream()
                .sorted((e1,e2) -> e1.numeroTotalProdutos() - e2.numeroTotalProdutos())
                .map(EncEficiente::clone)
                .collect(Collectors.toSet());
    }

    public Set<EncEficiente> encomendasValorDecrescente() {
        return this.encomendas.values().stream()
                .sorted((e1,e2) -> (int) (e2.calculaValorTotal() - e1.calculaValorTotal()))
                .map(EncEficiente::clone)
                .collect(Collectors.toSet());
    }

    public Map<String,List<Integer>> encomendasDeProduto() {
        Map<String,List<Integer>> prods = new HashMap<>();

        for(EncEficiente e : this.encomendas.values()) {
            for(String prod : e.get_lista_produtos()) {
                if(prods.containsKey(prod)) prods.get(prod).add(e.get_nr_encomenda());
                else {
                    List<Integer> new_l = new ArrayList<>();
                    new_l.add(e.get_nr_encomenda());
                    prods.put(prod, new_l);
                }
            }
        }
        return prods;
    }
}
