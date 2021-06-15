import java.util.ArrayList;
import java.util.List;

public class OpClass {
    OpClass(){
        
    }
    /*
    this functin get the node with state and return list of operator from this state
     */
    public static ArrayList<Node> getChildren(Node current) {

        ArrayList<Node> OpChild = new ArrayList<Node>();
        if (current.getParentMove() == null) current.setParentMove("");
        if (current.getDirection()==null) current.setDirection("");
        int i_Zero = 0, j_Zero = 0;
        int[][] CurrState = current.mat;
                /*
                explain for the fucntions
                the function get node and the list. check the border and the parent.
                swap the state matrix and then create Node with state mat.
                set to the node the cost the direction iterazit and it to the list.
                after that add it to the list.
                 */

                    DobleLeft(current, OpChild);
                    DoubleUp(current, OpChild);

                    DoubleRight(current, OpChild);
                    DoubleDown(current, CurrState,  OpChild);
// for the first zero
                    leftMove(current, current.zero1[0], current.zero1[1], OpChild);
                    upMove(current, current.zero1[0], current.zero1[1], OpChild);
                    rightMove(current, current.zero1[0], current.zero1[1], OpChild);
                    downMove(current, current.zero1[0], current.zero1[1], OpChild);
                // check if has 2 zeros
                    if (current.zero2[0]!=-1) {
                        leftMove(current, current.zero2[0], current.zero2[1], OpChild);
                        upMove(current, current.zero2[0], current.zero2[1], OpChild);
                        rightMove(current, current.zero2[0], current.zero2[1], OpChild);
                        downMove(current, current.zero2[0], current.zero2[1], OpChild);

        }
        return  OpChild;
    }
    private static void DobleLeft(Node curr, ArrayList<Node> OpChild) {
int [][] currState = curr.mat;
        int i_0 =curr.zero1[0];
        int j_0= curr.zero1[1];
            if(!curr.getParentMove().equals("LL")&&i_0<currState.length-1 && currState[i_0+1][j_0]==0 && j_0+1<currState[0].length){

                changePos(currState, i_0,j_0,i_0,j_0+1) ;
                changePos(currState, i_0+1,j_0,i_0+1,j_0+1);
                Node left_left =new Node(currState);
                left_left.setParentMove("RR");
                left_left.setMoves(curr.getMoves()+1);
                left_left.setCost(curr.getCost() + 6);
                left_left.setDirection(curr.getDirection() + currState[i_0][j_0+1] + "&" +currState[i_0+1][j_0+1] +"R-");
                OpChild.add(left_left);
                changePos(currState, i_0,j_0+1,i_0,j_0) ;
                changePos(currState, i_0+1,j_0+1,i_0+1,j_0) ;

        }
    }


    private static void DoubleDown(Node current, int[][] newState, ArrayList<Node> OpChild) {
        int i_0 =current.zero1[0];
        int j_0= current.zero1[1];
        int [][] currState = current.mat;
        if(!current.getParentMove().equals("UU")&&j_0<newState[0].length-1 && i_0>0&& newState[i_0][j_0+1]==0){

            changePos(currState, i_0,j_0,i_0-1,j_0) ;
            changePos(currState, i_0,j_0+1,i_0-1,j_0+1);
            Node DownNode =new Node(currState);
            DownNode.setParentMove("DD");
            DownNode.setMoves(current.getMoves()+1);

            DownNode.setCost(current.getCost() + 7);
            DownNode.setDirection(current.getDirection() + currState[i_0][j_0] + "&" +currState[i_0][j_0+1] +"D-");
            OpChild.add(DownNode);
            changePos(newState, i_0-1,j_0,i_0,j_0) ;
            changePos(newState, i_0-1,j_0+1,i_0,j_0+1) ;



        }




    }

    private static void DoubleRight(Node current, ArrayList<Node> OpChild) {
        int i_0 =current.zero1[0];
        int j_0= current.zero1[1];
        int [][] currState = current.mat;

            if(!current.getParentMove(). equals("RR")&&j_0>0 && i_0>currState.length-1&& currState[i_0+1][j_0]==0){

                changePos(currState, i_0,j_0,i_0,j_0-1) ;
                changePos(currState, i_0+1,j_0,i_0+1,j_0-1);
                Node right_Node =new Node(currState);
                changePos(currState, i_0,j_0-1,i_0,j_0) ;
                changePos(currState, i_0+1,j_0-1,i_0+1,j_0) ;
                right_Node.setMoves(current.getMoves()+1);

                right_Node.setCost(current.getCost() + 6);
                right_Node.setParentMove("LL");
                right_Node.setDirection(current.getDirection() +currState[i_0][j_0-1] + "&" +currState[i_0+1][j_0-1] +"L-");
                OpChild.add(right_Node);



        }

    }

