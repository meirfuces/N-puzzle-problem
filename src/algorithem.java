import java.io.IOException;
import java.util.*;

public class algorithem {
    static int counterVertex = 1;
    long startTime = 0;

    algorithem(long _startTime) {
        startTime = _startTime;
    }




    public String DFBnB(Utils reader) {
String path="";
int cost=1;
    Node start = new Node (reader.inputState);
    // create hash and stack
        HashMap<String, Node> H = new HashMap<>();
        Stack<Node> L = new Stack<>();
        // to save the nodes that bigger then g
        List<Node> listRemove = new LinkedList<>();
        L.add(start);
        H.put(start.keyMat, start);
         Comp comp = new Comp(reader);
        String result = "";
        int t = Integer.MAX_VALUE;
        //3
        while (!L.empty()) {
            if (reader.withOpen.equals("with open")) reader.printListOpen(H);
            //3.1 pop from the stack
            Node n = L.pop();
            //3.2
            if (n.isVisit()) {
                H.remove(n.keyMat);
            }
            //3.3
            else
            {
        //3.3.1
                n.setVisit(true);
                L.add(n);
                counterVertex++;

        //3.3.2 +3.3.3
                //sort the opChild
                PriorityQueue<Node> queueP = new PriorityQueue<Node>(comp);
                List<Node> getOpChild = OpClass.getChildren(n);
                queueP.addAll(getOpChild);
// iterator to run on queueP (can't with for each it;s prioritQueue)
                Iterator<Node> it = queueP.iterator();
                //3.3.4
                while (it.hasNext()) {

                    Node curr = it.next();
                    //3.3.4.1
                    if (comp.F(curr)>= t) {
                        //remove g all the nodes after it
                        removeNodes(queueP, t,listRemove,comp);


                        it = queueP.iterator();
                        //3.3.4.2
                    } else if (H.containsKey(curr.keyMat)  && H.get(curr.keyMat).isVisit()) {

                        queueP.remove(curr);
                        // put iterator after remove

                        it = queueP.iterator();

                    }
                    //3.3.4.3
                    else if (H.containsKey(curr.keyMat)&& !H.get(curr.keyMat).isVisit()) {
                        //3.3.4.3.1
                        if ( comp.F(H.get(curr.keyMat)) <=  comp.F(curr)) {
                            queueP.remove(curr);
                            // put iterator after remove
                            it = queueP.iterator();
                        }
                        //3.3.4.3.2
                        else {
                            L.remove(H.get(curr.keyMat));
                            H.remove(curr.keyMat);
                        }
                    }
                    //3.3.4.4
                    // we got the goal
                    else if (curr.keyMat.equals(reader.goalkey)) {

                        path = curr.getDirection();
                        cost = curr.getCost();
                        //3.3.4.4.1
                        t = comp.F(curr);
                        //3.3.4.4.3
                        removeNodes(queueP, t,listRemove,comp);
// after remove nodes
                        it = queueP.iterator();
                    }
                }
                //3.3.4.5
                //revrsing and add to the list
                List<Node> listRevrse = new LinkedList<>();
                listRevrse.addAll(queueP);
                Collections.reverse(listRevrse);
                for (Node a: listRevrse) {
                    L.add(a);
                    H.put(a.keyMat, a);
                }
            }
        }
//write to file
        reader.write(startTime, cost, path, counterVertex, reader.printTime);
        //4
        return result;

    }
// help function to remmove the nodes
    private void removeNodes(PriorityQueue<Node> queueP, int t, List<Node> l, Comp comp) {
        while (!queueP.isEmpty()) {
            if ( comp.F(queueP.peek())<t) {
                l.add(queueP.poll());
            } else {
                queueP.clear();
            }
        }
        for (Node insBack : l) {
            queueP.add(insBack);
        }
        l.clear();
    }


/*
Ida star
 */

