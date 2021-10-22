package net.proselyte.springsecurityapp.calculation.function;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.proselyte.springsecurityapp.model.FileColumn;
import net.proselyte.springsecurityapp.var.PatchResourse;

public class Schema {
	public static List<FileColumn> getFileMaineq(String accauntId) {

		File dir = new File(PatchResourse.SCHEMA + accauntId); // path
		List<FileColumn> schemaList = new ArrayList<FileColumn>();
		File[] arrFiles = dir.listFiles();
		if (arrFiles != null) {
			List<File> lst = Arrays.asList(arrFiles);
			schemaList = new ArrayList<FileColumn>();

			int i = 0;

			for (File f : lst) {

				String fileResources = f.toString();
				int indxBegin = fileResources.indexOf("resources");
				fileResources = fileResources.substring(indxBegin);
				schemaList.add(new FileColumn(fileResources));

				i++;
			}

		}
		return schemaList;
	}
}
