package com.capgemini.wsb.persistence.dao;

import com.capgemini.wsb.persistence.dao.AddressDao;
import com.capgemini.wsb.persistence.entity.AddressEntity;
import com.capgemini.wsb.persistence.entity.DoctorEntity;
import com.capgemini.wsb.persistence.entity.PatientEntity;
import com.capgemini.wsb.persistence.entity.VisitEntity;
import com.capgemini.wsb.persistence.enums.Specialization;
import com.capgemini.wsb.persistence.enums.TreatmentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientDaoTest
{
    @Autowired
    private PatientDao patientDao;

    @Autowired
    private VisitsDao visitsDao;

    @Test
    @Transactional
    public void shouldRemoveVisitsWhenRemovingPatients()
    {
        // given
        final Collection<VisitEntity> visits = patientDao.findOne(1L).getVisits();

        // when
        patientDao.delete(1L);

        // then
        assertThat(visits.stream().filter(x -> visitsDao.exists(x.getId())).collect(Collectors.toList())).isEmpty();
    }

    @Test
    @Transactional
    public void shouldFindPatientsByDoctor()
    {
        // given

        // when
        final List<PatientEntity> patients = patientDao.findByDoctor("Jan", "Cygwin");

        // then
        assertThat(patients.size()).isEqualTo(2L);
        assertThat(patients.stream().map(x -> x.getFirstName()+" "+x.getLastName()).collect(Collectors.toList()))
                .containsExactlyInAnyOrder("Benek Bobo", "Zbigniew Kowalski");
    }

    @Test
    @Transactional
    public void shouldFindPatientsHavingTreatmentType()
    {
        // given

        // when
        final List<PatientEntity> patients = patientDao.findPatientsHavingTreatmentType(TreatmentType.EKG);

        // then
        assertThat(patients.size()).isEqualTo(3L);
        assertThat(patients.stream().map(x -> x.getFirstName()+" "+x.getLastName()).collect(Collectors.toList()))
                .containsExactlyInAnyOrder("Benek Bobo", "Zbigniew Kowalski", "Kajetan Beton");
    }

    @Test
    @Transactional
    public void shouldFindSharingSameAddressWithDoc()
    {
        // given

        // when
        final List<PatientEntity> patients = patientDao.findPatientsSharingSameLocationWithDoc("Jan", "Beton");

        // then
        assertThat(patients.size()).isEqualTo(2L);
        assertThat(patients.stream().map(x -> x.getFirstName()+" "+x.getLastName()).collect(Collectors.toList()))
                .containsExactlyInAnyOrder("Krzysio Nowak", "Kajetan Beton");
    }

    @Test
    @Transactional
    public void shouldFindPatientsWithoutAnyAddress()
    {
        // given

        // when
        final List<PatientEntity> patients = patientDao.findPatientsWithoutLocation();

        // then
        assertThat(patients).isEmpty();
    }


}
