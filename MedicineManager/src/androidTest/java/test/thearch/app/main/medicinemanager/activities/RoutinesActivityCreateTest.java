package test.thearch.app.main.medicinemanager.activities;

import android.support.test.InstrumentationRegistry;
import android.test.ActivityInstrumentationTestCase2;

import org.joda.time.LocalTime;
import org.junit.Before;
import org.junit.Test;

import test.thearch.app.main.medicinemanager.CalendulaApp;
import test.thearch.app.main.medicinemanager.R;
import test.thearch.app.main.medicinemanager.database.DB;
import test.thearch.app.main.medicinemanager.fragments.RoutineCreateOrEditFragment;
import test.thearch.app.main.medicinemanager.persistence.Routine;
import test.thearch.app.main.medicinemanager.util.TestUtils;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


public class RoutinesActivityCreateTest extends ActivityInstrumentationTestCase2<RoutinesActivity> {

    public static final String NAME = "Breakfast";

    private RoutinesActivity mActivity;

    public RoutinesActivityCreateTest() {
        super(RoutinesActivity.class);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        CalendulaApp.disableReceivers = true;
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        DB.init(getInstrumentation().getContext());
        DB.dropAndCreateDatabase();
        mActivity = getActivity();
    }

    @Test
    public void testActivityCreated() {
        assertNotNull(mActivity);
    }

    @Test
    public void testCreateRoutine() {
        // ensure there are no routines
        assertEquals(DB.routines().count(), 0);
        // type name
        onView(withId(R.id.routine_edit_name)).perform(typeText(NAME));
        // close Soft Keyboard
        TestUtils.closeKeyboard();
        // set routine time (not possible vía UI)
        setTimepickerTime(18, 30);
        // open time picker
        onView(withId(R.id.button2)).perform(click());
        // check its open
        onView(withId(R.id.done_button)).check(matches(isDisplayed()));
        // press done
        onView(withId(R.id.done_button)).perform(click());
        // check button has the correct time
        onView(withId(R.id.button2)).check(matches(withText("18:30")));
        // click save
        onView(withId(R.id.add_button)).perform(click());

        // find routine and do assertions
        Routine r = DB.routines().findOneBy(Routine.COLUMN_NAME, NAME);
        assertNotNull(r);
        assertEquals(NAME, r.name());
        assertEquals(new LocalTime(18, 30), r.time());
    }


    private void setTimepickerTime(final int hour, final int minute) {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                RoutineCreateOrEditFragment f = (RoutineCreateOrEditFragment) mActivity.getViewPagerFragment(0);
                f.onDialogTimeSet(0, hour, minute);
            }
        });
    }


}