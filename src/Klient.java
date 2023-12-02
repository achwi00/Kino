import java.io.Serializable;
import java.util.HashMap;

public class Klient implements Serializable
{
    private String imie;
    private String nazwisko;
    private String mail;
    private Seans seans;

    HashMap<Character, Integer> miejsce;
    public Seans getSeans() {
        return this.seans;
    }
    public void setSeans(Seans seans) {
        this.seans = seans;
    }
    public Klient(String imie, String nazwisko, String mail, Seans seans)
    {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.mail = mail;
        this.seans = seans;
        miejsce = new HashMap<>();
    }
    @Override
    public String toString()
    {
        return "Klient [imie: " + imie + ", nazwisko: " + nazwisko + ", mail:" + mail + ",\nseans:\n " + seans.toString() + "\nmiejsce:\n" + miejsce + "]";
    }

    public void zarezerwujMiejsce(Character character, Integer i)//rezerwacja jednego miejsca z poziomu klienta
    {
        if (this.seans.zajmijMiejsce(character, i))
        {
            miejsce.put(character, i);
        }else {
            System.out.println("Operacja niemozliwa, miejsce " + character + i + "jest juz niedostepne.");
        }
    }

    //potrzebna zmiana struktury ktora jest podawana jako argument wywolania metody
    //HashMap nie zezwoli na rezerwacje kilku miejsc w tym samym rzedzie
    public void zarezerwujMiejsca(HashMap<Character, Integer> siedzenia)//rezerwacja kilku miejsc z poziomu klienta
    {
        this.seans.zajmijMiejsca(siedzenia);
    }
}