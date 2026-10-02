package modelo;
import modelo.ItemEmprestavel;

public class AudioLivro extends ItemEmprestavel {

    private final String narrador;
    private final int duracaoMinutos;

    public AudioLivro(String codigo, String titulo, int ano, String narrador,
                      int duracaoMinutos) {
        super(codigo, titulo, ano);

        if (narrador == null || narrador.isBlank()) {
            throw new IllegalArgumentException("Narrador e obrigatorio.");
        }

        this.narrador = narrador;
        this.duracaoMinutos = duracaoMinutos;
    }

    @Override
    public int getPrazoEmprestimoDias() {
        return 10;
    }

    @Override
    public double getMultaPorDia() {
        return 0.75;
    }

    @Override
    public String getCategoria() {
        return "AUDIO";
    }

    @Override
    public String getDescricaoDetalhada() {
        return "Narrado por " + narrador + " | " + duracaoMinutos + " min";
    }
}