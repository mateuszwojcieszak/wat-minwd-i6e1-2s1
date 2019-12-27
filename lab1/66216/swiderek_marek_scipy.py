from scipy.optimize import linprog
import numpy as np

#Funkcja celu
f = np.array([6,3,3,2])
#Macierz wspolczynnikow przy ograniczeniach
C = np.array([[15,10,20,19],[9,3,5,10]])
#Tablica wartosci ograniczen
b = np.array([26000,100000])
#Ograniczenia >= 0
x1 = (0,None)
x2 = (0,None)
x3 = (0,None)
x4 = (0,None)

solution = linprog(-f, A_ub = C, b_ub = b, bounds = (x1,x2,x3,x4), method = 'simplex')

print('x1 = ' + str(int(solution.x[0])))
print('x2 = ' + str(int(solution.x[1])))
print('x3 = ' + str(int(solution.x[2])))
print('x3 = ' + str(int(solution.x[3])))
