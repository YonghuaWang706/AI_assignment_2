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
import genetic_algo as ga
import sample_generator as sg
import file_reader as fr
import Initial_State_p1 as P1

if __name__ == "__main__":
    sg.puzzle_one_generator()
    # get the 40 numbers used
    # numbers = fr.read_puzzle1()
    # bin = []
    #
    # for index, num in enumerate(numbers):
    #     bin.append(num)
    #     #print(bin)
    # ga.calculate_fitness(bin)
