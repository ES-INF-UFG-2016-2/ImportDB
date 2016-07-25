package integ1.trab5.importBD.service;

import integ1.trab5.importBD.model.CursoImport;
import integ1.trab5.importBD.model.RegistroTipo1;
import integ1.trab5.importBD.model.RegistroTipo2;
import integ1.trab5.importBD.model.campos.Egresso2e3Campos;
import integ1.trab5.importBD.model.campos.Egresso4PCampos;
import integ1.trab5.importBD.model.campos.HistoricoUFG;
import integ1.trab5.importBD.model.campos.RealProgAcad;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Classe responsável pela comunicação com o servidor de banco de dados do
 * SempreUFG.
 *
 * @author gustavosotnas
 */
public class BDService {
    
    private static final String URL = "jdbc:postgresql://localhost:5432/teste?user=usuario&password=senha";
    private static Connection connection;
    
    /**
     * Conecta com o banco de dados, dado a URL predefinida.
     * 
     * @return true, se a conexão está ativa e estável; false, caso contrário
     * @throws SQLException quando a conexão falha
     */
    private static boolean connectToDB() throws SQLException {
        Connection conn = DriverManager.getConnection(URL);
        return conn.isValid(0);
    }
    
    public static void executeQuery(String sqlStatement) throws SQLException {
        
        if(connectToDB()) {
            PreparedStatement pstmt = connection.prepareStatement(sqlStatement);
            ResultSet rs = pstmt.executeQuery();
            // Fechar resultado.
            rs.close();
            // Fechar sentença.
            pstmt.close();
            // Fechar conexão.
            connection.close();
        }
    }
    
    /**
     * Recebe o array de registros, e manda cada conjunto 
     * de registros(RegistroTipo1 e RegistroTipo2) ao método persistirRegistros
     * @param dados Array de registros obtidos do arquivo
     * @throws SQLException 
     */
    public void persistir(ArrayList<CursoImport> dados) throws SQLException{
             
        for(CursoImport cursoImport: dados){
            persistirRegistros(cursoImport);
        }
        
    }
    
    /**
     * Recebe o conjunto de registros de um Egresso, e os organiza para gravação
     * @param cursoImport conjunto de registros do mesmo Egresso
     * @throws SQLException 
     */
    private void persistirRegistros(CursoImport cursoImport) throws SQLException{
        RegistroTipo1 reg1 = cursoImport.getRegEgressoT1();
        persistirReg1(reg1);
        
        ArrayList<RegistroTipo2> listaReg2 = cursoImport.getRegEgressoT2();
        
        // Como podem ter vários registros2, persiste todos contidos na lista
        for(RegistroTipo2 reg2: listaReg2){
            persistirReg2(reg2);
        }
    }
    /**
     * Persiste os dados do RegistroTipo1
     * @param reg1 Um RegistroTipo1
     * @throws SQLException 
     */
    private void persistirReg1(RegistroTipo1 reg1) throws SQLException{
        
        Egresso4PCampos egresso4PCampos = reg1.getEgresso4PCampos();
        HistoricoUFG historico = reg1.getHistoricoUFG();
        
        String sql = "INSERT INTO Egresso (nome, tipoID, id, dataNasc)"
                + " VALUES (?, ?, ?, ?)";

	PreparedStatement statement = getConnection().prepareStatement(sql);

	statement.setString(1, egresso4PCampos.getNome());
	statement.setString(2, egresso4PCampos.getTipoID());
        statement.setString(3, egresso4PCampos.getId());
        statement.setDate(4, (Date) egresso4PCampos.getDataNasc());  
        statement.execute();
        
        
        String sqlHistorico = "INSERT INTO HistoricoUFG WHERE idEgresso = "+
                egresso4PCampos.getNome() + 
                egresso4PCampos.getId()+
                " (anoInicio, anoFim, matricula, trabFinal) "
                + "VALUES(?, ?, ?, ?)";
        
        PreparedStatement statementHistorico = getConnection().prepareStatement(sql);
        
        statementHistorico.setInt(1, historico.getMesAnoIngresso());
        statementHistorico.setInt(2, historico.getMesAnoConclusao());
        statementHistorico.setInt(3, historico.getNumMatriculaNoCurso());
        statementHistorico.setString(4, historico.getTituloTrabalhoFinal());
        
        statementHistorico.execute();
        
    }
    
    /**
     * Persiste os dados do RegistroTipo2
     * @param reg2 Um RegistroTipo2
     * @throws SQLException 
     */
    private void persistirReg2(RegistroTipo2 reg2) throws SQLException{
        Egresso2e3Campos egresso2e3Campos = reg2.getEgresso2e3Campos();
        RealProgAcad progAcad = reg2.getRealProgAcad();
        
        
        String sql = "INSERT INTO RealProgAcad WHERE idEgresso = "+
                egresso2e3Campos.getTipoID() + 
                egresso2e3Campos.getId()+
                " (dataInicio, dataFim, descricao, tipo) "
                + "VALUES(?, ?, ?, ?)";
        
         PreparedStatement statement = getConnection().prepareStatement(sql);
        
        statement.setDate(1, (Date) progAcad.getDataInicio());
        statement.setDate(2, progAcad.getDatafim());
        statement.setString(3, progAcad.getDescricao());
        statement.setString(4, progAcad.getTipoProgAcad());
       
        statement.execute();
    }
    
    public Connection getConnection() {
		return connection;
	}

}
