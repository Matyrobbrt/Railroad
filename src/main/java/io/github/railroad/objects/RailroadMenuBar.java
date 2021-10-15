package io.github.railroad.objects;

import io.github.railroad.project.lang.LangProvider;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

/**
 * @author TurtyWurty
 */
public class RailroadMenuBar extends MenuBar {

    public final FileMenu fileMenu;

    public RailroadMenuBar(final FileMenu fileMenu) {
        super(fileMenu);
        this.fileMenu = fileMenu;
    }

    public static class FileMenu extends Menu {
        public final MenuItem openItem;
        public final MenuItem saveItem;

        public FileMenu(final MenuItem openItem, final MenuItem saveItem) {
            super(LangProvider.fromLang("menuBar.fileMenu.name"), null, openItem, saveItem);
            this.openItem = openItem;
            this.saveItem = saveItem;
        }
    }
}
