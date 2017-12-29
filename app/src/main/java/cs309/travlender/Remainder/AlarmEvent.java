package cs309.travlender.Remainder;

import cs309.travlender.Tools.Event;

/**
 * Created by alicewu on 11/20/17.
 */

abstract class AlarmEvent implements Comparable{
    final int TransportAlarm = 1;
    final int EarlyAlarm = 2;
    final int CommomAlarm = 3;
    private Event fatherEvent;
    private int Alarmtype;//闹铃类型
    private long Starttime;
    private long Alarmtime = -1; //初始化为-1 ， 等待更新

    public AlarmEvent(Event father) {
        fatherEvent = father;
        Starttime = father.getStarttime();
    }

    public int getID(){ return fatherEvent.getEventId(); }

    public Event getFatherEvent() {
        return fatherEvent;
    }

    public long getAlarmtime() {
        return Alarmtime;
    }

    public int getAlarmtype() {
        return Alarmtype;
    }

    public void setAlarmtime(long Alarmtime) {
        this.Alarmtime = Alarmtime;
    }

    public void setAlarmtype(int Alarmtype) {
        this.Alarmtype = Alarmtype;
    }

    public long getStarttime() {
        return Starttime;
    }

    public void setStarttime(long starttime) {
        Starttime = starttime;
    }

    public int compareTo(Object o){
        if (Alarmtime > ((AlarmEvent)o).getAlarmtime())
            return 1;
        else
            return -1;
    }

}