    void IDA(Utils reader) {
        System.out.println("IDA");

        Node start = new Node(reader.inputState);
        Comp comp = new Comp(reader);
        // CREATE hash and stack
        //1
        HashMap<String, Node> H = new HashMap<String, Node>();
        Stack<Node> L = new Stack<>();
//2
        int t = comp.F(start);
//3
        while (t != Integer.MAX_VALUE) {
//3.1
            int minF = Integer.MAX_VALUE;
            start.setVisit(false);
//3.2
            L.push(start);
            H.put(start.keyMat, start);
//3.3
            while (!L.isEmpty()) {
                if (reader.withOpen.equals("with open")) reader.printListOpen(H);
//3.3.1
                Node n = L.pop();
                //3.3.2
                if (n.isVisit()) {
                    H.remove(n.keyMat);
                }
                //3.3.3.3
                else
                {

                    n.setVisit(true);
                    L.push(n);
    //3.2.3
                    List<Node> Op = OpClass.getChildren(n);
                    for (Node g : Op) {

                        if (comp.F(g) > t) {
                            minF = Math.min(minF, comp.F(g));
                            continue;
                        }
                        Node re = H.get(g.keyMat);

                        if (H.containsKey(g.keyMat) && re.isVisit())
                            continue;

                        if (H.containsKey(g.keyMat) && !re.isVisit()) {

                            if (comp.F(re) > comp.F(g)) {
                                H.remove(re.keyMat);
                                L.remove(g);

                            } else
                                continue;
                        }
//got the goal
                        if (reader.goalkey.equals(g.keyMat)) {

                            reader.write(this.startTime, g.getCost(), g.getDirection(), counterVertex, reader.printTime);

                            return;
                        }

                        counterVertex++;
                        L.push(g);
                        H.put(g.keyMat, g);
                    }
                }
            }

            t = minF;
        }



    }

    void a_Star(Utils reader) {
        Node start = new Node(reader.inputState);
        //create priority queue and 2 hashMap
        HashMap<String, Node> OL = new HashMap<>(); //open list
        HashMap<String, Node> CL = new HashMap<>(); // close list
        Comp compSort = new Comp(reader);
        PriorityQueue<Node> nodePriorityQueue = new PriorityQueue<Node>(compSort);
        // add start to the priorityqueue
        nodePriorityQueue.add(start);

        while (!nodePriorityQueue.isEmpty()) {
            if (reader.withOpen.equals("with open")) reader.printListOpen(OL);

            start = nodePriorityQueue.poll();
            //the goal
            if (start.keyMat.equals(reader.goalkey)) {
                reader.write(this.startTime, start.getCost(), start.getDirection(), counterVertex, reader.printTime);

                return;
            }
            //add n to open list
            OL.put(start.keyMat, start);
            ArrayList<Node> Op = OpClass.getChildren(start);
            for (Node n : Op) {
                if (!OL.containsKey(n.keyMat) && !CL.containsKey(n.keyMat)) {
                    counterVertex++;
                    nodePriorityQueue.add(n);

                    CL.put(n.keyMat, n);
                }
                //replace the node in L with X
                if (OL.containsKey(n.keyMat)) {
                    Node in = OL.get(n.keyMat);
                    if (compSort.F(in) > compSort.F(n)) {
                        nodePriorityQueue.remove(in);
                        nodePriorityQueue.add(n);
                        OL.remove(in.keyMat);
                        OL.put(n.keyMat, n);
                    }
                }
            }
        }

    }

    /*
    DFID ALGO
     */
    void DFID(Utils reader) {
        Node start = new Node(reader.inputState);
        for (int depth = 1; depth < Integer.MAX_VALUE; depth++) {
            HashMap<String,Node> openList = new HashMap<>();
            String result = Limited_DFS(start, reader.goalkey, depth, openList, reader);
            if (!result.equals("cutoff")) {
                return;
            }

        }
    }

    private String Limited_DFS(Node n, String goalkey, int limit, HashMap<String,Node> openList, Utils reader) {
        if (reader.withOpen.equals("with open")) reader.printListOpen(openList);

        if (n.keyMat.equals(goalkey)) {
            reader.write(this.startTime, n.getCost(), n.getDirection(), counterVertex, reader.printTime);

            return n.getDirection();
        } else if (limit == 0) {
            return "cutoff";
        } else {

            openList.put(n.keyMat,n);
            boolean isCutoff = false;
            /// get all operators
            List<Node> OpChild = OpClass.getChildren(n);
            for (Node g : OpChild) {
                counterVertex++;


                if (openList.containsKey(g.keyMat)) continue;

//				 recorsia with level-1
                String result = Limited_DFS(g, goalkey, limit - 1, openList, reader);
                if (result.equals("cutoff")) isCutoff = true;
                else if (!result.equals("fail")) {

                    return result;
                }
            }
            openList.remove(n.keyMat);
            if (isCutoff == true) return "cutoff";
            else return "fail";
        }

    }

