

public class LinkedStackClass<T> extends UnorderedLinkedList<T>
{
	public LinkedStackClass()
	{
		super();
	}

	public void initializeStack()
	{
	  	initializeList();
	}

   	public boolean isEmptyStack()
	{
		return isEmptyList();
	}

	public boolean isFullStack()
	{
		return false;
	}

	public void push(T newElement)
	{
	    insertFirst(newElement);
	} //end push

	public T peek() throws StackUnderflowException
   	{
 		if (first == null)
       		throw new StackUnderflowException();

     	return front();
     } //end peek

     public void pop()throws StackUnderflowException
     {
          if (first == null)
             throw new StackUnderflowException();

          first = first.link;

          count--;

          if (first == null)
             last = null;
     }//end pop
}