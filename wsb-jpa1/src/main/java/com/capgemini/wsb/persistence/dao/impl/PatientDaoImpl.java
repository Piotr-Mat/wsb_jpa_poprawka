package com.capgemini.wsb.persistence.dao.impl;

import com.capgemini.wsb.persistence.dao.PatientDao;
import com.capgemini.wsb.persistence.entity.PatientEntity;
import com.capgemini.wsb.persistence.entity.VisitEntity;
import com.capgemini.wsb.persistence.enums.TreatmentType;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PatientDaoImpl extends AbstractDao<PatientEntity, Long> implements PatientDao {


    @Override
    public List<PatientEntity> findByDoctor(String firstName, String lastName) {
        return entityManager.createQuery(
                        "SELECT DISTINCT p FROM PatientEntity p " +
                                "JOIN p.visits v " +
                                "WHERE v.doctor.firstName = :firstName AND v.doctor.lastName = :lastName",
                        PatientEntity.class)
                .setParameter("firstName", firstName)
                .setParameter("lastName", lastName)
                .getResultList();
    }

    @Override
    public List<PatientEntity> findPatientsHavingTreatmentType(TreatmentType treatmentType) {
        return entityManager.createQuery(
                        "SELECT DISTINCT p FROM PatientEntity p " +
                                "JOIN p.visits v " +
                                "JOIN v.medicalTreatments t " +
                                "WHERE t.type = :treatmentType",
                        PatientEntity.class)
                .setParameter("treatmentType", treatmentType)
                .getResultList();
    }

    @Override
    public List<PatientEntity> findPatientsSharingSameLocationWithDoc(String firstName, String lastName) {
        return entityManager.createQuery(
                        "SELECT DISTINCT p FROM PatientEntity p " +
                                "JOIN p.addresses pa " +
                                "JOIN DoctorEntity d ON d.firstName = :firstName AND d.lastName = :lastName " +
                                "JOIN d.addresses da " +
                                "WHERE pa.id = da.id",
                        PatientEntity.class)
                .setParameter("firstName", firstName)
                .setParameter("lastName", lastName)
                .getResultList();
    }

    @Override
    public List<PatientEntity> findPatientsWithoutLocation() {
        return entityManager.createQuery(
                        "SELECT p FROM PatientEntity p " +
                                "WHERE p.addresses IS EMPTY",
                        PatientEntity.class)
                .getResultList();
    }
}
