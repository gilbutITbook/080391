package ch6.v1;

import ch2.v2.Employee;

import java.util.*;

public class Cache<Entity,Key> {
    private Map<Key, LinkedList<Entity>> store = new HashMap<>();

    public Cache() {}

    public boolean contains(Key key) {
        return store.containsKey(key);
    }
    public void add(Key key, Entity entity) {
        if (!store.containsKey(key)) {
            var list = new LinkedList<Entity>();
            list.add(entity);
            store.put(key, list);
        } else {
            var l = store.get(key);
            l.add(entity);
        }
    }
    public void add(Key key, Set<Entity> entity) {
        if(!store.containsKey(key)) {
            var list = new LinkedList<>(entity);
            store.put(key, list);
        } else {
            var l = store.get(key);
            l.addAll(entity);
        }
    }
    public Set<Entity> get(Key key) {
        if(!store.containsKey(key)) {
            return Collections.emptySet();
        } else {
            return new HashSet<>(store.get(key));
        }
    }
}
