package week3;
public class NumberDisplay
{
    private int limit;
    private int value;

    //contructor
    public NumberDisplay(int rollOverLimit)
    {
        limit = rollOverLimit;
        value = 0;
    }
    
    public int getValue()
    {
        return value;
    }

    public void setValue(int nilai)
    {
        if((nilai >= 0) && (nilai < limit))
        {
            value = nilai;
        }
    }

    public String getDisplayValue()
    {
        if(value<10)
        {
            return "0" + value; //concatenate
        }
        else
        {
            return "" + value;
        }
    }

    public void increment()
    {
        value = (value+1)%limit;
    }

}