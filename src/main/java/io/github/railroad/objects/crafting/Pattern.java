package io.github.railroad.objects.crafting;

import java.util.LinkedList;
import java.util.List;

import com.google.gson.JsonArray;

import io.github.railroad.project.lang.LangProvider;
import javafx.util.Pair;

public class Pattern {

	public String row1;
	public String row2;
	public String row3;

	public Pattern(String row1, String row2, String row3) {
		this.row1 = row1;
		this.row2 = row2;
		this.row3 = row3;
	}

	public void deletePattern() {
		row1 = "";
		row2 = "";
		row3 = "";
	}

	public List<Character> getKeys() {
		List<Character> keys = new LinkedList<>();

		for (String row : new String[] {
				row1, row2, row3
		}) {
			for (int i = 0; i <= row.toCharArray().length - 1; i++) {
				if (i < 3 && !keys.contains(row.toCharArray()[i]))
					keys.add(row.toCharArray()[i]);
			}
		}

		return keys;
	}

	public JsonArray toJsonArray() {
		var array = new JsonArray();
		if (row1 != "")
			array.add(row1);
		if (row2 != "")
			array.add(row2);
		if (row3 != "")
			array.add(row3);
		return array;
	}

	public Pair<Boolean, String> isValid() {
		var validRows = new LinkedList<String>();

		if (row1 != "")
			validRows.add(row1);
		if (row2 != "")
			validRows.add(row2);
		if (row3 != "")
			validRows.add(row3);

		if (validRows.isEmpty())
			return new Pair<>(false, LangProvider.fromLang("alert.shapedCrafting.patternEmpty"));

		for (int i = 0; i <= validRows.size() - 1; i++) {
			var row = validRows.get(i);
			var nextRow = "";
			if (i + 1 <= validRows.size() - 1)
				nextRow = validRows.get(i + 1);
			else
				nextRow = validRows.get(0);
			if (row.length() != nextRow.length())
				return new Pair<>(false, LangProvider.fromLang("alert.shapedCrafting.rowsLength"));
		}

		return new Pair<>(true, "");
	}

}
