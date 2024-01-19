package study.petclinic.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.petclinic.domain.Address;
import study.petclinic.domain.Owner;
import study.petclinic.exception.OwnerNotFoundException;
import study.petclinic.repository.OwnerRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OwnerService {

    private final OwnerRepository ownerRepository;

    @Transactional
    public Long register(Owner owner) {
        ownerRepository.save(owner);
        return owner.getId();
    }

    public List<Owner> findOwners() {
        return ownerRepository.findAll();
    }

    public Owner findOne(Long id) {
        return ownerRepository.findById(id).orElseThrow(() -> new OwnerNotFoundException("Owner not found with id: " + id));
    }

    public List<Owner> searchOwners(String name) {
        return ownerRepository.findByName(name);
    }

    @Transactional
    public void update(Long id, Address address) {
        Owner owner = ownerRepository.findById(id).orElseThrow(() -> new OwnerNotFoundException("Owner not found with id: " + id));
        owner.changeAddress(address);
    }
}
