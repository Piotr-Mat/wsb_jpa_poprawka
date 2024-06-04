package com.capgemini.wsb.persistence.dao.impl;

import com.capgemini.wsb.persistence.dao.DoctorDao;
import com.capgemini.wsb.persistence.entity.DoctorEntity;
import com.capgemini.wsb.persistence.entity.PatientEntity;
import com.capgemini.wsb.persistence.enums.Specialization;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DoctorDaoImpl extends AbstractDao<DoctorEntity, Long> implements DoctorDao {
    @Override
    public List<DoctorEntity> findBySpecialization(Specialization specialization) {
        return entityManager.createQuery("SELECT d FROM DoctorEntity d WHERE d.specialization = :specialization", DoctorEntity.class)
                .setParameter("specialization", specialization)
                .getResultList();
    }

    @Override
    public long countNumOfVisitsWithPatient(String docFirstName, String docLastName, String patientFirstName, String patientLastName) {
        return entityManager.createQuery(
                        "SELECT COUNT(v) FROM VisitEntity v " +
                                "WHERE v.doctor.firstName = :docFirstName AND v.doctor.lastName = :docLastName " +
                                "AND v.patient.firstName = :patientFirstName AND v.patient.lastName = :patientLastName", Long.class)
                .setParameter("docFirstName", docFirstName)
                .setParameter("docLastName", docLastName)
                .setParameter("patientFirstName", patientFirstName)
                .setParameter("patientLastName", patientLastName)
                .getSingleResult();
    }

    @Override
    public DoctorEntity findOne(Long id) {
        return entityManager.createQuery(
                        "SELECT d FROM DoctorEntity d LEFT JOIN FETCH d.visits WHERE d.id = :id", DoctorEntity.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
