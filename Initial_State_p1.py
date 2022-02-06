import file_reader as fr
import genetic_algo as ga


class Puzzle1:
    def __init__(self, arr):
    def self.original_list = arr

    def print(self):
        for num in self.original_list:
            print(num)


if __name__ == "__main__":
    a_puzzle = Puzzle1(fr.read_puzzle1())
    a_puzzle.print()
    result = ga.calculate_fitness(a_puzzle.original_list)
    print(result)

# Code to generate starting population:
def generate_population(pop_size, start_array):
    population = []
    for x in range(pop_size):
        temp = start_array.shuffle()
        population.append(temp)
    return population
