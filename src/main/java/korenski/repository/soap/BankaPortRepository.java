package korenski.repository.soap;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import korenski.model.soap.BankaPort;

@Repository
public interface BankaPortRepository extends CrudRepository<BankaPort, Long>{

	public BankaPort findBySwiftCode(String swiftCode);
	public BankaPort findOne(Long id);
	public BankaPort save(BankaPort bankaPort);
}
