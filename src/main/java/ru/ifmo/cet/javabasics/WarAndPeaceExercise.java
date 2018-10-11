package ru.ifmo.cet.javabasics;

import jdk.nashorn.api.tree.Tree;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class WarAndPeaceExercise {

    public static String warAndPeace() throws IOException {
        final Path tome12Path = Paths.get("src", "main", "resources", "WAP12.txt");
        final Path tome34Path = Paths.get("src", "main", "resources", "WAP34.txt");
        final Charset charset = Charset.forName("windows-1251");
        // TODO map lowercased words to its amount in text and concatenate its entries.
        // TODO If word "котик" occurred in text 23 times then its entry would be "котик - 23\n".
        // TODO Entries in final String should be also sorted by amount and then in alphabetical order if needed.
        // TODO Also omit any word with lengths less than 4 and frequency less than 10
        HashMap<String, Integer> dict = new HashMap<>();
        ArrayList<String> text;
        text = (ArrayList<String>) Files.readAllLines(tome12Path,charset);
        text.addAll(Files.readAllLines(tome34Path,charset));
        for (String str:text
             ) {
            str = str.replaceAll("[^a-zA-Zа-яА-Я]", " ");
            for (String word:str.split(" ")
                 ) {if (word.length()>=4) {
                if (dict.containsKey(word.toLowerCase())) {
                    dict.replace(word.toLowerCase(), dict.get(word.toLowerCase()) + 1);
                } else {
                    dict.put(word.toLowerCase(), 1);
                }
            }

            }
        }
        ArrayList<String> ss = new ArrayList<>();
        for(Map.Entry<String, Integer> pair  : dict.entrySet()) {
             Integer value = pair.getValue();
            if (value<10) {ss.add(pair.getKey());}
        }
        for (String s: ss
             ) {
            dict.remove(s);
        }

        List <Map.Entry<String, Integer>> list =
                new ArrayList<Map.Entry<String, Integer>>(dict.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
                int res = -a.getValue() + b.getValue();
                if(res == 0) res = -b.getKey().compareTo(a.getKey());
                return res;
            }
        });
        StringBuilder b = new StringBuilder();
        int c=0;
        for (Map.Entry e:list
             ) {b.append((String)e.getKey()+" - "+e.getValue()); c++;
                if (c!=list.size()) {b.append("\n");}
        }
       // throw new UnsupportedOperationException();

        return b.toString();

}
    public static void main (String[] args) throws IOException {

        System.out.print(warAndPeace());
    }
}