package helper;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MapUtil
{

	//http://stackoverflow.com/a/2581754/5845681
	// HashMap ordnet nicht, TreeMap ordnet nach key -> daher TreeMap zu LinkedMap um nach Value zu sortieren
	public static <K, V extends Comparable<? super V>> Map<K, V>  sortByValue( Map<K, V> map ) // '?' als Wildcard mit um generische Referenz zu realisieren; 'super V' um compareTo benutzen zu können (erbt Methode)
	{
		List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>( map.entrySet() ); // neue LinkedList erstellen
		Collections.sort( list, new Comparator<Map.Entry<K, V>>() // 'sort()' an Object hängen für einfachen Aufruf
				{
			@Override
			public int compare( Map.Entry<K, V> o1, Map.Entry<K, V> o2 )
			{
				return (o1.getValue()).compareTo( o2.getValue() );
			}
				} );

		Map<K, V> result = new LinkedHashMap<K, V>();
		for (Map.Entry<K, V> entry : list) // durchgehen und eine geordnete LinkedHashMap erstellen
		{
			result.put( entry.getKey(), entry.getValue() );
		}
		return result;
	}
}