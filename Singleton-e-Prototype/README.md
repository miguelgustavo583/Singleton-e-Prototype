🎓 Sistema Secretaria Acadêmica

Implementação acadêmica dos padrões de projeto Singleton e Prototype (GoF) em Java, aplicada ao processo de emissão e impressão de certificados de conclusão de curso.

📌 Sobre o projeto

O Sistema Secretaria Acadêmica simula o processo de emissão de certificados de conclusão de curso.

O projeto foi desenvolvido com o objetivo de demonstrar, na prática, a utilização de dois padrões de projeto do catálogo Gang of Four (GoF):

Padrão	Classe	Finalidade
🔒 Singleton	FilaDeImpressao	Garantir uma única instância da fila de impressão
🧬 Prototype	Certificado	Criar novos certificados a partir da clonagem de um modelo

A combinação dos dois padrões permite reutilizar um certificado-base e garantir que todos os documentos sejam enviados por uma única fila de impressão.

🧩 Padrões de Projeto
🔒 1. Singleton — FilaDeImpressao

A secretaria acadêmica deve possuir uma única conexão com a impressora central.

A criação de múltiplas filas de impressão poderia causar problemas como documentos misturados, concorrência indevida ou até travamentos da impressora.

⚙️ Implementação
instance é um atributo privado e estático, responsável por armazenar a única instância da classe.
O construtor é privado, impedindo que outras classes criem objetos diretamente com new.
getInstance() é o único ponto de acesso à instância.
Na primeira chamada, a instância é criada utilizando lazy initialization.
Nas chamadas seguintes, a mesma instância é retornada.
imprimir(String documento) simula o envio do certificado para a impressora central.
💡 Conceito
getInstance()
     │
     ▼
┌───────────────┐
│ Existe inst.? │
└───────┬───────┘
        │
   ┌────┴────┐
  NÃO       SIM
   │          │
   ▼          ▼
 cria       retorna
 instância   existente

🧬 2. Prototype — Certificado

A criação de um certificado pode envolver o carregamento de informações e elementos do curso.

Em vez de construir um novo certificado do zero para cada aluno, o sistema cria um certificado-modelo e utiliza esse objeto como protótipo.

Cada aluno recebe uma cópia independente do modelo, sendo alterado apenas o seu nome.

⚙️ Implementação
Certificado implementa a interface Cloneable.
O construtor Certificado(String nomeCurso) cria o certificado-modelo.
O curso é definido no modelo e o nome do aluno permanece inicialmente em branco.
clonar() utiliza super.clone() para criar uma nova instância.
setNomeAluno(String nome) personaliza cada certificado clonado.
getDados() retorna as informações formatadas para impressão.

Como os atributos utilizados em Certificado são do tipo String, que é imutável, a clonagem rasa realizada por super.clone() é suficiente para manter os clones independentes.

🔄 Fluxo de execução

O Main executa o seguinte roteiro:

        ┌──────────────────────┐
        │ 1. Obtém Singleton   │
        │ FilaDeImpressao      │
        └──────────┬───────────┘
                   ▼
        ┌──────────────────────┐
        │ 2. Cria o certificado│
        │      "molde"         │
        └──────────┬───────────┘
                   ▼
        ┌──────────────────────┐
        │ 3. Clona o modelo    │
        │      2 vezes         │
        └──────────┬───────────┘
                   ▼
        ┌──────────────────────┐
        │ 4. Personaliza cada  │
        │       clone          │
        └──────────┬───────────┘
                   ▼
        ┌──────────────────────┐
        │ 5. Envia certificados│
        │ para impressão       │
        └──────────┬───────────┘
                   ▼
        ┌──────────────────────┐
        │ 6. Valida Prototype  │
        │ clone1 == clone2     │
        │        → false       │
        └──────────────────────┘

📋 Etapas
Preparação — obtém a instância única de FilaDeImpressao através de getInstance().
Criação do molde — cria Certificado com new Certificado(nomeCurso).
Clonagem — utiliza clonar() duas vezes para gerar clone1 e clone2.
Personalização — define um aluno diferente para cada clone usando setNomeAluno().
Impressão — envia getDados() de cada certificado para imprimir().
Validação do Prototype — verifica clone1 == clone2, cujo resultado esperado é false.
Validação do Singleton — compara duas chamadas de getInstance(), cujo resultado esperado é true.
🧪 Testes realizados
Prototype
clone1 == clone2


Resultado:

false


Isso comprova que os dois certificados são objetos diferentes na memória, demonstrando que o Prototype criou novas instâncias.

Singleton
fila == outraReferencia


Resultado:

true


Isso comprova que as duas referências apontam para a mesma instância de FilaDeImpressao.

📂 Estrutura do projeto
secretaria-academica/
│
├── 📄 README.md
│
└── 📁 src/
    ├── 📄 FilaDeImpressao.java   # Singleton
    ├── 📄 Certificado.java       # Prototype
    └── 📄 Main.java              # Execução e testes

🚀 Como executar
Pré-requisito
☕ JDK 8 ou superior
Compilação

Entre na pasta src e compile os arquivos:

cd src
javac *.java

Execução
java Main

🖥️ Saída esperada
=== Imprimindo ===
Certificado de Conclusão
Curso: Análise e Desenvolvimento de Sistemas
Aluno: Lucas Sousa Alves

==================

=== Imprimindo ===
Certificado de Conclusão
Curso: Análise e Desenvolvimento de Sistemas
Aluno: Calleb de Oliveira Lima
==================

Teste de memória do Prototype (clone1 == clone2): false
Teste do Singleton (fila == outraReferencia): true

📐 Diagrama de classes

O diagrama de classes utilizado como referência para a implementação segue o modelo proposto pela atividade.

Ele contempla:

🔒 Singleton: FilaDeImpressao
🧬 Prototype: Certificado
🔗 Atributos, visibilidades e assinaturas de métodos definidos na atividade.
┌─────────────────────────────┐
│      FilaDeImpressao       │
├─────────────────────────────┤
│ - instance: FilaDeImpressao│
├─────────────────────────────┤
│ - FilaDeImpressao()        │
│ + getInstance()            │
│ + imprimir(documento)      │
└─────────────────────────────┘


┌─────────────────────────────┐
│        Certificado          │
├─────────────────────────────┤
│ - nomeCurso: String        │
│ - nomeAluno: String        │
├─────────────────────────────┤
│ + Certificado(nomeCurso)   │
│ + clonar()                  │
│ + setNomeAluno(nome)       │
│ + getDados()               │
└─────────────────────────────┘

🎯 Objetivos demonstrados

Com este projeto, são demonstrados na prática:

✅ Uso do padrão Singleton
✅ Uso do padrão Prototype
✅ Encapsulamento e controle de instanciação
✅ Lazy initialization
✅ Clonagem de objetos em Java
✅ Criação de objetos independentes a partir de um protótipo
✅ Validação dos padrões por meio de testes no Main
👨‍💻 Tecnologias





<p align="center"> 🎓 <strong>Sistema Secretaria Acadêmica</strong><br> Singleton + Prototype em Java </p>