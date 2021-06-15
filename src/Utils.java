import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Utils {
    String nameAlgo="";
    String Time;
    String withOpen;
    String goalkey="";
    int[][]inputState;
    int [][]outputGoal;
    long startTime;
    boolean printTime;
Utils(){

}
    public void readerFile() throws FileNotFoundException {
       startTime = System.currentTimeMillis();
        String File = "input.txt";
        Scanner in = new Scanner(new FileReader(File));
        nameAlgo =in.nextLine();
                Time=in.nextLine();
                if (Time.equals("with time")){
             printTime = true;
                }
                        withOpen=in.nextLine();
                String Size = in.nextLine();
                int col = Integer.parseInt(""+Size.charAt(2));
                int row = Integer.parseInt(""+Size.charAt(0));

        inputState = new int [row][col];
        for (int i = 0; i <row ; i++) {
            String [] splitArr = in.nextLine().split(",");
            for (int j = 0; j <col ; j++) {
                if (splitArr[j].charAt(0)=='_') splitArr[j]="0";
                inputState[i][j] = Integer.parseInt(splitArr[j]);
            }
        }
        //out String goal state
        in.nextLine();
        outputGoal = new int [row][col];
        for (int i = 0; i <row ; i++) {
            String [] splitArr = in.nextLine().split(",");
            for (int j = 0; j <col ; j++) {
                if (splitArr[j].charAt(0)=='_') splitArr[j]="0";
                outputGoal[i][j] = Integer.parseInt(splitArr[j]);
                goalkey+= outputGoal[i][j]+ " ";
            }
        }



//    printMat(inputState);
//        printMat(outputGoal);


    }



    public void  write(long startTime, int cost,String path, int countVertex, boolean withTime  ) {
        try {
            FileWriter writer = new FileWriter("output.txt");
            long end = System.currentTimeMillis();
            float sec = (end - startTime) / 1000F;

            path=path.substring(0,path.length()-1 );
            writer.write(path);
            writer.write("\r\n");   // write new line
            writer.write("Num: " + countVertex);


            writer.write("\r\n");   // write new line
            writer.write("Cost: " + cost);

            if(withTime) {
                writer.write("\r\n");   // write new line
                writer.write( "seconds "+ sec);
            }
            writer.close();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    void printMat(int [][]mat){
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void printListOpen(HashMap<String,Node> openList) {
    for (Node a: openList.values()){
        printMat(a.mat);
    }
    }


}
