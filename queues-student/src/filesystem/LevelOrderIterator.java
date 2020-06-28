package filesystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.NoSuchElementException;

import structures.Queue;


/**
 * An iterator to perform a level order traversal of part of a 
 * filesystem. A level-order traversal is equivalent to a breadth-
 * first search.
 */
public class LevelOrderIterator extends FileIterator<File> {
	Queue<File> que;
	
	/**
	 * Instantiate a new LevelOrderIterator, rooted at the rootNode.
	 * @param rootNode
	 * @throws FileNotFoundException if the rootNode does not exist
	 */
	public LevelOrderIterator(File rootNode) throws FileNotFoundException {
		if(!rootNode.exists()) {
			throw new FileNotFoundException();
		}
		que = new Queue<File>();
		que.enqueue(rootNode);
	}
	
	@Override
	public boolean hasNext() {
		return !que.isEmpty();
	}

	@Override
	public File next() throws NoSuchElementException {
		if(!hasNext()) {
			  throw new NoSuchElementException();
		  }
		  File returnV = que.dequeue();
		  if(returnV.isDirectory()) {
			  File[] sor = returnV.listFiles();
			  Arrays.sort(sor);
			  for(int i = 0 ; i < sor.length; i++) {
				  que.enqueue(sor[i]);
			  }
		  }
		  return returnV;
	}

	@Override
	public void remove() {
		// Leave this one alone.
		throw new UnsupportedOperationException();		
	}

}
