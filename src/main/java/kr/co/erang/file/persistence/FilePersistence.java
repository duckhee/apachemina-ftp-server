package kr.co.erang.file.persistence;

import kr.co.erang.file.domain.FileDomain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilePersistence extends JpaRepository<FileDomain, Long> {
}
