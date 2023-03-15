package rs.raf.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rs.raf.demo.model.Period;

import java.util.Date;
import java.util.List;

@Repository
public interface PeriodRepository extends CrudRepository<Period, Long> {

    List<Period> findPeriodByStartDateBeforeAndEndDateAfterAndMachineId(Date startDate, Date endDate, Long machineId);
    List<Period> findPeriodByStartDateAfterAndEndDateBeforeAndMachineId(Date startDate, Date endDate, Long machineId);
    List<Period> findPeriodByStartDateAndEndDateAndMachineId(Date startDate, Date endDate, Long machineId);

}
