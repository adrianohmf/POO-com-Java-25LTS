[Índice](../../README.md)

# Projeto da 2ª Unidade de POO

Disciplina de Programação Orientada a Objetos com Java 25 LTS, turmas IPI e TSI, IFPE. Os alunos constroem em dupla um sistema de reservas de espaços do campus, usando o conteúdo visto até o capítulo 13. Os capítulos 14 e 15 são desejados e valem pontos extras.

## Objetivo e o que será avaliado

Cada equipe entrega um programa Java de linha de comando que gerencia reservas de recursos do campus, aplicando o conteúdo do guia até o capítulo 13, Pacotes e Imports. Os capítulos 14 (datas e horas com java.time) e 15 (leitura e escrita de arquivos) são desejados e valem pontos extras.

- **Valor do projeto:** 10,0 pontos, distribuídos conforme a rubrica da seção de critérios.
- **Linguagem e ferramentas:** Java 25 LTS, apenas a biblioteca padrão. Não é permitido usar frameworks, banco de dados, interface gráfica nem bibliotecas externas no núcleo do projeto. A única exceção é o frontend opcional do bônus B6.
- **Consulta:** o guia da disciplina pode ser consultado durante o desenvolvimento.
- **Fora do escopo:** tudo o que vem depois do capítulo 15, como API REST e páginas web. Só o bônus B6 (frontend opcional) usa esse conteúdo.

## Conteúdo avaliado: capítulos 1 a 13, com 14 e 15 desejados

Cada capítulo tem um lugar definido no projeto, de modo que nenhum conteúdo fica de fora. Os capítulos 14 e 15 são desejados: não são obrigatórios e valem pontos extras.

| Capítulo | Conceito | Onde aparece no projeto |
|---|---|---|
| 1 | Tipos, `Scanner`, `switch`, laços, arrays, `String.format` | Menu em laço, leitura e validação de entradas, saída formatada |
| 2 e 3 | Conceitos de POO, classes e objetos | Classes do domínio e múltiplos objetos em execução |
| 4 | Atributos, métodos, sobrecarga | Pelo menos um método sobrecarregado, por exemplo listar por data e listar por data e recurso |
| 5 | Construtores e `record` | Construtores que validam; um `record` para o resumo de ocupação de cada recurso |
| 6 | Encapsulamento | Atributos `private`, getters, nenhum setter desnecessário |
| 7 | Herança | Três subclasses do recurso reservável |
| 8 | Polimorfismo | Regras de duração máxima e horário de funcionamento sobrescritas em cada subclasse; lista tratada pelo tipo da superclasse |
| 9 | Classe abstrata e interface | Superclasse abstrata e uma interface de exportação para CSV |
| 10 | `ArrayList` | Lista de reservas |
| 11 | `HashSet` e `HashMap` | Conjunto de solicitantes distintos; recursos indexados por código; total de horas por recurso |
| 12 | Exceções | Exceções personalizadas verificadas e `try`/`catch` no menu |
| 13 | Pacotes e imports | Código dividido em pacotes por responsabilidade |
| 14 | `java.time` (desejado) | `LocalDate`, `LocalTime`, `Duration`, `ChronoUnit`, `DateTimeFormatter`; datas inexistentes e passadas recusadas, antecedência mínima por tipo, `hoje` como parâmetro |
| 15 | Arquivos (desejado) | `Path`, `Files`, CSV, `try-with-resources`, UTF-8; dados gravados ao sair e recarregados ao abrir |

## O desafio: Sistema de Reservas do Campus

O campus tem recursos que várias pessoas disputam: laboratórios, equipamentos, quadras. Hoje as reservas são feitas em papel ou por mensagem, e acontecem choques de horário. O desafio é criar um programa que registre os recursos, aceite reservas, recuse as que violam as regras e mostre a agenda do dia. Quem fizer o capítulo 15 ainda faz com que o programa **lembre de tudo** quando for fechado e aberto de novo.

Para evitar projetos iguais, cada dupla escolhe um dos dez domínios abaixo. As regras e os requisitos são os mesmos; mudam os nomes das classes e os valores dos limites.