    private static void DoubleUp(Node current, ArrayList<Node> OpChild) {
        int i_0 =current.zero1[0];
        int j_0= current.zero1[1];
        int [][] currState = current.mat;
            if(!current.getParentMove().equals("DD")&& j_0<currState[0].length-1 && i_0<currState.length-1&& currState[i_0][j_0+1]==0){

                changePos(currState, i_0,j_0,i_0+1,j_0) ;
                changePos(currState, i_0,j_0+1,i_0+1,j_0+1);
                Node up_Node =new Node(currState);
                changePos(currState, i_0+1,j_0,i_0,j_0) ;
                changePos(currState, i_0+1,j_0+1,i_0,j_0+1) ;
                up_Node.setMoves(current.getMoves()+1);

                up_Node.setCost(current.getCost() + 7);
                up_Node.setParentMove("UU");
                up_Node.setDirection(current.getDirection() + currState[i_0+1][j_0] + "&" +currState[i_0+1][j_0+1] +"U-");
                OpChild.add(up_Node);
            }


    }


    private static void leftMove(Node current, int i_0, int j_0, ArrayList<Node> OpChild) {

        int [][] currState = current.mat;

            if(!current.getParentMove().equals("R")&&j_0<currState[0].length-1){
                changePos(currState, i_0,j_0,i_0,j_0+1) ;
                Node leftNode =new Node(currState);
                changePos(currState, i_0,j_0+1,i_0,j_0) ;
                leftNode.setCost(current.getCost() + 5);
                leftNode.setMoves(current.getMoves()+1);

                leftNode.setParentMove("L");
                leftNode.setDirection(current.getDirection() + currState[i_0][j_0 + 1] + "L-");
                OpChild.add(leftNode);
        }

    }

    private static void upMove(Node current, int i_0, int j_0, ArrayList<Node> OpChild) {

        int [][] currState = current.mat;

            if(!current.getParentMove(). equals("D")&&i_0<currState.length-1){
//            System.out.println("up");

                changePos(currState, i_0,j_0,i_0+1,j_0) ;
                Node upNode =new Node(currState);
                upNode.setMoves(current.getMoves()+1);

                upNode.setCost(current.getCost()+5);
                upNode.setParentMove("U");
                upNode.setDirection(current.getDirection()+ currState[i_0][j_0]+"U-");
                OpChild.add(upNode);
                changePos(currState, i_0+1,j_0,i_0,j_0) ;
        }
    }

    private static void rightMove(Node current, int i_0, int j_0, ArrayList<Node> OpChild) {

        int [][] currState = current.mat;

            if(!current.getParentMove().equals("L")&&j_0>0){

                changePos(currState, i_0,j_0,i_0,j_0-1) ;
                Node rightState =new Node(currState);
                rightState.setCost(current.getCost()+5);
                rightState.setMoves(current.getMoves()+1);

                rightState.setParentMove("R");
                rightState.setDirection(current.getDirection()+currState[i_0][j_0]+"R-");
                OpChild.add(rightState);
                changePos(currState, i_0,j_0-1,i_0,j_0) ;
            }


    }
    private static void downMove(Node current, int i_0, int j_0, ArrayList<Node> OpChild) {

        int [][] currState = current.mat;

            if(!current.getParentMove().equals("U")&&i_0>0){



                changePos(currState, i_0,j_0,i_0-1,j_0) ;
                Node downState =new Node(currState);
                downState.setMoves(current.getMoves()+1);

                downState.setParentMove("D");
                downState.setCost(current.getCost()+5);
                downState.setDirection(current.getDirection()+currState[i_0][j_0]+"D-");
                OpChild.add(downState);
                changePos(currState, i_0-1,j_0,i_0,j_0) ;
        }
    }




/*
swap zero and number
 */
    private static void changePos(int[][] newState, int i_0, int j_0, int p, int o) {

        newState[i_0][j_0] = newState[p][o];
        newState[p][o] = 0;

    }
}
