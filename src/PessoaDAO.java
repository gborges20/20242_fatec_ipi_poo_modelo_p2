//data access object
//mapeamento objeto-relacional
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
public class PessoaDAO {
  public void cadastrar(Pessoa p) throws Exception{
    //1. Especificar o comando SQL (INSERT)
    var sql = 
      "INSERT INTO tb_pessoa(nome, fone, email) VALUES (?, ?, ?)";
    //2. Estabelecer uma conexão com o bd
    var conexao = ConnectionFactory.conectar();
    //3. Preparar o comando
    var ps = conexao.prepareStatement(sql);
    //4. Substituir os eventuais placeholders
    ps.setString(1, p.getNome());
    ps.setString(2, p.getFone());
    ps.setString(3, p.getEmail());
    //5. Executar o comando
    ps.execute();
    //6. Fechar a conexão
    ps.close();
  }

  public Pessoa buscarPorCodigo(int codigo) {
    // Itera pela lista de pessoas e retorna a pessoa com o código especificado
    
    return null;
  }

  public void atualizar(Pessoa p) throws Exception{
    
    var sql = 
      "UPDATE tb_pessoa SET nome='?' SET fone='?' SET email='?' WHERE cod_pessoa='?'; ";
    //2. Estabelecer uma conexão com o bd
    var conexao = ConnectionFactory.conectar();
    //3. Preparar o comando
    var ps = conexao.prepareStatement(sql);
    //4. Substituir os eventuais placeholders
    ps.setString(1, p.getNome());
    ps.setString(2, p.getFone());
    ps.setString(3, p.getEmail());
    //5. Executar o comando
    ps.execute();
    //6. Fechar a conexão
    ps.close();
  }

  public void apagar(Pessoa p) throws Exception{
    var sql = "DELETE FROM tb_pessoa WHERE cod_pessoa = ?";
    var conexao = ConnectionFactory.conectar();
    var ps = conexao.prepareStatement(sql);
    ps.setInt(1, p.getCodigo());
    ps.execute();
    ps.close();
    //by Isabel Santos
  }

  public List <Pessoa> listar() throws Exception{
    var pessoas = new ArrayList<Pessoa>();
    var sql = "SELECT * FROM tb_pessoa";
    //7+: try-with-resources
    try(var conexao = ConnectionFactory.conectar()){
      var ps = conexao.prepareStatement(sql);
      ResultSet rs = ps.executeQuery();
      while(rs.next()){
        var codigo = rs.getInt("cod_pessoa");
        var nome = rs.getString("nome");
        var fone = rs.getString("fone");
        var email = rs.getString("email");
        var p = new Pessoa(codigo, nome, fone, email);
        pessoas.add(p);
      }
      return pessoas;
    }
  }
}
