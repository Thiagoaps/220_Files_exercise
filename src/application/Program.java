package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Product;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		List<Product> products = new ArrayList<>();
		
		System.out.println("Enter the file path: ");
		String path = sc.nextLine();
		
		File filePath = new File(path);
		
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))){
			String line = br.readLine();
			while(line != null) {
				String[] fields = line.split(",");
				String name = fields[0];
				double price = Double.parseDouble(fields[1]);
				int quantity = Integer.parseInt(fields[2]);				
				products.add(new Product(name, price, quantity));				
				line = br.readLine();							
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String newPath = "c:\\temp";
		boolean success = new File(newPath + "\\out").mkdir();
		System.out.println("Directory created successfully: " + success);
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("c:\\temp\\out\\summary.csv"))){
			
			for (Product p : products) {
				bw.write(p.getName() + ", " + p.totalValue());
				bw.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		System.out.println("PRODUCTS WITH TOTAL VALUE:");
		for (Product p : products) {
			System.out.println(p);
		}
				
		sc.close();
	}
}
