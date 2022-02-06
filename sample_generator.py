import random


def puzzle_one_generator():
    f = open("puzzle1.txt", "w")
    for i in range(40):
        f.write(str(random.randint(-10, 10)) + '\n')
