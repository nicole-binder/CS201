/**
 * SearchableCollection.java
 * Jeff Ondich, 13 February 2019
 *
 * An interface for use with our exploration of search
 * algorithms in CS201.
 */

public interface SearchableCollection<KeyType extends Comparable<KeyType>, ValueType> {
    /**
     * Returns the number of items in this collection
     */
    public int size();

    /**
     * Adds the specified item to this collection, associated with the
     * specified key.
     */
    public void add(KeyType key, ValueType item);

    /**
     * @return the value in this collection associated with the specified
     *   search key, or null if the search key is not contained in
     *   this collection.
     */
    public ValueType find(KeyType searchKey);

    /**
     * @return true if the the specified search key exists in this collection,
     *   or false if not.
     */
    public boolean containsKey(KeyType searchKey);
}
