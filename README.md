<p id="simbol" align="center">
	<h1 align="center">SDSUtilityLib 1.0</h1>
</p>

O SDSUtilityLib é uma biblioteca que visa agilizar o desenvolvimento de softwares dos mais diversos tipos. Desse modo, a ferramenta apresentada tem por objetivo o intuito possibilitar que o desenvolvedor efetue o reaproveitamento de suas classes, interfaces e métodos em seus projetos tornando o processo de desenvolvimento menos demorado e custoso. Sendo assim, por meio desse empacotamento você pode economizar tempo à medida em que aumenta sua eficiência e produtividade ao se dedicar a trechos específicos de seus softwares.

## Estruturas
- Date: Classe responsável por comportar-se como data, sendo comparável, duplicável e atualizável, podendo verificar a sua própria validade, bem como de suas alterações de dia, mês e ano.
- AVLTree: Classe responsável por comportar-se como árvore AVL, sendo comparável, duplicável e gravável, podendo efetuar inserções personalizáveis a partir de seu instanciamento, bem como realizar um CRUD em suas entradas por meio da complexidade O(log n), ademais, para percorrer a estrutura podem ser usadas operações funcionais, expressões lambdas e o padrão de projeto comportamental Iterator.
- AbstractRequest: Classe responsável por comportar-se como requisição abstrata, sendo comparável e duplicável, podendo retornar em String o valor da requisição formatado no que se refere às suas pontuações de casa decimal.
- AccountingFlowRequisition: Classe responsável por comportar-se como requisição de fluxo contábil, sendo comparável e duplicável, podendo retornar em String o valor da requisição formatado no que se refere às suas pontuações de casa decimal, bem como garantir que todo valor retornado seja positivo ou negativo, tudo a depender de seu sinal de entrada.
- AccountingRecipeRequisition: Classe responsável por comportar-se como requisição de receita contábil, sendo comparável e duplicável, podendo retornar em String o valor da requisição formatado no que se refere às suas pontuações de casa decimal, bem como garantir que todo valor retornado seja positivo, independente de seu sinal de entrada.
- AccountingExpenseRequisition: Classe responsável por comportar-se como requisição de despesa contábil, sendo comparável e duplicável, podendo retornar em String o valor da requisição formatado no que se refere às suas pontuações de casa decimal, bem como garantir que todo valor retornado seja negativo, independente de seu sinal de entrada.
- AccountingFlow: Classe responsável por comportar-se como fluxo contábil, sendo comparável e duplicável, podendo retornar em String o valor do fluxo contábil formatado no que se refere às suas pontuações de casa decimal, bem como garantir que todo valor retornado seja positivo ou negativo, tudo a depender de seu sinal de entrada.
- AccountingRecipe: Classe responsável por comportar-se como receita contábil, sendo comparável e duplicável, podendo retornar em String o valor do fluxo contábil formatado no que se refere às suas pontuações de casa decimal, bem como garantir que todo valor retornado seja positivo, independente de seu sinal de entrada.
- AccountingExpense: Classe responsável por comportar-se como despesa contábil, sendo comparável e duplicável, podendo retornar em String o valor do fluxo contábil formatado no que se refere às suas pontuações de casa decimal, bem como garantir que todo valor retornado seja negativo, independente de seu sinal de entrada.

## Interfaces
- Comparator: Interface responsável por fornecer as assinaturas de métodos de um comparador nativo do java, porém com o diferencial de ser serializável.
- Converter: Interface responsável por fornecer a assinatura de objeto conversor, podendo converter valor inteiro e decimal para String formatada no que se refere às casas decimais dos respectivos valores, além de converter String formatada para valores: inteiro e decimal. Ademais, valores inteiros e decimais podem ser convertidos para positivo e negativo.
- Duplicable: Interface responsável por fornecer a assinatura de objeto duplicável.
- Factory: Classe responsável por fornecer métodos de instanciamento de objetos, podendo realizar o instanciamento de threads por meio de expressões lambdas, ademais as threads podem conter semáforos globais pré-definidos ou fornecidos pelo desenvolvedor e até mesmo, atuar sem semáforo algum.
- FileStream: Interface responsável por fornecer as assinaturas dos métodos de um arquivo em fluxo, podendo gravar e carregar em disco qualquer estrutura que seja implementada por ela.
- Filter: Interface responsável por fornecer a assinatura de objeto filtrante, podendo realizar a filtragem de strings, valores decimais, inteiros e datas inválidas.
- Modifier: Interface responsável por fornecer a assinatura de método de um modificador, tendo como característica a capacidade de receber um dado de entrada e retornar um outro dado de saída de tipos abstratos independentes.
- Receiver: Interface responsável por fornecer a assinatura de método de um receptor, tendo como característica a capacidade de receber um dado de entrada de tipo abstrato.
- Sender: Interface responsável por fornecer a assinatura de método de um remetente, tendo como característica a capacidade de retornar um dado de tipo abstrato.
- Worker: Interface responsável por fornecer a assinatura de método de um trabalhador, tendo como característica a capacidade de executar um método sem o uso de entradas ou saídas.

## Exceções
- EntryNotFoundException: Classe responsável por comportar-se como exceção de entrada não encontrada.
- InvalidDateException: Classe responsável por comportar-se como exceção de data inválida.
- InvalidDoubleException: Classe responsável por comportar-se como exceção de valor decimal inválido.
- InvalidIntegerException: Classe responsável por comportar-se como exceção de valor inteiro inválido.
- InvalidStringException: Classe responsável por comportar-se como exceção de String inválida.
- KeyUsedException: Classe responsável por comportar-se como exceção de chave em uso.

<p align="center"><em> Copyright © 2021. Everton Bruno Silva dos Santos. </em></p>
