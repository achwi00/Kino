import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class Seans implements Serializable{
    private String tytul;
    private LocalDate data;
    private LocalTime godzina;
    private int ageRating;
    private HashMap<Character, HashMap<Integer, Boolean>> miejsca;

    public String getTytul()
    {
        return tytul;
    }

    public void setTytul(String tytul)
    {
        this.tytul = tytul;
    }

    public LocalDate getData()
    {
        return data;
    }

    public void setData(LocalDate data)
    {
        this.data = data;
    }

    public LocalTime getGodzina()
    {
        return godzina;
    }

    public void setGodzina(LocalTime godzina)
    {
        this.godzina = godzina;
    }

    public int getAgeRating()
    {
        return ageRating;
    }

    public void setAgeRating(int ageRating)
    {
        this.ageRating = ageRating;
    }

    public Seans(String tytul, LocalDate data, LocalTime godzina, int ageRating, int liczbaRzedow, int liczbaMiejsc) {
        this.tytul = tytul;
        this.data = data;
        this.godzina = godzina;
        this.ageRating = ageRating;
        this.miejsca = new HashMap<>(liczbaRzedow);
        Character ch = 'A'; //rzedy zaczynajac od A
        Integer nrMiejsca = 1; //miejsca w rzedzie zaczynajac od 1


        for(int i = 0; i < liczbaRzedow; ++i)
        {
            HashMap<Integer, Boolean> m = new HashMap<>(liczbaMiejsc); //hashmapa: nr miejsca, czyzajete
            for(int j = nrMiejsca; j<=liczbaMiejsc;j++) //zapelniamy kazdy rzad od 1 do liczbaMiejsc
            {
                m.put(j, false); //kazde miejsce niezajete w rzedzie
            }
            miejsca.put(ch,m); //wiazemy rzad z hashmapą miejsc
            ch++; //kolejny rzad
        }

    }
    @Override
    public String toString()
    {
        return "Seans [tytul: "+ tytul + ", data: " + data + ", godzina: " + godzina + ", ograniczenie wiekowe:  " + ageRating + ",\nmiejsca:\n" + miejsca + "]";
    }

    public void zapiszRezerwacje() throws IOException //zapisz do pliku liste miejsc
    {
        FileWriter f = new FileWriter("rezerwacje.txt");
        BufferedWriter out = new BufferedWriter(f);
        out.write(miejsca.toString());
        out.close();
    }
    public void odczytajRezerwacje() throws IOException //odczytaj z pliku liste miejsc
    {
        FileInputStream f = new FileInputStream("rezerwacje.txt");
        DataInputStream in = new DataInputStream(f);
        BufferedReader r = new BufferedReader(new InputStreamReader(in));
        String strLine;

        while ((strLine = r.readLine()) != null)
        {
            System.out.println(strLine); //wyrzucenie w konsoli
        }
        in.close();
    }
    public void wyswietlMiejsca()
    {
        for (Map.Entry<Character, HashMap<Integer, Boolean>> biezacyrzad : miejsca.entrySet())
        {
            Character rzad = biezacyrzad.getKey();
            HashMap<Integer, Boolean> siedzenia = biezacyrzad.getValue();

            System.out.println("Rzad " + rzad + ":");
            for (Map.Entry<Integer, Boolean> biezaceSiedzenie : siedzenia.entrySet())
            {
                Integer numerSiedzenia = biezaceSiedzenie.getKey();
                Boolean czyZajete = biezaceSiedzenie.getValue();

                System.out.println("Siedzenie " + numerSiedzenia + " czy zajete: " + czyZajete);
            }
        }
    }
    public boolean zajmijMiejsce(Character rzad, Integer i)
    {
        if (miejsca.containsKey(rzad))//jesli wgl mamy taki rzad
        {
            for (Map.Entry<Character, HashMap<Integer, Boolean>> biezacyRzad : miejsca.entrySet())
            {
                System.out.println(biezacyRzad.getKey());
                if (biezacyRzad.getKey()==rzad)
                {
                    System.out.println("klucz rowentry:" + biezacyRzad.getKey()); // ok dotad
                    HashMap<Integer, Boolean> siedzenia = biezacyRzad.getValue();
                    for (Map.Entry<Integer, Boolean> biezaceSiedzenie : siedzenia.entrySet())
                    {
                        //System.out.println("klucz seatEntry: " + biezaceSiedzenie.getKey());
                        if (biezaceSiedzenie.getKey().equals(i))
                        {
                            if(biezaceSiedzenie.getValue().equals(false))
                            {
                                //System.out.println("ostateczny klucz biezaceSiedzenie:" + biezaceSiedzenie.getKey());
                                System.out.println("key: " + biezaceSiedzenie.getKey() + "value" + biezaceSiedzenie.getValue());
                                biezaceSiedzenie.setValue(true);
                                return true;
                            }
                            else return false;
                        }
                    }
                }
            }


        }
        return false;
    }
    public void zajmijMiejsca(HashMap<Character, Integer> wybraneMiejsca)
    {
        //System.out.println(wybraneMiejsca.size());
            //dla każdej jednej pary klucz-wartosc zajmij miejsce(para.getKey(),para.getValue())
            //zajmijMiejsce()
            for(Map.Entry<Character, Integer> siedzenie : wybraneMiejsca.entrySet())
            {
                if(!zajmijMiejsce(siedzenie.getKey(), siedzenie.getValue()))
                {
                    System.out.println("Niemozliwe zajecie miejsca " + siedzenie.getValue() + " w rzedzie " + siedzenie.getKey());
                }
            }
    }
}