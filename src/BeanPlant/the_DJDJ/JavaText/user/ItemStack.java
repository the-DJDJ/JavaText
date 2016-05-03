package BeanPlant.the_DJDJ.JavaText.user;

/**
 *
 * @author the_djdj
 */
public class ItemStack extends Item {
    
    /** The item of this ItemStack. */
    private Item item;
    
    /** The amount of items in this stack. */
    private int amount;
    
    /**
     * The default constructor. This gets the item that this stack is associated
     * with, as well as the amount of items in the stack.
     * 
     * @param item The item that this stack is associated with.
     * @param amount The amount of items in this stack.
     */
    public ItemStack(Item item, int amount){
        
        this.item = item;
        this.amount = amount;
        
    }
    
    @Override
    public String getName(){
        
        return this.item.getName();
        
    }
    
    @Override
    public String getSingleName(){
        
        return this.item.getSingleName();
        
    }
    
    @Override
    public String getPluralName(){
        
        return this.item.getPluralName();
        
    }
    
    @Override
    public String getDescription(){
        
        return this.item.getDescription();
        
    }
    
    /**
     * Returns this item that this stack is associated with.
     * 
     * @return the item of this stack.
     */
    public Item getItem(){
        
        return this.item;
        
    }
    
    /**
     * Sets the item that this stack is associated with, and returns this stack
     * object.
     * 
     * @param item the new item of this stack.
     * @return this ItemStack objects
     */
    public ItemStack setItem(Item item){
        
        this.item = item;
        return this;
        
    }
    
    /**
     * Gets the amount of items that are in this stack.
     * 
     * @return the amount of items in this stack.
     */
    public int getAmount(){
        
        return this.amount;
        
    }
    
    /**
     * Adds a specific amount of items to this stack
     * 
     * @param amount the amount of items to add to this stack
     * @return the updated stack
     */
    public ItemStack add(int amount){
        
        this.amount += amount;
        return this;
        
    }
    
    /**
     * Removes a specific amount of items from this stack, or returns null if
     * there are not any items left
     * 
     * @param amount the amount of items to remove from this stack
     * @return the updated stack
     */
    public Item remove(int amount){
        
        if(this.amount - amount == 1){
            
            this.amount = 1;
            return this.getItem();
            
        } else if(this.amount - amount > 0){
            
            this.amount -= amount;
            return this;
            
        } else {
            
            this.amount = 0;
            return null;
            
        }
        
    }
    
    /**
     * Sets the amount of items that are in this stack.
     * 
     * @param amount the new amount in this stack.
     * @return this updated stack.
     */
    public ItemStack setAmount(int amount){
        
        this.amount = amount;
        return this;
        
    }
    
    @Override
    public boolean isStack(){
        
        return true;
        
    }
    
    @Override
    public String toString(){
        
        return this.getAmount() + "x " + this.getName();
        
    }
    
}
