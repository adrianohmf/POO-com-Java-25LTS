// Arquivo: Notificavel.java

interface Notificavel {
    void enviarEmail(String dest);
    void enviarSMS(String telefone);

    // Método default: tem implementação padrão (pode ser sobrescrito)
    default void notificarTodos(String dest, String tel) {
        enviarEmail(dest);
        enviarSMS(tel);
    }
}