| Domínio | Superclasse abstrata | Três subclasses sugeridas |
|---|---|---|
| Espaços do campus | `Espaco` | `Laboratorio`, `SalaDeAula`, `Auditorio` |
| Equipamentos | `Equipamento` | `Projetor`, `KitDeNotebooks`, `KitDeRobotica` |
| Áreas esportivas | `AreaEsportiva` | `Quadra`, `CampoDeFutebol`, `SalaDeGinastica` |
| Biblioteca | `Ambiente` | `SalaDeEstudo`, `CabineIndividual`, `SalaDeReuniao` |
| Oficinas e ateliês | `Oficina` | `AtelieDeArtesanato`, `LaboratorioMaker`, `CozinhaExperimental` |
| Veículos do campus | `Veiculo` | `Carro`, `Van`, `Onibus` |
| Estúdios e audiovisual | `RecursoAudiovisual` | `EstudioDeGravacao`, `SalaDeEdicao`, `KitDeFilmagem` |
| Coworking e inovação | `AmbienteDeInovacao` | `Coworking`, `SalaDeBrainstorm`, `SalaDePrototipagem` |
| Eventos e cerimônias | `LocalDeEvento` | `PatioCoberto`, `Anfiteatro`, `SalaDeExposicao` |
| Horta e agroecologia | `AreaDeCultivo` | `Horta`, `Estufa`, `Viveiro` |

O restante deste enunciado usa o domínio **Espaços do campus** como exemplo. Cada equipe troca `Espaco` e as subclasses pelos nomes do seu domínio.

## Regras de negócio

O programa deve recusar qualquer reserva que viole uma destas regras, explicando o motivo ao usuário, e nunca pode encerrar com erro por causa de uma entrada inválida.

1. **RN1, recurso:** tem código único (por exemplo `LAB01`), nome e capacidade maior que zero.
2. **RN2, reserva:** tem número, recurso, solicitante, data, horário de início, horário de fim e finalidade.
3. **RN3, horário:** o fim deve ser depois do início, no mesmo dia. Os horários são digitados como HH:mm e guardados em minutos desde a meia-noite.
4. **RN4, data:** digitada no formato `dd/MM/yyyy` e guardada como texto. O programa confere o formato com `split` e `Integer.parseInt` dentro de `try`/`catch`: dia de 1 a 31 e mês de 1 a 12. Desejado (capítulo 14): rejeitar datas que não existem, como `31/02/2026` (use `ResolverStyle.STRICT`), e datas que já passaram.
5. **RN5, conflito:** duas reservas do mesmo recurso, no mesmo dia, não podem se sobrepor. Elas se sobrepõem quando o início de uma é antes do fim da outra e vice-versa. Uma terminar às 16:00 e a outra começar às 16:00 **não** é conflito.
6. **RN6, limites por tipo:** cada subclasse define duração máxima e horário de funcionamento (abertura e fechamento), conforme a tabela abaixo. A reserva precisa começar e terminar dentro desse horário. Essas regras devem ser implementadas por **polimorfismo**, sem `if` ou `switch` sobre o tipo.
7. **RN7, cancelamento:** a reserva é cancelada pelo número. Os números continuam crescendo após reiniciar o programa (o próximo número é o maior existente mais um).
8. **RN8, persistência (desejada, capítulo 15):** ao sair, o programa grava os recursos e as reservas em CSV. Ao iniciar, carrega os arquivos. Na primeira execução os arquivos não existem, e isso não é erro. Linhas em branco são ignoradas. Sem esta regra, os dados ficam só na memória e se perdem ao fechar o programa; a demonstração pode usar alguns recursos pré-cadastrados no código.
9. **RN9, consultas:** agenda de um dia, total de horas reservadas por recurso e quantidade de solicitantes diferentes.

Limites do domínio de exemplo. Cada equipe define três pares de duração máxima e horário de funcionamento com valores diferentes entre si:

| Tipo | Duração máxima | Funcionamento |
|---|---|---|
| `Laboratorio` | 4 horas | 08:00 às 22:00 |
| `SalaDeAula` | 4 horas | 07:00 às 21:00 |
| `Auditorio` | 8 horas | 08:00 às 18:00 |

**Desejado (capítulo 14):** antecedência mínima por tipo, também por polimorfismo, com o método `antecedenciaMinimaDias()`: `Laboratorio` 1 dia, `SalaDeAula` 0 dia (pode ser reservada para hoje) e `Auditorio` 3 dias. Cada equipe define valores diferentes entre si.

## Requisitos técnicos obrigatórios

O código precisa mostrar, de forma verificável, que cada tópico foi dominado. RT1 a RT10 e RT14 são obrigatórios; RT11, RT12 e RT13 (capítulos 14 e 15) são desejados e valem pontos extras.

