package integ1.trab5.importBD.view;

import java.io.FileNotFoundException;

import integ1.trab5.importBD.controller.IOController;

public class ImportBD {
    
    public static String NOME_ARQUIVO_TEXTO_DEFAULT = "Egressos-para-Importar.txt";
    
    public static void main(String[] args) {
        
        System.out.println("==================== ImportBD =====================\n"
                + "Sistema de importação de dados para Bancos de dados\n");

        String arquivo = "";

        try
        {
            arquivo = IOController.recebeDados(args);
        }
        catch (IllegalArgumentException | FileNotFoundException e) {
            System.err.println("EXCEÇÃO: " + e.getLocalizedMessage());
        }
    }
}
