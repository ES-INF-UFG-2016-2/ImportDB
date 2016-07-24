package integ1.trab5.importBD.controller;

import integ1.trab5.importBD.view.ImportBD;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Classe que controla a entrada (input) e saída (output) de arquivos no app.
 * 
 * @author gustavosotnas
 */
public final class IOController {
    
    /**
     * Lê o(s) argumento(s) de linha de comando e o(s) processa, resultando na 
     * leitura bem sucedida do arquivo de texto para o sistema.
     *
     * @param args o(s) argumento(s) de linha de comando
     *             (caminho absoluto ou relativo do arquivo de texto)
     * @return o conteúdo do arquivo de texto passado como argumento
     * @throws IllegalArgumentException quando o usuário comete algum erro ao
     *                                  usar o programa
     * @throws FileNotFoundException quando o arquivo especificado não é encontrado
     */
    public static String recebeDados(String[] args) throws IllegalArgumentException, FileNotFoundException {

        if (args.length == 1) {
            String content = new Scanner(new File(args[0])).useDelimiter("\\Z").next();
            return content;
        }
        else if (args.length >= 2) {
            throw new IllegalArgumentException("Argumentos demais, um arquivo por vez.");
        }
        else {
            
            File arquivoDefault = new File(ImportBD.NOME_ARQUIVO_TEXTO_DEFAULT);
            
            if (arquivoDefault.exists() && !arquivoDefault.isDirectory()) {
                
                String content = new Scanner(arquivoDefault).useDelimiter("\\Z").next();
                return content;
            }
            else {

                throw new IllegalArgumentException("Nenhum arquivo foi passado como parâmetro para o programa e há nenhum \n"
                        + " "
                        + "arquivo \"" + ImportBD.NOME_ARQUIVO_TEXTO_DEFAULT + "\" na pasta atual.");
            }
        }
    }
}
