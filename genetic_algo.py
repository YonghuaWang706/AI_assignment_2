import random


def calculate_fitness(cur_sample):
    bin1_product = 1  # This is set to 1, for the multiplication.
    bin2_sum = 0

    # This multiplies all the numbers in bin 1
    for element in range(0, 9):
        bin1_product = bin1_product * cur_sample[element]

    # This sums all the items in bin 2
    for element in range(10, 19):
        bin2_sum = bin2_sum + cur_sample[element]

    # This subtracts the smallest number from the largest in bin 3
    bin3 = []
    for element in range(20, 29):
        bin3.append(cur_sample[element])
    bin3_min = min(bin3)
    bin3_max = max(bin3)
    bin3_diff = bin3_max - bin3_min

    print(bin1_product)
    print(bin2_sum)
    print(bin3_diff)
    return bin1_product + bin2_sum + bin3_diff

    # Bin 4 is ignored, so do nothing here.


def next_generation():
    pass


def selection():
    pass


def crossover(array1, array2):
    # split only on the first 10 because multiplication dominates the fitness
    split_point = random.randint(0, 39)
    out_1 = []
    out_2 = []
    for i in range(0, split_point):
        out_1[i] = array2[i]
        out_2[i] = array1[i]
    for j in range(split_point, len(array1)):
        out_1[j] = array1[j]
        out_2[j] = array1[j]
    return out_1, out_2


def crossover1(array):
    split_point = random.randint(0, 39)
    temp_array = []
    result = []
    for x in range(split_point):
        temp_array.append(array[x])
    for y in range(split_point, len(array)):
        result.append(array[y])
    result.append(temp_array)

    return result


def mutation(array):
    mutation_probability = random.uniform(0, 1.0)
    if mutation_probability < 0.05:
        first_index = random.randint(0, 40)
        second_index = random.randint(0, 40)
        array[first_index], array[second_index] = array[second_index], array[first_index]
