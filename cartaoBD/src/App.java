import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.chrono.IsoChronology;
import java.util.Scanner;

import java.sql.PreparedStatement;

import model.entity.Cartao;
import model.entity.ConectaBD;
import model.entity.Usuario;
import model.entity.entidades_DAO.CartaoDAO;
import model.entity.entidades_DAO.UsuarioDAO;

public class App {

    public static int menu(boolean is_primeiro_usuario) {
        if (is_primeiro_usuario) {
            Scanner teclado = new Scanner(System.in);
            System.out.println("-----MENU-----");
            System.out.println("1- Cadstrar usuário.");
            return teclado.nextInt();
        } else {
            Scanner teclado = new Scanner(System.in);
            System.out.println("-----MENU-----");
            System.out.println("1- Cadstrar usuário.");
            System.out.println("2- Gerar cartão para usuário.");
            System.out.println("3- Realizar transações.");
            System.out.println("4- Efetuar pagamentos.");
            System.out.println("5- Consultar saldo.");
            System.out.println("6- Listar cartões do usuario.");
            System.out.println("7- Consultar Log de transações.");
            System.out.println("8- Escolher usuário.");
            System.out.println("9- Sair");
            System.out.print("Digite: ");
            return teclado.nextInt();
        }

    }

    public static int metodoSelecionarUsuario() {
        Scanner teclado = new Scanner(System.in);
        ConectaBD con = new ConectaBD();
        con.getConexao();
        int i = 1;
        int id_usuario = 0;

        String sql = "SELECT id, nome FROM usuario";

        try {
            PreparedStatement pst = con.getConexao().prepareStatement(sql);
            ResultSet res = pst.executeQuery();
            System.out.println("Usuários cadastrados: ");

            while (res.next()) {
                System.out.println(i + " - " + res.getString("nome"));
                i++;
            }

            try {
                System.out.println("\nSelecione o usuário pelo número: ");
                while (!teclado.hasNextInt()) {
                    System.out.println("Valor inserido não foi um número. Escolha novamente:");
                    teclado.next();
                }
                int index_usuario = teclado.nextInt();

                while (index_usuario <= 0 || index_usuario > i - 1) {
                    System.out.println("Usuário inválido. Escolha novamente:");
                    while (!teclado.hasNextInt()) {
                        System.out.println("Valor inserido não foi um número. Escolha novamente:");
                        teclado.next();
                    }
                    index_usuario = teclado.nextInt();
                }
                ResultSet res2 = pst.executeQuery();
                int j = 0;

                while (res2.next()) {
                    if (index_usuario - 1 == j) {
                        id_usuario = res2.getInt("id");
                    }
                    j++;
                }

                sql = "SELECT id from usuario where id = ?;";
                PreparedStatement pst2 = con.getConexao().prepareStatement(sql);

                return id_usuario;

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Ocorreu um erro ao obter o número do usuário.");
                return -1; // Retornar um valor inválido para indicar erro
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ocorreu um erro ao conectar ao banco de dados.");
            return -1; // Retornar um valor inválido para indicar erro
        }
    }

    public static int getNumeroUsuarios() {
        ConectaBD con = new ConectaBD();
        con.getConexao();
        int i = 0;

        String sql = "SELECT nome FROM usuario";

        try {
            PreparedStatement pst = con.getConexao().prepareStatement(sql);
            ResultSet res = pst.executeQuery();
            while (res.next()) {
                i++;
            }

        } catch (Exception e) {
            System.out.println("Erro ao comunicar com banco de dados.");
            return -1;

        }
        return i;
    }

    public static void metodoCadastrarUsuario() {
        Scanner teclado = new Scanner(System.in);
        UsuarioDAO uDAO = new UsuarioDAO();

        System.out.println("Nome do novo usuário: ");
        String nome = teclado.next();
        System.out.println("Documento do novo usuário: ");
        String documento = teclado.next();
        System.out.println("Sexo do novo usuário: ");
        String genero = teclado.next();
        System.out.println("Telefone do novo usuário: ");
        String telefone = teclado.next();
        System.out.println("Data de nascimento do novo usuário: ");
        String data_nascimento = teclado.next();

        try {
            Usuario u = new Usuario(nome, documento, data_nascimento, genero, telefone);
            uDAO.cadastrasUsuario(u);
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    public static void metodoEscolherUsuario() {
        Scanner teclado = new Scanner(System.in);

    }

    public static void metodoGerarCartao(int user_id) {
        Scanner teclado = new Scanner(System.in);
        CartaoDAO cDAO = new CartaoDAO();

        System.out.println("Limite do cartão: ");
        Double limite_credito = teclado.nextDouble();
        boolean criado = cDAO.gerarCartaoParaUsuario(user_id, limite_credito);

        if (criado) {
            System.out.println("Cartão gerado com sucesso.");
        }

    }

    public static void metodoRealizarTransacao(int user_id) {
        Scanner teclado = new Scanner(System.in);
        CartaoDAO cDAO = new CartaoDAO();

        System.out.println("Quanto você deseja transferir?");
        double valorTransacao = teclado.nextDouble();

        System.out.println("Para quem você deseja transferir?");
        String recebedor = teclado.next();

        cDAO.realizarTransacao(user_id, valorTransacao, recebedor);

    }

    private static void metodoConsultarSaldo(int id_usuario){
        CartaoDAO cDAO = new CartaoDAO();
        cDAO.consultarSaldo(id_usuario);
    }

    private static void metodoListarCartoes(int id_usuario){
        UsuarioDAO uDAO = new UsuarioDAO();
        uDAO.listarCartoes(id_usuario);

    }

    public static void main(String[] args) throws Exception {
        boolean is_primeiro_usuario = getNumeroUsuarios() > 0 ? false : true;
        int user_id = 0;
        int op;
        System.out.println("\nSISTEMA DE CARTÃO DE CRÉDITO\n");
        do {
            is_primeiro_usuario = getNumeroUsuarios() > 0 ? false : true;

            if (is_primeiro_usuario == false && user_id == 0) {
                user_id = metodoSelecionarUsuario();

            }

            op = menu(is_primeiro_usuario);

            switch (op) {
                case 1: {
                    // Cadastar usuario
                    metodoCadastrarUsuario();
                    break;
                }

                case 2: {
                    System.out.println(user_id);
                    metodoGerarCartao(user_id);
                    break;
                }
                case 3: {
                    // realizar transacoes
                    metodoRealizarTransacao(user_id);
                    break;
                }

                case 4: {
                    // efetuar pagamento
                    break;

                }
                case 5: {
                    // consultar saldo
                    metodoConsultarSaldo(user_id);
                    break;

                }

                case 6: {
                    // listar cartoes
                    metodoListarCartoes(user_id);
                    break;
                }
                case 7: {
                    // Listar log de transacoes
                    break;
                }
                case 8: {
                    // escolher usuario
                    user_id = 0;
                    break;
                }

                case 9: {
                    System.out.println("Saindo...");
                    break;
                }

                default:
                    System.out.println("Opção inválida!!!");
                    break;
            }
        } while (op != 9);

    }
}
