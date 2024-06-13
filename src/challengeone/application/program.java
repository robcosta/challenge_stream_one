package challengeone.application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import challengeone.entities.Sale;

public class program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Entre o caminho da arquivo: ");
		String path = sc.nextLine();
		
		try(BufferedReader br = new BufferedReader(new FileReader(path))){
			
			List<Sale> sales = new ArrayList<>();
			
			String line = br.readLine();
			while(line != null) {
				String[] fields= line.split(",");
				Sale sale = new Sale(Integer.valueOf(fields[0]),Integer.valueOf(fields[1]),fields[2], Integer.valueOf(fields[3]) ,Double.valueOf(fields[4]));
				sales.add(sale);
				line = br.readLine();
			}
			
			System.out.println("\nCinco primeiras vendas de 2016 de maior preço médio");
			sales.stream()
				.filter(s -> s.getYear() == 2016)
				.sorted((s1, s2) -> s2.averagePrice().compareTo(s1.averagePrice()))
				.limit(5)
				.forEach(System.out::println);
			
			Double amount = sales.stream()
					.filter(s -> (s.getSeller().equals("Logan") && (s.getMonth() == 1 || s.getMonth() == 7)))
					.map(s -> s.getTotal())
					.reduce(0.0, (x,y) -> x + y);				
			
			System.out.println("\nValor total vendido pelo vendedor Logan nos meses 1 e 7 = " + String.format("%.2f", amount));

	
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		sc.close();
	}
}
