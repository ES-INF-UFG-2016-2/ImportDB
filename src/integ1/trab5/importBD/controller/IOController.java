package integ1.trab5.importBD.controller;

import integ1.trab5.importBD.model.CursoImport;
import integ1.trab5.importBD.model.DadosImportados;
import integ1.trab5.importBD.service.BDService;
import integ1.trab5.importBD.service.ImportService;
import integ1.trab5.importBD.view.ImportarEgressos;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.IllegalFormatException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Classe que controla a entrada (input) e saída (output) de arquivos no app.
 *
 * @author juliannyas
 */
public final class IOController {

    /**
     * Lê o(s) argumento(s) de linha de comando e o(s) processa, resultando na
     * leitura bem sucedida do arquivo de texto para o sistema.
     *
     * @param args o(s) argumento(s) de linha de comando (caminho absoluto ou
     * relativo do arquivo de texto)
     * @return o conteúdo do arquivo de texto passado como argumento
     * @throws IllegalArgumentException quando o usuário comete algum erro ao
     * usar o programa
     * @throws FileNotFoundException quando o arquivo especificado não é
     * encontrado
     */
    public static String recebeDados(String[] args) throws IllegalArgumentException, FileNotFoundException {

        if (args.length == 1) {
            String content = new Scanner(new File(args[0])).useDelimiter("\\Z").next();
            return content;
        } else if (args.length >= 2) {
            throw new IllegalArgumentException("Argumentos demais, um arquivo por vez.");
        } else {

            File arquivoDefault = new File(ImportarEgressos.NOME_ARQUIVO_TEXTO_DEFAULT);

            if (arquivoDefault.exists() && !arquivoDefault.isDirectory()) {

                String content = new Scanner(arquivoDefault).useDelimiter("\\Z").next();
                return content;
            } else {

                throw new IllegalArgumentException("Nenhum arquivo foi passado como parâmetro para o programa e há nenhum \n"
                        + " "
                        + "arquivo \"" + ImportarEgressos.NOME_ARQUIVO_TEXTO_DEFAULT + "\" na pasta atual.");
            }
        }
    }

    /**
     * Valida a consistência dos dados processados, antes de colocá-los no BD.
     *
     * @param input o conteúdo do arquivo de texto para importação
     * @throws IllegalFormatException quando a string tem uma sintaxe ou formato
     * incompatível com o programa.
     */
    public static boolean validaConsistenciaDados(String input) throws IllegalFormatException {

        /**
         * Validar: * Valor fixo "Reg.1" ou "Reg.2" + campos delimitados por
         * "\", (provavelmente será necessário mexer com `regex` e/ou
         * `substring`); * Deve haver um único registro tipo 1 para cada curso
         * que um dado egresso concluiu na UFG. Pode haver zero ou vários
         * registros tipo 2 para esse egresso em um curso. * Se os dados são
         * consistentes, criar arquivo "Relato-de-Importacao.txt", caso
         * contrário, criar / append no arquivo "Relato-de-Importacao.txt" que
         * os dados são inconsistentes e lançar exceção.
         */
        return true;

    }

    public static void imprimirRelatoImportacao(boolean isValido) {

        String nomeArq = "Relato-de-Importacao.txt";
        //tentando criar arquivo
        try {
            Formatter saida = new Formatter(nomeArq);
            saida.format("Arquivo gerado automaticamente!\n\n...www.terminaldeinformacao.com...");
            saida.close();
            JOptionPane.showMessageDialog(null, nomeArq + " criado!", "Arquivo", 1);
        } //mostrando erro em caso se nao for possivel gerar arquivo
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Arquivo nao pode ser gerado!", "Erro", 0);
        }
    }

    public static void persistirDados(ArrayList<CursoImport> dados) {
        
        try {
            BDService.persistir(dados);
        } catch (SQLException ex) {
            Logger.getLogger(IOController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static DadosImportados convertToObject(String arquivo) throws IOException {
        DadosImportados dados = ImportService.convertCSVtoObject(arquivo);
        return dados;
    }

}
