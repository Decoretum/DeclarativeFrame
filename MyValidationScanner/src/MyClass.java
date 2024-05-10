import annotations.GreaterThan;
import annotations.LessThan;
import annotations.NotNull;
import annotations.Range;

public class MyClass {

	@GreaterThan(lowerBound=0)
    private int myField1;

    @LessThan(upperBound=-1)
    private int myField2;
    
    @Range(lowerBound=5,upperBound=10)    
    private int myField3;
    
    @NotNull
    private String myString;
    
    private String myString2;
    
    public int getMyField1()
    {
        return myField1;
    }
    public void setMyField1(int myField1)
    {
        this.myField1 = myField1;
    }
    public int getMyField2()
    {
        return myField2;
    }
    public void setMyField2(int myField2)
    {
        this.myField2 = myField2;
    }
    public int getMyField3()
    {
        return myField3;
    }
    public void setMyField3(int myField3)
    {
        this.myField3 = myField3;
    }
    public String getMyString()
    {
        return myString;
    }
    public void setMyString(String myString)
    {
        this.myString = myString;
    }
    
    public String getMyString2()
    {
        return myString2;
    }
    public void setMyString2(String myString)
    {
        this.myString2 = myString;
    }

	
}
