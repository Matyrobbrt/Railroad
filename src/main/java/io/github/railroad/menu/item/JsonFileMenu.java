package io.github.railroad.menu.item;

import java.io.File;

import io.github.railroad.project.Project;
import io.github.railroad.project.lang.LangProvider;
import io.github.railroad.util.helper.ColorHelper;
import io.github.railroad.util.templates.json.ItemModelTemplate;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class JsonFileMenu extends MenuItem {

	private final Project project;

	public JsonFileMenu(String text, Project project) {
		super(text);
		this.setOnAction(event -> executeClick(event));
		this.project = project;
	}

	Stage stage = new Stage();
	ChoiceBox<String> cb = new ChoiceBox<String>();
	DirectoryChooser directoryChooser = new DirectoryChooser();
	File selectedDirectory;
	TextField pathArea = new TextField();
	TextField fileName = new TextField();
	Label fileNameLabel = new Label(" " + LangProvider.fromLang("textArea.fileName") + ": ");
	Label jsonType = new Label(" " + LangProvider.fromLang("menuItem.json.typeTextField"));
	Label pathLabel = new Label(" " + LangProvider.fromLang("textArea.selectPath") + ": ");

	VBox vbox = new VBox();

	private void executeClick(ActionEvent event) {
		clearCache();

		cb.getItems().add("Item Model");

		Button browse = new Button(LangProvider.fromLang("buttons.browse"));
		browse.setOnAction(e -> {
			selectedDirectory = directoryChooser.showDialog(stage);
			pathArea.setText(selectedDirectory.getPath());
		});
		browse.setStyle("-fx-background-color: " + ColorHelper.toHex(this.project.getTheme().getButtonColor()));

		Button next = new Button(LangProvider.fromLang("buttons.next"));
		next.setOnAction(e -> nextButtonClick(e));
		next.setStyle("-fx-background-color: " + ColorHelper.toHex(this.project.getTheme().getButtonColor()));
		
		Button cancel = new Button(LangProvider.fromLang("buttons.cancel"));
		cancel.setOnAction(e -> stage.close());
		cancel.setStyle("-fx-background-color: " + ColorHelper.toHex(this.project.getTheme().getButtonColor()));

		pathArea.autosize();
		pathArea.deselect();

		vbox.getChildren().add(new HBox(jsonType, cb));
		vbox.getChildren().add(new HBox(fileNameLabel, fileName));
		vbox.getChildren().add(new HBox(pathLabel, pathArea, browse));
		vbox.getChildren().add(new HBox(next, cancel));

		final var scene = new Scene(vbox);
		stage.setScene(scene);

		stage.setWidth(450);
		// stage.sizeToScene();
		stage.centerOnScreen();
		stage.requestFocus();
		stage.showAndWait();
	}

	private void nextButtonClick(ActionEvent event) {
		stage.close();
		if (cb.getSelectionModel().getSelectedIndex() == 0) {
			new ItemModelTemplate(selectedDirectory, fileName.getText()).openWindow(project);
			cb.getSelectionModel().clearSelection();
			clearCache();
			return;
		}
	}

	private void clearCache() {
		cb = new ChoiceBox<String>();
		vbox = new VBox();
		pathArea.setText("");
		fileName.setText("");
		directoryChooser.setInitialDirectory(this.project.getProjectFolder());
	}

}
