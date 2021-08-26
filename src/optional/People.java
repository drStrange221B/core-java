package optional;

public class People {
    private String name;
    private Integer id;

    public People(String name, Integer id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
