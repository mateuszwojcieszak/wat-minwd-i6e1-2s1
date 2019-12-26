# wat-minwd-i6e1-2s1

## Piotr Filochowski, grupa I6E2S1

## Rozwiązanie zadania

Rozwiązałem zadanie przy pomocy biblioteki https://mvnrepository.com/artifact/org.ojalgo/ojalgo/47.1.1 w Javie


## Treść zadania:
Zadanie 5
Zadanie 5. Dziecko w pewnym wieku potrzebuje określonych ilości witamin. Ilość witamin dostarczanych przez pewne produkty przedstawiono poniżej.


|	Produkty| A	    | B	    | C	    | E         |Koszt jednostkowy [zł]   |
|-----------|-------|-------|-------|-----------|-------------------------|
| P1	    | 6	    | 1	    | 9	    | 6	        | 1,2					  |
| P2	    | 3	    | 3	    | 1	    | 6	        | 1,8                     |
| Min ilośc	| 120	| 60	| 36	| 180	    |                         |

## a) 1.	Ile należy wykorzystać produktów P1 i P2, aby spełniając wymagania koszt spełnienia zapotrzebowania był jak najniższy?

## Model matematyczny

### Cechy

x1 -- ilosc zjedzonego produktu P1
x2 -- ilosc zjedzonego produktu P2

### Ograniczenia

6x1 + 3x2 > 120
1x1 + 3x2 > 60
9x1 + 1x2 > 36
6x1 + 6x2 > 180

### Funkcja celu

Z = 1,2x1 + 1,8x2 ---> min

### Zrzut z działania aplikacji
OPTIMAL 44.99999999999999 @ { 15.000000000000016, 14.999999999999986 }
############################################
0 <= x1: 15 (1.2)
0 <= x2: 15 (1.8)
36 <= Ograniczenie 3: 150.0
60 <= Ograniczenie 2: 60.0
30 <= Ograniczenie 4: 30.0
40 <= Ograniczenie 1: 45.0
############################################

### Wynik
x1 = 15.000000000000016
x2 = 14.999999999999986
Wartość funkcji celu 44.99999999999999

## b) 2.	Jak zmieni się rozwiązanie, jeśli ze względu na szkodliwe działanie nie można podawać więcej niż 240 jednostek witaminy A?

## Model matematyczny

### Cechy

x1 -- ilosc zjedzonego produktu P1
x2 -- ilosc zjedzonego produktu P2

### Ograniczenia

240 >  6x1 + 3x2 > 120
1x1 + 3x2 > 60
9x1 + 1x2 > 36
6x1 + 6x2 > 180

### Funkcja celu

Z = 1,2x1 + 1,8x2 ---> min


### Print z aplikacji:

OPTIMAL 44.99999999999999 @ { 15.000000000000016, 14.999999999999986 }
############################################
0 <= x1: 15 (1.2)
0 <= x2: 15 (1.8)
36 <= Ograniczenie 3: 150.0
60 <= Ograniczenie 2: 60.0
30 <= Ograniczenie 4: 30.0
40 <= Ograniczenie 1: 45.0 <= 80
############################################

### Wynik

x1 = 14.999999999999996
x2 = 14.999999999999986
Wartość funkcji celu 44.99999999999999