package pixcolor.test;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Comparator;

public class Listing {

	public static File[] liste(String path) {
		File f = new File(path);
		
		FilenameFilter filtre = new FilenameFilter() {
			  public boolean accept(File dir, String name) {
				    return name.endsWith(".png");
			  }
		};

		File[] files = f.listFiles(filtre);

		Arrays.sort(files, new Comparator<File>() {
	        public int compare(File f1, File f2) {
	            long d1 = f1.lastModified();
	            long d2 = f2.lastModified();
	            return d1 > d2 ? 1 : d1 < d2 ? -1 : 0;
	        }
	    });
		
		return files;
	}

}
