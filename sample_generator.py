import random

file_no = str(random.randint(0, 100))  # mostly-unique filenames (99% chance unique)


def puzzle_one_generator():
    # f = open("puzzle1.txt", "w")
    f = open("puzzle" + file_no + ".txt", "w")
    for i in range(40):
        f.write(str(random.uniform(-10, 10).__round__(1)) + '\n')
