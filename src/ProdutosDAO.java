import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProdutosDAO {

    // Método para listar os produtos
    public ArrayList<ProdutosDTO> listarProdutos() {
        ArrayList<ProdutosDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM produtos";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor((int) rs.getDouble("valor")); // Corrigido para double
                produto.setStatus(rs.getString("status"));
                lista.add(produto);
            }

        } catch (Exception e) {
            System.out.println("Erro ao listar produtos: " + e.getMessage());
        }

        return lista;
    }

    // Método para cadastrar um produto
    public boolean cadastrarProduto(ProdutosDTO produto) {
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getValor()); // Corrigido para double
            stmt.setString(3, produto.getStatus());

            stmt.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("Erro ao cadastrar produto: " + e.getMessage());
            return false;
        }
    }

    // Método para vender um produto
    public void venderProduto(int id) {
        String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Produto vendido com sucesso.");

        } catch (Exception e) {
            System.out.println("Erro ao vender produto: " + e.getMessage());
        }
    }
}

