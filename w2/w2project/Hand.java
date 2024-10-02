import java.util.ArrayList;

public class Hand {

  ArrayList<Card> value;
  /**
   * Creates an empty hand as an ArrayList of Cards.
   */
  public Hand() { this.value = new ArrayList<Card>(); }

  /**
   * Removes any cards currently in the hand.
   */
  public void reset() { this.value.clear(); }

  /**
   * Adds the specified card to the hand.
   * @param card the card to be added to the hand
   */
  public void add(Card card) { this.value.add(card); }

  /**
   * Returns the number of cards in the hand.
   * @return the number of cards in the hand
   */
  public int size() { return this.value.size(); }

  /**
   * Returns the card in the hand specified by the given index.
   * @param index the index of the card in the hand.
   * @return the card in the hand at the specified index.
   */
  public Card getCard(int index) { return this.value.get(index); }

  /**
   * Returns the summed value over all cards in the hand.
   * @return the summed value over all cards in the hand
   */
  public int getTotalValue() {
    return this.value.stream().mapToInt(card -> card.getValue()).sum();
  }

  /**
   * Returns a string representation of the hand.
   * @return a string representation of the hand
   */
  public String toString() {
    return this.value + " : " + getTotalValue();
  }
}
