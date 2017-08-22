package test.thearch.app.main.medicinemanager.database;

import android.support.test.InstrumentationRegistry;
import android.test.InstrumentationTestCase;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Before;
import org.junit.Test;

import test.thearch.app.main.medicinemanager.CalendulaApp;
import test.thearch.app.main.medicinemanager.persistence.DailyScheduleItem;
import test.thearch.app.main.medicinemanager.persistence.Medicine;
import test.thearch.app.main.medicinemanager.persistence.PickupInfo;
import test.thearch.app.main.medicinemanager.persistence.Presentation;
import test.thearch.app.main.medicinemanager.persistence.Routine;
import test.thearch.app.main.medicinemanager.persistence.Schedule;
import test.thearch.app.main.medicinemanager.persistence.ScheduleItem;

public class DBTest extends InstrumentationTestCase {

    @Before
    public void setUp() throws Exception {
        super.setUp();
        CalendulaApp.disableReceivers = true;
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        DB.init(getInstrumentation().getContext());
        DB.dropAndCreateDatabase();
    }

    @Test
    public void testDaoSave() throws Exception {
        Routine r = new Routine(new LocalTime(0, 0), "Test");
        Medicine m = new Medicine("TestMed", Presentation.CAPSULES);
        Schedule s = new Schedule(m);
        ScheduleItem i = new ScheduleItem(s, r);
        DailyScheduleItem d = new DailyScheduleItem(i);
        d.setTakenToday(true);

        PickupInfo pk = new PickupInfo();
        pk.setFrom(LocalDate.parse("2015-01-01"));
        pk.setTo(LocalDate.parse("2015-02-05"));
        pk.setMedicine(m);

        // save some stuff
        DB.routines().save(r);
        DB.medicines().save(m);
        DB.schedules().save(s);
        DB.scheduleItems().save(i);
        DB.dailyScheduleItems().save(d);
        DB.pickups().save(pk);

        // verify id property created
        assertNotNull(r.getId());
        assertNotNull(m.getId());
        assertNotNull(s.getId());
        assertNotNull(i.getId());
        assertNotNull(d.getId());
        assertNotNull(pk.id());

        assertEquals(DB.pickups().findByMedicine(m).get(0).from(), LocalDate.parse("2015-01-01"));
    }

    @Test
    public void testDaoFind() throws Exception {

        testDaoSave();
        assertEquals(DB.routines().findOneBy(Routine.COLUMN_NAME, "Test").time(), new LocalTime(0, 0));
        assertEquals(DB.medicines().findAll().get(0).name(), "TestMed");
        assertEquals(DB.schedules().findAll().get(0).items().size(), 1);
    }


}