import random

from Sample import Sample


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


def crossover(sample1, sample2):
    # after split, each should contain no less than 2 elements
    split_point = random.randint(2, 38)
    out_1 = []
    out_2 = []

    a1 = set(sample1.representation[0:split_point])
    a2 = set(sample1.representation[split_point:])

    b1 = set(sample2.representation[0:split_point])
    b2 = set(sample2.representation[split_point:])

    # use set to find intersection. If the index belongs to the set, ignores

    intersection_1 = a1.intersection(b2)
    intersection_2 = a2.intersection(b1)

    index_to_be_ignored = []
    for duplicate in intersection_1:
        cur_index = sample1.index_map[duplicate]
        index_to_be_ignored.append(cur_index)

    for duplicate in intersection_2:
        cur_index = sample2.index_map[duplicate]
        index_to_be_ignored.append(cur_index)

    index_to_be_ignored_set = set(index_to_be_ignored)

    for i in range(0, split_point):
        if i not in index_to_be_ignored_set:
            out_1[i] = sample2.representation[i]
            out_2[i] = sample1.representation[i]
        else:
            out_1[i] = sample1.representation[i]
            out_2[i] = sample2.representation[i]
    for j in range(split_point, len(sample1.representation)):
        out_1[j] = sample1.representation[j]
        out_2[j] = sample2.representation[j]

    child_1 = Sample(out_1)
    child_2 = Sample(out_2)
    return child_1, child_2


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
