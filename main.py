# START
# Generate the initial population
# Compute fitness
# REPEAT
#     Selection
#     Crossover
#     Mutation
#     Compute fitness
# UNTIL population has converged
# STOP
import sample_generator as sg
import file_reader as fr

if __name__ == "__main__":
    # sg.puzzle_one_generator()
    numbers = fr.read_puzzle1()
    for num in numbers:
        print(num)
