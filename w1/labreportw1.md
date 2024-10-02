# lab report week 1
## Hypothetical Project Description

You wrote code to simulate 3 different strategies for managing a grocery store checkout line. In the first strategy (S1), each register has its own line, and people choose which line to wait in as they arrive. In the second strategy (S2), people wait in a single line, and each time a register is open the next person in line advances to it. In the third strategy (S3), there are 2 lines, each of which functions like S2 for half of the cash registers.

Your code uses Java ArrayLists instead of Java Arrays because ArrayLists can change size dynamically rather than always being a fixed size like an Array.

You measure 2 metrics in your experiment: the average waiting time for each person–ideally this is as small as possible), and the size of the largest line–ideally this isn’t too large so it doesn’t extend too far into the aisles.

You have the following results of your experiments printed to your terminal:

Average wait time:
S1: 5.9 minutes
S2: 2.1 minutes
S3: 2.5 minutes

Maximum line size:
S1: 7
S2: 20
S3: 10

---

# Task 2: Simulating different strategies for managing a grocery store checkout line

## Abstract
Even in the mundane everyday task of waiting in grocery store checkout line, one can find algorithm and optimization, leading to significantly improved quality of life. The project below simulates the effects of 3 different strategies for managing a grocery checkout line, the main metrics being *Average wait time*  and *Maximum line size*. Key to this simulation is the usage of Java ArrayLists (as opposed to Java Array) which simulates better the dynamically changing size (rather than fixed size) of the checkout line. My results show that maintaining 2 lines, each corresponds to half of the registers, and letting the next person advances everytime a register is open effectively achieves low values of both Average wait time and Maximum line size. 

## Results 
I ran my simulation with 3 different strategies S1, S2, and S3, testing the outcomes of each strategy on both average wait time and maximum line size. In the first strategy (S1), each register has its own line, and people choose which line to wait in as they arrive. In the second strategy (S2), people wait in a single line, and each time a register is open the next person in line advances to it. In the third strategy (S3), there are 2 lines, each of which functions like S2 for half of the cash registers. The reults of the simulation is presented in the table below. The key metrics reported are the average waiting time per customer and the maximum size a line reached across all lines, across all time within each simulation. 

|  /  | S1  | S2   | S3 |
| --- | --- |--- |--- |
| Avg. waiting time (mins) | 5.9  | 2.1  | 2.3 | 
| Max. line size (people) | 7  | 20  | 10 | 

Simulated average waiting time per person, and maximum line size across all lines, across all time with 3 different strategies. 

S1 manages to keep the lines short at 7 of maximum line size but moves very slowly, with each person waiting 5.9 mins on average. On the other hand, S2's lines moves along the quickest at 2.1 average waiting time but are most humongous in size at 20. S3 maintains a moderately low waiting time at 2.3 mins and line size at 10 people. It shows that strategy C offers most optimal results and the best tradeoff in terms of our metrics. 

## Extensions 

It seems we can push S3's strategy a step further. Between the 3 strategies we see they are underlyingly varitations of each other. If we denote S2 as the original strategy, it can be said that S3 is splitting S2's one line into 2, and S1 is into N (with N being the number of register). As a generalization, we can modify the simulation so that strategy S4_i is splitting S2's line into i lines (1 <= i <= N), and test this strategy on the same metrics as before. Perhaps between i = 2 (the current best solution), and i = N (S1), we can find a solution that offers an even better compromise between average waiting time and line size. 

## Reflection 

[Response to question 1]
[Response to question 2]

## Acknowledgments 
In completing this project I discussed the questions with Tuan Anh, but didn't look at his code. 

--- 
# Task 3 
- It's Friday at 8 PM and you're still working on project 3. What should you do?
    - I should not go out to a party and focus on project 3. 
    - Anticipate time and if needed ask for extension

- It's Tuesday at 8 PM and you're still working on project 6. 
    - I Should Accept My Fate, Email The Professor And Devise A Plan On What To Do So This Doesn'T Happen Again. 

- You have used 4 late submission days so far. What should you do?
    - Talk to Prof And Academic Advisor And Dean Of Study
    - Devise A Strategy To Do Better
    - Trim-Off Non-Academic Duties

- You're going to be out of town on your usual lab period. What should you do to make sure you get credit for the lab work?
    - Do The Lab Work 
    - Check In With My Profs Via Email 

- List at least 3 office hour slots for any of the instructors or the evening TAs that your weekly schedule will usually allow you to attend.
    - M, T, R, SS: 10PM
    - W, F: 7-12PM
