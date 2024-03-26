package application;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import entities.Product;
import entities.Request;

public class Program {

	public static Map<String, Product> stock = new HashMap<>();
	public static Set<Request> requests = new HashSet<>();
	
	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in).useLocale(Locale.US);
		int opc;
		
		do {
			menu();
			opc = sc.nextInt();
			sc.nextLine();
			
			switch(opc) {
			case 1:
				addProduct(sc);
				break;
				
			case 2:
				viewStock(sc);
				break;
				
			case 3:
				changePrice(sc);
				break;
				
			case 4:
				makeRequest(sc);
				break;
				
			case 5:
				viewRequest(sc);
				break;
				
			case 6:
				System.out.println("Programa finalizado!");
				break;
				
			default:
				System.out.println("Invalid number");
				break;
				
			}
			
		}
		while(opc != 6);
		
		
		sc.close();
	}
	
	public static void menu() {
		System.out.println("\n1 - Add a product to stock");
		System.out.println("2 - View stock");
		System.out.println("3 - Update product price");
		System.out.println("4 - Make a request");
		System.out.println("5 - View request");
		System.out.println("6 - Exit");
		System.out.print("\nEnter an option: ");
	}
	
	public static void addProduct(Scanner sc) {
		System.out.print("Product name: ");
		String name = sc.nextLine();
		
		System.out.print("Quantity: ");
		Integer quantity = sc.nextInt();
		
		System.out.print("Price: ");
		Double price = sc.nextDouble();
		
		stock.put(name, new Product(name, quantity, price));
		System.out.println("Product added successfully!");
	}
	
	public static void viewStock(Scanner sc) {
		System.out.println("Products: ");
		for(String sk: stock.keySet()) {
			System.out.println("Name: " + sk + stock.get(sk).toString());
		}
	}
	
	public static void changePrice(Scanner sc) {
		System.out.print("Product name: ");
		String name = sc.nextLine();
		
		Product product = stock.get(name);
		if(product != null)	{
			System.out.print("New price: ");
			Double newPrice = sc.nextDouble();
			
			product.setPrice(newPrice);
		}
		else {
			System.out.println("Product haven't found");
		}
		
	}
	
	public static void makeRequest(Scanner sc) {
		System.out.print("How many itens you want: ");
		int hmitens = sc.nextInt();
		
		Request request = new Request();
		
		for(int i = 0; i < hmitens ; i++) {
			System.out.print("Product name #" + (i+1) + ": ");
			sc.nextLine();
			String name = sc.nextLine();
			if(stock.containsKey(name)) {
				System.out.print("Quantity: ");
				int quantity = sc.nextInt();
				
				if(quantity <= stock.get(name).getQuantity()) {
				
					Product product = stock.get(name);
					request.makeRequest(product, quantity);
				}
				else {
					System.out.println("Insufficiet quatity!");
					return;
				}
			}
			else {
				System.out.println("Product haven't found. Request canceled!");
				return;
			}
		}
		
		requests.add(request);
		System.out.println("Requested successfully! Your ID is #" + request.getId());
		
	}
	
	public static void viewRequest(Scanner sc) {
		System.out.println("Request list: ");
		
		for(Request request : requests) {
			System.out.println("ID: " + request.getId());
			System.out.println("Products: ");
			for(Product prod : request.getRequest().keySet()) {
				int quantity = request.getRequest().get(prod);
				System.out.println("-Name " + prod.getName() + "; Quantity: " + quantity
				+ "; Price: R$" + String.format("%.2f", prod.getPrice()));
			}
		}
		System.out.println();
	}
	
}
