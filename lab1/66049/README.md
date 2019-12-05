## Zad 4

[Imgur](https://imgur.com/Z0BpsRb)

X - liczba wyprodukowanych produktów P1
Y - liczba wyprodukowanych produktów P2
b1 - ilosc skladnika odzywczego I w P1 (3)
b2 - ilosc skladnika odzywczego II w P1 (8)
b3 - ilosc skladnika odzywczego III w P1 (12)
c1 - ilosc skladnika odzywczego I w P2 (9)
c2 - ilosc skladnika odzywczego II w P2 (4)
c3 - ilosc skladnika odzywczego III w P2 (3)
M - minimalna ilość skladnika odzywczego I w mieszance (27)
N - minimalna ilość składnika odzywczego II w mieszance (32)
Z - minimalna ilość składnika odżywczego III w mieszance (36)
k1 - koszt jednostkowy P1 (6)
k2 - koszt jednostkowy P2 (9)
Koszt wyprodukowania mieszanki k:   6 * X + 9 * Y
Ilość składnika odżywczego I :    3 * X + 9 * Y
Ilość składnika odżywczego II :   8 * X + 4 * Y
Ilość składnika odzywczego III : 12 * X + 3 * Y
a = <b1, b2, b3, c1, c2, c3, M, N, Z, k1, k2>
A = {<b1, b2, b3, c1, c2, c3, M, N, Z, k1, k2> _należy_ N^11}
x = <X,Y>
Omega(a) = {<X,Y> _nalezy_ N^2: b1 * X + c1 * Y >= M, b2 * X + c2 * Y >= N, b3 * X + c3 * Y >= Z}
w = k
W(a, x) = {k naturalne : k = k1 * X + k2 * Y}
W(a) = {k _nalezy_ W(a, x) : k _nalezy_ Omega(a)}
E_a(Z(x*)) = 1 gdy Z(x*) = min(W(a)) = max(Z(X)) po Omega ; 0 w p.p
Z(x) = f(a, x) = 6 * X + 9 * Y

Zadanie optymalizacyjne:
dla danych a _nalezy_ A
wyznaczyc takie x* _nalezy_ Omega(a)
aby E_a(Z(x*)) = 1

Odp A: 
X = 3
Y = 2
k = 36
Odp B: Nie zmieni się bo X > Y w podpunkcie A
