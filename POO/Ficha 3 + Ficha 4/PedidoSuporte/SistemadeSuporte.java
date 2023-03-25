import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.function.Function;
import java.util.Map;

public class SistemadeSuporte
{
    private List<PedidodeSuporte> pedidos;
    
    public SistemadeSuporte() {
        this.pedidos = new ArrayList<>();
    }

    public SistemadeSuporte(List<PedidodeSuporte> pedidos) {
        /*
        this.pedidos = new ArrayList<>();
        
        for(PedidodeSuporte ps : pedidos)
            this.pedidos.add(ps.clone());
        */
       
        this.pedidos = pedidos.stream().map(PedidodeSuporte::clone)
                                       .collect(Collectors.toList());
    }
    
    public SistemadeSuporte(SistemadeSuporte ss) {
        this.pedidos = ss.get_pedidos();
    }
    
    public List<PedidodeSuporte> get_pedidos() {
        /*
        List<PedidodeSuporte> newPedidos = new ArrayList<>();
        
        for(PedidodeSuporte ps : this.pedidos)
            newPedidos.add(ps.clone());

        
        return newPedidos;
        */
       
        return this.pedidos.stream().map(PedidodeSuporte::clone)
                                    .collect(Collectors.toList());
    }
    
    public void inserePedido(PedidodeSuporte pedido) {
        this.pedidos.add(pedido.clone());
    }
    
    public PedidodeSuporte procuraPedido(String user, LocalDateTime data) {
        /*
        for(PedidodeSuporte ps : this.pedidos) {
            if (
                user.equals(ps.get_nomeRemetente()) &&
                data.equals(ps.get_submetido())
            ) return ps.clone();
        }
        
        return null;
        */
        
        return pedidos.stream().filter(ps -> user.equals(ps.get_nomeRemetente()) && data.equals(ps.get_submetido()))
                               .findFirst()
                               .orElse(null);
    }
    
    public void resolvePedido(PedidodeSuporte pedido, String tecnico, String info) {
        for(PedidodeSuporte ps : pedidos) {
            if (ps.equals(pedido)) {
                pedido.set_nomeDestinatario(tecnico);
                pedido.set_resposta(info);
                pedido.set_respondido(LocalDateTime.now());
                break;
            }
        }
    }
    
    public List<PedidodeSuporte> resolvidos() {
        return this.pedidos.stream()
                   .filter(ps -> !ps.get_resposta().equals(""))
                   .map(PedidodeSuporte::clone)
                   .collect(Collectors.toList());
    }
    
    public List<PedidodeSuporte> resolvidos(LocalDateTime inicio, LocalDateTime fim) {
        return this.pedidos.stream()
                   .filter(ps -> !ps.get_resposta().equals(""))
                   .filter(ps -> ps.get_respondido().isAfter(inicio))
                   .filter(ps -> ps.get_respondido().isBefore(fim))
                   .map(PedidodeSuporte::clone)
                   .collect(Collectors.toList());
    }
    
    public String colaboradorTop() {
        return resolvidos().stream().map(PedidodeSuporte::get_nomeDestinatario)
                                    .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
                                    .entrySet()
                                    .stream()
                                    .max(Map.Entry.comparingByValue())
                                    .map(Map.Entry::getKey).orElse(null);
    }
    
    public double tempoMedioResolucao() {
        double tempoTotal = resolvidos().stream()
            .mapToDouble(ps -> ChronoUnit.MINUTES.between(ps.get_submetido(), ps.get_respondido()))
            .sum();
        
        return tempoTotal / resolvidos().size();
    }
    
    public double tempoMedioResolucao(LocalDateTime inicio, LocalDateTime fim) {
        return resolvidos().stream()
            .filter(ps -> ps.get_respondido().isAfter(inicio))
            .filter(ps -> ps.get_respondido().isBefore(fim))
            .mapToDouble(ps -> ChronoUnit.MINUTES.between(ps.get_submetido(), ps.get_respondido()))
            .sum()
            /resolvidos().size();
    }
    
    public PedidodeSuporte pedidoMaisLongo() {
        return resolvidos().stream()
            .max(Comparator.comparingLong(ps -> ChronoUnit.MINUTES.between(ps.get_submetido(), ps.get_respondido())))
            .get()
            .clone();
    }
    
    public PedidodeSuporte pedidoMaisLongo(LocalDateTime inicio, LocalDateTime fim) {
        return resolvidos().stream()
            .filter(ps -> ps.get_respondido().isAfter(inicio))
            .filter(ps -> ps.get_respondido().isBefore(fim))
            .max(Comparator.comparingLong(ps -> ChronoUnit.MINUTES.between(ps.get_submetido(), ps.get_respondido())))
            .get()
            .clone();
    }
    
    public PedidodeSuporte pedidoMaisCurto() {
        return resolvidos().stream()
            .min(Comparator.comparingLong(ps -> ChronoUnit.MINUTES.between(ps.get_submetido(), ps.get_respondido())))
            .get()
            .clone();
    }
    
    public PedidodeSuporte pedidoMaisCurto(LocalDateTime inicio, LocalDateTime fim) {
        return resolvidos().stream()
            .filter(ps -> ps.get_respondido().isAfter(inicio))
            .filter(ps -> ps.get_respondido().isBefore(fim))
            .min(Comparator.comparingLong(ps -> ChronoUnit.MINUTES.between(ps.get_submetido(), ps.get_respondido())))
            .get()
            .clone();
    }
}
