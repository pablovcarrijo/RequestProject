package entities;

import java.util.HashMap;
import java.util.Map;

public class Request {

	private static int contadorPedido = 0;
	private int id;
	private Map<Product, Integer> request;
	
	public Request() {
		this.id = ++contadorPedido;
		this.request = new HashMap<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Map<Product, Integer> getRequest() {
		return request;
	}

	public void makeRequest(Product product, int quantity) {
		request.put(product, quantity);
	}
	
}
