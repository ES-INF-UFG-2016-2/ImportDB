package integ1.trab5.importBD.view;

import integ1.trab5.importBD.controller.IOController;
import integ1.trab5.importBD.model.DadosImportados;

public class ImportarEgressos {

    public static String NOME_ARQUIVO_TEXTO_DEFAULT = "Egressos-para-Importar.txt";

    public static void main(String[] args) {
        System.out.println("==================== ImportBD =====================\n"
                + "Sistema de importação de dados para Bancos de dados\n");

        String arquivo = "";
        Boolean isValido;

        try {
            arquivo = IOController.recebeDados(args);
            System.out.println(arquivo);
            isValido = IOController.validaConsistenciaDados(arquivo);
            
            if (isValido) {
                DadosImportados dados = IOController.convertToObject(arquivo);
                IOController.persistirDados(dados.getConjuntoDeRegistros());
                IOController.imprimirRelatoImportacao(isValido);
            } else {
                IOController.imprimirRelatoImportacao(isValido);
            }
            
        } catch (Exception e) {
            System.err.println("EXCEÇÃO: " + e.getLocalizedMessage());
        }
    }
}
