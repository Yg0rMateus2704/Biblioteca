package modelo;
import modelo.ItemEmprestavel;

public class DVD extends ItemEmprestavel {

    private final int duracaoMinutos;
    private final String classificacaoIndicativa;

    public DVD(String codigo, String titulo, int ano, int duracaoMinutos,
               String classificacaoIndicativa) {
        super(codigo, titulo, ano);

        if (duracaoMinutos <= 0) {
            throw new IllegalArgumentException("Duracao deve ser positiva.");
        }

        this.duracaoMinutos = duracaoMinutos;
        this.classificacaoIndicativa = classificacaoIndicativa;
    }

    @Override
    public int getPrazoEmprestimoDias() {
        return 3;
    }

    @Override
    public double getMultaPorDia() {
        return 2.00;
    }

    @Override
    public String getCategoria() {
        return "DVD";
    }

    @Override
    public String getDescricaoDetalhada() {
        return duracaoMinutos + " min | Classificacao: " + classificacaoIndicativa;
    }

    public int getDuracaoMinutos() {
        return duracaoMinutos;
    }
}