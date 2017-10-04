# ufjf-dcc171-2017-3-trb1-cbvreis
<h4>1. Identificação do aluno: nome, matrícula e curso.</h4>


	NOME: CÁSSIO HENRIQUE RESENDE REIS
	MATRICULA 201576041
	CURSO: SISTEMAS DE INFORMAÇÃO.



<h4>2. Texto explicando o cenário para o qual o sistema foi desenvolvido;</h4>

	Sistema foi construído para uma lanchonete que utiliza o mesmo formato que o subway, lanchonetes que possibilitam o cliente montar o sanduíche de acordo com seus gostos.
	O Cliente escolhe o modelo do sanduíche desejado e pode optar pela escolha do refrigerante.
	Os valores de cada componente do sanduíche compõem o preço final.
		As opções para o cliente são.
			3 Variedades de pão
			3 Variedades de bife
			3 Variedades de molho
			3 Varierades de Refrigerante.

	Há também a possibilidade de adicionar uma descrição ao pedido.
	O pedido, enfim, segue o exemplo abaixo.

			Pão: Pão preto
			Bife:  Bife de boi
			Molho:  Barbecue
			Refrigerante: Coca-Cola
			Descrição: Bife mal passado.

			Valor do Pedido R$ 9.80


<h4>3. Modelo de dados utilizado;</h4>
#Fazer
<h4>4. Levantamento dos campos necessários para a construção das telas;</h4>

	O sistema possui uma janela principal que é responsável pelo controle de pedidos.
	Na Janela Principal temos os componentes:

		Um painel que mostra a lista de Mesas;
		Um painel que mostra a lista de Pedidos feitos por cada mesa;
		Painel mostrando os ítens solicitados no pedido(JRaddioButton para o cliente escolher o pedido);
		O botão "Adicionar Mesa" que possibilita a adição de uma nova mesa no sistema;
		O botão "Excluir Mesa" que possibilita a exclusão de uma mesa que já esteja na lista;
		O botão "Fazer Pedido" que chama a janela de pedidos;
		O botão "Calcular Pagamento" que indica a prévia do sanduíche escolhido;
		O botão "Emitir Relatórios" que permite a exclusão de um ou mais itens em um pedido;

	Na janela de relatórios:
		O botão "Adicionar Mesa"
		botão "Adicionar Mesa"
		Área de Texto para impressão do relatório na tela


<h4>5. Descrição sucinta dos pontos importantes do funcionamento da interface;</h4>

	A interface permite o usuário do sistema criar uma ou mais mesas.
	Em cada mesa pode ter 0 ou N pedidos.
	Os pedidos possuem os estados Abertos e Fechados.
	Se o pedido estiver Aberto o Usuário consegue inserir os ítens, caso esteja fechado um popup de erro é disparado na tela.
	O usuário tem acesso a um relatório que traz todas as informações, inclusive o quanto foi faturado no dia.
	É permitido também excluir uma mesa
	Foi tratado os erros e em alguns pontos desabilitado botões para melhor experiência do usuário.


<h4>6. Discussão dos pontos que apresentaram maior dificuldade de implementação;</h4>

	Maior dificuldade na implementação foi a modelagem a ser utilizada.
	Para garantir que todos os ítens da requisição fossem entregues, diversas maneiras de pensar o problema foi tratado.
	O desenvolvimento da emissão do relatório foi o ponto crítico.


<h4>7. Pontos onde podem ser realizadas melhorias futuras.</h4>

	Inclusão de um Banco de Dados para emissões de relatórios mais profundos.
	Mudanças de layout e designe
	Adicionar Novos ítens ao cardapio.
	Adicionar a possibilidade de gestão do estoque de mercadorias.


ufjf-dcc171-2017-3-trb1-cbvreis created by GitHub Classroom
