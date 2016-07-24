package integ1.trab5.importBD.service;

import java.sql.Connection;
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
    private static Connection conn;
    
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
            PreparedStatement pstmt = conn.prepareStatement(sqlStatement);
            ResultSet rs = pstmt.executeQuery();
            // Fechar resultado.
            rs.close();
            // Fechar sentença.
            pstmt.close();
            // Fechar conexão.
            conn.close();
        }
    }
}
