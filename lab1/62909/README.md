#Zadanie

Zadanie 1. Przedsiebiorstwo produkuje dwa wyroby. Do ich produkcji zuzywa sie m.in. dwa limitowane surowce. Zuzycie tych surowców na jednostke kazdego z wyrobów, dopuszczalne limity zuzycia oraz zyski jednostkowe ze sprzedazy podano w tabeli ponizej.

Wyroby: w1, w2

Zuzycie surowca na jednostke: I: dla w1: 8 dla w2: 7 II: dla w1: 16 dla w2: 4

Zysk jednostkowy [zl]: dla w1: 2 dla w2: 4

Limit zuzycia surowca: dla I: 96000 dla II: 56000

#Zadanie optymalizacyjne

1.	Ile nalezy wyprodukowac wyrobu W1, a ile W2, aby nie przekraczajac limitów zmaksymalizowac zysk ze sprzedazy wyrobów?
2.	Jak zmieni sie rozwiazanie, jesli proces produkcyjny pozwala na wyprodukowanie maksymalnie 5000 szt. wyrobu W1, oraz maksymalnie 4000 szt. wyrobu W2?

#Model matematyczny

funkcja celu: 2w1 + 4w2 -> max

ograniczenia dla punktu 1: 8w1 + 16w2 <= 96000 7w1 + 4w2 <= 56000 w1 ,w2 >= 0

dla punktu 2 dodatkowo: w1 <= 5000 oraz w2 <= 4000

#Wynik

1)Aby zmaksymalizowac zysk przy zachowaniu limitów nalezy wyprodukowac 0 w1 i 6000 w2
2)Gdy w1 jest nie wiecej niz w2, nalezy wyprodukowac 4000 w1 i 4000 w2
