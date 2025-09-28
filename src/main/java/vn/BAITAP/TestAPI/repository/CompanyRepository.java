package vn.BAITAP.TestAPI.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import vn.BAITAP.TestAPI.domain.Company;

public interface CompanyRepository extends JpaRepository<Company, Long>, JpaSpecificationExecutor<Company> {
    public Company save(Company company);

    public Page<Company> findAll(Pageable pageable);

    public List<Company> findAll();

    public Company findById(long id);

    public void deleteById(long id);

    public List<Company> findAll(Specification<Company> com);
}
