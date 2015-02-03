package com.github.ruediste.attachedProperties4J;

import java.util.HashMap;

/**
 * A map for attached properties. Instances of this class can be constructed and
 * managed by classes of other modules, but the methods are not accessible,
 * since they are intended to be used through {@link AttachedProperty}
 * instances.
 *
 * <p>
 * All operations are thread safe.
 * </p>
 */
public class AttachedPropertyMap {

	private HashMap<AttachedProperty<?, ?>, Object> map = new HashMap<AttachedProperty<?, ?>, Object>();

	@SuppressWarnings("unchecked")
	synchronized <T> T get(AttachedProperty<?, T> key) {
		return (T) map.get(key);
	}

	synchronized <T> void set(AttachedProperty<?, T> key, T value) {
		map.put(key, value);
	}

	synchronized void clear(AttachedProperty<?, ?> key) {
		map.remove(key);
	}

	synchronized boolean isSet(AttachedProperty<?, ?> key) {
		return map.containsKey(key);
	}
	public synchronized void clearAll() {
		map.clear();
	}

	@Override
	public int hashCode() {
		return map.hashCode();
	}
	
	public void putAll(AttachedPropertyBearer other){
		putAll(other.getAttachedPropertyMap());
	}
	
	public void putAll(AttachedPropertyMap other){
		map.putAll(other.map);
	}
	@Override
	public boolean equals(Object obj) {
		if (obj==this)
			return true;
		if (obj==null)
			return false;
		if (!getClass().equals(obj.getClass())){
			return false;
		}
		
		AttachedPropertyMap other=(AttachedPropertyMap) obj;
		return map.equals(other.map);
	}
	
	@Override
	public String toString() {
		return map.toString();
	}
}
