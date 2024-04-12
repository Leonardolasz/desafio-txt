package model.entities;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Perguntas {

    private List<String> perguntas;

    public List<String> getPerguntas() {
        return perguntas;
    }

    public Perguntas() {
        this.perguntas = new ArrayList<>();
    }

    public Perguntas(List<String> perguntas) {
        this.perguntas = new ArrayList<>();
    }

    public void addPergunta(String pergunta) {
        this.perguntas.add(pergunta);
    }

    public void deletePergunta(int index){
        perguntas.remove(index);
    }

    public static void cadastrarPergunta(String path, List perguntas){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            Scanner sc = new Scanner(System.in);

            System.out.print("Qual pergunda deseja adicionar ao formulário? ");
            String newQuestion = sc.nextLine();
            String addQuestion = (perguntas.size() + 1) + " - " + newQuestion;
            perguntas.add(addQuestion);
            bw.write(addQuestion);
            bw.newLine();
            bw.flush();
            sc.close();

        }
        catch(IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public static void deletarPergunta(List perguntas, String path){

        Scanner sc = new Scanner(System.in);
        System.out.println("===============Perguntas=================");
        perguntas.stream().forEach(System.out::println);
        System.out.println("===========================================");

        System.out.println("Qual index da pergunta que deseja deletar? ");
        int del = sc.nextInt();

        if(del > 4) {
            try {
                perguntas.remove(del - 1);
            }catch (IndexOutOfBoundsException e){
                throw new IndexOutOfBoundsException("Index inexistente!");
            }
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
                System.out.println(perguntas);
                for (Object p: perguntas) {
                    bw.write((String)p);
                    bw.newLine();
                    bw.flush();
                }
            } catch(IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        else{
            System.out.println("As 4 primeiras perguntas não podem ser deletas.");
        }
    }

    @Override
    public String toString() {
        return "Perguntas{" +
                "perguntas=" + perguntas +
                '}';
    }
}
