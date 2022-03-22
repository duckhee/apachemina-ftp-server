package kr.co.erang.user.persistence;

import kr.co.erang.user.domain.UserDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserPersistence extends JpaRepository<UserDomain, Long> {

    public Optional<UserDomain> findByUserId(String userId);

    public boolean existsByUserId(String userId);
}
