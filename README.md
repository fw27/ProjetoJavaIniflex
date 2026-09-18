# projeto java iniflex - teste tecnico

opa, tudo bem? esse aqui e o projeto que desenvolvi pro teste pratico de desenvolvedor java da iniflex!

resolvi fazer em java puro (sem frameworks ou gerenciadores pesados como maven/gradle) pra ficar bem leve e facil de qualquer pessoa abrir, compilar e rodar em qualquer maquina usando apenas o jdk padrao.

## estrutura do projeto

o codigo ta todo organizadinho dentro do pacote `br.com.iniflex` na pasta `src/`:

- `src/br/com/iniflex/Pessoa.java`: classe base com os atributos `nome` e `dataNascimento`, construtor, encapsulamento e o calculo de idade em anos completos usando a api java.time (`Period.between`).
- `src/br/com/iniflex/Funcionario.java`: classe que estende `Pessoa`, trazendo `salario` (usando `BigDecimal` pra garantir precisao financeira) e `funcao`, alem dos formatadores de data e moeda no padrao pt-br.
- `src/br/com/iniflex/Principal.java`: classe executavel com menu interativo no terminal para testar cada acao individualmente ou executar o fluxo completo.

## requisitos implementados

- 3.1: inseri todos os funcionarios da tabela exatamente na mesma ordem informada;
- 3.2: removi o funcionario "joao" da lista usando `removeIf`;
- 3.3: exibi todos os funcionarios com data formatada em `dd/mm/aaaa` e valor numerico com separador de milhar como ponto e decimal como virgula;
- 3.4: apliquei o reajuste salarial de 10% pra todos os funcionarios e atualizei a lista;
- 3.5: agrupei os funcionarios por funcao em um `Map<String, List<Funcionario>>` usando `Collectors.groupingBy`;
- 3.6: imprimi os funcionarios agrupados pelas suas respectivas funcoes a partir do map gerado no item 3.5;
- 3.8: filtrei e imprimi os funcionarios que fazem aniversario nos meses 10 (outubro) e 12 (dezembro);
- 3.9: localizei o funcionario mais velho (com maior idade), exibindo seu nome e a idade calculada;
- 3.10: ordenei e imprimi a lista em ordem alfabetica pelo nome;
- 3.11: somei o valor total de todos os salarios dos funcionarios usando `reduce` e exibi formatado;
- 3.12: calculei quantos salarios minimos cada um ganha, considerando a base de r$ 1.212,00 e arredondamento pra 2 casas decimais.

## como compilar e rodar

como e java puro, da pra compilar e rodar direto pela linha de comando bem simples:

### 1. compilando:

na raiz do projeto (onde fica a pasta `src`), execute:

```bash
javac -encoding UTF-8 -d bin src/br/com/iniflex/*.java
```

isso vai gerar as classes compiladas dentro da pasta `bin/`.

### 2. rodando:

apos compilar, execute:

```bash
java -cp bin br.com.iniflex.Principal
```

o programa vai abrir um menu interativo no proprio terminal onde voce pode escolher qual requisito quer testar individualmente (opcoes de 1 a 11), rodar o fluxo completo de uma vez (opcao 12) ou sair (opcao 0).

muito obrigado pela oportunidade de participar desse processo seletivo!