| Código | Requisito | Cap. |
|---|---|---|
| RT1 | Menu em laço com `switch`, leitura de dados pelo teclado e entradas inválidas tratadas sem derrubar o programa | 1 |
| RT2 | Todos os atributos `private`, objetos sempre válidos desde o construtor | 5, 6 |
| RT3 | Superclasse **abstrata** com pelo menos dois métodos abstratos e três subclasses concretas | 7, 9 |
| RT4 | Polimorfismo: os limites de duração e de horário de funcionamento vêm de métodos sobrescritos, e o gerenciador só enxerga o tipo da superclasse | 8 |
| RT5 | Uma **interface** (por exemplo `Exportavel`, com `paraCsv()`) implementada pelo recurso e pela reserva | 9 |
| RT6 | Um método **sobrecarregado** e pelo menos um `record` | 4, 5 |
| RT7 | `ArrayList` para as reservas e `HashSet` para os solicitantes distintos | 11 |
| RT8 | `HashMap` com os recursos por código e outro `HashMap` com o total de horas por recurso | 11 |
| RT9 | Pelo menos duas exceções **personalizadas** (verificadas), lançadas nas regras e tratadas no menu | 12 |
| RT10 | Quatro pacotes: `modelo`, `excecao`, `servico` e `app`; o pacote `persistencia` é desejado, com o capítulo 15 | 13 |
| RT11 | **Desejado:** `LocalDate`, `LocalTime`, `Duration` e `DateTimeFormatter`; `hoje` recebido como parâmetro nos métodos de regra, para o código ser testável | 14 |
| RT12 | **Desejado:** persistência em dois arquivos CSV com `Path` e `Files`, e leitura com `try-with-resources` ou `readAllLines` | 15 |
| RT13 | **Desejado:** arquivos em UTF-8, com nomes e finalidades acentuados voltando corretos após reiniciar | 15 |
| RT14 | Classe `App` com `main` clássico (necessário dentro de pacotes) e projeto que compila e roda com o JDK 25 | 13 |

## Modelo sugerido de pacotes e classes

A estrutura abaixo é uma sugestão testada em um protótipo. A equipe pode acrescentar classes, mas não pode juntar tudo em um único arquivo.

| Pacote | Classe | Responsabilidade |
|---|---|---|
| `modelo` | `Espaco` (abstrata) | Código, nome, capacidade; métodos abstratos `tipo()`, `duracaoMaximaMinutos()`, `aberturaMinutos()` e `fechamentoMinutos()`; desejado: `antecedenciaMinimaDias()` |
| `modelo` | `Laboratorio`, `SalaDeAula`, `Auditorio` | Cada uma sobrescreve os métodos abstratos com seus limites |
| `modelo` | `Reserva` | Dados da reserva (data como texto, horários em minutos); `duracaoMinutos()`; `conflitaCom(Reserva outra)` |
| `modelo` | `Exportavel` (interface) | `String paraCsv()` |
| `excecao` | `ConflitoDeHorarioException`, `RegraDeReservaException` | Erros de negócio, ambas estendem `Exception` |
| `servico` | `GerenciadorReservas` | Guarda o `HashMap` de recursos e a `ArrayList` de reservas; `reservar`, `cancelar`, consultas |
| `persistencia` | `RepositorioCsv` | Desejado (capítulo 15): salva e carrega os dois arquivos; devolve listas de objetos |
| `app` | `App` | `main`, menu e conversa com o usuário; não contém regras de negócio |

Dica: o método `conflitaCom` é o coração do projeto. Escreva-o primeiro e teste-o com horários que se sobrepõem, que apenas encostam e que ficam em dias diferentes antes de fazer o menu.

## Arquivos CSV (desejado) e exemplo de execução

Para quem fizer o capítulo 15, os dados ficam na pasta `dados/`, em dois arquivos de texto com campos separados por ponto e vírgula. As datas e horas são gravadas no formato padrão ISO (`2026-11-12`, `14:00`) e mostradas ao usuário como `dd/MM/yyyy`.

Arquivo `dados/espacos.csv`, com o tipo na primeira coluna:

```
Laboratorio;LAB01;Laboratório de Redes;30
SalaDeAula;S12;Sala 12;40
Auditorio;AUD01;Auditório Central;200
```

Arquivo `dados/reservas.csv`:

```
1;LAB01;Profa. Ana Souza;2026-11-12;14:00;16:00;Aula prática de Redes
```

Ao ler, o primeiro campo de `espacos.csv` decide qual subclasse criar. Nenhum texto pode conter `;`, e a equipe deve rejeitar esse caractere na digitação.

Trecho de uma execução, com a data de hoje fixada em 10/11/2026 e o laboratório `LAB01` já cadastrado. A redação exata das mensagens é livre; o que importa é o comportamento. Este trecho foi produzido por um protótipo testado.

