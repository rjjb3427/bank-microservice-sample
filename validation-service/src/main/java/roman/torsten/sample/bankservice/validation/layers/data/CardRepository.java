package roman.torsten.sample.bankservice.validation.layers.data;

import org.springframework.data.repository.CrudRepository;

public interface CardRepository extends CrudRepository<CardEntity, String> {
}
