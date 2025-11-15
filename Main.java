import java.util.*;// input : start city and goal city , output : path 

public class Main {

int N; // number of vertices in the graph
boolean[ ][ ] G; // the graph as an adjacency matrix
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
void setupGraph() //constructor 
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
void DFS(){ 
Stack<Integer> sta=new Stack<Integer>();
ArrayList<Integer> path = new ArrayList<>();
boolean[] vis = new boolean[N];

path.add(currPos);
vis[currPos]=true;

for(int i=0;i<N;i++){
if(G[currPos][i]){  
sta.push(i);
}}

while (!sta.isEmpty()){
    int node = sta.pop();
    boolean nochild = false;
    
     if (vis[node]) {
            continue;
        }
        
    path.add(node);
        vis[node] = true;
    
    if(node == goal){
    break;}
    
    for(int i=0;i<N;i++){
        if(G[node][i]==true){
            sta.push(i);
        }
    }
    
}
System.out.println("Path: ");
    for (int i =0 ;i<path.size();i++){
        if (i == path.size() - 1) {
                System.out.print(retCity(path.get(i)));
            } else {
                System.out.print(retCity(path.get(i)) + " -> ");
            }
    }
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
traGraph.DFS();
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
