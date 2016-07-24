package integ1.trab5.importBD.view;

import java.io.IOException;

import integ1.trab5.importBD.controller.IOController;
import java.util.ArrayList;
import java.util.Scanner;

public class ImportBD {
    
    public static String NOME_ARQUIVO_TEXTO_DEFAULT = "Egressos-para-Importar.txt";
    /** Internal Field Separator (IFS) */
    private static final String IFS = "\\\\"; // regex "\"
    
    public static void main(String[] args) {
        
        System.out.println("==================== ImportBD =====================\n"
                + "Sistema de importação de dados para Bancos de dados\n");

        String arquivo = "";

        try
        {
            arquivo = IOController.recebeDados(args);
            IOController.validaConsistenciaDados(arquivo);
            
            ArrayList<String[]> registros = parseCSV(arquivo);

            for (int i=0; i<registros.size(); i++) {
                for (int j=0; j<registros.get(i).length; j++) {
                    System.out.println(registros.get(i)[j]);
                }
                System.out.println(); 
            }
        }
        catch (IllegalArgumentException | IOException e) {
            System.err.println("EXCEÇÃO: " + e.getLocalizedMessage());
        }
    }
    
    private static ArrayList<String[]> parseCSV(String csvFileContent) throws IOException {

        Scanner scanner = new Scanner(csvFileContent);
        String line = "";
        ArrayList<String[]> result = new ArrayList<>();
        
        while (scanner.hasNextLine()) {

            line = scanner.nextLine();
            String[] registro = line.split(IFS);
            result.add(registro);
        }
        scanner.close();
        return result;
    }
}
