import java.util.HashSet;

public class Vertex {
    private String url;
    private HashSet<Integer> neighbors = new HashSet<>();
    public Vertex(String url)
    {
        this.url=url;
    }
    public void addNeighbor(String add)
    {
        neighbors.add(add.hashCode());
    }

    public String getUrl() {
        return url;
    }

    public HashSet<Integer> getNeighbors() {
        return neighbors;
    }
}
