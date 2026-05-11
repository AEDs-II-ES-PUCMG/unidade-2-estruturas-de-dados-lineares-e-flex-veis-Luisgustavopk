import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.function.Predicate;

public class Lista<E> implements Iterable<E> {

    private Celula<E> cabeca;
    private Celula<E> cauda;
    private int tamanho;

    public Lista() {
        Celula<E> sentinela = new Celula<>();
        cabeca = cauda = sentinela;
        tamanho = 0;
    }

    public boolean vazia() {
        return cabeca == cauda;
    }

    public int tamanho() {
        return tamanho;
    }

    public void inserirFinal(E item) {
        Celula<E> nova = new Celula<>(item);
        cauda.setProximo(nova);
        cauda = nova;
        tamanho++;
    }

    public void inserirInicio(E item) {
        Celula<E> nova = new Celula<>(item, cabeca.getProximo());
        if (vazia()) cauda = nova;
        cabeca.setProximo(nova);
        tamanho++;
    }

    public E removerInicio() {
        if (vazia()) throw new NoSuchElementException("Lista vazia!");
        Celula<E> primeiro = cabeca.getProximo();
        cabeca.setProximo(primeiro.getProximo());
        if (primeiro == cauda) cauda = cabeca;
        primeiro.setProximo(null);
        tamanho--;
        return primeiro.getItem();
    }

    public void imprimir() {
        if (vazia()) {
            System.out.println("A lista está vazia!");
        } else {
            Celula<E> aux = cabeca.getProximo();
            while (aux != null) {
                System.out.println(aux.getItem());
                aux = aux.getProximo();
            }
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Celula<E> atual = cabeca.getProximo();

            @Override
            public boolean hasNext() {
                return atual != null;
            }

            @Override
            public E next() {
                if (!hasNext()) throw new NoSuchElementException();
                E item = atual.getItem();
                atual = atual.getProximo();
                return item;
            }
        };
    }

    // Tarefa 1
    public E buscarPor(Comparator<E> criterioDeBusca, E item) {

             if (vazia()) throw new IllegalStateException("Lista vazia!");
             
             Celula<E> atual = cabeca.getProximo();
             while (atual != null) {
                if (criterioDeBusca.compare(item, (E) atual) == 0){
                    return item;
                }
                atual = atual.getProximo();
             }

             return null;

        // TODO: Percorrer sequencialmente a lista e retornar o primeiro elemento
        //       equivalente a item segundo o criterioDeBusca (compare == 0).
        //       Retornar null caso nenhum elemento seja encontrado.
    }

    // Tarefa 2
    public double somarMultiplicacoes(Function<E, Double> extratorValor, Function<E, Integer> extratorFator) {

         if (vazia()) throw new IllegalStateException("Lista vazia!");

          double soma = 0;
    
          Celula<E> atual = cabeca.getProximo();
             while (atual != null) {
               soma += extratorValor.apply((E) atual) * extratorFator.apply((E) atual);
                atual = atual.getProximo();
             }
         
        return soma;

        // TODO: Para cada elemento, extrair o valor (extratorValor) e o fator (extratorFator),
        //       multiplicá-los e acumular no somatório final.
        //       Lançar IllegalStateException se a lista estiver vazia.
    }

    // Tarefa 3
    public Lista<E> filtrar(Predicate<E> condicional) {

         if (vazia()) throw new IllegalStateException("Lista vazia!");

        Lista<E> novaLista = new Lista<>();

         Celula<E> atual = cabeca.getProximo();
             while (atual != null) {
                if (condicional.test((E) atual)) {
                    novaLista.inserirInicio((E) atual);
                }
                atual = atual.getProximo();
             }

             return novaLista;
    }
}
