package BeanPlant.the_DJDJ.JavaText.world.exit;

import BeanPlant.the_DJDJ.JavaText.world.Location;

import java.io.Serializable;

/**
 * An exit for a location. This is basically a path that links one location to
 * another. One location can have many exits, but each exit can only lead to one
 * place.
 *
 * @author the_DJDJ
 */
public class Exit implements Serializable {
    
    // Numerical codes
    public static final transient int UNDEFINED = 0;
    public static final transient int NORTH = 1;
    public static final transient int SOUTH = 2;
    public static final transient int EAST  = 3;
    public static final transient int WEST  = 4;
    public static final transient int UP    = 5;
    public static final transient int DOWN  = 6;
    public static final transient int NORTHEAST = 7;
    public static final transient int NORTHWEST = 8;
    public static final transient int SOUTHEAST = 9;
    public static final transient int SOUTHWEST = 10;
    public static final transient int IN = 11;
    public static final transient int OUT = 12;
    public static final transient int CONTINUE = 13;
    public static final transient int YES = 14;
    public static final transient int NO = 15;

    /** The directions. */
    public static final transient String[] directionName = { 
        
        "UNDEFINED",
        "NORTH",
        "SOUTH",
        "EAST",
        "WEST",
        "UP",
        "DOWN",
        "NORTHEAST",
        "NORTHWEST",
        "SOUTHEAST",
        "SOUTHWEST",
        "IN",
        "OUT",
        "CONTINUE",
        "YES",
        "NO"
    
    };

    /** The short direction names. */
    public static final transient String[] shortDirectionName = {
        
        "NULL",
        "N",
        "S",
        "E",
        "W",
        "U",
        "D",
        "NE",
        "NW",
        "SE",
        "SW",
        "I",
        "O",
        "C",
        "Y",
        "N"
    
    };

    /** Where the exit leads to. */
    private Location m_leadsTo = null;
    
    /** The direction that the location is in. */
    private final int m_direction;

    /** The full name of the direction. */
    private String m_directionName;
    
    /** The shortened name of the direction. */
    private String m_shortDirectionName;
    
    /** Whether or not this exit is locked. */
    private boolean m_locked;
    
    /** Whether or not this exit can be locked. */
    private boolean m_lockable;
    
    /** The type of this exit. */
    private Type m_type;

    /**
     * The default constructor. Initialises all variables and sets them to the
     * default values.
     */
    public Exit() {
        
        this(Exit.UNDEFINED, null, Type.UNDEFINED, false, false);
    
    }

    /**
     * The main constructor. Initialises all variables and sets them to the
     * default values, and takes values for the direction of the exit and where
     * it leads to.
     * 
     * @param direction The direction that the exit is in.
     * @param leadsTo Where the direction leads to.
     */
    public Exit(int direction, Location leadsTo) {
        
        this(direction, leadsTo, Type.UNDEFINED, false, false);
    
    }
    
    /**
     * The main constructor. Initialises all variables and sets them to the
     * default values, and takes values for the direction of the exit, where
     * it leads to, and what type of exit it is.
     * 
     * @param direction The direction that the exit is in.
     * @param leadsTo Where the direction leads to.
     * @param type The type of exit that this is.
     */
    public Exit(int direction, Location leadsTo, Type type){
        
        this(direction, leadsTo, type, false, true);
        
    }
    
    /**
     * The full constructor. Initialises all variables and sets them to the
     * default values, and takes values for the direction of the exit, where
     * it leads to, whether or not it is locked and what type of exit it is.
     * 
     * @param direction The direction that the exit is in.
     * @param leadsTo Where the direction leads to.
     * @param type The type of exit that this is.
     * @param locked Whether or not the exit is locked.
     * @param lockable Whether or not the exit can be locked
     */
    public Exit(int direction, Location leadsTo, Type type, boolean locked, boolean lockable) {
        
        this.m_direction = direction;

        // Assign direction names
        if (direction <= directionName.length ) m_directionName = directionName[m_direction];
        if (direction <= shortDirectionName.length ) m_shortDirectionName = shortDirectionName[m_direction];

        // Assign location
        this.m_leadsTo = leadsTo;
        
        this.m_locked = locked;
        this.m_lockable = lockable;
        this.m_type = type;
        
    }

    /**
     * Sets the direction name that this exit is in.
     * 
     * @param directionName The new direction name.
     */
    public void setDirectionName(String directionName) {
            
        m_directionName = directionName;
    
    }

    /**
     * Returns the direction name that this exit is in.
     * 
     * @return The direction name.
     */
    public String getDirectionName() {
            
        return m_directionName;
    
    }

    /**
     * Sets the short direction name that this exit is in.
     * 
     * @param shortDirectionName The new short direction name.
     */
    public void setShortDirectionName (String shortDirectionName) {
            
        m_shortDirectionName = shortDirectionName;
    
    }

    /**
     * Returns the short direction name that this exit is in.
     * 
     * @return The short direction name.
     */
    public String getShortDirectionName () {
            
        return m_shortDirectionName;
    
    }

    /**
     * Sets the location that this exit leads to
     * 
     * @param leadsTo The new location that this exit leads to.
     */
    public void setLeadsTo (Location leadsTo) {
            
        m_leadsTo = leadsTo;
    
    }

    /**
     * Returns the location that this exit leads to.
     * 
     * @return The location that this exit leads to.
     */
    public Location getLeadsTo() {
            
        return m_leadsTo;
    
    }
    
    /**
     * Returns whether or not this exit is locked.
     * 
     * @return Whether or not this exit is locked.
     */
    public boolean isLocked(){
        
        return this.m_locked;
        
    }
    
    /**
     * Sets whether or not this exit is locked.
     * 
     * @param locked whether or not this exit is locked.
     */
    public void setLocked(boolean locked){
        
        this.m_locked = locked;
        
    }
    
    /**
     * Returns whether or not this exit can be locked.
     * 
     * @return Whether or not this exit can be locked.
     */
    public boolean isLockable(){
        
        return this.m_lockable;
        
    }
    
    /**
     * Sets whether or not this exit can be locked.
     * 
     * @param lockable Whether or not this exit can be locked.
     */
    public void setLockable(boolean lockable){
        
        this.m_lockable = lockable;
        
    }
    
    /**
     * Sets the type of exit that this is.
     * 
     * @param type the type of exit that this is.
     */
    public void setType(Type type){
        
        this.m_type = type;
        
    }
    
    /**
     * Returns the type of exit that this is.
     * 
     * @return the type of exit that this is.
     */
    public Type getType(){
        
        return this.m_type;
        
    }
    
    /**
     * @inheritDoc
     * 
     * @return A string representation of the exit
     */
    @Override
    public String toString() {
            
        return m_directionName;
    
    }

}
