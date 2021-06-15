import java.io.FileNotFoundException;

public class Ex1 {

    public static void main(String[] args) throws FileNotFoundException {
        Utils util = new Utils();
        util.readerFile();
        long startTime = System.currentTimeMillis();
        algorithem algo = new algorithem(startTime);

        switch (util.nameAlgo) {

            case "BFS":
                algo.BFS(util, startTime);
                break;
            case "DFID":
                algo.DFID(util);
                break;
            case "A*":
                algo.a_Star(util);
                break;
            case "IDA*":
                algo.IDA(util);
                break;
            case "DFBnB":
                algo.DFBnB(util);

        }
    }


}
