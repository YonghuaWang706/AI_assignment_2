# AI_assignment_2
Genetics Algorithm Simulation

Yonghua Wang, David Leandres, Benjamin Klaiman

VIDEO DEMO: https://youtu.be/U7ui_1GQ5fc

To run the program run the GeneticAlgo file.
It should then prompt you for the puzzle, file location, time to run, and desired population size.

The default locations for puzzle one is src/puzzle1.txt and for puzzle two it is src/puzzle2.txt

To change the elitism or culling settings go to GeneticAlgo in src and on line 36 and 37 choose how many from each population you want to cull or keep. (Remeber to maintain an even population.)

To modify the generation limit change the number of totalRounds on line 75 for puzzle one and on line 81 for puzzle two in src/GeneticAlgo.java