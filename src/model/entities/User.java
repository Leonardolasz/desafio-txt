package model.entities;

import model.exceptions.DomainException;
import utils.MenuPrincipal;

import java.io.*;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class User {

    private String name;
    private String email;
    private Integer age;
    private  Double height;
    private List<String> users;

    public User() {
    }

    public User(String name, String email, Integer age, Double height) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.height = height;
    }

    public List<String> getUsers() {
        return users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public static User cadastrar(List perguntas, String path, File dir, BufferedReader br, FileReader fr, List<User> users){
        Scanner sc = new Scanner(System.in);

        String name = null;
        String email = null;
        int age = 0;
        double height = 0.0;
        try {
            fr = new FileReader(path);
            br = new BufferedReader(fr);

            // Solicita entrada do usuário após cada pergunta
            for(int i = 0; i < perguntas.size();i++){

                if(perguntas.get(i).equals("1 - Qual seu nome?")){
                    System.out.println(perguntas.get(i));
                    name = sc.nextLine();
                    if(name.trim().replaceAll("\\s+", "").length() < 10){
                        throw new DomainException("Nome deve ter no mínimo 10 caracteres");
                    }
                }
                else if(perguntas.get(i).equals("2 - Qual seu email?")){
                    System.out.println(perguntas.get(i));
                    email = sc.nextLine();
                    if(!email.contains("@")){
                        throw new DomainException("Email inválido");
                    }
                    for(int in = 0;in < users.size(); in++){
                        if(users.get(in).getEmail().equals(email)){
                            System.out.println("Email já está cadastrado! tente novamente com outro email.");
                            System.out.println("\n\n");
                            MenuPrincipal.menu();
                        }

                    }
                }
                else if(perguntas.get(i).equals("3 - Qual sua idade?")){
                    System.out.println(perguntas.get(i));
                    age = sc.nextInt();
                    sc.nextLine(); // Consumir a quebra de linha pendente
                    if(age < 18){
                        throw new DomainException("Usuário deve ter mais de 18 anos.");
                    }
                }
                else if(perguntas.get(i).equals("4 - Qual sua altura?")){
                    System.out.println(perguntas.get(i));
                    height = sc.nextDouble();
                    sc.nextLine(); // Consumir a quebra de linha pendente
                }
                else {
                    System.out.println(perguntas.get(i));
                    String aux = sc.nextLine();
                }
                while(users.get(i).getEmail().equals(email)){
                    System.out.println("Email já cadastrado. Digite novamente: ");
                    email = sc.nextLine();
                }
            }
            System.out.println(name + " " + email + " " + age + " " + height);
            // Lê e imprime cada linha do arquivo
//            while((line = br.readLine()) != null) {
//                System.out.println(line);
//
//                // Solicita entrada do usuário após cada pergunta
//                if(name == null) {
//                    name = sc.nextLine();
//                }
//                else if(email == null) {
//                    email = sc.nextLine();
//                }
//                else if(age == 0) {
//                    age = sc.nextInt();
//                    sc.nextLine(); // Consumir a quebra de linha pendente
//                }
//                else if(height == 0.0) {
//                    height = sc.nextDouble();
//                    sc.nextLine(); // Consumir a quebra de linha pendente
//                }
//                else{
//                String aux = sc.nextline();
//                }
//            }


        } catch(IOException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                if(br != null)
                    br.close();
                if(fr != null)
                    fr.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        // Cria um objeto model.entities.User com base nas respostas fornecidas
        User user1 = new User(name, email, age, height);

        // Obtendo lista de arquivos existentes
        if(!dir.exists()) {
            dir.mkdirs(); // Cria a pasta "users" e todos os diretórios pai necessários
        }

        File[] files = dir.listFiles(File::isFile);

        // Verificando o maior número de arquivos existentes
        int maxFileNumer = 0;
        for(File file : files) {
            String fileName = file.getName();
            int fileNumber = Integer.parseInt(fileName.split("-")[0]);
            if(fileNumber > maxFileNumer) {
                maxFileNumer = fileNumber;
            }
        }

        // Determinando o próximo número do arquivo
        int nextFileNumber = maxFileNumer + 1;

        try {
            // Criando nome do arquivo
            String fileName = nextFileNumber + "-" + user1.getName().toUpperCase() + ".txt";

            // Criando FileWriter e BufferedWriter
            FileWriter writer = new FileWriter(dir + "\\" + fileName);
            BufferedWriter bw = new BufferedWriter(writer);

            // Escrevendo dados no arquivo
            bw.write(user1.getName());
            bw.newLine();
            bw.write(user1.getEmail());
            bw.newLine();
            bw.write(String.valueOf(user1.getAge()));
            bw.newLine();
            bw.write(String.valueOf(user1.getHeight()));
            bw.newLine();

            // Flush e fechar FileWriter
            bw.flush();
            writer.flush();
            writer.close();
            bw.close();
        } catch(IOException e) {
            System.out.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
        return user1;
    }

    public static void listar(List<User> users) {
        for (int i = 0; i < users.size() ;i++) {
            System.out.println(i+1 + " - " + users.get(i).getName());
        }
    }

    public static void pesquisar(int escolha, List<User> users){
        Scanner sc = new Scanner(System.in);

        if(escolha == 1) {
            System.out.print("Digite o nome que deseja buscar: ");
            String name = sc.nextLine().toLowerCase();
            users.stream()
                    .filter(f -> f.getName().toLowerCase().contains(name))
                    .collect(Collectors.toList())
                    .forEach(System.out::println);

        }
        else if(escolha == 2) {
            System.out.print("Digite o email que deseja buscar: ");
            String name = sc.nextLine().toLowerCase();
            users.stream()
                    .filter(f -> f.getEmail().toLowerCase().contains(name))
                    .collect(Collectors.toList())
                    .forEach(System.out::println);
        }
        else if(escolha == 3) {
            System.out.print("Digite a idade que deseja buscar: ");
            int age = sc.nextInt();
            users.stream()
                    .filter(f -> f.getAge() == age)
                    .collect(Collectors.toList())
                    .forEach(System.out::println);
        }
        sc.close();
    }

    @Override
    public String toString() {
        return name + " "
                + email + " "
                + age + " "
                + height
                ;
    }
}
