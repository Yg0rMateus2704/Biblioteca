pakage Servicos;
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

    public void cadastrarUsuario(Usuario ususario){

        if(usuario == null){
            throw new IllegalArgumentException("Usuário inválido");
        }

        if(totalUsuarios > usuarios.length){
            throw new IllegalStateException("Capacidade máxima de usuários atingida");
        }

        usuarios[totalUsuarios++] = usuario
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



    
}