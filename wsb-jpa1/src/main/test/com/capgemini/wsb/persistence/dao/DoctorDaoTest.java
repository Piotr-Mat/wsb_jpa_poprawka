package com.capgemini.wsb.persistence.dao;

import com.capgemini.wsb.persistence.dao.AddressDao;
import com.capgemini.wsb.persistence.entity.AddressEntity;
import com.capgemini.wsb.persistence.entity.DoctorEntity;
import com.capgemini.wsb.persistence.entity.PatientEntity;
import com.capgemini.wsb.persistence.enums.Specialization;
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
public class DoctorDaoTest
{
    @Autowired
    private DoctorDao doctorDao;


    @Test
    @Transactional
    public void shouldFindDocsBySpecialization()
    {
        // given
        final Specialization spec = Specialization.OCULIST;

        // when
        final List<DoctorEntity> doctors = doctorDao.findBySpecialization(spec);

        // then
        assertThat(doctors.size()).isEqualTo(3L);
        assertThat(doctors.stream().map(x -> x.getFirstName()+" "+x.getLastName()).collect(Collectors.toList()))
                .containsExactlyInAnyOrder("Jan Nowak", "Jan Terefere", "Jan Krzywy");
    }

    @Test
    @Transactional
    public void shouldCountNumOfVisitsBetweenDocAndPatient()
    {
        // given

        // when
        final long numOfVisits = doctorDao.countNumOfVisitsWithPatient("Jan", "Krzywy", "Krzysio", "Nowak");

        // then
        assertThat(numOfVisits).isEqualTo(3L);
    }

    @Test
    @Transactional
    public void shouldProveCorrectMappingDoctorToVisit() {
        // given

        // when
        final DoctorEntity doc = doctorDao.findOne(1L);

        // then
        assertThat(doc.getVisits().size()).isEqualTo(2L);
        assertThat(doc.getVisits().stream().map(x -> x.getPatient().getFirstName()+" "+x.getPatient().getLastName()).collect(Collectors.toList()))
                .containsExactlyInAnyOrder("Krzysio Nowak", "Zbigniew Kowalski");
    }
}
