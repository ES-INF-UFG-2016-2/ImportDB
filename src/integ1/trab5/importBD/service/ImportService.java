package integ1.trab5.importBD.service;

import integ1.trab5.importBD.model.CursoImport;
import integ1.trab5.importBD.model.DadosImportados;
import integ1.trab5.importBD.model.RegistroTipo1;
import integ1.trab5.importBD.model.RegistroTipo2;
import integ1.trab5.importBD.model.campos.Egresso2e3Campos;
import integ1.trab5.importBD.model.campos.Egresso4PCampos;
import integ1.trab5.importBD.model.campos.HistoricoUFG;
import integ1.trab5.importBD.model.campos.RealProgAcad;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * Serviço conversor do arquivo de importação do SempreUFG num objeto estruturado.
 *
 * @author gustavosotnas
 */
public class ImportService {
    
    /** Internal Field Separator (IFS) */
    private static final String IFS = "\\\\"; // regex "\"
    
    public static DadosImportados convertCSVtoObject(String csvFileContent) throws IOException {
        ArrayList<String[]> dadosFiltrados = parseCSV(csvFileContent);
        
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
    
    /**
     * Converte a estruturas de strings recebida para o formato de dados usável no bd.
     * 
     * @param input o conteúdo do arquivo convertido para pseudo-matriz
     * @return O objeto com todos os dados prontos para o BD
     */
    private static DadosImportados toDadosImportados(ArrayList<String[]> input){
    
        ArrayList<CursoImport> registros = new ArrayList<>();
        DadosImportados dadosImportados = new DadosImportados(registros);
        
        RegistroTipo1 reg1 = new RegistroTipo1();
        ArrayList<RegistroTipo2> reg2 = new ArrayList<>();
        
        for(String[] linha: input){
            
            CursoImport cursoImport;
            
            switch(linha[0]){
                case "Reg.1":
                
                    reg1 = new RegistroTipo1();
                    Egresso4PCampos egresso4PCampos = new Egresso4PCampos();
                    HistoricoUFG historicoEgresso = new HistoricoUFG();

                    egresso4PCampos.setNome(linha[1]);
                    egresso4PCampos.setTipoID(linha[2]);
                    egresso4PCampos.setId(linha[3]);
                    egresso4PCampos.setDataNasc(new Date(Date.parse(linha[4])));

                    reg1.setEgresso4PCampos(egresso4PCampos);
                    reg1.setNomeCursoUFG(linha[5]);


                    historicoEgresso.setMesAnoIngresso(Integer.parseInt(linha[6]));
                    historicoEgresso.setMesAnoConclusao(Integer.parseInt(linha[7]));
                    historicoEgresso.setNumMatriculaNoCurso(Integer.parseInt(linha[8]));
                    historicoEgresso.setTituloTrabalhoFinal(linha[9]);

                    reg1.setHistoricoUFG(historicoEgresso);
                    
                    
                break;
                case "Reg.2":
                    RegistroTipo2 registroTipo2 = new RegistroTipo2();
                    Egresso2e3Campos egresso2e3Campos = new Egresso2e3Campos();
                    
                    egresso2e3Campos.setTipoID(linha[1]);
                    egresso2e3Campos.setId(linha[2]);

                    registroTipo2.setEgresso2e3Campos(egresso2e3Campos);
                    registroTipo2.setIdCursoCursadoUFG(linha[3]);
                    RealProgAcad realProgAcad = new RealProgAcad();
                    
                    realProgAcad.setTipoProgAcad(linha[4]);
                    realProgAcad.setDataInicio(new Date(Date.parse(linha[5])));
                    realProgAcad.setDataFim(new Date(Date.parse(linha[6])));
                    realProgAcad.setDescricao(linha[7]);
                    
                    registroTipo2.setRealProgAcad(realProgAcad);
                    
                    reg2.add(registroTipo2);
                    
                break;
        }
       
    }
    return dadosImportados;
}
}
