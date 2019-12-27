Robert WIEWIÓRA I6E1S1 ZAD 9
Przedsiębiorstwo produkuje trzy wyroby. Do ich produkcji zużywa się m.in. dwa limitowane surowce. Zużycie tych surowców na jednostkę każdego z wyrobów, dopuszczalne limity zużycia oraz zyski jednostkowe ze sprzedaży podano w tabeli poniżej. Należy wyznaczyć takie ilości poszczególnych wyrobów, aby zysk był maksymalny.
# Instalacja
pip install -r requirements.txt w folderze z tym plikiem
python project.py
Wyroby	Zużycie surowca na jednostkę	Zysk jednostkowy [zł]
			I	II	
W1		5	1	10

W2		3	2	24

W3	 	0	4	12

Limit zużycia surowca	3600	4800	
Zbuduj model matematyczny i rozwiąż zadanie metodą geometryczną.
## Model matematyczny:
Funkcja celu f: max(10x + 24y + 12z)
gdzie:
x - liczba wyrobów 1
y - liczba wyrobów 2
z - liczba wyrobów 3

w1 = x(5s1 + s2)
w2 = y(3s1 + 2s2)
w3 = z(4s2)

Ograniczenia:
5x + 3y <= 3600
x + 2y + 4z <= 4800
x, y, z >=0
Zadanie optymalizacyjne:
Ile należy wyprodukować wyrobu W1, ile wyrobu W2, a ile wyrobu W3, aby nie przekraczając limitów zmaksymalizować zysk ze sprzedaży wyrobów?
Wyniki:
Status: Optimal
w1  =  0.0
w2  =  1200.0
w3  =  600.0
objective =  36000.0

Należy wybrać tylko w2
