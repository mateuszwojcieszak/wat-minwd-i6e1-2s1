require_relative "./rulp/lib/rulp"


Rulp::log_level = Logger::INFO

given[

X_i >= 0,
Y_i >= 0,
Z_i >= 0

]

Rulp::Max( objective =  2 * X_i + 3 * Y_i + 1 * Z_i ) [
                        3 * X_i + 2 * Y_i + 3 * Z_i <= 100,
                        2 * X_i + 3 * Y_i +     Z_i <= 100,
                        1 * X_i + 5 * Y_i + 2 * Z_i <= 150
].glpk

result = objective.evaluate

print "Results:\n"
print "X : ", X_i.value, "\n"
print "Y : ", Y_i.value, "\n"
print "Z : ", Z_i.value, "\n"
print "Objective : ", result, "\n"
