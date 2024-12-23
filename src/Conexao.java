import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

    public static Connection getConexao() {
        Connection conn = null;
        try {
            String url = "jdbc:mysql://localhost:3306/seu_banco"; // Substitua "seu_banco"
            String user = "seu_usuario"; // Substitua "seu_usuario"
            String password = "sua_senha"; // Substitua "sua_senha"

            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
        return conn;
    }
}
