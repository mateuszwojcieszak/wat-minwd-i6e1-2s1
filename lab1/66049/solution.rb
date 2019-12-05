require_relative "./rulp/lib/rulp"


Rulp::log_level = Logger::INFO

given[

X_i >= 0,
Y_i >= 0,

]

Rulp::Min( objective =  6 * X_i + 9 * Y_i ) [
                        3 * X_i + 9 * Y_i >= 27,
                        8 * X_i + 4 * Y_i >= 32,
                       12 * X_i + 3 * Y_i >= 36
].glpk

result = objective.evaluate

print "Results:\n"
print "X : ", X_i.value, "\n"
print "Y : ", Y_i.value, "\n"
print "Objective : ", result, "\n"
