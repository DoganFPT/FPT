package model;

public class IDGenerator {
    private static long id=0;

    public IDGenerator() {

    }

    public static long getNextID() throws IDOverFlowException{
        if(id<10000) {
            return id++;
        }else{
            throw new IDOverFlowException();
        }
    }
}
