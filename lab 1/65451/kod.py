import numpy as np
from scipy.optimize import linprog
A = np.array([[-6, -3, -4, -4], [-1, -3, -2, -4], [-1, 0, 0, 0], [0, -1, 0, 0], [0, 0, -1, 0], [0, 0, 0, -1]])
b = np.array([-120, -60, 0, 0, 0, 0])
c = np.array([1.2, 1.8, 2.0, 0.9])
res = linprog(c, A_ub=A, b_ub=b,bounds=(0, None))
print('Optimal value:', res.fun, '\nP:', res.x)
