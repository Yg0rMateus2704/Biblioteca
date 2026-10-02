package modelo;

public abstract class Usuario{

    private final String matricula;
    private final String nome;
    private final String email;
    private final ItemAcervo[] intensEmprestados;
    private int quantidadeEmprestado;
    private double multaAcumulada;

    private static int totalUsuarioCriados = 0;

    protected Usuario(String matricula, String nome, String email){
        this.matricula = matricula;
        this.nome = nome;
        this.email = email;
        this.intensEmprestados = new ItemAcervo[10];
        this.quantidadeEmprestado = 0;
        this.multaAcumulada = 0;
        totalUsuarioCriados++;
    }

    public abstract int getLimiteEmprestimo();
    public abstract double getPercentualDesconto();
    public abstract String getCategoria();

    public double aplicarDesconto( double multaAcumulada){
        if(multaAcumulada < 0){
            throw new IllegalArgumentException("A multa não pode ser menor que zero!");
        }

        return  multaAcumulada * getPercentualDesconto();
    }

    public void registrarEmprestimo(ItemAcervo item){
        intensEmprestados[quantidadeEmprestado] = item;
        quantidadeEmprestado++;
    }

    public boolean registrarDevolucao(ItemAcervo item){

        for(int i = 0; i < quantidadeEmprestado; i++){
            if(intensEmprestados[i].equals(item)){
                for(int j = i; j <  quantidadeEmprestado - 1; j++){
                    itensEmprestados[j] = intensEmprestados[j + 1];
                }

                itensEmprestados[quantidadeEmprestado] = null;
                quantidadeEmprestado--;
                return true;
            }

        }

        return false;
    }

}