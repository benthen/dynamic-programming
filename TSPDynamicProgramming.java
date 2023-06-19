import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TSPDynamicProgramming {
    private static int[][] dp;
    private static int[][] distance;
    private static int n;
    private static Map<String, Integer> cityIndices;
    private static String[] cities;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of cities: ");
        n = scanner.nextInt();
        scanner.nextLine();

        distance = new int[n + 1][n + 1];
        dp = new int[1 << (n + 1)][n + 1];
        cityIndices = new HashMap<>();
        cities = new String[n + 1];

        System.out.println("Enter the names of cities: ");
        for (int i = 1; i <= n; i++) {
            String cityName = scanner.nextLine();
            cities[i] = cityName;
            cityIndices.put(cityName, i);
        }

        System.out.println("Enter the distances between cities: ");
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                System.out.printf("Enter the distance from %s to %s: ", cities[i], cities[j]);
                distance[i][j] = scanner.nextInt();
            }
        }

        System.out.print("Enter the starting city: ");
        String startingCity = scanner.next();

        int startingCityIndex = cityIndices.get(startingCity);
        int minCost = tsp(1, startingCityIndex);

        System.out.println("Minimum cost of TSP tour: " + minCost);

        scanner.close();
    }

    private static int tsp(int mask, int pos) {
        if (mask == (1 << (n + 1)) - 1) {
            // All cities have been visited, return the cost of returning to the starting city
            return distance[pos][1];
        }

        if (dp[mask][pos] != 0) {
            // The optimal cost for the current subproblem has already been computed
            return dp[mask][pos];
        }

        int minCost = Integer.MAX_VALUE;

        for (int city = 1; city <= n; city++) {
            if ((mask & (1 << city)) == 0) {
                // City has not been visited yet
                int newCost = distance[pos][city] + tsp(mask | (1 << city), city);
                minCost = Math.min(minCost, newCost);
            }
        }

        dp[mask][pos] = minCost;

        return minCost;
    }
}

