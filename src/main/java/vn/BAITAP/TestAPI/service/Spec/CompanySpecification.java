package vn.BAITAP.TestAPI.service.Spec;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import vn.BAITAP.TestAPI.domain.Company;
import vn.BAITAP.TestAPI.domain.Job;

@Service
public class CompanySpecification {
    public Specification<Company> likeNameAndAddress(String nameCom, String addressCom) {
        Specification<Company> specCon = Specification.where(null);
        if (nameCom != null) {
            Specification<Company> spe1 = (root, query, builder) -> builder.like(root.get("name"), "%" + nameCom + "%");
            specCon = specCon.and(spe1);
        }
        if (addressCom != null) {
            Specification<Company> spe2 = (root, query, builder) -> builder.like(root.get("address"),
                    "%" + addressCom + "%");
            specCon = specCon.and(spe2);
        }

        return specCon;
    }
}
