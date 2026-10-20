# Códigos de exemplo

Todos os códigos do guia, prontos para abrir no VS Code, organizados por **capítulo**, **seção** e **exemplo**. Em cada exemplo, as classes ficam em arquivos separados e o programa principal (`void main()`) fica em um arquivo só dele.

## Como usar

1. Abra a pasta `codigos` no VS Code (**File > Open Folder**), ou apenas a pasta do exemplo que quiser.
2. Abra o arquivo do programa principal e clique em **Run** acima do `void main()`.
3. Ou use o terminal, dentro da pasta do exemplo:

```
cd 06-encapsulamento/6.1-comecando-pelo-mais-simples-nome-e-idade/ComEncapsulamento
java ComEncapsulamento.java
```

O Java 25 compila automaticamente as outras classes da mesma pasta. Os exemplos que leem do teclado (`IO.readln` e `Scanner`) devem ser executados no terminal.

## Organização

```
codigos/
  NN-nome-do-capitulo/
    N.x-nome-da-secao/
      NomeDoExemplo/
        NomeDoExemplo.java   (programa principal, com void main)
        Classe.java          (uma classe por arquivo)
```

Pastas especiais:

* `erros-comuns/` e `trechos/`: arquivos `.txt` com trechos ilustrativos do guia. Eles não são programas completos e alguns têm erros de propósito, por isso não são compilados.
* Os arquivos `Main*.java` dos exemplos que só tinham classes (como `MainAluno.java`) foram adicionados para que o exemplo possa ser executado.
* O exemplo `3.3` repete a classe `Aluno` da seção `3.2` para rodar sozinho.

## Índice dos exemplos

### [1. Introdução ao Java e ao JDK 25](01-introducao-java-jdk25/)

