package task2_data_structure;

import java.util.Scanner;

public class TruckPackingVersion1 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the truck capacity (1 <= M <= 1000): ");
        int M = input.nextInt();
        
        // Check if the truck capacity is within the valid range
        if (M < 1 || M > 1000) {
            System.out.println("Truck capacity should be between 1 and 1000.");
            return;
        }

        System.out.print("Enter the number of items you want to put in the truck: ");
        int numDesiredItems = input.nextInt();

        int[] items = new int[numDesiredItems];
        int[] chosenItems = new int[M + 1];

        // Check if the number of desired items is valid
        if (numDesiredItems <= 0) {
            System.out.println("The number of desired items should be greater than zero.");
            return;
        }

        for (int i = 0; i < numDesiredItems; i++) {
            System.out.print("Enter the weight of item " + (i + 1) + " (must be a power of 2): ");
            int weight = input.nextInt();

            // Check if the weight of the item is a power of 2
            if (!isPowerOfTwo(weight)) {
                System.out.println("Item weight must be a power of 2.");
                return;
            }

            items[i] = weight;
        }

        int[] dp = new int[M + 1];

        // Initialize the dynamic programming array with a value greater than the potential maximum number of items
        for (int i = 1; i <= M; i++) {
                 dp[i] = M + 1;
        }

        // Initialize dp[0] as 0, indicating that no items are needed for an empty truck
        dp[0] = 0;

        for (int i = 1; i <= M; i++) {
            for (int j = 0; j < numDesiredItems; j++) {
                if (items[j] <= i && dp[i - items[j]] != M+1) {
                    if (dp[i] > dp[i - items[j]] + 1) {
                        // Update dp[i] with the minimum number of items required
                        dp[i] = dp[i - items[j]] + 1;
                        // Store the position of the chosen item
                        chosenItems[i] = j;
                    }
                }
            }
        }

            if (dp[M] == M+1) {
            System.out.println("No solution!");
        } else {
            System.out.println("Minimum number of items needed: " + dp[M]);
            System.out.print("Chosen items (positions in the array): ");
            
            // Trace back to find the chosen items
            int remainingCapacity = M;
            while (remainingCapacity > 0) {
                int chosenItemPos = chosenItems[remainingCapacity];
                System.out.print((chosenItemPos+1) + " ");
                remainingCapacity -= items[chosenItemPos];
            }
            System.out.println();
        }
    }

    public static boolean isPowerOfTwo(int n) {
        return (n != 0) && ((n & (n - 1)) == 0);
    }
}