```
Espaços: 3 | Reservas: 0

1. Cadastrar espaço  2. Reservar  3. Cancelar  4. Agenda do dia  5. Sair
Opção: 2
Espaço: LAB01
Solicitante: Profa. Ana Souza
Data (dd/MM/yyyy): 12/11/2026
Início (HH:mm): 14:00
Fim (HH:mm): 16:00
Finalidade: Aula prática de Redes
Reserva confirmada: #1 Laboratório de Redes | 12/11/2026 14:00-16:00 | Profa. Ana Souza | Aula prática de Redes

Opção: 2
Espaço: LAB01
Solicitante: Prof. Carlos Lima
Data (dd/MM/yyyy): 12/11/2026
Início (HH:mm): 15:00
Fim (HH:mm): 17:00
Finalidade: Prova
Não foi possível: Conflito com a reserva #1 Laboratório de Redes | 12/11/2026 14:00-16:00 | Profa. Ana Souza | Aula prática de Redes

Opção: 4
Data (dd/MM/yyyy): 12/11/2026
  #1 Laboratório de Redes | 12/11/2026 14:00-16:00 | Profa. Ana Souza | Aula prática de Redes
```

## Pontos extras: conteúdo desejado e desafios bônus

Quem concluir o núcleo do projeto pode ganhar até **3,0 pontos extras no total**, sem ultrapassar 10,0. D1 e D2 (conteúdo desejado dos capítulos 14 e 15) valem até 1,0 cada, os bônus B1 a B5 valem 0,25 cada (até 1,0, e usam o conteúdo dos capítulos 14 e 15) e o frontend opcional, B6, vale até 1,0. Cada extra só conta se estiver funcionando e explicado no README.

| Extra | Descrição | Conteúdo usado |
|---|---|---|
| D1 | Datas e horas com `java.time` (capítulo 14): `LocalDate`, `LocalTime`, `Duration` e `DateTimeFormatter` no lugar de texto e minutos; datas inexistentes e já passadas recusadas; antecedência mínima por tipo; `hoje` recebido como parâmetro nos métodos de regra. Vale até 1,0 ponto. | Capítulo 14 (RT11) |
| D2 | Persistência em arquivos (capítulo 15): recursos e reservas gravados em dois CSV com `Path` e `Files`, em UTF-8, e carregados ao iniciar; primeira execução sem arquivos não dá erro (RN8). Vale até 1,0 ponto. | Capítulo 15 (RT12 e RT13) |
| B1 | Relatório mensal gravado em `relatorios/2026-11.txt`, com horas reservadas por recurso e a taxa de ocupação | `Files.write`, `Duration`, `HashMap` |
| B2 | Importação de um CSV de reservas que ignora linhas inválidas e mostra quantas foram descartadas | Exceções, `split`, exercício 15.4 |
| B3 | Reserva semanal repetida por N semanas, valendo tudo ou nada: se uma semana tiver conflito, nenhuma é criada | `plusWeeks`, exceções |
| B4 | Horários livres de um recurso em um dia, entre 08:00 e 22:00 | `LocalTime`, `Duration`, laços |
| B5 | Arquivo `log.txt` que registra data, hora e a operação feita a cada execução | `LocalDateTime`, `StandardOpenOption.APPEND` |
| B6 | Frontend opcional: página HTML com JavaScript que mostra a agenda do dia e cria reservas, consumindo uma API feita com `HttpServer` sobre as mesmas classes do sistema. Vale até 1,0 ponto. | Capítulo 16 do guia (API REST e frontend) |

## Entrega, equipes e integridade acadêmica

