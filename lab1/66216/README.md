##Treść zadania

Zadanie 10. W pewnym zakładzie produkcyjnym wytwarza się cztery wyroby (1, 2, 3 i 4). Ich
produkcja wymaga nakładów pewnych środków produkcji. Część z tych środków zakład może
nabywać w nieograniczonych ilościach. Jednak niektóre z nich mogą być wykorzystane tylko w ściśle
określonych granicach. Do tej grupy należą środki A i B. Limity tych środków podano w ostatniej
kolumnie tabeli.
Jednostkowe nakłady środków na produkcje wyrobów przedstawiono w tabeli. Znając zysk ze
sprzedaży jednostki każdego z wyrobów (ostatni wiersz) wyznaczyć optymalne z punktu widzenia
zysków rozmiary produkcji.

Link do tabelki:
https://imgur.com/c6XHaSX

Limit środków A : 26 000
Limit środków B : 100 000

##Model matematyczny zadania

Funkcja celu f : max(6x1+3x2+3x5+2x4)

Gdzie :
x1 - liczba wyrobów 1.
x2 - liczba wyrobów 2.
x3 - liczba wyrobów 3.
x4 - liczba wyrobów 4.

A = 15x1 + 10x2 + 20x3 + 19x4
B = 9x1 + 3x2 + 5x3 + 10x4

Ograniczenia :
15x1 + 10x2 + 20x3 + 19x4 <= 26 000
9x1 + 3x2 + 5x3 + 10x4 <= 100 000
x1,x2,x3,x4 >= 0

##Cel zadania
Należy wyznaczyć ilość każdego z wyrobów, którą należy wyprodukować, przy zachowaniu ograniczeń, aby zyski z produkcji były najwyższe.   

#Skrypt rozwiązujący zadanie wymaga bibliotek SciPy oraz numpy. Aby je zainstalować należy skorzystać z pip:
pip install scipy numpy

##Analiza otrzymanych wyników
Otrzymane rozwiązanie :
x1 = 1733.33
x2 = 0
x3 = 0
x4 = 0

Zatem należy wyprodukować 1733 sztuki wyrobu pierwszego. Przyniesie to najwyższy możliwy zysk z produkcji równy 10 398.

