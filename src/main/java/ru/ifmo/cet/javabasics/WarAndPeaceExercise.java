package ru.ifmo.cet.javabasics;


import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;


public class WarAndPeaceExercise {

    public static String warAndPeace() throws IOException {
        final Path tome12Path = Paths.get("src", "main", "resources", "WAP12.txt");
        final Path tome34Path = Paths.get("src", "main", "resources", "WAP34.txt");
        final Charset charset = Charset.forName("windows-1251");
        // TODO map lowercased words to its amount in text and concatenate its entries.
        // TODO Also omit any word with lengths less than 4 and frequency less than 10
        HashMap<String, Integer> dict = new HashMap<>();
        ArrayList<String> text;
        text = (ArrayList<String>) Files.readAllLines(tome12Path,charset);
        text.addAll(Files.readAllLines(tome34Path,charset));
        String str = text.toString();
        str = str.replaceAll("[^a-zA-Zа-яА-Я]", " ");
        Arrays.stream(str.split(" "))
                .map(String::toLowerCase)
                .filter(s->s.length()>3)
                .forEach(s->{dict.put(s, dict.getOrDefault(s, 0) + 1);});

        ArrayList<String> ss = new ArrayList<>();


        List <Map.Entry<String, Integer>> list =
                new ArrayList<Map.Entry<String, Integer>>();
        dict.entrySet().stream().filter(pair -> pair.getValue() >= 10).forEach(s->{list.add(s);});
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
                int res=-a.getValue() + b.getValue();
                return res==0? -b.getKey().compareTo(a.getKey()):-a.getValue() + b.getValue();
            }
        });

        StringBuilder b = new StringBuilder();
        int c=0;
        list.forEach(s->b.append((String)s.getKey()+" - "+s.getValue()+"\n"));


        b.deleteCharAt(b.length()-1);
        return b.toString();

    }
    public static void main (String[] args) throws IOException {

        System.out.print(warAndPeace());
    }
}