/**
 * Padrão Prototype.
 *
 * Representa o certificado de conclusão de um curso. Em vez de recarregar
 * do banco de dados a arte e os dados pesados do certificado a cada aluno,
 * o sistema cria um certificado "molde" (com o nome do curso já definido e
 * o nome do aluno em branco) e o clona para cada novo aluno, alterando
 * apenas o nome de quem vai recebê-lo.
 */
public class Certificado implements Cloneable {

    private String nomeCurso;
    private String nomeAluno;

    /**
     * Cria o certificado "molde", informando apenas o nome do curso.
     * O nome do aluno começa em branco e é preenchido depois, em cada clone.
     */
    public Certificado(String nomeCurso) {
        this.nomeCurso = nomeCurso;
        this.nomeAluno = "";
    }

    /**
     * Cria uma cópia independente deste certificado (clonagem rasa é
     * suficiente aqui, pois os atributos são Strings, que são imutáveis).
     */
    public Certificado clonar() {
        try {
            return (Certificado) super.clone();
        } catch (CloneNotSupportedException e) {
            // Não deve acontecer, pois a classe implementa Cloneable.
            throw new RuntimeException("Falha ao clonar o certificado", e);
        }
    }

    public void setNomeAluno(String nome) {
        this.nomeAluno = nome;
    }

    /**
     * Retorna os dados formatados do certificado, prontos para impressão.
     */
    public String getDados() {
        return "Certificado de Conclusão\n"
                + "Curso: " + nomeCurso + "\n"
                + "Aluno: " + nomeAluno;
    }
}