- **Equipes:** duplas. Os dois integrantes programam e fazem commits no repositório, e os dois respondem na arguição. Trabalho individual só com autorização do professor. Cada dupla escolhe o seu domínio.
- **O que entregar:** o repositório Git da dupla no GitHub, com a pasta `src` com os pacotes, a pasta `dados` com arquivos de exemplo (somente se fizer o capítulo 15) e um `README.md`. O `.gitignore` deve excluir os arquivos `.class` e a pasta `out`.
- **Como entregar:** no Google Classroom, na atividade do projeto, envie o link do repositório (um envio por dupla). O repositório deve ser público ou, se for privado, ter o professor adicionado como colaborador. Vale o conteúdo do repositório na data limite, então não faça commits depois do prazo.
- **Commits:** todo o código deve ser enviado ao GitHub com `git push`, em commits feitos por cada integrante com a própria conta. Será analisado o número de commits aceitos de cada um da dupla, e a nota de um integrante pode ser reduzida se ele quase não tiver commits ou se o trabalho tiver sido enviado de uma só vez, no fim.
- **README.md:** nome dos integrantes, domínio escolhido, como compilar e executar, e uma lista curta de decisões de projeto. Se usar ferramentas de IA como apoio, declare quais e para quê.
- **Compilação:** o professor clona o repositório e compila com `javac -d out $(find src -name '*.java')` e executa `br.edu.ifpe.reservas.app.App`, como no capítulo 13. Ajuste o nome do pacote ao da sua equipe, se mudar.
- **Datas de entrega e de arguição:** a definir pelo professor.
- **Integridade:** o projeto é da equipe. Código muito semelhante entre equipes, ou que os integrantes não consigam explicar na arguição, não conta como produção própria e tem a nota reduzida nos itens afetados.

## Rubrica de avaliação

O projeto vale 10,0 pontos. Cada critério é avaliado de zero até o valor máximo, conforme o que o código realmente faz e o que a equipe consegue explicar.

| Critério | Pontos | Nível pleno |
|---|---|---|
| Funcionamento e regras de negócio (RN1 a RN7 e RN9; a RN8 é desejada) | 3,0 | Todas as regras obrigatórias funcionam na demonstração; entradas inválidas nunca derrubam o programa |
| Modelagem orientada a objetos (RT2 a RT6) | 2,5 | Encapsulamento correto, herança com classe abstrata, polimorfismo nos limites, interface implementada, sobrecarga e `record` |
| Coleções e mapas (RT7 e RT8) | 1,0 | `ArrayList`, `HashSet` e `HashMap` usados onde fazem sentido, sem busca manual desnecessária |
| Exceções (RT9) | 1,0 | Exceções personalizadas lançadas nas regras e tratadas apenas no menu, com mensagem clara |
| Pacotes, organização e qualidade do código (RT10 e RT14) | 1,0 | Pacotes por responsabilidade, nomes claros, métodos curtos, sem código repetido, regras fora da classe `App` |
| README, repositório Git e arguição oral | 1,5 | Instruções claras, repositório no GitHub com commits aceitos dos dois integrantes e respostas seguras sobre o próprio código |
| **Total** | **10,0** |  |

Ajustes na nota:

- Projeto que não compila recebe no máximo 3,0.
- Uso de biblioteca externa, framework ou banco de dados desconta 1,0.
- Os pontos extras (D1, D2, B1 a B5 e B6) somam até 3,0 e a nota final não passa de 10,0.

## Arguição oral e roteiro de desenvolvimento

Cada equipe apresenta o programa funcionando e responde a perguntas por cerca de 10 minutos. O professor escolhe uma pergunta para cada integrante, e quem não souber explicar o próprio código perde os pontos do item correspondente.

Perguntas de exemplo:

1. Por que `Espaco` é abstrata? O que acontece se você tentar fazer `new Espaco(...)`? (capítulo 9)
2. Onde está o polimorfismo? Mostre uma linha que chama um método sem saber o tipo exato do objeto. (capítulo 8)
3. Por que `conflitaCom` compara o início de uma reserva com o fim da outra, nos dois lados? O que muda se trocar `<` por `<=`?
4. Por que usar `HashMap` para os recursos em vez de percorrer uma lista? (capítulo 11)
5. Por que as exceções são tratadas no `App` e não dentro de `reservar`? (capítulo 12)
6. Desejado (capítulo 15): o que acontece se o arquivo tiver uma linha em branco? E se a pasta `dados` não existir?
7. Desejado (capítulo 14): por que `hoje` entra como parâmetro em vez de `LocalDate.now()` dentro do método?

Roteiro sugerido, em etapas que podem ser distribuídas no tempo disponível:

1. **Modelo:** `Espaco`, as três subclasses, `Reserva` e `conflitaCom`, testados em um `main` provisório.
2. **Regras e exceções:** `GerenciadorReservas.reservar` com todas as validações e as duas exceções.
3. **Coleções e consultas:** agenda do dia, total de horas por recurso, solicitantes distintos.
4. **Persistência (desejado, capítulo 15):** `RepositorioCsv` salvando e carregando, com os acentos conferidos.
5. **Menu:** classe `App` com o laço, as leituras e as mensagens de erro.
6. **Fechamento:** conferência das regras com a demonstração, README, repositório no GitHub entregue no Classroom e, se houver tempo, os bônus.

---

[Índice](../../README.md)
