package util;

import entities.User;

import java.io.*;
import java.util.Locale;
import java.util.Scanner;

public class Utils {

    public static User cadastrar(String path, File dir, BufferedReader br, FileReader fr){
        Scanner sc = new Scanner(System.in).useLocale(Locale.US);

        String name = null;
        String email = null;
        int age = 0;
        double height = 0.0;
        try {
            fr = new FileReader(path);
            br = new BufferedReader(fr);
            String line;

            // Lê e imprime cada linha do arquivo
            while ((line = br.readLine()) != null) {
                System.out.println(line);

                // Solicita entrada do usuário após cada pergunta
                if (name == null) {
                    name = sc.nextLine();
                } else if (email == null) {
                    email = sc.nextLine();
                } else if (age == 0) {
                    age = sc.nextInt();
                    sc.nextLine(); // Consumir a quebra de linha pendente
                } else if (height == 0.0) {
                    height = sc.nextDouble();
                    sc.nextLine(); // Consumir a quebra de linha pendente
                }
            }


        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (br != null)
                    br.close();
                if (fr != null)
                    fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // Cria um objeto entities.User com base nas respostas fornecidas
        User user1 = new User(name, email, age, height);

        // Obtendo lista de arquivos existentes
        if (!dir.exists()) {
            dir.mkdirs(); // Cria a pasta "users" e todos os diretórios pai necessários
        }

        File[] files = dir.listFiles(File::isFile);

        // Verificando o maior número de arquivos existentes
        int maxFileNumer = 0;
        for (File file : files) {
            String fileName = file.getName();
            int fileNumber = Integer.parseInt(fileName.split("-")[0]);
            if (fileNumber > maxFileNumer) {
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
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
        Utils.listar();
        return user1;
    }

    public static void listar() {
        File dir = new File("C:\\temp\\users");
        File[] files = dir.listFiles(File::isFile);
        for (int i = 0; i < files.length; i++) {
            try (BufferedReader bfr = new BufferedReader(new FileReader(files[i]))) {
                String line = bfr.readLine();
                System.out.println((i + 1) + " - " + line);
            } catch (IOException e) {
                System.out.println("Erro ao listar nomes: " + e.getMessage());
            }
        }
    }
}
