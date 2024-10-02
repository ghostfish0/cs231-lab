public class Blackjack {
  private Deck deck;
  private Hand playerHand;
  private Hand dealerHand;
  private int reshuffleCutoff;
  private int playerHitCutoff;
  private int dealerHitCutoff;

  /**
   * Initialize atrributes
   * @param reshuffleCutoff a cuttoff value for reshuffling the deck
   * @param playerHitCutoff a cuttoff value for when the player stops hitting
   * @param dealerHitCutoff a cuttoff value for when the dealer stops hitting
   */
  public Blackjack(int reshuffleCutoff, int playerHitCutoff,
                   int dealerHitCutoff) {
    this.reshuffleCutoff = reshuffleCutoff;
    this.playerHitCutoff = playerHitCutoff;
    this.dealerHitCutoff = dealerHitCutoff;
    this.deck = new Deck();
    reset();
  }
  /**
   * Initialize with given reshuffleCutoff
   * @param reshuffleCutoff a cuttoff value for reshuffling the deck
   */
  public Blackjack(int reshuffleCutoff) { this(reshuffleCutoff, 16, 17); }
  /**
   * Initialize attributes with default values
   */
  public Blackjack() { this(12, 16, 17); }

  /**
   * Reset the game
   * If the number of cards in the deck
   * is less than the reshuffle cutoff,
   * then the method
   * should create a fresh (complete), shuffled deck
   * Otherwise, it should not modify the deck,
   * just clear the player and dealer hands.
   */
  public void reset() {
    this.deck =
        this.deck.size() < this.reshuffleCutoff ? new Deck() : this.deck;
    this.playerHand = new Hand();
    this.dealerHand = new Hand();
  }
  /**
   * Deal out a card each to the player and the dealer
   */
  public void deal() {
    this.dealerHand.add(this.deck.deal());
    this.dealerHand.add(this.deck.deal());
    this.playerHand.add(this.deck.deal());
    this.playerHand.add(this.deck.deal());
  }

  /**
   * Player: hit (draw from the deck) until >= 16
   * @return False if bust, True if not
   */
  public boolean playerTurn() {
    while (this.playerHand.getTotalValue() < this.playerHitCutoff)
      this.playerHand.add(this.deck.deal());
    return (this.playerHand.getTotalValue() <= 21);
  }
  /**
   * Dealer: hit (draw from the deck) until >= 17
   * @return False if bust, True if not
   */
  public boolean dealerTurn() {
    while (this.dealerHand.getTotalValue() < this.dealerHitCutoff)
      this.dealerHand.add(this.deck.deal());
    return (this.dealerHand.getTotalValue() <= 21);
  }

  public void setReshuffleCutoff(int cutoff) { this.reshuffleCutoff = cutoff; }
  public void setPlayerHitCutoff(int cutoff) { this.playerHitCutoff = cutoff; }
  public void setDealerHitCutoff(int cutoff) { this.dealerHitCutoff = cutoff; }

  public int getReshuffleCutoff(int cutoff) { return this.reshuffleCutoff; }
  public int getPlayerHitCutoff(int cutoff) { return this.playerHitCutoff; }
  public int getDealerHitCutoff(int cutoff) { return this.dealerHitCutoff; }

  public Hand getPlayerHand() { return this.playerHand; }
  public Hand getDealerHand() { return this.dealerHand; }

  public String toString() {
    return "Player hand: " + playerHand + "\nDealer hand: " + dealerHand;
  }
  /**
   * helper function of game() so game() can use return value;
   * @param verbose Whether to print the initial and final hands of the dealer
   * the player, and the results of the game
   * @return -1 if dealer wins, 0 if push and 1 if player wins
   */
  public int printGame(boolean verbose, int results, String strResults) {
    if (verbose) {
      System.out.println("player at " + this.playerHitCutoff + ", dealer at " +
                         this.dealerHitCutoff);
      System.out.println(this);
      System.out.println("**" +
                         ((results > 0)
                              ? "Player won!"
                              : ((results < 0) ? "Dealer won!" : "Push.")) +
                         " " + strResults);
    }
    return results;
  }

  public int printGame(boolean v, int r) { return printGame(v, r, ""); }

  public int game(boolean verbose) {
    reset();
    deal();

    int results = -2;

    if (verbose) {
      System.out.println(this);
    }

    if (!this.playerTurn())
      return printGame(verbose, -1, "Player busts");
    if (!this.dealerTurn())
      return printGame(verbose, 1, "Dealer busts");

    int p = this.playerHand.getTotalValue();
    int d = this.dealerHand.getTotalValue();
    if (p == d)
      return printGame(verbose, 0, "Push");
    if (p > d)
      return printGame(verbose, 1);
    if (p < d)
      return printGame(verbose, -1);

    return printGame(verbose, -2);
  }
  public static void main(String[] args) {}
}
