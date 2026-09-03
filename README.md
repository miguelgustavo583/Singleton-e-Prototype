# Sistema Secretaria Acadêmica — Singleton + Prototype

Projeto acadêmico que implementa dois padrões de projeto (GoF) para resolver
o problema de emissão de certificados de conclusão de curso pela secretaria
acadêmica.

## Padrões utilizados

### 1. Singleton — `FilaDeImpressao`
A secretaria só pode ter **uma única conexão** com a impressora central.
Se o sistema criasse várias filas de impressão ao mesmo tempo, os
documentos sairiam misturados ou a impressora travaria.

Como foi implementado:
- O atributo `instance` é **privado e estático**, guardando a única
  instância da classe.
- O **construtor é privado**, o que impede qualquer outra classe de criar
  uma nova `FilaDeImpressao` com `new`.
- O método estático `getInstance()` é o único ponto de acesso: na primeira
  chamada ele cria a instância (*lazy initialization*); nas chamadas
  seguintes, sempre devolve a mesma instância já existente.
- O método `imprimir(String documento)` simula o envio do documento para a
  impressora central.

### 2. Prototype — `Certificado`
Carregar a arte e os dados do curso do banco de dados toda vez que um
certificado precisa ser emitido é uma operação pesada. Em vez disso, o
sistema monta **um certificado "molde"** (com o nome do curso já definido e
o nome do aluno em branco) e o **clona** para cada aluno, alterando apenas
o nome de quem vai recebê-lo.

Como foi implementado:
- A classe implementa a interface `Cloneable`.
- O construtor `Certificado(String nomeCurso)` cria o molde: define o nome
  do curso e deixa `nomeAluno` em branco.
- O método `clonar()` usa `super.clone()` (clonagem nativa do Java) para
  criar uma cópia independente do certificado, sem precisar recarregar
  nada do banco de dados.
- `setNomeAluno(String nome)` personaliza cada clone com o nome de um
  aluno específico.
- `getDados()` retorna os dados já formatados, prontos para impressão.

Como os atributos de `Certificado` são `String` (imutável), a clonagem
rasa feita por `super.clone()` já é suficiente para garantir que os
objetos clonados sejam totalmente independentes entre si.

## Roteiro executado em `Main`
1. **Preparação** — solicita a instância única da `FilaDeImpressao` via
   `getInstance()`.
2. **O Molde** — cria o certificado original com `new Certificado(nomeCurso)`,
   deixando o nome do aluno em branco.
3. **A Clonagem** — chama `clonar()` duas vezes para gerar `clone1` e
   `clone2`.
4. **Personalização** — usa `setNomeAluno()` para dar a cada clone o nome
   de um aluno diferente.
5. **Impressão** — envia `getDados()` de cada clone para `imprimir()` da
   fila de impressão.
6. **Validação obrigatória** — imprime no console o teste
   `clone1 == clone2`, que resulta em `false`, provando que os dois clones
   são objetos independentes na memória (o Prototype de fato criou cópias
   novas, e não referências para o mesmo objeto).

Como teste extra, o `Main` também compara duas chamadas a
`FilaDeImpressao.getInstance()` (`fila == outraReferencia`), que resulta em
`true`, comprovando que o Singleton está funcionando corretamente.

## Estrutura do projeto
```
secretaria-academica/
├── README.md
└── src/
    ├── FilaDeImpressao.java   (Singleton)
    ├── Certificado.java       (Prototype)
    └── Main.java              (roteiro de execução)
```

## Como executar
Pré-requisito: JDK instalado (Java 8+).

```bash
cd src
javac *.java
java Main
```

### Saída esperada
```
=== Imprimindo ===
Certificado de Conclusão
Curso: Análise e Desenvolvimento de Sistemas
Aluno: Ana Beatriz Souza
==================

=== Imprimindo ===
Certificado de Conclusão
Curso: Análise e Desenvolvimento de Sistemas
Aluno: Carlos Eduardo Lima
==================

Teste de memória do Prototype (clone1 == clone2): false
Teste do Singleton (fila == outraReferencia): true
```

## Diagrama de classes
O diagrama de classes que orientou a implementação (Singleton `FilaDeImpressao`
e Prototype `Certificado`) está de acordo com o modelo fornecido pela
atividade, com os mesmos atributos, visibilidades e assinaturas de método.
