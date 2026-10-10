package principal;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import model.Produto;

public class Principal {
    public static void main(String[] args) {
        //1 - Dada a lista de números inteiros a seguir, encontre o maior número dela.
        List<Integer> numeros = Arrays.asList(10, 20, 30, 40, 50);
        Optional<Integer> numerosMaior = numeros.stream().max(Integer::compare);
        System.out.println(numerosMaior);

        //2 - Dada a lista de palavras (strings) abaixo, agrupe-as pelo seu tamanho. No código a seguir, há um exemplo prático do resultado esperado.
        List<String> palavras = Arrays.asList("java", "stream", "lambda", "code");
        Map<Integer, List<String>> palavrasSeparadas = palavras.stream()
                .collect(Collectors.groupingBy(String::length));
                //n -> n.toString().length()
        System.out.println(palavrasSeparadas);

        //3 - Dada a lista de nomes abaixo, concatene-os separados por vírgula. No código a seguir, há um exemplo prático do resultado esperado.
        List<String> nomes = Arrays.asList("Alice", "Bob", "Charlie");
        String nomesConcatenados = nomes.stream()
                .collect(Collectors.joining(", "));
        System.out.println(nomesConcatenados);

        //4 - Dada a lista de números inteiros abaixo, calcule a soma dos quadrados dos números pares.
        List<Integer> numeros2 = Arrays.asList(1, 2, 3, 4, 5, 6);
        Integer somaDosQuadrados = numeros2.stream()
                .filter(n -> n%2==0)
                .map(n -> n *= n)
                .reduce(0, Integer::sum);
        System.out.println(somaDosQuadrados);

        //5 - Dada uma lista de números inteiros, separe os números pares dos ímpares.
        Map<String, List<Integer>> imparesPares = numeros2.stream()
                .collect(Collectors.groupingBy(n -> n % 2 == 0?"Pares":"Impares"));
        Map<Boolean, List<Integer>> particionado = numeros2.stream()
                                                          .collect(Collectors.partitioningBy(n -> n % 2 == 0));
        System.out.println(imparesPares);//prefiro o meu sinceramente
        System.out.println(particionado);

        //6 - Dada a lista de produtos abaixo, agrupe-os por categoria em um Map<String, List<Produto>>.
        List<Produto> produtos = Arrays.asList(
            new Produto("Smartphone", 800.0, "Eletrônicos"),
            new Produto("Notebook", 1500.0, "Eletrônicos"),
            new Produto("Teclado", 200.0, "Eletrônicos"),
            new Produto("Cadeira", 300.0, "Móveis"),
            new Produto("Monitor", 900.0, "Eletrônicos"),
            new Produto("Mesa", 700.0, "Móveis")
        );
        Map<String, List<Produto>> produtosPorCategoria = produtos.stream()
                .collect(Collectors.groupingBy(Produto::getCategoria));
        System.out.println(produtosPorCategoria);

        //7 - Dada a lista de produtos acima, conte quantos produtos há em cada categoria e armazene em um Map<String, Long>.
        Map<String, Long> produtosContados = produtos.stream()
                .collect(Collectors.groupingBy(Produto::getCategoria, Collectors.counting()));
        System.out.println(produtosContados);

        //8 - Dada a lista de produtos acima, encontre o produto mais caro de cada categoria e armazene o resultado em um Map<String, Optional<Produto>>.
        Map<String, Optional<Produto>> maisCaro = produtos.stream()
                .collect(Collectors.groupingBy(Produto::getCategoria,
                    Collectors.maxBy(Comparator.comparingDouble(Produto::getPreco))));
        System.out.println(maisCaro);

        //9 - Dada a lista de produtos acima, calcule o total dos preços dos produtos em cada categoria e armazene o resultado em um Map<String, Double>.
        Map<String, Double> totalDosPrecos = produtos.stream()
                .collect(Collectors.groupingBy(Produto::getCategoria, 
                    Collectors.summingDouble(Produto::getPreco)));
        System.out.println(totalDosPrecos);
    }
}
