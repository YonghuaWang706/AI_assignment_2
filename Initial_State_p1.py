import file_reader as fr
import genetic_algo as ga


class Puzzle1:
    def __init__(self):
        pass


if __name__ == "__main__":
    a_puzzle = Puzzle1()


# Code to generate starting population:
def generate_population(pop_size, start_array):
    population = []
    for x in range(pop_size):
        temp = start_array.shuffle()
        population.append(temp)
    return population
