

import java.util.*;
import javax.swing.event.*;

/**
 * (borrowed from textbook, edited)
  A Subject class for the observer pattern.
*/
public class Model
{
   /**
      Constructs a DataModel object
      @param d the data to model
   */
   public Model(int[] d)
   {
      data = d;
      listeners = new ArrayList<ChangeListener>();
   }

   /**
      Constructs a DataModel object
      @return the data in an ArrayList
   */
   public int[] getData()
   {
      return (int[]) (data.clone());
   }

   /**
      Attach a listener to the Model
      @param c the listener
   */
   public void attach(ChangeListener c)
   {
      listeners.add(c);
   }

   /**
      Change the data in the model at a particular location
      @param location the index of the field to change
      @param value the new value
   */
   public void update(int[] value)
   {
	  data = value.clone();
      for (ChangeListener l : listeners) {
         l.stateChanged(new ChangeEvent(this));
      }
   }

   int[] data;
   ArrayList<ChangeListener> listeners;
}
