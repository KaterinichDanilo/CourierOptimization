import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Vertex {
    private String label;
    private boolean isVisited;
    private int number;


    public Vertex(String label) {
        this.label = label;
        this.isVisited = false;
    }

    public Vertex(String label, int number, boolean isVisited) {
        this.label = label;
        this.number = number;
        this.isVisited = isVisited;
    }
}
