# Import PuLP modeler functions
from pulp import *

# A new problem
prob = LpProblem("example", LpMaximize)

# Variables
p1 = LpVariable("p1", 0, None, LpInteger)
p2 = LpVariable("p2", 0, None, LpInteger)


# Objective (cel)
prob += 50*p1 + 10*p2, "objective"

# Constraints (Ograniczenia)
prob += 12*p1 + 4*p2 <= 480, "c1"
prob += 8*p1 + 8*p2 <= 640, "c2"


# Solve the problem using the default solver
prob.solve()

# Print the status of the problem
print("Status:", LpStatus[prob.status])

# Print the value of the variables
for v in prob.variables():
	print(v.name, " = ", v.varValue)

# Print the value of the objective
print("objective = ", value(prob.objective))
