# Import PuLP modeler functions
from pulp import *

# A new problem
prob = LpProblem("example", LpMaximize)

# Variables
w1 = LpVariable("w1", 0, None, LpInteger)
w2 = LpVariable("w2", 0, None, LpInteger)


# Objective (cel)
prob += 2*w1 + 4*w2, "objective"

# Constraints (Ograniczenia)
prob += 8*w1 + 16*w2 <= 96000, "c1"
prob += 7*w1 + 4*w2 <= 56000, "c2"
prob += w1<=5000, "c3"
prob += w2<=4000, "c4"


# Solve the problem using the default solver
prob.solve()

# Print the status of the problem
print("Status:", LpStatus[prob.status])

# Print the value of the variables
for v in prob.variables():
	print(v.name, " = ", v.varValue)

# Print the value of the objective
print("objective = ", value(prob.objective))
