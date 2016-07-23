package integ1.trab5.importBD.view;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class ImportBD {

	public static void main (String[] args) {

		System.out.println("==================== ImportBD =====================\n"
		                 + "Sistema de importação de dados para Bancos de dados");

		try {
			
		} catch(Exception e) {
			System.out.println("EXCECAO: " + e.getMesssage());
		}
	}
	
	private static void recebeDados(String[] args) throws IllegalArgumentException, FileNotFoundException {
		System.out.println(args[0]);
		if (args.length == 1) {
			
			String content = new Scanner(new File(args[0])).useDelimiter("\\Z").next();
			System.out.println(content);
		}
		else {
			throw new IllegalArgumentException("Nenhum arquivo foi passado como "
							 + "parâmetro para o programa");
		}
	}
}