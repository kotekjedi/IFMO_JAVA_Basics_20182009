package ru.ifmo.cet.javabasics;

/**
 * Нужно реализовать констурктор и метод, возвращающий слова песни про бутылки на стене.
 * <p>
 * Слова следующие:
 * <p>
 * 99 bottles of beer on the wall, 99 bottles of beer
 * Take one down, pass it around, 98 bottles of beer
 * 98 bottles of beer on the wall, 98 bottles of beer
 * Take one down, pass it around, 97 bottles of beer
 * 97 bottles of beer on the wall, 97 bottles of beer
 * Take one down, pass it around, 96 bottles of beer
 * 96 bottles of beer on the wall, 96 bottles of beer
 * Take one down, pass it around, 95 bottles of beer
 * 95 bottles of beer on the wall, 95 bottles of beer
 * ...
 * <p>
 * 3 bottles of beer on the wall, 3 bottles of beer
 * Take one down, pass it around, 2 bottles of beer
 * 2 bottles of beer on the wall, 2 bottles of beer
 * Take one down, pass it around, 1 bottles of beer
 * 1 bottle of beer on the wall, 1 bottle of beer
 * Take one down, pass it around, no more bottles of beer on the wall
 * No more bottles of beer on the wall, no more bottles of beer
 * Go to the store and buy some more, 99 bottles of beer on the wall
 * <p>
 * Дело усложняется тем, что текст песни переменный:
 * За раз может быть взято несколько бутылок.
 * Значение передается в качестве параметра конструктора
 * Нужно ограничить возможность взятия бутылок натуральным число не более 99 бутылок за раз.
 */
public class BottleSong {

    private int bottles;


    BottleSong(int bottleTakenAtOnce) {
        //TODO
         if (bottleTakenAtOnce<100 && bottleTakenAtOnce>0) {bottles=bottleTakenAtOnce;}
    }

    public String getBottleSongLyrics() {
        //TODO
        // * 3 bottles of beer on the wall, 3 bottles of beer
        // * Take one down, pass it around, 2 bottles of beer

        if (bottles > 99 || bottles < 1) {
            throw new IllegalArgumentException();
        }
        String [] ones = {"","one ","two ","three ","four ","five ","six ","seven ","eight ","nine ","ten "};
        String [] ones1 = {"","one","two","three","four","fif","six","seven","eight","nine","ten"};
        String [] tens = {"","teen ","twenty ","thirty ","forty ","fifty ","sixty ","seventy ","eighty ","ninety ","hundred"};
        String spelling;
        String wall = " bottles of beer on the wall, ";
        String part = " bottles of beer on the wall.\n";
        if(bottles > 19){
            spelling =(tens[(bottles)/10]+ ones[bottles%10]);
        }
        else if (bottles>13) {spelling =ones1[bottles%10]+"teen ";}
        else if (bottles==11) {spelling ="eleven ";}
        else if (bottles==12) {spelling ="twelve ";}
        else if (bottles==13) {spelling ="thirteen ";}
        else{
            spelling =(ones[bottles]);
        }

        String end = " bottles of beer.\nTake " + spelling + "down and pass around, ";
        int current = 99;
        StringBuilder b = new StringBuilder();
       // b.append(current).append(wall).append(current).append(end).append(current-bottles).append(part);
        while (current >bottles) {
            b.append(current).append(wall).append(current).append(end).append(current-bottles);
                    if (current-bottles!=1) {b.append(part);}
                    else {b.append(" bottle of beer on the wall.\n");}
            current-=bottles;
        }
        if(current > 19){
            spelling =(tens[(current)/10]+ ones[current%10]);
        }
        else if (current>13) {spelling =ones1[current%10]+"teen ";}
        else if (current==11) {spelling ="eleven ";}
        else if (current==12) {spelling ="twelve ";}
        else if (current==13) {spelling ="thirteen ";}
        else{
            spelling =(ones[current]);
        }
        if (current==1) {
        b.append(current).append(" bottle of beer on the wall, ").append(current).append(" bottle of beer.\n");}
        if ( current!=1) {b.append(current).append(" bottles of beer on the wall, ").append(current).append(" bottles of beer.\n");}
        b.append("Take " +spelling+"down and pass around, no more bottles of beer on the wall.\n");
        b.append("No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n");

       return b.toString();

    }
    public static void main (String[] args){
        String Lyrics = new BottleSong(40).getBottleSongLyrics();
        System.out.print(Lyrics);
    }
}
