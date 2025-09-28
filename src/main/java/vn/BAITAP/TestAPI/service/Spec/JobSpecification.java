package vn.BAITAP.TestAPI.service.Spec;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import vn.BAITAP.TestAPI.domain.Job;

@Service
public class JobSpecification {
    public static Specification<Job> lessThanCon(long salary, String typeJob, String nameJob) {
        Specification<Job> specCon = Specification.where(null);
        if (salary != 0) {
            Specification<Job> spe1 = (root, query, builder) -> builder.lessThanOrEqualTo(root.get("salary"), salary);
            specCon = specCon.and(spe1);
        }
        if (typeJob != null) {
            Specification<Job> spe2 = (root, query, builder) -> builder.like(root.get("typeJob"), "%" + typeJob + "%");
            specCon = specCon.and(spe2);
        }
        if (nameJob != null) {
            Specification<Job> spe3 = (root, query, builder) -> builder.like(root.get("nameJob"), "%" + nameJob + "%");
            specCon = specCon.and(spe3);
        }

        return specCon;
    }

    public static Specification<Job> lessThanAndGreaterThanCons(long salary1, long salary2, String typeJob,
            String nameJob) {
        Specification<Job> specCon = Specification.where(null);
        if (salary1 != 0) {
            Specification<Job> spe1 = (root, query, builder) -> builder.greaterThan(root.get("salary"), salary1);
            specCon = specCon.and(spe1);
        }
        if (salary2 != 0) {
            Specification<Job> spe2 = (root, query, builder) -> builder.lessThan(root.get("salary"), salary2);
            specCon = specCon.and(spe2);
        }
        if (typeJob != null) {
            Specification<Job> spe3 = (root, query, builder) -> builder.like(root.get("typeJob"), "%" + typeJob + "%");
            specCon = specCon.and(spe3);
        }
        if (nameJob != null) {
            Specification<Job> spe4 = (root, query, builder) -> builder.like(root.get("nameJob"), "%" + nameJob + "%");
            specCon = specCon.and(spe4);
        }

        return specCon;
    }

    public static Specification<Job> GreaterThanCon(long salary, String typeJob, String nameJob) {
        Specification<Job> specCon = Specification.where(null);
        if (salary != 0) {
            Specification<Job> spe1 = (root, query, builder) -> builder.greaterThanOrEqualTo(root.get("salary"),
                    salary);
            specCon = specCon.and(spe1);
        }
        if (typeJob != null) {
            Specification<Job> spe3 = (root, query, builder) -> builder.like(root.get("typeJob"), "%" + typeJob + "%");
            specCon = specCon.and(spe3);
        }
        if (nameJob != null) {
            Specification<Job> spe2 = (root, query, builder) -> builder.like(root.get("nameJob"), "%" + nameJob + "%");
            specCon = specCon.and(spe2);
        }
        return specCon;
    }
}
