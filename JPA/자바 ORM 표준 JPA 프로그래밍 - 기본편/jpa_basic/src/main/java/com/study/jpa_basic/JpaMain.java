package com.study.jpa_basic;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            // 비영속 상태
            Member member = new Member();
            member.setId(100L);
            member.setName("HelloJPA");

            // 1차 캐시에 저장
            em.persist(member);

            // 영속 엔티티 조회
            Member memberA = em.find(Member.class, "HelloJPA");

            // 준영속 상태
            em.detach(memberA);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
