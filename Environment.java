/*
This class models a the Environmental conditions that a snipe will exist in
@author Anupam Dahal, Torunima Taposhi
 */
public class Environment
{
    public boolean isPlentiful;
    public boolean isDangerous;

    /**
     * Constructor for an Environment
     * @param plenty    an Environment can be Plentiful meaning more food
     * @param dangerous an Environment can be dangerous meaning more predator
     */
    public Environment(boolean plenty, boolean dangerous){
        this.isPlentiful = plenty;
        this.isDangerous = dangerous;
    }

}
