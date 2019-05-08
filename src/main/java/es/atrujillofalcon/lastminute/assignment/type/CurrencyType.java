package es.atrujillofalcon.lastminute.assignment.type;

public enum CurrencyType {

    EUR("€");

    CurrencyType(String symbol) {
        this.symbol = symbol;
    }

    private String symbol;

    public String getSymbol() {
        return symbol;
    }
}
