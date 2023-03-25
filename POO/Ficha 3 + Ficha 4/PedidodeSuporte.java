import java.time.LocalDateTime;

public class PedidodeSuporte
{
    private String nomeRemetente;
    private String nomeDestinatario;
    private String assunto;
    private String descricao;
    private String resposta;
    private LocalDateTime submetido;
    private LocalDateTime respondido;
    
    public PedidodeSuporte() {
        this.nomeRemetente = "";
        this.nomeDestinatario = "";
        this.assunto = "";
        this.descricao = "";
        this.resposta = "";
        this.submetido = LocalDateTime.now();
        this.respondido = LocalDateTime.now();
    }
    
    public PedidodeSuporte(String nomeRemetente, String nomeDestinatario, String assunto, String descricao, 
                           String resposta, LocalDateTime submetido, LocalDateTime respondido) {
        this.nomeRemetente = nomeRemetente;
        this.nomeDestinatario = nomeDestinatario;
        this.assunto = assunto;                    
        this.descricao = descricao;
        this.resposta = resposta;
        this.submetido = submetido;
        this.respondido = respondido;
    }
    
    public PedidodeSuporte(PedidodeSuporte ps) {
        this.nomeRemetente = get_nomeRemetente();
        this.nomeDestinatario = get_nomeDestinatario();
        this.assunto = get_assunto();                    
        this.descricao = get_descricao();
        this.resposta = get_resposta();
        this.submetido = get_submetido();
        this.respondido = get_respondido();
    }
    
    public PedidodeSuporte clone() {
        return new PedidodeSuporte(this);
    }
    
    public String get_nomeRemetente() {
        return this.nomeRemetente;
    }
    
    public void set_nomeRemetente(String nomeRemetente) {
        this.nomeRemetente = nomeRemetente;
    }
    
    public String get_nomeDestinatario() {
        return this.nomeDestinatario;
    }
    
    public void set_nomeDestinatario(String nomeDestinatario) {
        this.nomeDestinatario = nomeDestinatario;
    }
    
    public String get_assunto() {
        return this.assunto;
    }
    
    public void set_assunto(String assunto) {
        this.assunto = assunto;
    }
    
    public String get_descricao() {
        return this.descricao;
    }
    
    public void set_descricao(String descricao) {
        this.descricao = descricao;
    }
    
    public String get_resposta() {
        return this.resposta;
    }
    
    public void set_resposta(String resposta) {
        this.resposta = resposta;
    }
    
    public LocalDateTime get_submetido() {
        return this.submetido;
    }
    
    public void set_submetido(LocalDateTime submetido) {
        this.submetido = submetido;
    }
    
    public LocalDateTime get_respondido() {
        return this.respondido;
    }
    
    public void set_respondido(LocalDateTime respondido) {
        this.respondido = respondido;
    }
    
    public boolean equals(PedidodeSuporte ps) {
        if (this.nomeRemetente.equals(ps.get_nomeRemetente()) &&
            this.nomeDestinatario.equals(ps.get_nomeDestinatario()) &&
            this.assunto.equals(ps.get_assunto()) &&
            this.descricao.equals(ps.get_descricao()) &&
            this.resposta.equals(ps.get_resposta()) &&
            this.submetido.equals(ps.get_submetido()) &&
            this.respondido.equals(ps.get_respondido())
        ) return true;
        
        return false;
    }
}
