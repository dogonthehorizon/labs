## PatternMatching-1: Which of the following will match?

a = [1, 2, 3] # True, the symbol 'a' can be matched to the list

a = 4 # True, the symbol 'a' can be matched to the integer 4

4 = a # True, the symbol 'a' is bound to 4, so the two sides can be matched.

[a, b] = [1, 2, 3] # False, there is no way to zip the symbols 'a' and 'b' to 1, 2, 3

a = [[1, 2, 3]] # True, the symbol 'a' can be matched to the list of lists.

[a] = [[1, 2, 3]] # True, the symbol 'a' can be matched to the inner list [1,2,3]

[[a]] = [[1,2,3]] # False, 'a' can't be matched to any list on the right hand side

## PatternMatching-2: Which of the following will match?

[a, b, a] = [1, 2, 3] # False, because 'a' is bound to 1 for the duration of this match
                      # and therefore cannot also be matched to 2.

[a, b, a] = [1, 1, 2] # False, again because 'a' is bound to 1 for the duration of this match.

[a, b, a] = [1, 2, 1] # True, 'a' is matched to 1 for the duration of this match.

## PatternMatching-3: If you assume the variable 'a' initially contains the value 2,
##                    Which of the following will match?

a = 2
[a, b, a] = [1, 2, 3] # False, 'a' is bound to 2 and therefore cannot be bound to 1 or 3

a = 2
[a, b, a] = [1, 1, 2] # False, 'a' is bound to 2 and cannot be bound to 1

a = 2
a = 1 # True, 'a' can be bound to 1

a = 2
^a = 2 # True, the '^' symbol forces Elixir to reference the current value of 'a', which is 2

a = 2
^a = 1 # False, 1 != 2

a = 2
^a = 2 - a # False, the current value of a is 2, and 2 - 2 is 0
