def read_puzzle1(file='puzzle1.txt'):
    f = open(file, 'r', newline='\n')
    numbers = []
    for line in f:
        numbers.append(int(line))
    f.close()
    return numbers
