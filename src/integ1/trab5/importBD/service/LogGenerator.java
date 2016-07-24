package integ1.trab5.importBD.service;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Serviço de geração de logs para um arquivo de texto.
 *
 * @author gustavosotnas
 */
public final class LogGenerator {

    private static final String LOG_OUTPUT_FILE = "Relato-de-Importacao.txt";
    
    /** Enum de categorias de log: Dados consistentes ou inconsistentes. */
    public enum LogCategories {
        CONS("CONSISTENTE"), INCONS("INCONSISTENTE");
        private final String text;
        private LogCategories(final String category) { this.text = category; }
        public String toString() { return text; }
    }

    /**
     * Gera um arquivo de log de acordo com a categoria e o texto da mensagem
     * passada por parâmetro.
     * 
     * Código baseado em: 
     * http://www.cs.utexas.edu/~mitra/csSummer2009/cs303/lectures/fileIO.html
     * 
     * @param category categoria da mensagem
     * @param message a mensagem
     * @throws IOException quando o programa não consegue criar o arquivo no computador.
     */
    public static void generate(LogCategories category, String message) throws IOException {

        FileWriter fWriter = new FileWriter (LOG_OUTPUT_FILE, true);
        PrintWriter pWriter = new PrintWriter (fWriter);
        
        pWriter.print("[" + getCurrentTimeStamp() + "] ");
        pWriter.print(category.toString() + ": ");
        pWriter.println(message);
        pWriter.close();
    }

    /**
     * Obtêm a data e hora atual do computador.
     * 
     * Código baseado em: http://stackoverflow.com/q/1459656
     * 
     * @return uma string de data e hora atual do computador.
     */
    private static String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }
}
