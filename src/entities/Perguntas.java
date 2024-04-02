package entities;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public String toString() {
        return "Perguntas{" +
                "perguntas=" + perguntas +
                '}';
    }
}