| Seção | Exemplo | Programa principal | Classes |
|---|---|---|---|
| 1.4 Entendendo o `void main()` | [`forma-classica`](01-introducao-java-jdk25/1.4-entendendo-o-void-main/forma-classica/) | [`OlaMundo.java`](01-introducao-java-jdk25/1.4-entendendo-o-void-main/forma-classica/OlaMundo.java) | (nenhuma) |
| 1.4 Entendendo o `void main()` | [`forma-moderna`](01-introducao-java-jdk25/1.4-entendendo-o-void-main/forma-moderna/) | [`OlaMundo.java`](01-introducao-java-jdk25/1.4-entendendo-o-void-main/forma-moderna/OlaMundo.java) | (nenhuma) |
| 1.4 Entendendo o `void main()` | [`quando-usar-forma-classica`](01-introducao-java-jdk25/1.4-entendendo-o-void-main/quando-usar-forma-classica/) | [`OlaMundo.java`](01-introducao-java-jdk25/1.4-entendendo-o-void-main/quando-usar-forma-classica/OlaMundo.java) | (nenhuma) |
| 1.4 Entendendo o `void main()` | [`quando-usar-forma-moderna`](01-introducao-java-jdk25/1.4-entendendo-o-void-main/quando-usar-forma-moderna/) | [`OlaMundo.java`](01-introducao-java-jdk25/1.4-entendendo-o-void-main/quando-usar-forma-moderna/OlaMundo.java) | (nenhuma) |
| 1.6 Primeiro programa | [`OlaMundo`](01-introducao-java-jdk25/1.6-primeiro-programa/OlaMundo/) | [`OlaMundo.java`](01-introducao-java-jdk25/1.6-primeiro-programa/OlaMundo/OlaMundo.java) | (nenhuma) |
| 1.7 Tipos de dados em Java | [`Casting`](01-introducao-java-jdk25/1.7-tipos-de-dados-em-java/Casting/) | [`Casting.java`](01-introducao-java-jdk25/1.7-tipos-de-dados-em-java/Casting/Casting.java) | (nenhuma) |
| 1.7 Tipos de dados em Java | [`ClassesWrapper`](01-introducao-java-jdk25/1.7-tipos-de-dados-em-java/ClassesWrapper/) | [`ClassesWrapper.java`](01-introducao-java-jdk25/1.7-tipos-de-dados-em-java/ClassesWrapper/ClassesWrapper.java) | (nenhuma) |
| 1.7 Tipos de dados em Java | [`FormatandoSaida`](01-introducao-java-jdk25/1.7-tipos-de-dados-em-java/FormatandoSaida/) | [`FormatandoSaida.java`](01-introducao-java-jdk25/1.7-tipos-de-dados-em-java/FormatandoSaida/FormatandoSaida.java) | (nenhuma) |
| 1.7 Tipos de dados em Java | [`LeitorDados`](01-introducao-java-jdk25/1.7-tipos-de-dados-em-java/LeitorDados/) | [`LeitorDados.java`](01-introducao-java-jdk25/1.7-tipos-de-dados-em-java/LeitorDados/LeitorDados.java) | (nenhuma) |
| 1.7 Tipos de dados em Java | [`LeitorNumeros`](01-introducao-java-jdk25/1.7-tipos-de-dados-em-java/LeitorNumeros/) | [`LeitorNumeros.java`](01-introducao-java-jdk25/1.7-tipos-de-dados-em-java/LeitorNumeros/LeitorNumeros.java) | (nenhuma) |
| 1.7 Tipos de dados em Java | [`LeitorSimples`](01-introducao-java-jdk25/1.7-tipos-de-dados-em-java/LeitorSimples/) | [`LeitorSimples.java`](01-introducao-java-jdk25/1.7-tipos-de-dados-em-java/LeitorSimples/LeitorSimples.java) | (nenhuma) |
| 1.7 Tipos de dados em Java | [`MeuPrograma`](01-introducao-java-jdk25/1.7-tipos-de-dados-em-java/MeuPrograma/) | [`MeuPrograma.java`](01-introducao-java-jdk25/1.7-tipos-de-dados-em-java/MeuPrograma/MeuPrograma.java) | (nenhuma) |
| 1.7 Tipos de dados em Java | [`Operadores`](01-introducao-java-jdk25/1.7-tipos-de-dados-em-java/Operadores/) | [`Operadores.java`](01-introducao-java-jdk25/1.7-tipos-de-dados-em-java/Operadores/Operadores.java) | (nenhuma) |
| 1.7 Tipos de dados em Java | [`ProblemaBuffer`](01-introducao-java-jdk25/1.7-tipos-de-dados-em-java/ProblemaBuffer/) | [`ProblemaBuffer.java`](01-introducao-java-jdk25/1.7-tipos-de-dados-em-java/ProblemaBuffer/ProblemaBuffer.java) | (nenhuma) |
| 1.7 Tipos de dados em Java | [`ScannerSemImport`](01-introducao-java-jdk25/1.7-tipos-de-dados-em-java/ScannerSemImport/) | [`ScannerSemImport.java`](01-introducao-java-jdk25/1.7-tipos-de-dados-em-java/ScannerSemImport/ScannerSemImport.java) | (nenhuma) |
| 1.7 Tipos de dados em Java | [`Strings`](01-introducao-java-jdk25/1.7-tipos-de-dados-em-java/Strings/) | [`Strings.java`](01-introducao-java-jdk25/1.7-tipos-de-dados-em-java/Strings/Strings.java) | (nenhuma) |
| 1.7 Tipos de dados em Java | [`TiposDados`](01-introducao-java-jdk25/1.7-tipos-de-dados-em-java/TiposDados/) | [`TiposDados.java`](01-introducao-java-jdk25/1.7-tipos-de-dados-em-java/TiposDados/TiposDados.java) | (nenhuma) |

### [3. Classes e Objetos](03-classes-e-objetos/)

| Seção | Exemplo | Programa principal | Classes |
|---|---|---|---|
| 3.2 Criando uma classe | [`Aluno`](03-classes-e-objetos/3.2-criando-uma-classe/Aluno/) | [`MainAluno.java`](03-classes-e-objetos/3.2-criando-uma-classe/Aluno/MainAluno.java) | [`Aluno.java`](03-classes-e-objetos/3.2-criando-uma-classe/Aluno/Aluno.java) |
| 3.3 Criando objetos (instanciando) | [`ProgramaAluno`](03-classes-e-objetos/3.3-criando-objetos-instanciando/ProgramaAluno/) | [`ProgramaAluno.java`](03-classes-e-objetos/3.3-criando-objetos-instanciando/ProgramaAluno/ProgramaAluno.java) | [`Aluno.java`](03-classes-e-objetos/3.3-criando-objetos-instanciando/ProgramaAluno/Aluno.java) |
| 3.4 Múltiplos objetos — exemplo prático | [`Loja`](03-classes-e-objetos/3.4-multiplos-objetos-exemplo-pratico/Loja/) | [`Loja.java`](03-classes-e-objetos/3.4-multiplos-objetos-exemplo-pratico/Loja/Loja.java) | [`Produto.java`](03-classes-e-objetos/3.4-multiplos-objetos-exemplo-pratico/Loja/Produto.java) |

