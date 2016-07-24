package integ1.trab5.importBD.view;

import java.io.IOException;

import integ1.trab5.importBD.controller.IOController;
import integ1.trab5.importBD.service.LogGenerator;

public class ImportBD {
    
    public static String NOME_ARQUIVO_TEXTO_DEFAULT = "Egressos-para-Importar.txt";
    
    public static void main(String[] args) {
        
        System.out.println("==================== ImportBD =====================\n"
                + "Sistema de importação de dados para Bancos de dados\n");

        String arquivo = "";

        try
        {
            arquivo = IOController.recebeDados(args);
            IOController.validaConsistenciaDados(arquivo);
            LogGenerator.generate(LogGenerator.LogCategories.CONS, "O arquivo está ótimo, obrigado!");
        }
        catch (IllegalArgumentException | IOException e) {
            System.err.println("EXCEÇÃO: " + e.getLocalizedMessage());
        }
    }
}
