/*
 *    Calendula - An assistant for personal medication management.
 *    Copyright (C) 2016 CITIUS - USC
 *
 *    Calendula is free software; you can redistribute it and/or modify
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

package test.thearch.app.main.medicinemanager.events;

import test.thearch.app.main.medicinemanager.persistence.Medicine;
import test.thearch.app.main.medicinemanager.persistence.Patient;
import test.thearch.app.main.medicinemanager.persistence.Routine;
import test.thearch.app.main.medicinemanager.persistence.Schedule;

/**
 * Created by joseangel.pineiro on 11/4/14.
 */
public class PersistenceEvents {

    public static ModelCreateOrUpdateEvent ROUTINE_EVENT = new ModelCreateOrUpdateEvent(Routine.class);
    public static ModelCreateOrUpdateEvent MEDICINE_EVENT = new ModelCreateOrUpdateEvent(Medicine.class);
    public static ModelCreateOrUpdateEvent SCHEDULE_EVENT = new ModelCreateOrUpdateEvent(Schedule.class);


    public static class ModelCreateOrUpdateEvent {
        public Class<?> clazz;

        public ModelCreateOrUpdateEvent(Class<?> clazz) {
            this.clazz = clazz;
        }
    }

    public static class MedicineAddedEvent {

        public Long id;

        public MedicineAddedEvent(Long id) {
            this.id = id;
        }
    }

    public static class UserCreateEvent {
        public Patient patient;
        public UserCreateEvent(Patient patient) {
            this.patient = patient;
        }
    }

    public static class UserUpdateEvent {
        public Patient patient;
        public UserUpdateEvent(Patient patient) {
            this.patient = patient;
        }
    }

    public static class ActiveUserChangeEvent {
        public Patient patient;
        public ActiveUserChangeEvent(Patient patient) {
            this.patient = patient;
        }
    }



}
