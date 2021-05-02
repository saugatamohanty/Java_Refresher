public class VarArgsExample {
    public static void main(String[] args) {
        String imem1 = "Apples";
        String item2 = "Oranges";
        String item3 = "Pears";
        printShoppingList(3, imem1, item2, item3);
        printShoppingList(4, "Bread", "Milk", "Eggs", "Bananas");
    }




    private static void printShoppingList(int count, String... items) {
        System.out.println("SHOPPING LIST" + count);
        for (int i = 0; i < items.length; i++) {
            System.out.println(i + 1 + ": " + items[i]);
        }
        System.out.println();

    }
}
