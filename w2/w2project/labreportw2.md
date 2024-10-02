# Lab Report Week 2: Blackjack!

# Abstract
The objective of this lab is to implement and analyze a Monte-Carlo simulation of the Blackjack game in Java. Following given templates in smaller classes my main understanding came from writing Blackjack.java and Simulation.java as well as Extension 2 and 5. The project is an implementation of abstracting real world problems and behavior (like the game Blackjack) into classes and methods under Object-oriented programming using Java. It also utilized multidimensional arrays to store simulation results, helper functions to "DRY" up and tidy the code, as well as git version control to implement extensions with changing game mechanics. Under the core project and extension 2 and 5, key understandings are: (1) the relationship between how competitive the dealer and the player are and the outcome of the game; (2) In Monte-Carlo simulation, the results stablizes (standard deviation gets smaller) as the number of runs get bigger. 


The simulation runs the Blackjack game for different combinations of player and dealer hit cutoffs. The results are stored in a 3D array, where each element represents the number of times a particular outcome (dealer wins, push, player wins) occurred for a specific combination of hit cutoffs.

## Results

### Exploration results 
The Simulation class implements a Monte-Carlo simulation with varying cutoffs for the dealer and the player, and aims to find the probability 
of each of the game's outcomes. That is, the variable measured is `P_who_wins(playerHitCutoff, dealerHitCutoff) = ?` with `P` being the probability of either `The player wins` or `Push` or `The dealer wins`. `Simulation.java` ran 10_000 Blackjack games for each pair of value of (`playerHitCutoff`, `dealerHitCutoff)`). The results in the 3 heatmap tables, corresponding to the 3 possible outcomes are presented below: 

Simulated P for each possible outcome. The header column and row are respectively `playerHitCutoff` and `dealerHitCutoff`. Each independent variable varies from 2 (the least possible sum) to 21 (the highest possible sum without busting). Red corresponding to low probability and Green corresponding to high probability and anything yellow-ish is an in-between value;

It seems from around 2-13 cutoff on both axes whoever is more risky is more likely to win. That is, unless they're very adventurous and still chooses to hits at 19-20, at which point both has only about 30% chance of winning. 

### Extension 2 results 
I chose extension 2 as it seemed interesting to implement the logic in code as well as see how it affects the probability heatmaps. I modified the original code in a new git branch and implemented the rule. The Simulation conditions remains the same as before: 10_000 Blackjack games for each pair of value of (`playerHitCutoff`, `dealerHitCutoff)`). And the results are presented in the 3 heatmap tables below:

Simulated P for each possible outcome. The header column and row are respectively `playerHitCutoff` and `dealerHitCutoff`. Each independent variable varies from 2 (the least possible sum) to 21 (the highest possible sum without busting). Red corresponding to low probability and Green corresponding to high probability and anything yellow-ish is an in-between value;

While still resembling the original results as observed from the heatmap, the Ace = 11 rule creates a much larger advantage for the dealer. With this rule, if the dealer is incredibly conservative in his play (hitting from 2 to 11) the player has virtually no chance if they dares to hit beyond 11. And although the more adventurous is the dealer the more chance the player has, the player only tops at around 70% chance of winning if them and the dealer respectively stops hitting at around 10-11 and 20, an unlikely scenario. Furthermore the heatmap appears more sharp (drastic, consistent change between the 11-12 cutoffs for the player) with the additional rule. It also seems the dealer has the most advantage when the player is risky (hitting above 11) and the dealer themself is not (not hitting above 12). While they (the dealer) still has advantage at around 60-70% winning probability even if the player is risky, they have less probability than if the player is playing safe. Vice versa is true for the player (as we see the color of the first and last heatmaps are inverted). Pushes tend to happen the most at around 9-10% probability when the player and the dealer are equally risky, hitting from around 11 to 18 . 

In both versions, it is notable how the dealer is more advantagous (especially with the Ace rule). This results makes sense, as the player always playing first is more prone to busting than dealer. They are also disadvantaged by the fact that a 21 doesn't guarantee winning for the player and still guarantee a push at worst for them. The results obtained from the simulation is in line with this logic. 

### Extension 5 results 
I chose extension 5 as it is simple to implement for my schedule. I modified `Simulation.java` to use the default Blackjack constructor and ran the simulation on varying values of Number of games, for each value measuring the probability of each scenario. My metric to be considered is the standard deviation of each `Number of games` value's set of probabilities, i.e. how far apart they are from each other? The 3 line graphs below each corresponding to the 3 possible outcomes as before demonstrates the results: 

Standard deviation of probability (x-axis) against number of times Monte-Carlo simulation ran per set (y-axis) for 3 possible outcomes. It follows a decreasing trend. 

We should expect that as we run more simulation our results stablize, and we did. The graph evidently shows the standard deviation decreases as our independent variable increases. The results between the 3 scenarios are practically identical. It is disappointing, and surprising however to see that between 5000 and 10_000 the standard deviation seems to flat line. This can mean, for next time's purpose, perhaps only 5000 run times is enough and save more time. 


## Acknowledgements
I talked to Tuan Anh about which extensions we were considering doing. 
