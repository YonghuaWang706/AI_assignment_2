import genetic_algo as ga


class Sample:
    def __init__(self, arr):
        self.current_representation = arr
        self.fitness = ga.calculate_fitness(arr)

    def print(self):
        for num in self.current_representation:
            print(num)
