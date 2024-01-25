package study.petclinic.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.petclinic.domain.Vet;
import study.petclinic.repository.VetRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VetService {

    private final VetRepository vetRepository;

    public List<Vet> findAllVets() {
        List<Vet> vets = vetRepository.findAll();
        return vets;
    }
}
