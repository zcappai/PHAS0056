package final2018_19;

import java.util.*;

public interface ImageSelector {

	/**
	 * DEFINES FUNCTION THAT TAKES LIST OF OBJECTS REPRESENTING IMAGES, RETURNS SUBSET OF IMAGES
	 * @param imageList
	 * @return
	 */
	public ArrayList<Classification> select(ArrayList<Classification> imageList, String species);
}