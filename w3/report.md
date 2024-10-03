# Lab Report Week 3: Conway's Game of Life!

# Abstract
The objective of this project is to implement Conway's Game of Life in Java. Following given templates I modified and extended upon needs. The project explores using the Grid data structure and extend upon OOP in Java. I ended up using the `Queue` interface in Java as a `LinkedList` type to implement my Project idea, and I also modified heavily the drawing code in `LandscapeDisplay.java` to my needs, of which I understood a lot more. 

The project runs a simple window running Conway's Game of Life with a default 100x100 grid. 

## Results

## Exploration results 
yappa

## Extension 1: Historical Data!
You might have noticed the lingering marks of the old population on the landscape as red marks (graphic!) that slowly fades into the darkness. This is a really cool visual effect I found from [here](https://www.patater.com/life/) and wanted to replicate. 

Simulated P for each possible outcome. The header column and row are respectively `playerHitCutoff` and `dealerHitCutoff`. Each independent variable varies from 2 (the least possible sum) to 21 (the highest possible sum without busting). Red corresponding to low probability and Green corresponding to high probability and anything yellow-ish is an in-between value;

While still resembling the original results as observed from the heatmap, the Ace = 11 rule creates a much larger advantage for the dealer. With this rule, if the dealer is incredibly conservative in his play (hitting from 2 to 11) the player has virtually no chance if they dares to hit beyond 11. And although the more adventurous is the dealer the more chance the player has, the player only tops at around 70% chance of winning if them and the dealer respectively stops hitting at around 10-11 and 20, an unlikely scenario. Furthermore the heatmap appears more sharp (drastic, consistent change between the 11-12 cutoffs for the player) with the additional rule. It also seems the dealer has the most advantage when the player is risky (hitting above 11) and the dealer themself is not (not hitting above 12). While they (the dealer) still has advantage at around 60-70% winning probability even if the player is risky, they have less probability than if the player is playing safe. Vice versa is true for the player (as we see the color of the first and last heatmaps are inverted). Pushes tend to happen the most at around 9-10% probability when the player and the dealer are equally risky, hitting from around 11 to 18 . 

In both versions, it is notable how the dealer is more advantagous (especially with the Ace rule). This results makes sense, as the player always playing first is more prone to busting than dealer. They are also disadvantaged by the fact that a 21 doesn't guarantee winning for the player and still guarantee a push at worst for them. The results obtained from the simulation is in line with this logic. 

### Extension 5 results 
I chose extension 5 as it is simple to implement for my schedule. I modified `Simulation.java` to use the default Blackjack constructor and ran the simulation on varying values of Number of games, for each value measuring the probability of each scenario. My metric to be considered is the standard deviation of each `Number of games` value's set of probabilities, i.e. how far apart they are from each other? The 3 line graphs below each corresponding to the 3 possible outcomes as before demonstrates the results: 

Standard deviation of probability (x-axis) against number of times Monte-Carlo simulation ran per set (y-axis) for 3 possible outcomes. It follows a decreasing trend. 

We should expect that as we run more simulation our results stablize, and we did. The graph evidently shows the standard deviation decreases as our independent variable increases. The results between the 3 scenarios are practically identical. It is disappointing, and surprising however to see that between 5000 and 10_000 the standard deviation seems to flat line. This can mean, for next time's purpose, perhaps only 5000 run times is enough and save more time. 


## Reflection and Acknowledgements
I discussed with no one during my project but consulted a lot of websites in my looking for ideas. Although I pursued an extension idea of GPU accelearting by parallel computing the neighbors sum and landscape update I decided not to do it for this project realzing how complicated (or high learning curve) this idea is. 

All my extension ideas came from [here](https://www.patater.com/life/), I did not sample their code but tried to replicate it completely by myself.

## References: 
https://docs.oracle.com/javase/8/docs/api/java/awt/Component.html#addMouseMotionListener-java.awt.event.MouseMotionListener-
https://docs.oracle.com/javase/8/docs/api/java/awt/event/MouseEvent.html
https://www.geeksforgeeks.org/queue-interface-java/
https://docs.oracle.com/javase/8/docs/api/java/awt/Container.html
https://docs.oracle.com/javase/8/docs/api/javax/swing/JPanel.html
https://docs.oracle.com/javase/7/docs/api/javax/swing/JComponent.html
https://docs.oracle.com/javase/8/docs/api/java/awt/Window.html#pack--
https://docs.oracle.com/javase/7/docs/api/java/awt/Color.html
https://docs.oracle.com/javase/7/docs/api/java/awt/Graphics.html