Trechos ilustrativos (`.txt`): [`erros-comuns.txt`](03-classes-e-objetos/erros-comuns/erros-comuns.txt)

### [4. Atributos e Métodos](04-atributos-e-metodos/)

| Seção | Exemplo | Programa principal | Classes |
|---|---|---|---|
| 4.1 Tipos de atributos — diagrama | [`AtributoEstatico`](04-atributos-e-metodos/4.1-tipos-de-atributos-diagrama/AtributoEstatico/) | [`AtributoEstatico.java`](04-atributos-e-metodos/4.1-tipos-de-atributos-diagrama/AtributoEstatico/AtributoEstatico.java) | [`Produto.java`](04-atributos-e-metodos/4.1-tipos-de-atributos-diagrama/AtributoEstatico/Produto.java) |
| 4.1 Tipos de atributos — diagrama | [`TiposAtributos`](04-atributos-e-metodos/4.1-tipos-de-atributos-diagrama/TiposAtributos/) | [`TiposAtributos.java`](04-atributos-e-metodos/4.1-tipos-de-atributos-diagrama/TiposAtributos/TiposAtributos.java) | [`Produto.java`](04-atributos-e-metodos/4.1-tipos-de-atributos-diagrama/TiposAtributos/Produto.java) |
| 4.2 Tipos de métodos | [`TiposMetodos`](04-atributos-e-metodos/4.2-tipos-de-metodos/TiposMetodos/) | [`TiposMetodos.java`](04-atributos-e-metodos/4.2-tipos-de-metodos/TiposMetodos/TiposMetodos.java) | [`Calculadora.java`](04-atributos-e-metodos/4.2-tipos-de-metodos/TiposMetodos/Calculadora.java) |
| 4.3 Sobrecarga de métodos (Overloading) | [`Sobrecarga`](04-atributos-e-metodos/4.3-sobrecarga-de-metodos-overloading/Sobrecarga/) | [`Sobrecarga.java`](04-atributos-e-metodos/4.3-sobrecarga-de-metodos-overloading/Sobrecarga/Sobrecarga.java) | [`Impressora.java`](04-atributos-e-metodos/4.3-sobrecarga-de-metodos-overloading/Sobrecarga/Impressora.java) |

Trechos ilustrativos (`.txt`): [`metodo-com-retorno-somar.txt`](04-atributos-e-metodos/4.2-tipos-de-metodos/trechos/metodo-com-retorno-somar.txt), [`metodo-void-exibirSoma.txt`](04-atributos-e-metodos/4.2-tipos-de-metodos/trechos/metodo-void-exibirSoma.txt), [`erros-comuns.txt`](04-atributos-e-metodos/erros-comuns/erros-comuns.txt)

### [5. Construtores](05-construtores/)

| Seção | Exemplo | Programa principal | Classes |
|---|---|---|---|
| 5.2 Construtor resolve o problema | [`Construtores`](05-construtores/5.2-construtor-resolve-o-problema/Construtores/) | [`Construtores.java`](05-construtores/5.2-construtor-resolve-o-problema/Construtores/Construtores.java) | [`Aluno.java`](05-construtores/5.2-construtor-resolve-o-problema/Construtores/Aluno.java) |
| 5.3 Records (JDK 25) | [`ComRecord`](05-construtores/5.3-records-jdk-25/ComRecord/) | [`ComRecord.java`](05-construtores/5.3-records-jdk-25/ComRecord/ComRecord.java) | [`Produto.java`](05-construtores/5.3-records-jdk-25/ComRecord/Produto.java) |
| 5.3 Records (JDK 25) | [`RecordProduto`](05-construtores/5.3-records-jdk-25/RecordProduto/) | [`MainRecordProduto.java`](05-construtores/5.3-records-jdk-25/RecordProduto/MainRecordProduto.java) | [`Produto.java`](05-construtores/5.3-records-jdk-25/RecordProduto/Produto.java) |
| 5.3 Records (JDK 25) | [`SemRecord`](05-construtores/5.3-records-jdk-25/SemRecord/) | [`SemRecord.java`](05-construtores/5.3-records-jdk-25/SemRecord/SemRecord.java) | [`Produto.java`](05-construtores/5.3-records-jdk-25/SemRecord/Produto.java) |
| 5.3 Records (JDK 25) | [`UsandoRecords`](05-construtores/5.3-records-jdk-25/UsandoRecords/) | [`UsandoRecords.java`](05-construtores/5.3-records-jdk-25/UsandoRecords/UsandoRecords.java) | [`Municipio.java`](05-construtores/5.3-records-jdk-25/UsandoRecords/Municipio.java), [`Produto.java`](05-construtores/5.3-records-jdk-25/UsandoRecords/Produto.java) |

