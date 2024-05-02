package week3;
public class ClockDisplay
{
    private NumberDisplay miliseconds;
    private NumberDisplay seconds;
    private NumberDisplay minutes;
    private NumberDisplay hours;
    private String displayString;

    public ClockDisplay()
    {
        hours = new NumberDisplay(24);
        minutes = new NumberDisplay(60);
        seconds = new NumberDisplay(60);
        miliseconds = new NumberDisplay(1000);
        updateDisplay();
    }
    
    public ClockDisplay(int hour, int minute, int second, int milisecond)
    {
        hours = new NumberDisplay(24);
        minutes = new NumberDisplay(60);
        seconds = new NumberDisplay(60);
        miliseconds = new NumberDisplay(1000);
        setTime(hour,minute, second, milisecond);
    }
    
    public void timetick()
    {
        miliseconds.increment();
        if(miliseconds.getValue()==0)
        {
            seconds.increment();
        }
        if(seconds.getValue()==0)
        {
            minutes.increment();
        }
        if(minutes.getValue()==0)
        {
            hours.increment();
        }
        updateDisplay();
    }

    public void setTime(int hour, int minute, int second, int milisecond)
    {
        hours.setValue(hour);
        minutes.setValue(minute);
        seconds.setValue(second);
        miliseconds.setValue(milisecond);
        updateDisplay();
    }

    public String getTime()
    {
        return displayString;
    }

    public void updateDisplay()
    {
        displayString = hours.getDisplayValue()+":"+minutes.getDisplayValue()+":"+seconds.getDisplayValue()+":"+miliseconds.getDisplayValue();
    }

    public void RealClock(int hour, int minute, int seconds, int miliseconds)
    {
        while(hour>0){
        timetick();
        System.out.println(getTime());
        }
    }

}