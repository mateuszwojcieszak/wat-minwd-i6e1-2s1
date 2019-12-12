from pulp import *

# A new problem
prob = LpProblem("example", LpMaximize)

# Variables
x = LpVariable("w1", 0, None, LpInteger)
y = LpVariable("w2", 0, None, LpInteger)
z = LpVariable("w3", 0, None, LpInteger)

# Objective
prob += 10*x + 24*y + 12*z, "objective"

# Constraints
prob += 5*x + 3*y <= 3600, "c1"
prob += 1*x + 2*y + 4*z <= 4800, "c2"

# Solve the problem using the default solver
prob.solve()

# Print the status of the problem
print("Status:", LpStatus[prob.status])

# Print the value of the variables
for v in prob.variables():
	print(v.name, " = ", v.varValue)

# Print the value of the objective
print("objective = ", value(prob.objective))
