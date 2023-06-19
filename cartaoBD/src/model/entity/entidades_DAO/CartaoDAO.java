package model.entity.entidades_DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Scanner;

import javax.swing.plaf.metal.MetalBorders.ScrollPaneBorder;

import org.mariadb.jdbc.export.Prepare;

import model.entity.Cartao;
import model.entity.ConectaBD;
import model.entity.Transacao;

public class CartaoDAO {

    public static String getDataAtual() {
        LocalDate dataAtual = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return dataAtual.format(formato);
    }

    public static String gerarDataValidade() {
        LocalDate dataAtual = LocalDate.now();
        LocalDate dataFutura = dataAtual.plusYears(3);
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return dataFutura.format(formato);
    }

    public static String gerarNumeroCartao() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 12; i++) {
            if (i > 0 && i % 4 == 0) {
                sb.append(" ");
            }
            int numeroAleatorio = random.nextInt(10);
            sb.append(numeroAleatorio);
        }

        sb.append(" 9999");

        return sb.toString();
    }

    public boolean gerarCartaoParaUsuario(int id_usuario, double limite_credito) {
        ConectaBD con = new ConectaBD();

        String sql = "SELECT * FROM usuario WHERE id = ?";

        try {
            PreparedStatement pst = con.getConexao().prepareStatement(sql);
            pst.setInt(1, id_usuario);

            String data_validade = gerarDataValidade();
            String num_cartao = gerarNumeroCartao();

            ResultSet res = pst.executeQuery();

            if (res.next()) {
                Cartao cartao = new Cartao(id_usuario, num_cartao, data_validade, limite_credito);
                cartao.setTipo("credito");
                sql = "INSERT INTO cartao (id_usuario, num_cartao, data_validade, limite_credito, tipo) VALUES (?,?,?,?,?)";
                PreparedStatement pst2 = con.getConexao().prepareStatement(sql);
                pst2.setInt(1, id_usuario);

                pst2.setString(2, num_cartao);
                pst2.setString(3, data_validade);
                pst2.setDouble(4, limite_credito);
                pst2.setString(5, cartao.getTipo());

                pst2.execute();

                // inserir no log
                return true;
            }
            System.out.println("Erro ao gerar cartão");
            // inserir no log
            return false;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("Erro de conexão.");
            // inserir no log
            return false;
        }

    }

    public int realizarTransacao(int id_usuario, double valorTransacao, String recebedor) {
        Scanner teclado = new Scanner(System.in);
        int index_cartao = 0;
        ConectaBD con = new ConectaBD();
        String sql = "SELECT * FROM cartao WHERE id_usuario = ?";

        try {
            int i = 1;
            PreparedStatement pst = con.getConexao().prepareStatement(sql);
            pst.setInt(1, id_usuario);

            ResultSet res = pst.executeQuery();

            System.out.println("Escolha um cartão: ");
            while (res.next()) {
                System.out.println(i + "- " + res.getString("num_cartao"));
                i++;

            }

            while (index_cartao < 1 || index_cartao >= i) {

                if (!teclado.hasNextInt()) {
                    System.out.println("Valor inserido não foi um número. Escolha novamente:");
                    teclado.next();
                } else {
                    index_cartao = teclado.nextInt();
                    if (index_cartao > 0 || index_cartao < i) {
                        System.out.println("AQUII");

                        ResultSet res2 = pst.executeQuery();
                        int id_cartao;
                        int j = 1;
                        while (res2.next()) {
                            if (index_cartao == j) {
                                Transacao transacao = new Transacao();

                                id_cartao = res2.getInt("id");

                                String sql2 = "SELECT * FROM cartao WHERE id = ?;";

                                PreparedStatement pst2 = con.getConexao().prepareStatement(sql2);

                                pst2.setInt(1, id_cartao);

                                res = pst2.executeQuery();

                                if (res.next()) {
                                    double limite_utilizado = res.getDouble("limite_utilizado");
                                    double limite_credito = res.getDouble("limite_credito");

                                    if (limite_utilizado + valorTransacao > limite_credito) {
                                        System.out.println("Saldo insuficiente. Transação cancelada.");
                                        return -1;

                                    } else {
                                        String updateLimiteUtilizado = "UPDATE cartao set limite_utilizado = ? where id = ?;";
                                        PreparedStatement pstupdate = con.getConexao().prepareStatement(updateLimiteUtilizado);
                                        pstupdate.setDouble(1, limite_utilizado + valorTransacao);
                                        pstupdate.setInt(2, id_cartao);

                                        transacao.setId_cartao(id_cartao);
                                        transacao.setId_usuario(id_usuario);
                                        transacao.setRecebedor(recebedor);
                                        transacao.setData_transacao(getDataAtual());
                                        transacao.setTipo(res.getString("tipo"));

                                        sql = "Insert into transacao (id_cartao, id_usuario, recebedor, data_transacao, tipo) values (?,?,?,?,?)";

                                        pst = con.getConexao().prepareStatement(sql);
                                        pst.setInt(1, transacao.getId_cartao());
                                        pst.setInt(2, transacao.getId_usuario());
                                        pst.setString(3, transacao.getRecebedor());
                                        pst.setString(4, transacao.getData_transacao());
                                        pst.setString(5, transacao.getTipo());
                                        pst.execute();
                                        pstupdate.executeUpdate();
                                        System.out.println("Transação feita com sucesso.");

                                    }

                                }

                                return 0;
                            }
                        }

                        return 0;

                    }
                    return -1;
                }

                System.out.println("Valor inválido.");
                return -1;

            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("Erro de conexão.");
            return -1;
        }
        return -1;

    }

}
