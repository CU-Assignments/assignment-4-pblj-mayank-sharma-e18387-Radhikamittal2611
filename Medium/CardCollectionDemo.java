import java.util.*;

class CardCollection {
    private Map<String, List<String>> cardMap;

    public CardCollection() {
        cardMap = new HashMap<>();
    }

    public void addCard(String symbol, String card) {
        cardMap.computeIfAbsent(symbol, k -> new ArrayList<>()).add(card);
    }

    public List<String> getCardsBySymbol(String symbol) {
        return cardMap.getOrDefault(symbol, Collections.emptyList());
    }

    public void displayAllCards() {
        for (Map.Entry<String, List<String>> entry : cardMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}

public class CardCollectionDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CardCollection collection = new CardCollection();

        collection.addCard("Hearts", "Ace");
        collection.addCard("Hearts", "King");
        collection.addCard("Spades", "Queen");
        collection.addCard("Spades", "Jack");
        collection.addCard("Diamonds", "10");
        collection.addCard("Clubs", "9");

        System.out.println("Enter the symbol to find all cards (e.g., Hearts, Spades):");
        String symbol = scanner.nextLine();
        List<String> cards = collection.getCardsBySymbol(symbol);

        if (cards.isEmpty()) {
            System.out.println("No cards found for symbol: " + symbol);
        } else {
            System.out.println("Cards in " + symbol + ": " + cards);
        }

        System.out.println("\nDisplaying all stored cards:");
        collection.displayAllCards();
    }
}