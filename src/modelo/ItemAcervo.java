import modelo.Config;
package modelo;

public abstract class ItemAcervo{

    private final String codigo;
    private final String titulo;
    private final int ano;
    private boolean disponivel;
    private static int totalItensCriados = 0;

    protected ItemAcervo(String codigo, String titulo, int ano){

        if(codigo == null || codigo.isBlank()){
            throw new IllegalArgumentException("Código Invaido");
        }

        if(titulo == null || titulo.isBlank()){
            throw new IllegalArgumentException("Titulo Invalido");
        }

        if(ano <= Config.ANO_MINIMO_PUBLICACAO || ano > Config.ANO_MAX_PUBLICACAO){
            throw new IllegalArgumentException("Ano Invalido");
        }

        this.ano = ano;
        this.titulo = titulo;
        this.codigo = codigo;
        this.disponivel = disponivel;
        totalItensCriados++;
    }

    public abstract String getCategoria();
    public abstract String getDescricao();

    public String getTitulo(){
        return titulo;
    }

    public  String getCodigo(){
        return codigo;
    }

    public  int getAno(){
        return ano;
    }

    public boolean isDisponivel(){
        return  disponivel;
    }

    protected void marcarComoDisponivel(){
        this.disponivel = true;
    }

    protected void marcarComoEmprestado(){
        this.disponivel = false;
    }

    public static int getTotalItensCriados(){
        return totalItensCriados;
    }



    
}