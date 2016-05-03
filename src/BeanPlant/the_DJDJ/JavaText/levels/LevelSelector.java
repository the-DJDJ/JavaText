package BeanPlant.the_DJDJ.JavaText.levels;

import BeanPlant.the_DJDJ.JavaText.world.World;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

/**
 * A simple class that chooses a random level to load for the player. This
 * searches the package for any world classes that might be present, and then
 * loads a random one for the player to play.
 *
 * @author the_DJDJ
 */
public class LevelSelector {
    
    /** The random world to load for the user. */
    private final Random random;
    
    /** The world to load for the user. */
    private World world;
    
    /** The list for the class loaders to load additional classes. */
    private final List<ClassLoader> classLoadersList;
    
    /** The reflections variable for finding the classes. */
    private final Reflections reflections;
    
    /** An array of all the world classes so that a random one can be loaded. */
    private final Object[] classes;
    
    /**
     * Creates a new LevelSelector object. This loads all of the external level
     * classes and instantiates a new Random object so that a random class can
     * be loaded
     */
    public LevelSelector(){
        
        random = new Random();
        
        classLoadersList = new LinkedList<>();
        
        classLoadersList.add(ClasspathHelper.contextClassLoader());
        classLoadersList.add(ClasspathHelper.staticClassLoader());
        
        reflections = new Reflections(new ConfigurationBuilder()
                .setScanners(new SubTypesScanner(false /* don't exclude Object.class */), new ResourcesScanner())
                .setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
                .setUrls(ClasspathHelper.forJavaClassPath())
                .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix("Levels"))));
        
        classes = reflections.getSubTypesOf(World.class).toArray();
        
    }
    
    /**
     * The method that returns a world. This method uses the random class to
     * generate a random world, and then load the world that that value is
     * associated with.
     * 
     * @return A world for the user to play in.
     * 
     * @throws java.lang.InstantiationException
     * @throws java.lang.IllegalAccessException
     */
    public World getNewWorld() throws InstantiationException, IllegalAccessException {
        
        try {
            
            return (World) ((Class) this.classes[this.random.nextInt(this.classes.length)]).newInstance();
        
        } catch (IllegalArgumentException ex){
            
            return new Demo();
            
        }
        
    }
    
}
