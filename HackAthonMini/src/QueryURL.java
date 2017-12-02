import java.util.*;

public class QueryURL {
    private String start;
    private String goal;
    private String check;
    private boolean toCheck;
    public QueryURL(String start, String goal,String check, boolean toCheck)
    {
        this.start=start;
        this.goal=goal;
        this.check=check;
        this.toCheck=toCheck;
    }
    public ArrayList<String> process() {
        HashSet<Integer> goneTo = new HashSet<>();
        HashMap<Integer,Vertex> vertices = new HashMap<>();
        ArrayList<String> output = new ArrayList<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        goneTo.add(start.hashCode());
        boolean done = false;
        String current;
        while(!queue.isEmpty() && !done)
        {
            //System.out.println("here1");
            current = queue.remove().trim();
            Vertex v = new Vertex(current);
            vertices.put(current.hashCode(),v);
            //goneTo.add(current.hashCode());
            output.add(current);
            //System.out.println("popping " + current);
            if(!AGreatIdea.extractUrlsFromString(current,check,toCheck).isEmpty()) {
                ArrayList<String> temp = QueryFunctions.getLinks(current.trim());

                //System.out.println("here");
                for (int i = 0; i < temp.size(); i++) {
                    String tempNode = temp.get(i).trim();
                    // System.out.println(temp.get(i));
                    if (tempNode.equals(goal)) {
                        //System.out.println("found " + goal);
                        output.add(tempNode);
                        v.addNeighbor(tempNode);
                        vertices.put(tempNode.hashCode(), new Vertex(tempNode));
                        done = true;
                        i = temp.size();
                    } else {
                        if (!AGreatIdea.extractUrlsFromString(tempNode, check, toCheck).isEmpty() && !goneTo.contains(tempNode.hashCode())) {
                            v.addNeighbor(tempNode);
                            queue.add(tempNode);
                            goneTo.add(tempNode.hashCode());
                            //System.out.println("Adding " + temp.get(i).trim());
                        }
                    }
                }
            }
           // System.out.println("here2");
        }
       // System.out.println(output.size());
        Vertex end = vertices.get(output.get(output.size()-1).hashCode());
        if(end==null) System.out.println("empty");
        for(int i=output.size()-2; output.size()>2 && i>=0 && end!=null; i--)
        {
           // System.out.println("here");
            if(vertices.get(output.get(i).hashCode()).getNeighbors().contains(end.getUrl().hashCode()))
            //if(end.getNeighbors().contains(output.get(i).hashCode()))
            {
               // System.out.println("keeping " + output.get(i));
                end = vertices.get(output.get(i).hashCode());
            }
            else
            {
               // System.out.println("removing " + output.get(i));
                output.remove(i);
                //i++;
            }
        }
        return output;
    }
}
