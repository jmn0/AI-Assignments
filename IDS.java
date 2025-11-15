import java.util.*;// input : start city and goal city , output : path
public class Main {
int N;
// number of vertices in the graph
boolean[ ][ ] G;
// the graph as an adjacency matrix
// G[i][j] is true if there is an edge from i to j
int currPos;
int goal ;

Main(int size,int loc, int goald)
{
N=size;
currPos=loc;
goal=goald;

setupGraph();
System.out.println("----------Traverse-------------");
System.out.println();
}
void setupGraph()
//constructor
{
G=new boolean[N][N];
G[0][1]=G[1][0]=true;
G[0][8]=G[8][0]=true;
G[0][4]=G[4][0]=true;
G[1][2]=G[2][1]=true;
G[1][3]=G[3][1]=true;
G[2][6]=G[6][2]=true;
G[3][4]=G[4][3]=true;
G[3][5]=G[5][3]=true;
G[6][7]=G[7][6]=true;
G[8][9]=G[9][8]=true;
G[9][10]=G[10][9]=true;
G[10][11]=G[11][10]=true;
}

ArrayList<Integer> shortestPath = new ArrayList<>();

void IDS() {
    int maxDepth = N;
    boolean found = false;

    for (int depth = 0; depth <= maxDepth; depth++) {
        boolean[] visited = new boolean[N];
        ArrayList<Integer> path = new ArrayList<>();

        if (DLS(currPos, goal, depth, visited, path)) {
            found = true;
            System.out.println("Goal found at depth " + depth);
            printPath(shortestPath);
            break;
        }
    }

    if (!found) {
        System.out.println("Failure: Goal not found within depth limit.");
    }
}
boolean DLS(int node, int goal, int limit, boolean[] visited, ArrayList<Integer> path) {
    visited[node] = true;
    path.add(node);

    if (node == goal) {
        if (shortestPath.isEmpty() || path.size() < shortestPath.size()) {
            shortestPath = new ArrayList<>(path);
        }
        return true;
    }

    if (limit == 0) {
        path.remove(path.size() - 1);
        visited[node] = false;
        return false;
    }

    boolean found = false;
    for (int i = 0; i < N; i++) {
        if (G[node][i] && !visited[i]) {
            if (DLS(i, goal, limit - 1, visited, path)) {
                found = true;
            }
        }
    }

    path.remove(path.size() - 1);
    visited[node] = false;
    return found;
}


    void printPath(ArrayList<Integer> path) {
        System.out.print("Path: ");
        for (int i = 0; i < path.size(); i++) {
            if (i == path.size() - 1) {
                System.out.print(retCity(path.get(i)));
            } else {
                System.out.print(retCity(path.get(i)) + " -> ");
            }
        }
        System.out.println();
    }


public static void main(String[] args)
{
int cityChoice;
int goalcity;
int inplimit;
Scanner inp=new Scanner(System.in);
System.out.println("\n\nChoose a city number to start with: \n");
for(int i=0;i<12;i++)//shows the list of cities
System.out.println(retCity(i)+ " city["+i+"]");
System.out.print("\nstart Destinaton: \n");
cityChoice=inp.nextInt();
//User's choice of the list
System.out.println("\n\nChoose the Destinaton: \n");
goalcity=inp.nextInt();

if(cityChoice<0 ||cityChoice>=12)//the user choice is not included in the list
{
System.out.println("Mistake,run the program again");
System.exit(0);
}
Main traGraph=new Main(12,cityChoice,goalcity);
traGraph.IDS();
}
public static String retCity(int i)
// the function returns city name, according to its index in V array
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
}}