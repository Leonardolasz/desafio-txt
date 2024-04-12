package utils;

import java.util.Scanner;

public class MenuPrincipal {

    private int escolha;

    public MenuPrincipal(int escolha) {
        this.escolha = escolha;
    }

    public int getEscolha() {
        return escolha;
    }

    public void setEscolha(int escolha) {
        this.escolha = escolha;
    }

    public static int menu(){
        Scanner sc = new Scanner(System.in);
        System.out.println("=====Menu principal=====\n" +
                "1 - Cadastrar o usuário\n" +
                "2 - Listar todos usuários cadastrados\n" +
                "3 - Cadastrar nova pergunta no formulário\n" +
                "4 - Deletar pergunta do formulário\n" +
                "5 - Pesquisar usuário por nome ou idade ou email\n\n");

        System.out.print("Escolha uma opção de 1 a 5: ");

        int res = sc.nextInt();
        return res;
    }
}
