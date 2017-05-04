package roman.torsten.sample.bankservice.validation.layers.data;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cards")
public class CardEntity {
    @Id
    private String id;
    private String holderName;

    public CardEntity() {
    }

    public CardEntity(String id, String holderName) {
        this.id = id;
        this.holderName = holderName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }
}
