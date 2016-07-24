package integ1.trab5.importBD.service;

import integ1.trab5.importBD.model.DadosImportados;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Serviço conversor do arquivo de importação do SempreUFG num objeto estruturado.
 *
 * @author gustavosotnas
 */
public class ImportService {
    
    /** Internal Field Separator (IFS) */
    private static final String IFS = "\\\\"; // regex "\"
    
    public static DadosImportados convertCSVtoObject(String csvFileContent) {
        // parseCSV(csvFileContent);
        return null;
    }
    
    /**
     * Converte uma string no formato "CSV-like" para uma "pseudo-matriz"
     * (ArrayList de array de strings).
     * 
     * Código baseado em:
     * http://www.mkyong.com/java/how-to-read-and-parse-csv-file-in-java/
     * http://stackoverflow.com/a/1096628
     * http://stackoverflow.com/a/11769570
     * 
     * @param csvFileContent o conteúdo do arquivo no formato "CSV-like"
     * @return o conteúdo no formato de estrutura de dados
     * @throws IOException quando os descriptors falham
     */
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
