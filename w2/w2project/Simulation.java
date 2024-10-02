public class Simulation {
  private int N;
  private int[][][] counts; // scenario, player's cutoff, dealer's cutoff
  private String[] labels;
  private Blackjack foo;

  public Simulation() {
    this.N = 10_000;
    this.counts = new int[3][30][30];
    this.labels = new String[] {"Dealer wins", "Pushes", "Player wins"};
    this.foo = new Blackjack();
  }

  public void reset() { this.counts = new int[3][30][30]; }

  public void run(int playerHitCutoff, int dealerHitCutoff) {
    this.foo.setPlayerHitCutoff(playerHitCutoff);
    this.foo.setDealerHitCutoff(dealerHitCutoff);
    for (int iteration = 0; iteration < N; iteration++) {
      this.counts[this.foo.game(false) + 1][playerHitCutoff]
            [dealerHitCutoff]++; // +1 bc. counts' index starts from 0
    }
  }

  public String toString() {
    String str = "";
    for (int scenario = 0; scenario < 3; scenario++) {
      str += this.labels[scenario] + ": \n";
      for (int playerHitCutoff = 2; playerHitCutoff < 21; playerHitCutoff++) { // player varies by column
        for (int dealerHitCutoff = 2; dealerHitCutoff < 21; dealerHitCutoff++) { // dealer varies by row
          str += 100.0 * this.counts[scenario][playerHitCutoff][dealerHitCutoff] / N + "	";
        }
        str += "\n";
      }
    }
    return str;
  }
  public static void main(String[] args) {
    Simulation sim = new Simulation();
    for (int playerHitCutoff = 2; playerHitCutoff < 21; playerHitCutoff++) {
      for (int dealerHitCutoff = 2; dealerHitCutoff < 21; dealerHitCutoff++) {
        sim.run(playerHitCutoff, dealerHitCutoff);
      }
    }
    System.out.println(sim);
  }
}
