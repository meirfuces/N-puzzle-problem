public class Node {
    int[][]mat;
    int[]zero1;
    int []zero2;
    String keyMat="";
    private int cost;
    private boolean visit;
    private String parentMove;
    private String direction;
    private int moves;
    int row,col;
    Node (int [][]state) {
        this.mat = new int[state.length][state[0].length];
        row = state.length;
        col = state[0].length;
        zero1 = new int[2];
        zero1[0] = -1;
        zero2 = new int[2];
        zero2[0] = -1;
        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state[i].length; j++) {
                this.mat[i][j] = state[i][j];
                keyMat += mat[i][j] + " ";
                if (zero1[0]==-1&&this.mat[i][j] == 0) {
                    zero1[0] = i;
                    zero1[1] = j;
                    continue;
                }
                if (zero1[0] != -1 && this.mat[i][j] == 0) {
                    zero2[0] = i;
                    zero2[1] = j;
                }
            }
        }
    }

    public String getParentMove() {
        return parentMove;
    }

    public void setParentMove(String parentMove) {
        this.parentMove = parentMove;
    }

    public int getMoves() {
        return moves;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public boolean isVisit() {
        return visit;
    }

    public void setVisit(boolean visit) {
        this.visit = visit;
    }
}
