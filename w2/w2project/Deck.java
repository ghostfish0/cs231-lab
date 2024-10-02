import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {

  ArrayList<Card> value;
  int[] stdDeck = {0, 0, 4, 4, 4, 4, 4, 4, 4, 4, 16, 4};

  /**
   * Creates the underlying deck as an ArrayList of Card objects.
   * Calls build() as a subroutine to build the deck itself.
   */
  public Deck() {
    this.value = new ArrayList<Card>();
    build();
  }

  /**
   * Builds the underlying deck as a standard 52 card deck.
   * Replaces any current deck stored.
   */
  public void build() {
    this.value.clear();
    for (int cardValue = 2; cardValue <= 11; cardValue++) {
      Card card = new Card(cardValue);
      for (int count = 0; count < stdDeck[cardValue]; count++) {
        this.value.add(card);
      }
    }
    shuffle();
  }

  /**
   * Returns the number of cards left in the deck.
   * @return the number of cards left in the deck
   */
  public int size() { return this.value.size(); }

  /**
   * Returns and removes the first card of the deck.
   * @return the first card of the deck
   */
  public Card deal() {
    Card top = this.value.get(0);
    this.value.remove(0);
    return top;
  }

  /**
   * Shuffles the cards currently in the deck.
   */
  public void shuffle() { Collections.shuffle(this.value); }

  /**
   * Returns a string representation of the deck.
   * @return a string representation of the deck
   */
  public String toString() { return "" + this.value; }
}
