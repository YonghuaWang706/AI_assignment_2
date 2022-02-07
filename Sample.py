import genetic_algo as ga


class Sample:
    def __init__(self, arr):
        self.representation = arr
        self.index_map = {}
        for i, ele in enumerate(arr):
            self.index_map[ele] = i
        self.fitness = ga.calculate_fitness(arr)

    def print(self):
        for num in self.representation:
            print(num)
