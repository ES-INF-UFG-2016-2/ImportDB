package integ1.trab5.importBD.service;

import integ1.trab5.importBD.model.RegistroTipo1;
import integ1.trab5.importBD.model.RegistroTipo2;
import integ1.trab5.importBD.model.campos.Egresso4PCampos;
import integ1.trab5.importBD.model.campos.HistoricoUFG;
import integ1.trab5.importBD.model.campos.RealProgAcad;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    
    public void persistir(RegistroTipo1 reg1, RegistroTipo2 reg2) throws SQLException{
        Egresso4PCampos egresso4PCampos = reg1.getEgresso4PCampos();
        HistoricoUFG historico = reg1.getHistoricoUFG();
        RealProgAcad realProgAcad = reg2.getRealProgAcad();

        persistirEgresso(egresso4PCampos);
        
        persistirHistorico(egresso4PCampos,historico);
        
    }
    
    private void persistirEgresso(Egresso4PCampos egresso4PCampos) throws SQLException{
        
        String sql = "INSERT INTO Egresso (nome, tipoID, id, dataNasc)"
                + " VALUES (?, ?, ?, ?)";

	PreparedStatement statement = getConnection().prepareStatement(sql);

	statement.setString(1, egresso4PCampos.getNome());
	statement.setString(2, egresso4PCampos.getTipoID());
        statement.setString(3, egresso4PCampos.getId());
        statement.setDate(4, (Date) egresso4PCampos.getDataNasc());  
        statement.execute();
    }
    
    private void persistirHistorico(Egresso4PCampos egresso4PCampos, HistoricoUFG historico) throws SQLException{
        
        String sql = "INSERT INTO HistoricoUFG WHERE idEgresso = "+
                egresso4PCampos.getNome() + 
                egresso4PCampos.getId()+
                " (anoInicio, anoFim, matricula, trabFinal) "
                + "VALUES(?, ?, ?, ?)";
        
        PreparedStatement statement = getConnection().prepareStatement(sql);
        
        statement.setInt(1, historico.getMesAnoIngresso());
        statement.setInt(2, historico.getMesAnoConclusao());
        statement.setInt(3, historico.getNumMatriculaNoCurso());
        statement.setString(4, historico.getTituloTrabalhoFinal());
        
        statement.execute();
        
    }
    
    public Connection getConnection() {
		return connection;
	}

}
