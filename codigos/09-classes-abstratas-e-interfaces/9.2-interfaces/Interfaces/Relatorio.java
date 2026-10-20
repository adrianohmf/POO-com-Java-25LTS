// Arquivo: Relatorio.java

// Implementando MÚLTIPLAS interfaces
class Relatorio implements Imprimivel, Notificavel {
    private String titulo;
    private String conteudo;

    Relatorio(String titulo, String conteudo) {
        this.titulo   = titulo;
        this.conteudo = conteudo;
    }

    @Override
    public void imprimir() {
        IO.println("==================================================");
        IO.println("RELATORIO: " + titulo);
        IO.println("--------------------------------------------------");
        IO.println(conteudo);
        IO.println("==================================================");
    }

    @Override
    public void salvarEmArquivo() {
        IO.println("'" + titulo + "' salvo em arquivo.");
    }

    @Override
    public void enviarEmail(String dest) {
        IO.println("E-mail para " + dest + ": " + titulo);
    }

    @Override
    public void enviarSMS(String tel) {
        IO.println("SMS para " + tel + ": " + titulo);
    }
}
