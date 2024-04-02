package application;

import entities.Perguntas;
import entities.User;
import util.Utils;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Locale.setDefault(Locale.US);

        String path = "C:\\temp\\formulario.txt";
        File dir = new File("C:\\temp\\users");
        BufferedReader br = null;
        FileReader fr = null;

        LineNumberReader lnr = new LineNumberReader(new FileReader(path));
        lnr.skip(Long.MAX_VALUE);
        int retorno = lnr.getLineNumber() + 1;

        var perguntas = new ArrayList<>();
        List<User> users = new ArrayList<>();
        String line;

        // Adicionado perguntas inicias do txt na entidade perguntas
        try {
            fr = new FileReader(path);
            br = new BufferedReader(fr);

            while ((line = br.readLine()) != null) {
                perguntas.add(line); // Adiciona a linha à lista de perguntas
            }
        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            try {
                if (br != null) br.close();
                if (fr != null) fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Adicionado users inicias do txt na entidade user
        File folder = new File(String.valueOf(dir));
        File[] listOfFiles = folder.listFiles();

        for(File file: listOfFiles){
            if(file.isFile()){
                try(BufferedReader bfr = new BufferedReader(new FileReader(file))){
                    String name = bfr.readLine();
                    String email = bfr.readLine();
                    int age = Integer.parseInt(bfr.readLine());
                    double height = Double.parseDouble(bfr.readLine());

                    User user = new User(name, email, age, height);
                    users.add(user);
                }
                catch (IOException e){
                    System.out.println("Erro: " + e.getMessage());
                }
            }
        }

        System.out.println("Menu principal\n" +
                "1 - Cadastrar o usuário\n" +
                "2 - Listar todos usuários cadastrados\n" +
                "3 - Cadastrar nova pergunta no formulário\n" +
                "4 - Deletar pergunta do formulário\n" +
                "5 - Pesquisar usuário por nome ou idade ou email\n\n");

        System.out.print("Escolha uma opção de 1 a 5: ");

        int res = sc.nextInt();
        sc.nextLine();

        switch (res){

            // Cadastro Usuário
            case 1:
                users.add(Utils.cadastrar(path, dir, br, fr));

                System.out.println();

                for (Object user: users) {
                    System.out.println(user);
                }
                break;

            // Listando usuários
            case 2: Utils.listar();
                break;

            // Cadastrando nova pergunta
            case 3:
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {

                    System.out.print("Qual pergunda deseja adicionar ao formulário? ");
                    String newQuestion = sc.nextLine();
                    String addQuestion = retorno + " - " + newQuestion;
                    perguntas.add(addQuestion);
                    bw.write(addQuestion);
                    bw.newLine();

                } catch (IOException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;

            // Deletando pergunta
            case 4:
                System.out.println("===============Perguntas=================");
                perguntas.stream().forEach(System.out::println);
                System.out.println("===========================================");

                System.out.println("Qual index da pergunta que deseja deletar? ");
                int del = sc.nextInt();

                if (del > 4) {
                    try {
                        perguntas.remove(del - 1);
                    }catch (IndexOutOfBoundsException e){
                        throw new IndexOutOfBoundsException("Index inexistente!");
                    }
                }
                //TODO implementar deleção de pergunta do txt
                break;

            case 5:
                //pesquisando por nome, email ou idade
                System.out.print("Deseja pesquisar por (1)Nome, (2)Email ou (3)Idade? ");
                int escolha = sc.nextInt();
                sc.nextLine();

                if(escolha == 1 || escolha == 2) {
                    System.out.print("Digite o nome ou email que deseja buscar: ");
                    String name = sc.nextLine().toLowerCase();
                    users.stream()
                            .filter(f -> f.getName().toLowerCase().contains(name))
                            .collect(Collectors.toList())
                            .forEach(System.out::println);

                    users.stream()
                            .filter(f -> f.getEmail().toLowerCase().contains(name))
                            .collect(Collectors.toList())
                            .forEach(System.out::println);
                } else if(escolha == 3) {
                    System.out.print("Digite a idade que deseja buscar: ");
                    int age = sc.nextInt();
                    users.stream()
                            .filter(f -> f.getAge() == age)
                            .collect(Collectors.toList())
                            .forEach(System.out::println);
                }

                break;
        }
    }
}
