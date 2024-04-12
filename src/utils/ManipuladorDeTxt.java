package utils;

import model.entities.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class ManipuladorDeTxt {

    public static void perguntasTxt(String path,BufferedReader br, FileReader fr, List perguntas, String line){
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
    }

    public static void usersTxt(File dir, List users){
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
                catch(IOException e){
                    System.out.println("Erro: " + e.getMessage());
                }
            }
        }
    }
}
