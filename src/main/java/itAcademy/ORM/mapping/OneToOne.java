package itAcademy.ORM.mapping;

public class OneToOne {

    private Integer foreignKey;

    public OneToOne(Integer foreignKey) {
        this.foreignKey = foreignKey;
    }

    public Integer getForeignKey() {
        return foreignKey;
    }

    public void setForeignKey(Integer foreignKey) {
        this.foreignKey = foreignKey;
    }
}
