package Servicos;
import modelo.ItemAcervo;
import modelo.Usuario;

public class Biblioteca{

    public final String nome;
    public final ItemAcervo[] acervo;;
    private int totalItens;
    private final Usuario[] usuarios;
    private int totalUsuarios;

    public Biblioteca(String nome){
        this.nome = nome;
        this.acervo = new ItemAcervo[Config.CAPACIDADE_ACERVO];
        this.totalItens = 0;
        this.usuarios = new Usuario[Config.CAPACIDADE_MAXIMA_USUARIOS];
        this.totalUsuarios = 0;
    }

    public void cadastrarItem(ItemAcervo item){

        if(item == null){
            throw new IllegalArgumentException("Item inválido");
        }

        if(totalItens > Config.CAPACIDADE_ACERVO){
            throw new IllegalStateException("Capacidade máxima de itens atingida");
        }

        if(item.getCodigo() == null || item.getCodigo().isBlank()){
            throw new IllegalArgumentException("Código do item inválido");
        }

        if(buscarItemPorCodigo(item.getCodigo()) != null){
            throw new IllegalArgumentException("Item já cadastrado");
        }

        acervo[totalItens++] = item;
    
    }

    public void cadastrarUsuario(Usuario usuario){

        if(usuario == null){
            throw new IllegalArgumentException("Usuário inválido");
        }

        if(totalUsuarios > usuarios.length){
            throw new IllegalStateException("Capacidade máxima de usuários atingida");
        }

        usuarios[totalUsuarios++] = usuario;
    }

    public String buscarItemPorCodigo(String codigo){
        for(int i = 0; i < acervo.length; i++){
            if(acervo[i].getCodigo().equals(codigo)){
                return acervo[i];
            }      
        }
        return null;
    }

    public boolean emprestar(String itemCodigo, Usuario usuario){

        ItemAcervo item = busacarItemPorCodigo(itemCodigo);

        if(item == null){
            throw new IllegalArgumentException("Item não encontrado");
            return false;
        }

        if (item instanceof Emprestavel emprestavel) {
            return emprestavel.emprestar(usuario);
        }
 // Chegou aqui: o item existe, mas nao cumpre o contrato Emprestavel.
        System.out.printf("   [X] %-22s tentou \"%s\" - obra de consulta local%n", usuario.getNome(), item.getTitulo());
        return false;
    }

    public double processarDevolucao(String codigoItem, Usuario usuario, int diasAtraso){

        ItemAcervo item = buscarItemPorCidigo(codigoItem);

        if(item == null || !(item instanceof Emprestavel empresavel)){
            return 0.0;
        }

        if (!emprestavel.devolver()) {
            return 0.0;
        }

        double multaBruta =item.calcularMulta(diasAtraso);
        double multaLiquida = usiario.calcularDesconto(multaBruta);

        if (multaLiquida > 0) {
            usuario.acumularMulta(multaLiquida);
        }

        return multaLiquida;

    }

    /** Cabecalho padrao dos relatorios. */
    private void imprimirSeparador() {
        System.out.println("-".repeat(Config.LARGURA_RELATORIO));
    }

      public void listarAcervo() {
        System.out.println("[1] ACERVO CADASTRADO (" + totalItens + " itens)");
        imprimirSeparador();

        for (int i = 0; i < totalItens; i++) {
            ItemAcervo item = acervo[i];

            String prazoEMulta;
            // instanceof verificando CONTRATO, nao decidindo comportamento
            if (item instanceof Emprestavel e) {
                prazoEMulta = String.format("%2d dias | R$ %.2f/dia",
                        e.getPrazoEmprestimoDias(), e.getMultaPorDia());
            } else {
                prazoEMulta = "CONSULTA LOCAL";
            }

            System.out.printf("%-8s | %-31s | %s%n",
                    item.getCategoria(), item.getTitulo(), prazoEMulta);
        }
        System.out.println();
    }

    public double calcularMultasTotais(){

        double multaTotais = 0.0;

        for(int i; i < totalUsuarios; i++){
            multaTottais = usuarios[i].getMultaAculmulada();
        }

        return multaTotais;
    }

    public void listarSituacaoUsuarios() {
        System.out.println("[5] SITUACAO DOS USUARIOS");
        imprimirSeparador();

        for (int i = 0; i < totalUsuarios; i++) {
            // toString() polimorfico: AlunoPosGraduacao e Professor acrescentam
            // informacao com super.toString(); os demais usam a versao do pai.
            System.out.println("   " + usuarios[i]);
        }

        imprimirSeparador();
        System.out.printf("   TOTAL EM MULTAS: R$ %.2f%n%n", calcularMultasTotais());
    }

    public void listarReservas(){

        for(int i = 0; i < totalItens; i++){
            ItemAcervo item = acervo[i];

            if(item instanceof Emprestavel e && e.temReserva()){
                System.out.printf("   %-31s | %s%n", item.getTitulo(), e.getReservante());
            }
        }


    }

     public void notificarDevedores() {
        System.out.println("[7] NOTIFICACOES AUTOMATICAS");
        imprimirSeparador();

        for (int i = 0; i < totalUsuarios; i++) {
            Usuario u = usuarios[i];
            if (u.getMultaAcumulada() > 0) {
                // notificar() e notificarUrgente() sao metodos DEFAULT da
                // interface Notificavel — nenhuma subclasse os implementou.
                if (u.getMultaAcumulada() >= 10.0) {
                    u.notificarUrgente(String.format(
                            "Debito de R$ %.2f pendente.", u.getMultaAcumulada()));
                } else {
                    u.notificar(String.format(
                            "Voce possui R$ %.2f em multas.", u.getMultaAcumulada()));
                }
            }
        }
        System.out.println();
    }

    /** Contagem de obras de consulta local. */
    public int contarObrasDeReferencia() {
        int total = 0;
        for (int i = 0; i < totalItens; i++) {
            if (acervo[i] instanceof ObraReferencia) {
                total++;
            }
        }
        return total;
    }

    public String getNome() {
        return nome;
    }

    public int getTotalItens() {
        return totalItens;
    }

    public ItemAcervo[] getAcervo() {
        return java.util.Arrays.copyOf(acervo, totalItens);
    }




    
}