Trechos ilustrativos (`.txt`): [`sem-construtor.txt`](05-construtores/5.1-o-problema-sem-construtor/trechos/sem-construtor.txt), [`com-construtor.txt`](05-construtores/5.2-construtor-resolve-o-problema/trechos/com-construtor.txt), [`erros-comuns.txt`](05-construtores/erros-comuns/erros-comuns.txt)

### [6. Encapsulamento](06-encapsulamento/)

| Seção | Exemplo | Programa principal | Classes |
|---|---|---|---|
| 6.1 Começando pelo mais simples: nome e idade | [`ComEncapsulamento`](06-encapsulamento/6.1-comecando-pelo-mais-simples-nome-e-idade/ComEncapsulamento/) | [`ComEncapsulamento.java`](06-encapsulamento/6.1-comecando-pelo-mais-simples-nome-e-idade/ComEncapsulamento/ComEncapsulamento.java) | [`PessoaComEncapsulamento.java`](06-encapsulamento/6.1-comecando-pelo-mais-simples-nome-e-idade/ComEncapsulamento/PessoaComEncapsulamento.java) |
| 6.1 Começando pelo mais simples: nome e idade | [`SemEncapsulamento`](06-encapsulamento/6.1-comecando-pelo-mais-simples-nome-e-idade/SemEncapsulamento/) | [`SemEncapsulamento.java`](06-encapsulamento/6.1-comecando-pelo-mais-simples-nome-e-idade/SemEncapsulamento/SemEncapsulamento.java) | [`PessoaSemEncapsulamento.java`](06-encapsulamento/6.1-comecando-pelo-mais-simples-nome-e-idade/SemEncapsulamento/PessoaSemEncapsulamento.java) |
| 6.2 O problema sem encapsulamento | [`ContaEncapsulada`](06-encapsulamento/6.2-o-problema-sem-encapsulamento/ContaEncapsulada/) | [`ContaEncapsulada.java`](06-encapsulamento/6.2-o-problema-sem-encapsulamento/ContaEncapsulada/ContaEncapsulada.java) | [`ContaBancaria.java`](06-encapsulamento/6.2-o-problema-sem-encapsulamento/ContaEncapsulada/ContaBancaria.java) |
| 6.3 Diferença entre private e protected | [`PrivateVsProtected`](06-encapsulamento/6.3-diferenca-entre-private-e-protected/PrivateVsProtected/) | [`MainPessoa.java`](06-encapsulamento/6.3-diferenca-entre-private-e-protected/PrivateVsProtected/MainPessoa.java) | [`Aluno.java`](06-encapsulamento/6.3-diferenca-entre-private-e-protected/PrivateVsProtected/Aluno.java), [`Pessoa.java`](06-encapsulamento/6.3-diferenca-entre-private-e-protected/PrivateVsProtected/Pessoa.java) |

Trechos ilustrativos (`.txt`): [`erros-comuns.txt`](06-encapsulamento/erros-comuns/erros-comuns.txt)

### [7. Herança](07-heranca/)

