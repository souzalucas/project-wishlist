package com.project.demo.steps;

import java.util.List;

import com.project.demo.support.api.ProductApi;
import com.project.demo.support.api.WishlistApi;
import com.project.demo.support.domain.Product;
import com.project.demo.support.domain.Wishlist;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class WishlistStepDefinitions {
	
	private Integer wishlistLimit;
	
	private WishlistApi wishlistApi;
	
	private ProductApi productApi;
	
	private Wishlist wishlist;
	
	private List<Product> products;
	
	private int statusCode;
		
	public WishlistStepDefinitions() {
		wishlistApi = new WishlistApi();
		productApi = new ProductApi();
	}
	
	@Dado("uma wishlist")
	public void dadoUmaWishlist() {
		wishlistLimit = Integer.valueOf(20);
		
		// Criando 1 wishlist
		wishlist = wishlistApi.createWishlist(20);
		
		// Criando 1 produto
		products = productApi.createProducts(1);
	}
	
	@Quando("eu adiciono um produto na wishlist")
	public void adicionarProdutoNaWishlist() {
		wishlist = wishlistApi.addProduct(wishlist, products.get(0));
	}
  
	@Então("eu recebo a wishlist com o novo produto adicionado")
	public void receboAWishlistComUmNovoProduto() {
		assertThat(wishlist.getProducts(), hasItem(hasProperty("id", is(products.get(0).getId()))));
	}
	
	@Dado("uma wishlist com pelo menos um produto")
	public void dadoUmaWishlistComPeloMenosUmProduto() {

		wishlist = wishlistApi.createWishlist(20);
		
		products = productApi.createProducts(1);
		
		// Adicionando 1 produto à wishlist
		wishlistApi.addProduct(wishlist, products.get(0));
	}
	
	@Quando("eu tento adicionar um produto que já exista na wishlist")
	public void adicionarProdutoExistenteNaWishlist() {
		statusCode = wishlistApi.addProductBadRequest(wishlist, products.get(0));
	}
  
	@Então("eu recebo o status de erro {int} pois o produto já existe na lista")
	public void receboStatusErro(int status) {
		assertThat(statusCode, is(status));
	}
	
	@Dado("uma wishlist com a capacidade máxima de {int} produtos e cheia")
	public void dadoWishlistCheia(int limit) {
		wishlistLimit = Integer.valueOf(limit);
		
		wishlist = wishlistApi.createWishlist(wishlistLimit);
		
		products = productApi.createProducts(wishlistLimit+1);
		
		// Adicionando <wishlistLimit> produtos à wishlist
		wishlistApi.addProducts(wishlist, wishlistLimit, products);
	}
	
	@Quando("eu tento adicionar um produto novo na wishlist")
	public void tentoAdicionarProdutoNovo() {
		statusCode = wishlistApi.addProductBadRequest(wishlist, products.get(wishlistLimit));
	}
	
	@Então("eu recebo o status de erro {int} pois a wishlist está cheia")
	public void receboStatusErroLimit(int status) {
		assertThat(statusCode, is(status));
	}
	
	@Dado("uma wishlist com um produto, pelo menos")
	public void dadoWishlistComUmProdutoPeloMenos() {
		wishlistLimit = Integer.valueOf(20);
		
		wishlist = wishlistApi.createWishlist(wishlistLimit);
		
		products = productApi.createProducts(1);
		
		wishlistApi.addProduct(wishlist, products.get(0));
	}
	
	@Quando("eu tento remover um produto da wishlist")
	public void tentoRemoverProduto() {
		wishlist = wishlistApi.removeProduct(wishlist, products.get(0));
	}
	
	@Então("eu recebo a wishlist, agora sem o produto")
	public void receboWishListSemProduto() {
		assertThat(wishlist.getProducts(), not(hasItem(hasProperty("id", is(products.get(0).getId())))));
	}
	
	@Dado("uma wishlist com {int} produtos")
	public void dadoWishlistComTantosProdutos(int numProducts) {
		wishlistLimit = Integer.valueOf(20);
		
		wishlist = wishlistApi.createWishlist(20);
		
		products = productApi.createProducts(numProducts);
		
		wishlistApi.addProducts(wishlist, numProducts, products);
	}
	
	@Quando("eu tento consultar todos os produtos da wishlist")
	public void tentoConsultarTodosProdutos() {
		products = wishlistApi.getAllProducts(wishlist);
	}
	
	@Então("eu recebo a lista dos {int} produtos que estão na wishlist")
	public void receboListaDosTantosProdutos(int numProdutos) {
		assertThat(products.size(), is(numProdutos));
	}
	
	@Dado("uma wishlist vazia")
	public void dadoWishlistVazia() {
		wishlistLimit = Integer.valueOf(20);
		
		wishlist = wishlistApi.createWishlist(20);
	}
	
	@Quando("eu tento consultar todos os produtos dessa wishlist")
	public void tentoConsultarTodosProdutosDessa() {
		products = wishlistApi.getAllProducts(wishlist);
	}
	
	@Então("eu recebo uma lista vazia")
	public void receboListaVazia() {
		assertThat(products.size(), is(0));
	}
	
	@Dado("uma wishlist com alguns produtos")
	public void dadoWishlistComALgunsProdutos() {
		wishlistLimit = Integer.valueOf(20);
		
		wishlist = wishlistApi.createWishlist(20);
		
		products = productApi.createProducts(3);
		
		wishlistApi.addProducts(wishlist, 3, products);
	}
	
	@Quando("eu tento consultar se um determinado produto está na wishlist")
	public void tentoConsultarProdutoNaWishlist() {
		statusCode = wishlistApi.getProduct(wishlist, products.get(0));
	}
	
	@Então("O produto é encontrado na wishlist e eu recebo o status {int}")
	public void produtoEncontrado(int status) {
		assertThat(statusCode, is(status));
	}
	
	@Dado("uma wishlist com produtos adicionados")
	public void dadoWishlistComALgunsProdutosAdc() {
		wishlistLimit = Integer.valueOf(20);
		
		wishlist = wishlistApi.createWishlist(20);
		
		products = productApi.createProducts(4);
		
		wishlistApi.addProducts(wishlist, 3, products);
	}
	
	@Quando("eu tento consultar se um produto está na wishlist")
	public void tentoConsultarSeProdutoNaWishlist() {
		statusCode = wishlistApi.getProductBadRequest(wishlist, products.get(3));
	}
	
	@Então("O produto não é encontrado na wishlist e eu recebo o status {int}")
	public void produtoNaoEncontrado(int status) {
		assertThat(statusCode, is(status));
	}
}
