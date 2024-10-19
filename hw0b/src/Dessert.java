public class Dessert {

    public int flavor;
    public int price;
    public static int numDessert = 0;

    public Dessert(int f, int p) {
        flavor = f;
        price = p;
        numDessert++;
    }

    public void printDessert() {
        System.out.print(flavor + " " + price + " " + numDessert);
    }

    public static void main(String[] args) {
        System.out.print("I love dessert!");
    }
}
