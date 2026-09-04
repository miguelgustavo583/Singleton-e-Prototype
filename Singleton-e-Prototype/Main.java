/**
 * Classe principal: demonstra o uso conjunto dos padrões Singleton
 * (FilaDeImpressao) e Prototype (Certificado) no cenário da
 * Secretaria Acadêmica.
 */
public class Main {

    public static void main(String[] args) {

        // 1) Preparação: solicita a instância única da FilaDeImpressao.
        FilaDeImpressao fila = FilaDeImpressao.getInstance();

        // 2) O Molde: cria o certificado original informando apenas o
        //    nome do curso. O nome do aluno permanece em branco.
        Certificado certificadoOriginal = new Certificado("Análise e Desenvolvimento de Sistemas");

        // 3) A Clonagem: usa clonar() para criar duas cópias independentes
        //    do certificado original.
        Certificado clone1 = certificadoOriginal.clonar();
        Certificado clone2 = certificadoOriginal.clonar();

        // 4) Personalização: usa setNomeAluno() para colocar o nome de
        //    dois alunos diferentes, um em cada clone.
        clone1.setNomeAluno("Lucas Sousa Alves");
        clone2.setNomeAluno("Calleb de Oliveira Lima");

        // 5) Impressão: envia os dados dos dois clones para a Fila de Impressão.
        fila.imprimir(clone1.getDados());
        fila.imprimir(clone2.getDados());

        // Validação obrigatória: prova que a clonagem gerou objetos
        // independentes na memória (deve imprimir "false").
        System.out.println("Teste de memória do Prototype (clone1 == clone2): " + (clone1 == clone2));

        // Bônus: prova que a FilaDeImpressao é realmente única (Singleton).
        FilaDeImpressao outraReferencia = FilaDeImpressao.getInstance();
        System.out.println("Teste do Singleton (fila == outraReferencia): " + (fila == outraReferencia));
    }
}
