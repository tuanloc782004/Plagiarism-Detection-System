package util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SimilarityUtil {

	public static double jaccardSimilarity(String text1, String text2) {
		Set<String> set1 = new HashSet<>(Arrays.asList(text1.toLowerCase().split("\\W+")));
		Set<String> set2 = new HashSet<>(Arrays.asList(text2.toLowerCase().split("\\W+")));

		Set<String> intersection = new HashSet<>(set1);
		intersection.retainAll(set2);

		Set<String> union = new HashSet<>(set1);
		union.addAll(set2);

		if (union.size() == 0)
			return 0;

		return (double) intersection.size() / union.size();
	}
}
