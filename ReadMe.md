Prosta aplikacja konsolowa do obsługi kina (rezerwacji miejsc na seans, powiązanie ich z klientem).
Funkcje:
- stworzenie klienta
- stworzenie seansu
- rezerwacja miejsca na seansie (powiązanie klient-seans)
- rezerwacja kilku miejsc na seans *wymaga poprawek
- zapisanie wszystkich miejsc na seansie do pliku, włącznie z informacją, czy są wolne
- odczytanie miejsc z pliku.

Potrzebne zmiany:
- w klasie Klient przerobienie metody zarezerwujMiejsca(). Struktura danych HashMap przyjmowana jako argument wywołania
metody nie jest odpowiednia; HashMap pozwala na przechowanie elementów klucz- wartość, więc do jednego klucza(rzędu) nie
przypiszemy kilku wartości (miejsc), co powoduje, że nie jest możliwe zajęcie kilku miejsc w tym samym rzędzie.
- w klasie Seans w metodzie zajmijMiejsca(), konieczne będą zmiany wywołane powyższymi zmianami w klasie Klient