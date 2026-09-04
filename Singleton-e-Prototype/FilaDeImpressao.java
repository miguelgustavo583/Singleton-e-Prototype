/**
 * Padrão Singleton.
 *
 * Garante que exista, em toda a aplicação, uma única instância responsável
 * por enviar documentos para a impressora central. Isso evita que múltiplas
 * "filas" concorrentes façam a impressora travar ou misture os documentos
 * de diferentes partes do sistema.
 */
public class FilaDeImpressao {

    // Única instância da classe, compartilhada por toda a aplicação.
    private static FilaDeImpressao instance;

    // Construtor privado: impede que outras classes façam "new FilaDeImpressao()".
    private FilaDeImpressao() {
    }

    /**
     * Ponto de acesso global à instância única.
     * Cria a instância apenas na primeira chamada (lazy initialization).
     */
    public static FilaDeImpressao getInstance() {
        if (instance == null) {
            instance = new FilaDeImpressao();
        }
        return instance;
    }

    /**
     * Simula o envio de um documento para a impressora central.
     */
    public void imprimir(String documento) {
        System.out.println("=== Imprimindo ===");
        System.out.println(documento);
        System.out.println("==================\n");
    }
}
