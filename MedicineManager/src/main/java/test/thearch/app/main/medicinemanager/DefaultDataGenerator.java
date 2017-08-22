/*
 *    Medicine Manager - An assistant for personal medication management.
 *    Copyright (C) 2016 CITIUS - USC
 *
 *    Medicine Manager is free software; you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation; either version 3 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this software.  If not, see <http://www.gnu.org/licenses/>.
 */

package test.thearch.app.main.medicinemanager;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import org.joda.time.LocalTime;

import test.thearch.app.main.medicinemanager.database.DB;
import test.thearch.app.main.medicinemanager.persistence.Medicine;
import test.thearch.app.main.medicinemanager.persistence.Patient;
import test.thearch.app.main.medicinemanager.persistence.Routine;
import test.thearch.app.main.medicinemanager.persistence.Schedule;

/**
 * Created by joseangel.pineiro on 12/2/13.
 */
public class DefaultDataGenerator {

    public static void fillDBWithDummyData(Context ctx) {
        Resources r = ctx.getResources();
        if (Routine.findAll().size() == 0 && Schedule.findAll().size() == 0 && Medicine.findAll().size() == 0) {
            try {
                Log.d("DefaultDataGenerator", "Creating dummy data...");
                Patient p = DB.patients().getActive(ctx);
                new Routine(p, new LocalTime(9, 0), r.getString(R.string.routine_breakfast)).save();
                new Routine(p, new LocalTime(13, 0), r.getString(R.string.routine_lunch)).save();
                new Routine(p, new LocalTime(21, 0), r.getString(R.string.routine_dinner)).save();
                Log.d("DefaultDataGenerator", "Dummy data saved successfully!");
            } catch (Exception e) {
                Log.e("DefaultDataGenerator", "Error filling db with dummy data!", e);
            }
        }
    }

    public static void generateDefaultRoutines(Patient p, Context ctx){
        Resources r = ctx.getResources();
        new Routine(p, new LocalTime(9, 0), r.getString(R.string.routine_breakfast)).save();
        new Routine(p, new LocalTime(13, 0), r.getString(R.string.routine_lunch)).save();
        new Routine(p, new LocalTime(21, 0), r.getString(R.string.routine_dinner)).save();
    }

}
