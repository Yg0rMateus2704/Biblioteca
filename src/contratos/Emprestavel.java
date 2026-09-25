public interface Emprestavel{
    
    int getPrazoEmprestimoDias();
    int getAnoPublicao();
    double getMultaPorDia();
    boolean emprestar(Usuario usuario);
    boolean devolver();

    default boolean permiteRenovacao(){
        return false;
    }

    default double calcularMulta(int diasAtraso){
        if(diasAtraso <= 0){
            return 0;
        }

        return diasAtraso *getMultaPorDia();
    }

}