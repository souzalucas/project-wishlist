# language: pt 

	Funcionalidade: Gerenciamento da wishlist de um cliente
	
		O cliente irá usar as funcionalidades da wishlist
		
	Cenario: Adiciona um produto na wishlist do cliente
		Dado uma wishlist
		Quando eu adiciono um produto na wishlist
		Então eu recebo a wishlist com o novo produto adicionado
		
	Cenario: Tenta adicionar um produto que já existe na wishlist do cliente
		Dado uma wishlist com pelo menos um produto
		Quando eu tento adicionar um produto que já exista na wishlist
		Então eu recebo o status de erro 400 pois o produto já existe na lista

	Cenario: Tenta adicionar um produto em uma wishlist já cheia
		Dado uma wishlist com a capacidade máxima de 20 produtos e cheia
		Quando eu tento adicionar um produto novo na wishlist
		Então eu recebo o status de erro 400 pois a wishlist está cheia

	Cenario: Remove um produto de uma wishlist 
		Dado uma wishlist com um produto, pelo menos
		Quando eu tento remover um produto da wishlist
		Então eu recebo a wishlist, agora sem o produto
		
	Cenario: Consulta todos os produtos de uma wishlist 
		Dado uma wishlist com 5 produtos
		Quando eu tento consultar todos os produtos da wishlist
		Então eu recebo a lista dos 5 produtos que estão na wishlist
		
	Cenario: Consulta todos os produtos de uma wishlist vazia 
		Dado uma wishlist vazia
		Quando eu tento consultar todos os produtos dessa wishlist
		Então eu recebo uma lista vazia
		
	Cenario: Consulta se um determinado produto está na wishlist
		Dado uma wishlist com alguns produtos
		Quando eu tento consultar se um determinado produto está na wishlist
		Então O produto é encontrado na wishlist e eu recebo o status 200
		
	Cenario: Consulta um produto que não está na wishlist
		Dado uma wishlist com produtos adicionados
		Quando eu tento consultar se um produto está na wishlist
		Então O produto não é encontrado na wishlist e eu recebo o status 400
		