| Seção | Exemplo | Programa principal | Classes |
|---|---|---|---|
| 7.1 Visualizando a herança | [`Heranca`](07-heranca/7.1-visualizando-a-heranca/Heranca/) | [`Heranca.java`](07-heranca/7.1-visualizando-a-heranca/Heranca/Heranca.java) | [`Aluno.java`](07-heranca/7.1-visualizando-a-heranca/Heranca/Aluno.java), [`Pessoa.java`](07-heranca/7.1-visualizando-a-heranca/Heranca/Pessoa.java), [`Professor.java`](07-heranca/7.1-visualizando-a-heranca/Heranca/Professor.java) |
| 7.1 Visualizando a herança | [`OverrideCorreto`](07-heranca/7.1-visualizando-a-heranca/OverrideCorreto/) | [`MainOverride.java`](07-heranca/7.1-visualizando-a-heranca/OverrideCorreto/MainOverride.java) | [`Animal.java`](07-heranca/7.1-visualizando-a-heranca/OverrideCorreto/Animal.java), [`Cachorro.java`](07-heranca/7.1-visualizando-a-heranca/OverrideCorreto/Cachorro.java) |
| 7.2 Hierarquia de múltiplos níveis | [`Hierarquia`](07-heranca/7.2-hierarquia-de-multiplos-niveis/Hierarquia/) | [`Hierarquia.java`](07-heranca/7.2-hierarquia-de-multiplos-niveis/Hierarquia/Hierarquia.java) | [`Animal.java`](07-heranca/7.2-hierarquia-de-multiplos-niveis/Hierarquia/Animal.java), [`Cachorro.java`](07-heranca/7.2-hierarquia-de-multiplos-niveis/Hierarquia/Cachorro.java), [`Gato.java`](07-heranca/7.2-hierarquia-de-multiplos-niveis/Hierarquia/Gato.java), [`Mamifero.java`](07-heranca/7.2-hierarquia-de-multiplos-niveis/Hierarquia/Mamifero.java) |

Trechos ilustrativos (`.txt`): [`override-com-erro.txt`](07-heranca/7.1-visualizando-a-heranca/trechos/override-com-erro.txt), [`erros-comuns.txt`](07-heranca/erros-comuns/erros-comuns.txt)

### [8. Polimorfismo](08-polimorfismo/)

| Seção | Exemplo | Programa principal | Classes |
|---|---|---|---|
| 8.1 Visualizando o polimorfismo | [`Polimorfismo`](08-polimorfismo/8.1-visualizando-o-polimorfismo/Polimorfismo/) | [`Polimorfismo.java`](08-polimorfismo/8.1-visualizando-o-polimorfismo/Polimorfismo/Polimorfismo.java) | [`Animal.java`](08-polimorfismo/8.1-visualizando-o-polimorfismo/Polimorfismo/Animal.java), [`Cachorro.java`](08-polimorfismo/8.1-visualizando-o-polimorfismo/Polimorfismo/Cachorro.java), [`Gato.java`](08-polimorfismo/8.1-visualizando-o-polimorfismo/Polimorfismo/Gato.java), [`Vaca.java`](08-polimorfismo/8.1-visualizando-o-polimorfismo/Polimorfismo/Vaca.java) |

Trechos ilustrativos (`.txt`): [`erros-comuns.txt`](08-polimorfismo/erros-comuns/erros-comuns.txt)

### [9. Classes Abstratas e Interfaces](09-classes-abstratas-e-interfaces/)

| Seção | Exemplo | Programa principal | Classes |
|---|---|---|---|
| 9.1 Classes Abstratas — quando usar | [`ClassesAbstratas`](09-classes-abstratas-e-interfaces/9.1-classes-abstratas-quando-usar/ClassesAbstratas/) | [`ClassesAbstratas.java`](09-classes-abstratas-e-interfaces/9.1-classes-abstratas-quando-usar/ClassesAbstratas/ClassesAbstratas.java) | [`Diretor.java`](09-classes-abstratas-e-interfaces/9.1-classes-abstratas-quando-usar/ClassesAbstratas/Diretor.java), [`Funcionario.java`](09-classes-abstratas-e-interfaces/9.1-classes-abstratas-quando-usar/ClassesAbstratas/Funcionario.java), [`Professor.java`](09-classes-abstratas-e-interfaces/9.1-classes-abstratas-quando-usar/ClassesAbstratas/Professor.java) |
| 9.2 Interfaces | [`Interfaces`](09-classes-abstratas-e-interfaces/9.2-interfaces/Interfaces/) | [`Interfaces.java`](09-classes-abstratas-e-interfaces/9.2-interfaces/Interfaces/Interfaces.java) | [`Imprimivel.java`](09-classes-abstratas-e-interfaces/9.2-interfaces/Interfaces/Imprimivel.java), [`Notificavel.java`](09-classes-abstratas-e-interfaces/9.2-interfaces/Interfaces/Notificavel.java), [`Relatorio.java`](09-classes-abstratas-e-interfaces/9.2-interfaces/Interfaces/Relatorio.java) |

