package application;

import model.entities.User;

import java.io.*;
import java.util.*;

import static model.entities.Perguntas.*;
import static model.entities.User.pesquisar;
import static utils.ManipuladorDeTxt.perguntasTxt;
import static utils.ManipuladorDeTxt.usersTxt;
import static utils.MenuPrincipal.menu;


public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
//        Locale.setDefault(Locale.US);

        String path = "C:\\temp\\formulario.txt";
        File dir = new File("C:\\temp\\users");
        BufferedReader br = null;
        FileReader fr = null;

        var perguntas = new ArrayList<>();
        List<User> users = new ArrayList<>();
        String line = null;

        // Adicionado perguntas inicias do txt na entidade perguntas
        perguntasTxt(path, br, fr, perguntas, line);
//        try {
//            fr = new FileReader(path);
//            br = new BufferedReader(fr);
//
//            while ((line = br.readLine()) != null) {
//                perguntas.add(line); // Adiciona a linha à lista de perguntas
//            }
//        } catch (IOException e) {
//            System.out.println("Erro: " + e.getMessage());
//        } finally {
//            try {
//                if (br != null) br.close();
//                if (fr != null) fr.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

        // Adicionado users inicias do txt na entidade user
        usersTxt(dir, users);
//        File folder = new File(String.valueOf(dir));
//        File[] listOfFiles = folder.listFiles();
//
//        for(File file: listOfFiles){
//            if(file.isFile()){
//                try(BufferedReader bfr = new BufferedReader(new FileReader(file))){
//                    String name = bfr.readLine();
//                    String email = bfr.readLine();
//                    int age = Integer.parseInt(bfr.readLine());
//                    double height = Double.parseDouble(bfr.readLine());
//
//                    User user = new User(name, email, age, height);
//                    users.add(user);
//                }
//                catch(IOException e){
//                    System.out.println("Erro: " + e.getMessage());
//                }
//            }
//        }

        // Chamando menu principal dentro do switch para obter a opção desejada pelo usuario
        switch(menu()){

            // Cadastro Usuário
            case 1:
                users.add(User.cadastrar(perguntas, path, dir, br, fr, users));
                System.out.println();
                break;

            // Listando usuários
            case 2:
                User.listar(users);
                break;

            // Cadastrando nova pergunta
            case 3:
                cadastrarPergunta(path, perguntas);
                System.out.println(perguntas);
                break;

            // Deletando pergunta
            case 4:
                deletarPergunta(perguntas, path);
                break;

            case 5:
                //pesquisando por nome, email ou idade
                System.out.print("Deseja pesquisar por (1)Nome, (2)Email ou (3)Idade? ");
                int escolha = sc.nextInt();
                sc.nextLine();
                pesquisar(escolha, users);
                break;
        }
        sc.close();
    }
}
