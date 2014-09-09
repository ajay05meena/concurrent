/**
 * 
 */
package akm.util;

/**
 * @author akm
 *
 */
public interface Queue<T> {
	T dequeue();
	Queue<T> enqueue(T ele);
}
