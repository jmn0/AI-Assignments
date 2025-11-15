
import java.util.*; 
 
public class Greedy 
{     
    int N;  // number of vertices in the graph 
    boolean[ ][ ] G;  // the graph as an adjacency matrix 
    int [] StrdestToGoal;  // an array to keep the distance 
    int [][] toCityCost;
    boolean[] vis;
    int costFromStart[];

    public static void main(String[] args) { 
        new Greedy(); // I like doing things this way so I don't have to use static objects 
    } 
     
    Greedy( ) { 
        N=13; 
        setupGraph(); 
        setupStrdest(); 
        btwCities();    
        vis = new boolean[N];
        costFromStart = new int[N];
        Arrays.fill(costFromStart, Integer.MAX_VALUE);
        costFromStart[10] = 0;
        System.out.println("------------------------------"); 
        System.out.println(); 
        
         
        // perform a GREEDY search algorithm on the graph 
        int goal = 2;
        Greedy(10, 2, costFromStart);                             //Arad city is to be the start location, Arad=10 in the array 
        System.out.println(); 
        System.out.println("------------------------------"); 
        System.out.println(); 
    }     
 
    // initial setup of the graph 
    void setupGraph() 
    {  
        G=new boolean[N][N]; 
         
        G[0][1]=G[1][0]=true; // notice that for each edge G[i][j] == G[j][i] 
        G[0][3]=G[3][0]=true; // this means that the graph is undirected 
 
  
 
        G[1][5]=G[5][1]=true; 
        G[1][7]=G[7][1]=true; 
        G[2][8]=G[8][2]=true; 
        G[2][5]=G[5][2]=true; 
        G[3][4]=G[4][3]=true; 
        G[4][6]=G[6][4]=true; 
        G[5][7]=G[7][5]=true; // notice that for each edge G[i][j] == G[j][i] 
        G[6][10]=G[10][6]=true; 
        G[7][9]=G[9][7]=true; 
        G[8][9]=G[9][8]=true; 
        G[9][10]=G[10][9]=true; 
        G[9][12]=G[12][9]=true; 
        G[10][11]=G[11][10]=true; 
        G[11][12]=G[12][11]=true;         
    } 
    
    int Hfunc(int i)  { 
    return StrdestToGoal[i]; }
     
    int HGfunc(int i, int goal, int[] costFromStart) {
    return costFromStart[i] + StrdestToGoal[i];
        }

     
    void setupStrdest()  { 
        StrdestToGoal=new int[N]; 
        StrdestToGoal[0]=242; //Dobreta 
        StrdestToGoal[1]=160; //Craiova 
        StrdestToGoal[2]=0; //Bucharest 
        StrdestToGoal[3]=241; //Mehadia 
        StrdestToGoal[4]=244; //Lugoj 
        StrdestToGoal[5]=98; //Pitesti 
        StrdestToGoal[6]=329; //Timisoara 
        StrdestToGoal[7]=193; //Rimnicu Vilcea 
        StrdestToGoal[8]=178; //Fagaras 
        StrdestToGoal[9]=253; //Sibiu 
        StrdestToGoal[10]=366; //Arad 
        StrdestToGoal[11]=374; //Zerind 
        StrdestToGoal[12]=380; //Oradea         
    } 
     
    void btwCities() { 
toCityCost=new int[N][N]; 
toCityCost[0][1]=toCityCost[1][0]=120;  
toCityCost[0][3]=toCityCost[3][0]=75;  
toCityCost[1][5]=toCityCost[5][1]=138; 
toCityCost[1][7]=toCityCost[7][1]=146; 
toCityCost[2][8]=toCityCost[8][2]=211; 
toCityCost[2][5]=toCityCost[5][2]=101; // notice that for each edge G[i][j] == G[j][i] 
// this means that the graph is undirected 
toCityCost[3][4]=toCityCost[4][3]=70; 
toCityCost[4][6]=toCityCost[6][4]=111; 
toCityCost[5][7]=toCityCost[7][5]=97;  
toCityCost[6][10]=toCityCost[10][6]=118; 
toCityCost[7][9]=toCityCost[9][7]=80; 
toCityCost[8][9]=toCityCost[9][8]=99; 
toCityCost[9][10]=toCityCost[10][9]=140; 
toCityCost[9][12]=toCityCost[12][9]=151; 
toCityCost[10][11]=toCityCost[11][10]=75;         
toCityCost[11][12]=toCityCost[12][11]=71; 
}
  

 
    // perform a Greedy search on the aforementioned graph G      
void Greedy(int loc, int goal, int[] costFromStart)  { 

    List<Integer> list = new ArrayList<Integer>();

if (loc == goal) {
    System.out.println("Goal has been reached, at " + retCity(loc));
    return;
}
        
    System.out.println("The current location is: " + retCity(loc)); 
        
    vis[loc] = true;
    System.out.print("["); 
    for (int i = 0; i < N; i++) {
        if (G[i][loc] && !vis[i]) { 
            list.add(i);
            int newCost = costFromStart[loc] + toCityCost[loc][i];
            if (newCost < costFromStart[i]) {
                costFromStart[i] = newCost;
            }
            System.out.print(retCity(i) + "(" + HGfunc(i, goal, costFromStart) + ")");
        }             
    }
    System.out.println("]\n");

    if (list.size() == 1) {
        Greedy(list.get(0), goal, costFromStart); 
        return;
    }

    int dest = list.get(0); 
    for (int i = 1; i < list.size(); i++) {
        if (HGfunc(list.get(i), goal, costFromStart) < HGfunc(dest, goal, costFromStart)) {
            dest = list.get(i); 
        }
    }

    if (HGfunc(dest, goal, costFromStart) != 0) {
        Greedy(dest, goal, costFromStart);
    }
}

        String retCity(int i) // the function returns city name, according to its index in V array 
        { 
            if(i==0) 
            return "Dobreta"; 
            else if(i==1) 
            return "Craiova"; 
            else if(i==2) 
return "Bucharest"; 
else if(i==3) 
return "Mehadia"; 
else if(i==4) 
return "Lugoj"; 
else if(i==5) 
return "Pitesti"; 
else if(i==6) 
return "Timisoara"; 
else if(i==7) 
return "Rimnicu Vilcea"; 
else if(i==8) 
return "Fagaras"; 
else if(i==9) 
return "Sibiu"; 
else if(i==10) 
return "Arad"; 
else if(i==11) 
return "Zerind"; 
else 
return "Oradea";             
} 
}