Trechos ilustrativos (`.txt`): [`erros-comuns.txt`](09-classes-abstratas-e-interfaces/erros-comuns/erros-comuns.txt)

### [10. Coleções e Estruturas de Dados](10-colecoes-e-estruturas-de-dados/)

| Seção | Exemplo | Programa principal | Classes |
|---|---|---|---|
| 10.1 Array vs ArrayList | [`UsandoArrayList`](10-colecoes-e-estruturas-de-dados/10.1-array-vs-arraylist/UsandoArrayList/) | [`UsandoArrayList.java`](10-colecoes-e-estruturas-de-dados/10.1-array-vs-arraylist/UsandoArrayList/UsandoArrayList.java) | [`Aluno.java`](10-colecoes-e-estruturas-de-dados/10.1-array-vs-arraylist/UsandoArrayList/Aluno.java) |
| 10.2 HashMap | [`UsandoHashMap`](10-colecoes-e-estruturas-de-dados/10.2-hashmap/UsandoHashMap/) | [`UsandoHashMap.java`](10-colecoes-e-estruturas-de-dados/10.2-hashmap/UsandoHashMap/UsandoHashMap.java) | (nenhuma) |

Trechos ilustrativos (`.txt`): [`erros-comuns.txt`](10-colecoes-e-estruturas-de-dados/erros-comuns/erros-comuns.txt)

### [11. Tratamento de Exceções](11-tratamento-de-excecoes/)

| Seção | Exemplo | Programa principal | Classes |
|---|---|---|---|
| 11.1 Hierarquia de exceções | [`TratamentoExcecoes`](11-tratamento-de-excecoes/11.1-hierarquia-de-excecoes/TratamentoExcecoes/) | [`TratamentoExcecoes.java`](11-tratamento-de-excecoes/11.1-hierarquia-de-excecoes/TratamentoExcecoes/TratamentoExcecoes.java) | (nenhuma) |
| 11.2 Exceções personalizadas | [`ExcecoesPersonalizadas`](11-tratamento-de-excecoes/11.2-excecoes-personalizadas/ExcecoesPersonalizadas/) | [`ExcecoesPersonalizadas.java`](11-tratamento-de-excecoes/11.2-excecoes-personalizadas/ExcecoesPersonalizadas/ExcecoesPersonalizadas.java) | [`Conta.java`](11-tratamento-de-excecoes/11.2-excecoes-personalizadas/ExcecoesPersonalizadas/Conta.java), [`SaldoInsuficienteException.java`](11-tratamento-de-excecoes/11.2-excecoes-personalizadas/ExcecoesPersonalizadas/SaldoInsuficienteException.java) |

Trechos ilustrativos (`.txt`): [`erros-comuns.txt`](11-tratamento-de-excecoes/erros-comuns/erros-comuns.txt)

### [12. Projeto Final — Aplicação Completa](12-projeto-final/)

| Seção | Exemplo | Programa principal | Classes |
|---|---|---|---|
| (sem seção numerada) | [`SistemaAcademico`](12-projeto-final/SistemaAcademico/) | [`SistemaAcademico.java`](12-projeto-final/SistemaAcademico/SistemaAcademico.java) | [`Aluno.java`](12-projeto-final/SistemaAcademico/Aluno.java), [`AlunoNaoEncontradoException.java`](12-projeto-final/SistemaAcademico/AlunoNaoEncontradoException.java), [`NotaInvalidaException.java`](12-projeto-final/SistemaAcademico/NotaInvalidaException.java), [`Pessoa.java`](12-projeto-final/SistemaAcademico/Pessoa.java), [`Relatorio.java`](12-projeto-final/SistemaAcademico/Relatorio.java), [`Turma.java`](12-projeto-final/SistemaAcademico/Turma.java) |

### [13. Boas Práticas e Convenções](13-boas-praticas-e-convencoes/)

Trechos ilustrativos (`.txt`): [`boas-praticas.txt`](13-boas-praticas-e-convencoes/13.2-boas-praticas-gerais/trechos/boas-praticas.txt)

### [14. Erros Comuns e Como Resolver](14-erros-comuns/)

Trechos ilustrativos (`.txt`): [`erros-de-logica.txt`](14-erros-comuns/trechos/erros-de-logica.txt)
