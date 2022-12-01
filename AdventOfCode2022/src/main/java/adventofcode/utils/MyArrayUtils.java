package adventofcode.utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;

/**
 * Contains all utility methods for Arrays, which do not require BWise specific objects.
 * For array utilities which do require BWise objects like StorableObject or ModelObject, 
 * use com.bwise.logic.util.BWiseArrayUtil.  
 * @author charles
 */
public final class MyArrayUtils
{
    private static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];

    private MyArrayUtils()
    {   
    }

    /**
     * @param oldColl
     * @param newColl
     * @return Set of objects that exist in oldColl but no longer in newColl
     */
    public static <T> Set<T> calcRemovals(Collection<T> oldColl, Collection<?> newColl)
    {
        return calcAdditions(newColl, oldColl);
    }

    /**
     * @param oldColl
     * @param newColl
     * @return Set of objects that exist in newColl but not yet in oldColl
     */
    public static <T> Set<T> calcAdditions(Collection<?> oldColl, Collection<T> newColl)
    {
        Set<T> result = new HashSet<>();
        for (T element : newColl)
        {
            if (!oldColl.contains(element))
            {
                result.add(element);
            }
        }
        return result;
    }

    /**
     * @param oldArray
     * @param newArray
     * @return Set of objects that exist in oldArray but no longer in newArray
     */
    public static <T> Set<T> calcRemovals(T[] oldArray, T[] newArray)
    {
        return calcAdditions(newArray, oldArray);
    }

    /**
     * @param <T> - Class Type
     * @param oldArray
     * @param newArray
     * @return Set of objects that exist in newArray but not yet in oldArray
     */
    public static <T> Set<T> calcAdditions(T[] oldArray, T[] newArray)
    {
        Set<T> result = new HashSet<>();
        Collection<T> oldColl = Arrays.asList(oldArray);
        for (T element : newArray)
        {
            if (!oldColl.contains(element))
            {
                result.add(element);
            }
        }
        return result;
    }

    /**
     * @param array
     * @param length
     * @return an Object[] of the supplied length, filled with nulls if array was too small
     */
    public static Object[] resize(Object[] array, int length)
    {
        if (array.length == length)
        {
            return array;
        }
        if (length == 0)
        {
            return EMPTY_OBJECT_ARRAY;
        }
        Object[] newArray = new Object[length];
        for (int i = 0; i < length; i++)
        {
            newArray[i] = (i < array.length) ? array[i] : null;
        }
        return newArray;
    }

    /**
     * Converts the array of type S in an array of type R. This method is typically used to up / downcast 
     * to a super / sub class. The downcast can not be made type safe because of type erasure. 
     * Therefore only use when you are sure that the collection is only filled with items of type R,
     * otherwise a ClassCastException will occur. 
     * @param array
     * @param theClass
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <S, R> R[] convertToArray(S[] array, Class<R> theClass)
    {
        // This cast is safe as long as array is not null.
        R[] newArray = (R[]) Array.newInstance(theClass, array.length);
        for (int i = 0; i < array.length; i++)
        {
            // Possible ClassCastException if not used properly
            newArray[i] = (R) array[i];
        }
        return newArray;
    }
  
    /**
     * Converts the collection Type S in an array of type R. This method is typically used to up / downcast 
     * to a super / sub class, and as such can not be made type safe because of type erasure. 
     * Therefore only use when you are sure that the collection is only filled with items of type R,
     * otherwise a ClassCastException will occur. 
     * @param collection collection of some super type S
     * @param theClass Type R which is a sub class of S.
     * @return an array of type R[]
     */
    @SuppressWarnings("unchecked")
    public static <S, R> R[] convertToArray(Collection<S> collection, Class<R> theClass)
    {
        // This cast is safe as long as collection is not null.
        R[] newArray = (R[])Array.newInstance(theClass, collection.size());
        Iterator<S> iter = collection.iterator();
        int i = 0;
        while (iter.hasNext())
        {
            // Possible ClassCastException if not used properly
            newArray[i] = (R)iter.next();
            i++;
        }
        return newArray;
    }

    /**
     * @param collection collection to be converted to array
     * @param theClass type of the array
     * @return an array
     */
    @SuppressWarnings("unchecked")
    public static <R> R[] convertToArray(List<R> collection, Class<R> theClass)
    {
        R[] newArray = (R[])Array.newInstance(theClass, collection.size());
        int i = 0;
        for (R value: collection)
        {
            newArray[i] = value;
            i++;
        }
        return newArray;
    }

    /**
     * Accepts null for either parameter and as element in either parameter
     * @param <T> Class type
     * @param array1
     * @param array2
     * @return true if both arrays are null or if their contents are equal
     */
    public static <T> boolean equalArrays(T[] array1, T[] array2)
    {
        boolean eq = array1 == array2;
        if (!eq && array1 != null && array2 != null)
        {
            final int n = array2.length;
            eq = array1.length == n;
            for (int i = 0; eq && i < n; i++)
            {
                eq = (array1[i] == null) ? array2[i] == null : array1[i].equals(array2[i]);
            }
        }
        return eq;
    }
    
    /**
     * The parameters should not be null, but accepts null as element in either parameter
     * @param coll1
     * @param coll2 - To compare List and Set: pass the Set as this argument for better performance
     * @return true if size and contents equal; for List: also sequence must be equal
     */
    public static <C> boolean equalCollections(Collection<C> coll1, Collection<C> coll2)
    {
        final int n = coll2.size();
        boolean eq = coll1.size() == n;
        if (eq && n > 0)
        {
            if (coll1 instanceof List)
            {
                if (coll2 instanceof List)
                {
                    Iterator<?> iter1 = coll1.iterator();
                    Iterator<?> iter2 = coll2.iterator();
                    while (eq && iter1.hasNext())
                    {
                        Object obj1 = iter1.next();
                        eq = (obj1 == null) ? iter2.next() == null : obj1.equals(iter2.next());
                    }
                }
                else
                {
                    for (Iterator<?> iter2 = coll2.iterator(); eq && iter2.hasNext();)
                    {
                        eq = coll1.contains(iter2.next());
                    }
                }
            }
            else
            {
                for (Iterator<?> iter1 = coll1.iterator(); eq && iter1.hasNext();)
                {
                    eq = coll2.contains(iter1.next());
                }
            }
        }
        return eq;
    }
    
    /**
     * Add the element to the list if it is not yet present
     * @param list
     * @param element
     * @return true if the element was actually added
     */
    public static <T> boolean addIfAbsent(List<T> list, T element)
    {
        if (list.contains(element))
        {
            return false;
        }
        list.add(element);
        return true;
    }
    
    /**
     * @param coll
     * @return cloned collection into java.util.HashSet or ArrayList
     */
    public static <E> Collection<E> cloneCollection(Collection<E> coll)
    {
        if (coll instanceof Set)
        {
            return new HashSet<>(coll);
        }
        if (coll instanceof List)
        {
            return new ArrayList<>(coll);
        }
        throw new IllegalArgumentException("Illegal collection type: " + coll.getClass());
    }
    
    /**
     * @param coll
     * @return empty clone of collection
     */
    public static <E> Collection<E> cloneCollectionEmpty(Collection<E> coll)
    {
        if (coll instanceof Set)
        {
            return new HashSet<>(coll.size());
        }
        if (coll instanceof List)
        {
            return new ArrayList<>(coll.size());
        }
        throw new IllegalArgumentException("Illegal collection type: " + coll.getClass());
    }
    
    /**
     * Add @value to the Set at the @key, or create an HashSet if absent
     * @param map map
     * @param key key
     * @param value value
     */
    public static <K, V> void addToMapWithSet(Map<K, Set<V>> map, K key, V value)
    {
        if (key != null && value != null)
        {
            map.computeIfAbsent(key, v -> new HashSet<>()).add(value);
        }
    }
    
    /**
     * Add @value to the list at the @key, or create an ArrayList if absent
     * @param map map
     * @param key key
     * @param value value
     */
    public static <K, V> void addToMapWithList(Map<K, List<V>> map, K key, V value)
    {
        if (key != null && value != null)
        {
            map.computeIfAbsent(key, v -> new ArrayList<>()).add(value);
        }
    }

    public static void addToMapWithList(Map<Integer, List<String>> findMappings, Integer key, List<String> input)
    {
        addToMapWithList(findMappings, key, input);
    }


    /**
     * Add @value to the Array at the @key, or create an Array with single value if absent
     * @param map
     * @param key
     * @param value
     */
    public static void addToMapWithArray(MapObjectsByName map, String key, Object value)
    {
        if (key != null && value != null)
        {
            Object[] values = (Object[])map.get(key);
            if (values == null)
            {
                values = new Object[0];
            }
            values = ArrayUtils.add(values, value);
            map.put(key, values);
        }
    }

    /**
     * Helper method to construct an Array of subclasses of some given superclass.
     * The direct solution, eg. <code>
     * <br>Class&lt? extends Number&gt[] = new Class&lt? extends Number&gt{Integer.class, Float.class}
     * <br></code>
     * produces a Compiler error, whereas usage of the raw type results in code that is not type-checked.
     * An Array constructed this way should <strong>never be modified</strong> in order to preserve type-safety.
     * <br>The typical usage pattern is expected to be <code>
     * <br>@SuppressWarnings("unchecked")
     * <br>private final static Class&lt? extends Number&gt[] MY_CLASSES =
     * <br>&nbsp&nbsp&nbsp BWiseArrayUtil.classes(Integer.class, Float.class);
     * <br>public Class&lt? extends Number&gt[] acceptableNumberTypes()
     * <br>{
     * <br>&nbsp&nbsp&nbsp return MY_CLASSES;
     * <br>}
     * </code>
     * <br>The constant, eg. <code>MY_CLASSES</code> can of course be used directly as argument in a function call as well.
     * 
     * @param <T> The (typically inferred) common superclass of the arguments
     * @param classes The classes involved
     * @return The array implicitly constructed from the arguments (or the argument itself)
     */
    public static <T> Class<? extends T>[] withClasses(Class<? extends T> ... classes)
    {
        return classes;
    }
    
    /**
     * @return empty class array
     */
    @SuppressWarnings("unchecked")
    public static <T> Class<T>[] classes()
    {
        return new Class[0];
    }
    
    /**
     * @param cls1 the class
     * @return an array containing the provided class
     */
    @SuppressWarnings("unchecked")
    public static <T> Class<? extends T>[] classes(Class<? extends T> cls1)
    {
        return withClasses(cls1);
    }
    
    /**
     * @param cls1 the first class
     * @param cls2 the second class
     * @return an array containing the provided classes
     */
    @SuppressWarnings("unchecked")
    public static <T> Class<? extends T>[] classes(
Class<? extends T> cls1, Class<? extends T> cls2)
    {
        return withClasses(cls1, cls2);
    }

    /**
     * @param cls1 the first class
     * @param cls2 the second class
     * @param cls3 the third class
     * @return an array containing the provided classes
     */
    @SuppressWarnings("unchecked")
    public static <T> Class<? extends T>[] classes(
Class<? extends T> cls1, Class<? extends T> cls2, Class<? extends T> cls3)
    {
        return withClasses(cls1, cls2, cls3);
    }

    /**
     * @param cls1 the first class
     * @param cls2 the second class
     * @param cls3 the third class
     * @param cls4 the fourth class
     * @return an array containing the provided classes
     */
    @SuppressWarnings("unchecked")
    public static <T> Class<? extends T>[] classes(
Class<? extends T> cls1, Class<? extends T> cls2, Class<? extends T> cls3, Class<? extends T> cls4)
    {
         return withClasses(cls1, cls2, cls3, cls4);
    }

    /**
     * Clear the collection and add new elements
     * @param target collection to be cleared and populated with new elements
     * @param elements elements that will be added to target
     */
    public static <T> void clearAndAdd(Collection<T> target, T... elements)
    {
        target.clear();
        for (T each : elements)
        {
            target.add(each);
        }
    }
    
    /**
     * Returns the intersection of Collection a and b.
     * Example: a = {"aap", "noot", "mies"}
     *          b = {"aap", "banaan"}
     *          result = {"aap"}
     * @param a
     * @param b
     * @return
     */
    public static <T> Set<T> intersection(Collection<T> a, Collection<T> b)
    {
        HashSet<T> result = new HashSet<>();
        for (T itemA : a)
        {
            for (T itemB : b)
            {
                if (itemA.equals(itemB))
                {
                    result.add(itemA);
                    break;
                }
            }
        }
        return result;
    }
    
    /**
     * @return the union of Collection a and b.
     * Example: a = {"aap", "noot", "mies"}
     *          b = {"aap", "banaan"}
     *          result = {"aap", "banaan", "noot", "mies"}
     * @param a
     * @param b
     */
    public static <T> Set<T> union(Collection<T> a, Collection<T> b)
    {
        HashSet<T> result = new HashSet<>();
        result.addAll(a);
        result.addAll(b);
        return result;
    }

    /**
     * Array is considered blank if it is either:<br>
     * <ul>
     * <li>null
     * <li>empty
     * <li>has only null elements
     * </ul>
     * 
     * @param array array to check
     * @return {@code true} if array is 'blank', {@code false} otherwise
     */
    public static <T> boolean isBlank(T[] array)
    {
        if (array == null)
        {
            return true;
        }
        if (array.length == 0)
        {
            return true;
        }
        for (T item : array)
        {
            if (item != null)
            {
                return false;
            }
        }
        return true;
    }

}
