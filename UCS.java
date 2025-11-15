 import java.util.*;// input : start city and goal city , output : path 

public class Main {

int N; // number of vertices in the graph
boolean[ ][ ] G; // the graph as an adjacency matrix
// G[i][j] is true if there is an edge from i to j
int currPos;
int goal ;
int[][] cost;



Main(int size,int loc, int goald)
{
N=size;
currPos=loc;
goal=goald;
setupCosts();
System.out.println("----------Traverse-------------");
System.out.println();
}

static class Node implements Comparable<Node> {
    int city;
    int cost;

    Node(int city, int cost) {
      this.city = city;
      this.cost = cost;
        }

    @Override
    public int compareTo(Node other) {
    return Integer.compare(this.cost, other.cost);
        }
    }

void setupCosts() {
    cost = new int[N][N];
    G = new boolean[N][N]; 

    setCost(0, 1, 35);
    setCost(0, 8, 30);
    setCost(0, 4, 45);
    setCost(1, 2, 80);
    setCost(1, 3, 50);
    setCost(2, 6, 100);
    setCost(3, 4, 40);
    setCost(3, 5, 70);
    setCost(6, 7, 60);
    setCost(8, 9, 55);
    setCost(9, 10, 65);
    setCost(10, 11, 50);
}

void setCost(int a, int b, int c) {
    G[a][b] = G[b][a] = true;
    cost[a][b] = cost[b][a] = c;
}


void UCS() {
    PriorityQueue<Node> pq = new PriorityQueue<>();
    boolean[] visited = new boolean[N];
    int[] parent = new int[N];
    int[] totalCost = new int[N]; 

    Arrays.fill(parent, -1);
    Arrays.fill(totalCost, Integer.MAX_VALUE);
    totalCost[currPos] = 0;

    pq.offer(new Node(currPos, 0));

    while (!pq.isEmpty()) {
        Node current = pq.poll();

        if (visited[current.city]) continue;
        visited[current.city] = true;

        if (current.city == goal) {
            printPath(parent, goal);
            System.out.println("\nTotal Cost: " + current.cost + " KM");
            return;
        }

        for (int i = 0; i < N; i++) {
            if (G[current.city][i] && !visited[i]) {
                int newCost = current.cost + cost[current.city][i];

                if (newCost < totalCost[i]) {
                    totalCost[i] = newCost;
                    parent[i] = current.city;
                    pq.offer(new Node(i, newCost));
                }
            }
        }
    }

    System.out.println("Goal not reachable.");


}
//System.out.println("Path: ");
void printPath(int[] parent, int goal) {
    if (parent[goal] == -1) {
        System.out.print(retCity(goal));
        return;
    }
    printPath(parent, parent[goal]);
    System.out.print(" -> " + retCity(goal));
}



public static void main(String[] args)
{
int cityChoice;
int goalcity;

Scanner inp=new Scanner(System.in);
System.out.println("\n\nChoose a city number to start with: \n");
for(int i=0;i<12;i++)//shows the list of cities
System.out.println(retCity(i)+ " city["+i+"]");
System.out.print("\nstart destintion: ");
cityChoice=inp.nextInt();//User's choice of the list

System.out.println("\n\nChoose the Destinaton: \n");
goalcity=inp.nextInt();

if(cityChoice<0 ||cityChoice>=12)//the user choice is not included in the list
{
System.out.println("Mistake,run the program again");
System.exit(0);
}
Main traGraph=new Main(12,cityChoice,goalcity);
traGraph.UCS();
}
public static String retCity(int i)// the function returns city name, according to its index in V array
{
if(i==0)
return "Buraydah";
else if(i==1)
return "Unayzah";
else if(i==2)
return "AlZulfi";
else if(i==3)
return "Al-Badai";
else if(i==4)
return "Riyadh-Alkhabra";
else if(i==5)
return "AlRass";
else if(i==6)
return "UmSedrah";
else if(i==7)
return "Shakra";
else if(i==8)

return "Al-Bukayriyah";
else if(i==9)
return "Sheehyah";
else if(i==10)
return "Dhalfa";
else return "Mulida";
}
}