    void BFS(Utils reader, long startTime) {
        Node start = new Node(reader.inputState);
        // create hash and queue
        HashMap<String, Node> openList = new HashMap<>();
        Queue<Node> Q = new LinkedList<>();
        Q.add(start);
        HashMap<String, Node> closeList = new HashMap<>();
        String goal = reader.goalkey;
        int count = 1;
// ADD REACH to goal becuase we know that we get a answer otherwise have a mistake
        while (!Q.isEmpty() && !start.keyMat.equals(goal)) {
            Node curr = Q.poll();
            if (reader.withOpen.equals("with open")) reader.printListOpen(openList);
            List<Node> children = OpClass.getChildren(curr);
            for (Node g : children) {
                count++;

                if (!openList.containsKey(g.keyMat) && !closeList.containsKey(g.keyMat)) {
                    // got the goal
                    if (g.keyMat.equals(goal)) {
                        System.out.println("path: " + g.getDirection());
                        System.out.println("cost: " + g.getCost());
                        System.out.println("Num: " + count);
                        reader.write(startTime, g.getCost(), g.getDirection(), count, reader.printTime);

                        return;
                    }
                    // add to the hash and queue
                    openList.put(g.keyMat, g);
                    Q.add(g);
                }
            }
            closeList.put(curr.keyMat, curr);
        }
    }

// My comperator for sort List and to do huristic function
    //using for algoritem A* IDA* and DFBnB

    public class Comp implements Comparator<Node> {
        Utils reader;
//constractur
        public Comp(Utils _reader) {
            reader = _reader;
        }
// the function for comp
        @Override
        public int compare(Node o1, Node o2) {

            if (F(o1) > F(o2)) {
                return 1;
            }
            if (F(o1) < F(o2)) {
                return -1;
            }
// check who is created before
            if (o1.getMoves() < o2.getMoves()) {
                return -1;
            }
            if (o1.getMoves() > o2.getMoves()) {
                return 1;

            }
            // if the num of moves equal go by the order of the task
            else if (o1.getMoves() == o2.getMoves()) {

                if (o1.getParentMove().equals("LL")) return -1;
                if (o2.getParentMove().equals("LL")) return 1;
                if (o1.getParentMove().equals("UU")) return -1;
                if (o2.getParentMove().equals("UU")) return 1;
                if (o1.getParentMove().equals("RR")) return -1;
                if (o2.getParentMove().equals("RR")) return 1;
                if (o1.getParentMove().equals("DD")) return -1;
                if (o2.getParentMove().equals("DD")) return 1;

                if (o1.getParentMove().equals("L")) return -1;
                if (o2.getParentMove().equals("L")) return 1;
                if (o1.getParentMove().equals("U")) return -1;
                if (o2.getParentMove().equals("U")) return 1;
                if (o1.getParentMove().equals("R")) return -1;
                if (o2.getParentMove().equals("R")) return 1;
                if (o1.getParentMove().equals("D")) return -1;
                if (o2.getParentMove().equals("D")) return 1;
            }
            return -1;
        }
        // huristic function
        public int F(Node x) {
            return (int) (x.getCost()*1) + (int)(h(x, x.mat, this.reader.outputGoal)*1);
        }
// manheten function

        private int h(Node x, int[][] stateMAt, int[][] goalState) {

            int dist = 0;

            for (int i = 0; i < stateMAt.length; i++) {
                for (int j = 0; j < stateMAt[0].length; j++) {
//                if (mat[i][j]==0) continue;
                    if (stateMAt[i][j] != goalState[i][j]&&stateMAt[i][j]!=0) { // checks if the matrix is equal in all places if not search the block to place
                        for (int l = 0; l < stateMAt.length; l++) {
                            for (int k = 0; k < stateMAt[0].length; k++) {
                                if (stateMAt[i][j] == (goalState[l][k])) {

                                    dist += (Math.abs(i - l) + Math.abs(j - k));

                                }
                            }
                        }
                    }
                }
            }
            if (reader.nameAlgo.equals("A*"))
                return (int)(1.62*dist);
            return (int)(dist*3);
        }
    }
}
