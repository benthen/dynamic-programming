# Pseudocode of Dynamic Programming Algorithm to solve Travelling Salesperson Problem

1. Start
2. Declare dp, distance, n, cityIndices, cities;
3. function main()
      1. Start
      2. Read number of cities, n from user
      3. Initialize distance matrix of size (n+1)x(n+1)
      4. Initialize dp array of size [1 << (n + 1)][n + 1]
      5. Initialize cityIndices with new empty HashMap
      6. Initialize cities as array of strings with length n+1
      7. Read the names of cities 
      8. for i = 1 to n:
            1. cityName = read a line from input
            2. cities[i] = cityName
            3. cityIndices.put(cityName, i)

      9. Read the distances between cities 
     10. for i = 1 to n:
            1. for j = 1 to n:
                  1. print "Enter the distance from " + cities[i] + " to " + cities[j] + ": "
                  2. distance[i][j] = read integer from input

      11. Read the starting city
      12. startingCityIndex = cityIndices.get(startingCity)
      13. minCost = tsp(1, startingCityIndex)
      14. Print the computed minimum cost of TSP tour 

End

3. function tsp(mask, pos): 
    1. Start 
    2. if mask is equal to ((1 << (n + 1)) - 1):
            return distance[pos][1]
    
    3. if dp[mask][pos] is not equal to 0:
            return dp[mask][pos]
    
    4. minCost = infinity
    
    5. for city = 1 to n:
          1. if (mask & (1 << city)) is equal to 0:
                1. newCost = distance[pos][city] + tsp(mask | (1 << city), city)
                2. minCost = min(minCost, newCost)
    
    6. dp[mask][pos] = minCost
    
    7. return minCost
End

# Analysis of Dynamic Programming ALgorithm
