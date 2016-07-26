package integ1.trab5.importBD.service;

import integ1.trab5.importBD.model.CursoImport;
import integ1.trab5.importBD.model.DadosImportados;
import integ1.trab5.importBD.model.RegistroTipo1;
import integ1.trab5.importBD.model.RegistroTipo2;
import integ1.trab5.importBD.model.campos.Egresso2e3Campos;
import integ1.trab5.importBD.model.campos.Egresso4PCampos;
import integ1.trab5.importBD.model.campos.HistoricoUFG;
import integ1.trab5.importBD.model.campos.ProgramaAcademico;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
        DadosImportados dados = toDadosImportados(dadosFiltrados);
        return dados;
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
        HashMap<String, CursoImport> registrosMap = new HashMap<>();
        
        DadosImportados dadosImportados = new DadosImportados(registros);
        
        RegistroTipo1 registroTipo1 = new RegistroTipo1();
        ArrayList<RegistroTipo2> listaReg2 = new ArrayList<>();
        
        for(String[] linha: input){
            
            CursoImport cursoImport;
            
            switch(linha[0]){
                case "Reg.1":
                
                    registroTipo1 = new RegistroTipo1();
                    Egresso4PCampos egresso4PCampos = new Egresso4PCampos();
                    HistoricoUFG historicoEgresso = new HistoricoUFG();

                    egresso4PCampos.setNome_egresso(linha[1]);
                    egresso4PCampos.setTipo_doc_identidade(linha[2]);
                    egresso4PCampos.setNum_doc_identidade(linha[3]);
                    egresso4PCampos.setData_nasc(new Date(Date.parse(linha[4])));

                    registroTipo1.setEgresso4PCampos(egresso4PCampos);
                    registroTipo1.setNomeCursoUFG(linha[5]);


                    historicoEgresso.setMesAnoIngresso(Integer.parseInt(linha[6]));
                    historicoEgresso.setMesAnoConclusao(Integer.parseInt(linha[7]));
                    historicoEgresso.setNumMatriculaNoCurso(Integer.parseInt(linha[8]));
                    historicoEgresso.setTituloTrabalhoFinal(linha[9]);
                    
                    registroTipo1.setHistoricoUFG(historicoEgresso);
                    
                    if(registrosMap.containsKey(registroTipo1.getNomeCursoUFG())){
                        
                        CursoImport cursoImportTmp = registrosMap.get(registroTipo1.getNomeCursoUFG());
                        RegistroTipo1 reg1tmp = cursoImportTmp.getRegEgressoT1();
                        
                        // Caso ache encontre um Reg1 que não tenha Reg2 associado, verifica o tamanho do array de Reg2
                        // (Evita NullPointerException)
                        if(cursoImportTmp.getRegEgressoT2().size()>0){
                            
                            RegistroTipo2 reg2tmp = cursoImportTmp.getRegEgressoT2().get(0);
                            Egresso2e3Campos egresso2e3CamposTmp = reg2tmp.getEgresso2e3Campos();
                            
                            //Verifica se o id do Egresso lido é o mesmo id encontrado em um Reg2 do hashmap
                            //(Caso encontre no hashMap um Reg2 sem Reg1)
                            if(egresso4PCampos.getId_egresso()== egresso2e3CamposTmp.getId_egresso()){
                            
                                cursoImportTmp = new CursoImport(registroTipo1, cursoImportTmp.getRegEgressoT2());

                                registrosMap.replace(registroTipo1.getNomeCursoUFG(), cursoImportTmp);
                            
                            }else {
                            
                            //caso não encontre nenhum Egresso já no hashMap com o id do Reg1 lido
                            //Cria um reg2 vazio e adiciona o reg1 que foi encontrado
                                registrosMap = retornaNovoReg1(registrosMap, registroTipo1);
                            }
                            
                        } else {
                            
                            registrosMap = retornaNovoReg1(registrosMap, registroTipo1);
                        }
                        
                    }else {
                       
                        registrosMap = retornaNovoReg1(registrosMap, registroTipo1);
                    }
                    
                    
                break;
                
                case "Reg.2":
                    RegistroTipo2 registroTipo2 = new RegistroTipo2();
                    Egresso2e3Campos egresso2e3Campos = new Egresso2e3Campos();
                    
                    egresso2e3Campos.setTipo_doc_identidade(linha[1]);
                    egresso2e3Campos.setNum_doc_identidade(linha[2]);

                    registroTipo2.setEgresso2e3Campos(egresso2e3Campos);
                    registroTipo2.setIdCursoCursadoUFG(linha[3]);
                    ProgramaAcademico realProgAcad = new ProgramaAcademico();
                    
                    realProgAcad.setTipoProgAcad(linha[4]);
                    realProgAcad.setDataInicio(new Date(Date.parse(linha[5])));
                    realProgAcad.setDataFim(new Date(Date.parse(linha[6])));
                    realProgAcad.setDescricao(linha[7]);
                    
                    registroTipo2.setProgAcademico(realProgAcad);
                    
                    listaReg2.add(registroTipo2);
                    
                    
                    //Verifica se já existe um Reg1 com o mesmo curso
                    if(registrosMap.containsKey(registroTipo2.getIdCursoCursadoUFG())){
                        
                        CursoImport cursoImportTmp = registrosMap.get(registroTipo2.getIdCursoCursadoUFG());
                        RegistroTipo1 reg1tmp = cursoImportTmp.getRegEgressoT1();
                        Egresso4PCampos egresso4PCamposTmp = reg1tmp.getEgresso4PCampos();
                        
                        //verifica se o id a ser persistido é o mesmo do Egresso encontrado no HashMap
                        if(egresso2e3Campos.getId_egresso()== egresso4PCamposTmp.getId_egresso()){
                            
                            ArrayList<RegistroTipo2> listaReg2Tmp = cursoImportTmp.getRegEgressoT2();
                            
                            listaReg2Tmp.add(registroTipo2);
                        
                            cursoImportTmp = new CursoImport(reg1tmp, listaReg2Tmp);
                        
                            registrosMap.replace(registroTipo2.getIdCursoCursadoUFG(), cursoImportTmp);
                        
                            
                        }else{
                            
                            registrosMap = retornaNovoReg2(registrosMap, registroTipo2);
                        }
                       
                        
                    }else {
                            
                        registrosMap = retornaNovoReg2(registrosMap, registroTipo2);
                    }
                    
                break;
        }
       
    }
    return dadosImportados;
}
    
    public static HashMap<String, CursoImport> retornaNovoReg1(HashMap<String, CursoImport> registrosMap, RegistroTipo1 reg1){
        ArrayList<RegistroTipo2> reg2 = new ArrayList<>();
        CursoImport cursoImportTmp = new CursoImport(reg1, reg2);
                        
        registrosMap.put(reg1.getNomeCursoUFG(), cursoImportTmp);
        return registrosMap;
    }
    
    public static HashMap<String, CursoImport> retornaNovoReg2(HashMap<String, CursoImport> registrosMap, RegistroTipo2 registroTipo2){
        RegistroTipo1 reg1 = new RegistroTipo1();
        ArrayList<RegistroTipo2>reg2 = new ArrayList<RegistroTipo2>();
                            
        reg2.add(registroTipo2);
                            
        CursoImport cursoImportTmp = new CursoImport(reg1, reg2);
                        
        registrosMap.put(registroTipo2.getIdCursoCursadoUFG(), cursoImportTmp);
        
        return registrosMap;
    }
}
