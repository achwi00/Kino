import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Main
{
    public static void main(String[] args) throws IOException, ClassNotFoundException
    {

//        HashMap<Character, HashMap<Integer, Boolean>> miejscaTest = new HashMap<>();
//
//        // Add some data to the HashMap
//        miejscaTest.put('A', new HashMap<>());
//        miejscaTest.get('A').put(1, false);
//        miejscaTest.get('A').put(2, true);
//        miejscaTest.get('A').put(3, false);
//        miejscaTest.put('B', new HashMap<>());
//        miejscaTest.get('B').put(1, true);
//        miejscaTest.get('B').put(2, false);
//        miejscaTest.get('B').put(3, true);
//
//        // Iterate through the rows and seats
//        for (Map.Entry<Character, HashMap<Integer, Boolean>> rowEntry : miejscaTest.entrySet()) {
//            Character row = rowEntry.getKey();
//            HashMap<Integer, Boolean> seats = rowEntry.getValue();
//
//            System.out.println("Row " + row + ":");
//            for (Map.Entry<Integer, Boolean> seatEntry : seats.entrySet()) {
//                Integer seatNumber = seatEntry.getKey();
//                Boolean isTaken = seatEntry.getValue();
//
//                System.out.println("Seat " + seatNumber + " is taken: " + isTaken);
//            }
//        }

        Seans seans = new Seans("TM", LocalDate.now(), LocalTime.MIDNIGHT, 16, 3, 5);
        seans.wyswietlMiejsca();

        //seans.zajmijMiejsce('A', 3);
        //seans.wyswietlMiejsca();

        Klient klient0 = new Klient("Katarzyna", "Nowak", "knowak@gmail.com", seans);
        klient0.zarezerwujMiejsce('B', 5);
        seans.wyswietlMiejsca();

        HashMap<Character, Integer> listaMiejsc = new HashMap<>();
        listaMiejsc.put('B', 1);
        listaMiejsc.put('C', 4);
        listaMiejsc.put('D', 4);
        Klient klient1 = new Klient("Mariusz","Kowalski","mariusz123@02.com", seans);
        klient1.zarezerwujMiejsca(listaMiejsc);
        System.out.println("Proba zajecia miejsca B1, C4, D4");
        seans.wyswietlMiejsca();

        System.out.println("Proba zajecia miejsca B5");
        Klient klient2 = new Klient("Barbara","Kwiatkowska", "barb33@gmail.com", seans);
        klient2.zarezerwujMiejsce('B',5);
        seans.wyswietlMiejsca();

        System.out.println("zapisanie rezerwacji, odczyt rezerwacji: ");
        seans.zapiszRezerwacje();
        seans.odczytajRezerwacje();

        System.out.println("Serializacja klient0:");
        //nowy obiekt ObjectOutputStream do zapisu
        ObjectOutputStream wy = new ObjectOutputStream(new FileOutputStream(".klient.dat"));
        //zapisanie klienta
        wy.writeObject(klient0);
        wy.close();
        //nowy obiekt ObjectInputStream do odczytu
        ObjectInputStream we = new ObjectInputStream(new FileInputStream(".klient.dat"));
        //odczyt z pliku
        Klient k = (Klient) we.readObject();
        //wypisanie na konsole danych i zamknięcie strumienia
        System.out.println(k);
        we.close();



    }
}