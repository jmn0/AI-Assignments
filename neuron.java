
import java.util.Scanner;
public class neuron {

 private float w1st; // The 1st weight
 private float w2nd; // The 2nd weight
 private float threshold;
 private float learnRate;
 private boolean doEpoch; //false: do epoch again,true: not to do epoch

 public static void main(String []args)
 {
 float w1;float w2;
 System.out.println("The program deals with one neural network. By running the program");
 System.out.println("the neuron repeatedly improves its output till it becomes equivalent to AND-gate output");
 System.out.println("The number of the neuron inputs are two with two weights\n");

 Scanner inp=new Scanner (System.in);

 System.out.print("Enter weight1 [range: -0.5,0.5]: ");
 w1=Float.parseFloat(inp.nextLine());//weight1 from the user
 //inp.nextLine();
 System.out.print("Enter weight2 [range: -0.5,0.5]: ");
 w2=Float.parseFloat(inp.nextLine());//weight2 from the user
 //inp.nextLine();

 if ( (w1<-0.5 || w1>0.5) || (w2<-0.5 || w2>0.5) )
 {
 System.out.println("The weights are not correct. Run the program again");
 System.exit(0);//Stop the program if the range of weights' values are not in (-0.5,0.5)
 }

 neuron ANN=new neuron(w1,w2,0.2f,0.1f); 
 //threshold: 0.2f, learning Rate: 0.1f
 ANN.Epoch(); //do the 1st epoch
 }
 
public void neurWeightUpdate(float X1, float X2, float Y, float Yd)
 {
 doEpoch=true;
 float err=Yd-Y;
// 
 w1st=w1st + learnRate * X1 * err;//weight1 is updated
 w1st=(float)Math.round(w1st*10)/10;

 w2nd=w2nd + learnRate * X2 * err;//weight2 is updated
 w2nd=(float)Math.round(w2nd*10)/10;
 }


 public float compActOut(float X1,float X2)//calculate the actual ouput (Y)
 {
 float output;
 output = X1 * w1st + X2 * w2nd;
 if (output>=threshold) // Actual output > the threshold, true: 1.0, false: 0.0
 return 1.0f;
 return 0.0f;
 }

public neuron(float w1,float w2,float th,float rate) { //the constructor
 w1st=w1;w2nd=w2;threshold=th; learnRate=rate;
 }

 public void Epoch( ) {
 doEpoch=false; float actOutput;
 float inpX1,inpX2,desOutput;

 /* The truth table of AND-GATE:
 X1 X2 Y(Output)
 The 1st |0.0|,|0.0| |0.0|
 The 2nd |0.0|,|1.0| |0.0|
 The 3rd |1.0|,|0.0| |0.0|
 The 4th |1.0|,|1.0| |1.0|
 */

//the 1st case
 System.out.println("-------------------------------------------");
 System.out.println("|X-1|X-2|Yde|we1|we2|Yac|Err|we1|we2|");

 inpX1=0.0f; inpX2=0.0f; desOutput = 0.0f;
 actOutput=compActOut(inpX1,inpX2);

System.out.print("|"+inpX1+"|"+inpX2+"|"+desOutput+"|"+w1st+"|"+w2nd+"|"+actOutput+"|"+
Float.toString(desOutput-actOutput)+"|");

 if(actOutput != desOutput)//true if there is a difference between the actual and desired output
    neurWeightUpdate(inpX1,inpX2,actOutput,desOutput);
 //The weights are to be updated if the condition is true
 System.out.println(w1st+"|"+w2nd+"|");

 //the 2nd case
 inpX1=0.0f; inpX2=1.0f; desOutput = 0.0f;
 actOutput=compActOut(inpX1,inpX2);

System.out.print("|"+inpX1+"|"+inpX2+"|"+desOutput+"|"+w1st+"|"+w2nd+"|"+actOutput+"|"+
Float.toString(desOutput-actOutput)+"|");

if (actOutput != desOutput)
    neurWeightUpdate(inpX1,inpX2,actOutput,desOutput);
 System.out.println(w1st+"|"+w2nd+"|");

 //the 3rd case
 inpX1=1.0f; inpX2=0.0f; desOutput = 0.0f;
 actOutput=compActOut(inpX1,inpX2);

System.out.print("|"+inpX1+"|"+inpX2+"|"+desOutput+"|"+w1st+"|"+w2nd+"|"+actOutput+"|"+
Float.toString(desOutput-actOutput)+"|");

 if(actOutput != desOutput)
    neurWeightUpdate(inpX1,inpX2,actOutput,desOutput);
 System.out.println(w1st+"|"+w2nd+"|");

//the 4th case
 inpX1=1.0f; inpX2=1.0f; desOutput = 1.0f;
 actOutput=compActOut(inpX1,inpX2);

System.out.print("|"+inpX1+"|"+inpX2+"|"+desOutput+"|"+w1st+"|"+w2nd+"|"+actOutput+"|"+
Float.toString(desOutput-actOutput)+"|");

 if(actOutput != desOutput)
    neurWeightUpdate(inpX1, inpX2, actOutput, desOutput);
 System.out.println(w1st+"|"+w2nd+"|");

 if (doEpoch==true)
    Epoch();
 }

}