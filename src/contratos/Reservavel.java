public interface Reservavel{

    void reservar(Usuario usuario);
    boolean temReserva();
    String getReservante();

    default void cancelarReserva(){
        
    }


}