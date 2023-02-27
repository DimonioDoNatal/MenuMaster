package restauranteDAO;

import com.mysql.jdbc.PreparedStatement;
import restaurante.factory.ConnectionFactory;
import restaurantepkg.AdminPKG;
import restaurantepkg.Clientes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public void save(Clientes clientes){

        String sql = "INSERT INTO cadastro_cliente(nome_cliente,cep_cliente,email_cliente,tel_cliente, senha_cliente) VALUES(?,?,?,?,?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            //cria conexao com banco de dados
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setString(1, clientes.getNomeCliente());
            pstm.setString(2, clientes.getCepCliente());
            pstm.setString(3, clientes.getEmailCliente());
            pstm.setString(4, clientes.getTelefoneCliente());
            pstm.setString(5, clientes.getSenha_cliente());

            //executa a query

            pstm.execute();

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            //fechar a conexao

            try {
                if (pstm!=null){
                    pstm.close();
                }
                if (conn!=null){
                    conn.close();
                }
        }catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public List<Clientes> getClientes() {

        String sql = "SELECT * FROM cadastro_cliente";

        List<Clientes> clientes = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstn = null;
        //pesquisa SELECT
        ResultSet rset = null;

        try {

            conn = ConnectionFactory.createConnectionToMySQL();

            pstn = (PreparedStatement) conn.prepareStatement(sql);

            rset = pstn.executeQuery();


            while (rset.next()) {
                Clientes pegaclientes = new Clientes();

                //pegar dados


                pegaclientes.setCepCliente(rset.getString("cep_cliente"));
                pegaclientes.setNomeCliente(rset.getString("nome_cliente"));
                pegaclientes.setEmailCliente(rset.getString("email_cliente"));
                pegaclientes.setTelefoneCliente(rset.getString("tel_cliente"));
                pegaclientes.setSenha_cliente(rset.getString("senha_cliente"));


                clientes.add(pegaclientes);
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {


                if (rset != null) {
                    rset.close();
                }
                if (pstn != null) {
                    pstn.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        return clientes;


    }
}
