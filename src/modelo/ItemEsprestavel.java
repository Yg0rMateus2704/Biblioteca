public abstract class ItemEmprestavel extends ItemAcervo implements Emprestavel{
 
    private Usuario usuarioAtual;

    protected ItemEmprestavel(String codigo, Strind titulo, int ano){
        super(codigo, titulo, ano);
        this.usuarioAtual = null;
    }

    @Override
    public boolean emprestar(Usuario usuario){
        if(usuario == null){
            throw IllegalArgumentException("Usuário não pode ser Null");
        }

        if(!isDisponivel()){
            System.out.println("Item Indisponivel");
        }

        marcarComoEmprestado();

        return true;
    }



}