package integ1.trab5.importBD.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Classe que controla a entrada (input) e saída (output) de arquivos no app.
 * 
 * @author gustavosotnas
 */
public final class IOController {
    
    public static void recebeDados(String[] args) throws IllegalArgumentException, FileNotFoundException {

        if (args.length == 1) {
            System.out.println("Arquivo:" + args[0]);
            String content = new Scanner(new File(args[0])).useDelimiter("\\Z").next();
            System.out.println(content);
        }
        else {
            throw new IllegalArgumentException("Nenhum arquivo foi passado como"
                    + " parâmetro para o programa");
        }
    }
}
