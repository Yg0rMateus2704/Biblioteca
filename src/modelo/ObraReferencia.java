public class ObraReferencia extends ItemAcervo{

    private final Sring tipoObra;
    private final String localizacaoEstante;

    public ObraReferencia(String titulo, String autor, String editora, String anoPublicacao, String localizacaoEstante){
        super(titulo, autor, editora, anoPublicacao);
        this.tipoObra = tipoObra;
        this.localizacaoEstante = localizacaoEstante;
    }

    public void consultarNoLocal(Usuario usuario){
        System.out.println("Obra referencia consultada pelo usuario" + usuario.getNome());
        System.out.println("Localização da obra de referência: " + localizacaoEstante);
    }

    @Override 
    public String getCategoria(){
        return "Referência";
    }

    @Override 
    public String getDescricaoDetalhada(){
        return "Tipo da Obra" + tipoObra + "\nLocalização na Estantante" + localizacaoEstante;
    }

    public String getLocalizacaoEstante(){
        return this.localizacaoEstante;
    }


}