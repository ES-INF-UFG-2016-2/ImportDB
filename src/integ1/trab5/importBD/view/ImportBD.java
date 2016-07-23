package integ1.trab5.importBD.view;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class ImportBD {
    
    public static void main(String[] args) {
        
        System.out.println("==================== ImportBD =====================\n"
                + "Sistema de importação de dados para Bancos de dados");
        
        try {
            recebeDados(args);
        }
        catch (IllegalArgumentException | FileNotFoundException e) {
            System.err.println("EXCEÇÃO: " + e.getLocalizedMessage());
        }
    }
    
    private static void recebeDados(String[] args) throws IllegalArgumentException, FileNotFoundException {
        if (args.length == 1) {
            System.out.println("Arquivo:" + args[0]);
            String content = new Scanner(new File(args[0])).useDelimiter("\\Z").next();
            System.out.println(content);
        }
        else {
            throw new IllegalArgumentException("Nenhum arquivo foi passado como "
                    + "parâmetro para o programa");
        }
    }
}
