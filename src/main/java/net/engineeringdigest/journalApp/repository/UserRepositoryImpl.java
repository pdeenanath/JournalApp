package net.engineeringdigest.journalApp.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import net.engineeringdigest.journalApp.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.management.Query;
import java.util.List;

public class UserRepositoryImpl {

    @Autowired
    private EntityManager entityManager;

//    public List<UserEntity> getUserForSA() {
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<UserEntity> cq = cb.createQuery(UserEntity.class);
//        Root<UserEntity> root = cq.from(UserEntity.class);
//
//        cq.select(root).where(cb.equal(root.get("userName"), "Deenu"));
//
//        return entityManager.createQuery(cq).getResultList();
//    }

    public long getUserForSA(String email, boolean sentimentAnalysis) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<UserEntity> root = cq.from(UserEntity.class);

        cq.select(cb.count(root))
                .where(
                        cb.or(
                                cb.equal(root.get("email"), email),
                                cb.isNotNull(root.get("email")),
                                cb.equal(root.get("sentimentAnalysis"), sentimentAnalysis)
                        )
                );

        return entityManager.createQuery(cq).getSingleResult();
    }
    }

