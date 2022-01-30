/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Time;

/**
 *
 * @author midni
 */
public class TimeSlot {

    private int timeSlotID;
    private Time timeSlotStart;
    private Time timeSlotEnd;

    public int getTimeSlotID() {
        return timeSlotID;
    }

    public void setTimeSlotID(int timeSlotID) {
        this.timeSlotID = timeSlotID;
    }

    public Time getTimeSlotStart() {
        return timeSlotStart;
    }

    public void setTimeSlotStart(Time timeSlotStart) {
        this.timeSlotStart = timeSlotStart;
    }

    public Time getTimeSlotEnd() {
        return timeSlotEnd;
    }

    public void setTimeSlotEnd(Time timeSlotEnd) {
        this.timeSlotEnd = timeSlotEnd;
    }
}
