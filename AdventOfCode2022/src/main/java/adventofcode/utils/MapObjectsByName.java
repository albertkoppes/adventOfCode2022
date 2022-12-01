package adventofcode.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 
 * A dedicated map that supports name to object mapping.
 * This map is capable to pass through the WebApp - Logic layer
 * with proper conversion of the objects.
 * 
 * @author Peter
 */
public class MapObjectsByName
{
    private Map<String, Object> map = new HashMap<String, Object>();
    private final Map<String, MapObjectsByName> submaps = new HashMap<String, MapObjectsByName>();

    /**
     * clear the map
     */
    public void clear()
    {
        map.clear();
    }

    /**
     * @param key key to verify
     * @return true when map contains key
     */
    public boolean containsKey(Object key)
    {
        return map.containsKey(key);
    }

    /**
     * @param value value to verify
     * @return true when map contains value
     */
    public boolean containsValue(Object value)
    {
        return map.containsValue(value);
    }

    /**
     * @return true when map is empty
     */
    public boolean isEmpty()
    {
        return map.isEmpty();
    }

    /**
     * @return the keyset of the map
     */
    public Set<String> keySet()
    {
        return map.keySet();
    }

    /**
     * @return size of map
     */
    public int size()
    {
        return map.size();
    }

    /**
     * @return values of map
     */
    public Collection<Object> values()
    {
        return map.values();
    }

    /**
     * @return entryset of map
     */
    public Set<Entry<String, Object>> entrySet()
    {
        return map.entrySet();
    }

    /**
     * @param key key to object
     * @return object stored in map under key "key"
     */
    public Object get(String key)
    {
        return map.get(key);
    }

    /**
     * @param key key
     * @param value value to store in map
     * @return previous value of map entry under key
     */
    public Object put(String key, Object value)
    {
        return map.put(key, value);
    }

    /**
     * Adds all entries of another instance to this instance
     * @param argMap another map objects by name instance
     */
    public void putAll(MapObjectsByName argMap)
    {
        for (Entry<String, Object> entry : argMap.entrySet())
        {
            map.put(entry.getKey(), entry.getValue());
        }

        for (Entry<String, MapObjectsByName> argEntry : argMap.submaps.entrySet())
        {
            MapObjectsByName mySubmap = getSubMap(argEntry.getKey(), true); // Could add new submap if it is in argMap
            mySubmap.putAll(argEntry.getValue());
        }
    }

    @Override
    public String toString()
    {
        return map.toString();
    }

    /**
     * 
     * @param key key to find submap
     * @param createIfTrue when true adds a submap with key "key" to this instance
     * @return the sub map
     */
    public MapObjectsByName getSubMap(String key, boolean createIfTrue)
    {
        MapObjectsByName submap = submaps.get(key);
        if (submap == null && createIfTrue)
        {
            submap = new MapObjectsByName();
            submaps.put(key, submap);
        }
        return submap;
    }

    /**
     * @return key set of the sub maps
     */
    public Set<String> getSubMapKeys()
    {
        return submaps.keySet();
    }

    /**
     * Insert map as submap in this instance
     * @param key key to be used
     * @param map map to insert as submap
     */
    public void putSubMap(String key, MapObjectsByName map)
    {
        submaps.put(key, map);
    }

    /**
     * @param key key to use
     * @return the submap stored by key "key"
     */
    public MapObjectsByName getSubMap(String key)
    {
        return submaps.get(key);
    }
}
