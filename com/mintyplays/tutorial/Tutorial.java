package com.mintyplays.tutorial;

import com.mintyplays.tutorial.event.EventManager;
import com.mintyplays.tutorial.event.EventTarget;
import com.mintyplays.tutorial.event.events.EventKey;
import com.mintyplays.tutorial.module.ModuleManager;
import de.Hero.clickgui.ClickGUI;
import de.Hero.settings.SettingsManager;
import org.lwjgl.opengl.Display;

public class Tutorial {
    public String name = "Tutorial", version = "1", creator = "MintyCodes";

    public static Tutorial instance = new Tutorial();

    public SettingsManager settingsManager;
    public EventManager eventManager;
    public ModuleManager moduleManager;
    public ClickGUI clickGui;

    public void startClient() {
        settingsManager = new SettingsManager();
        eventManager = new EventManager();
        moduleManager = new ModuleManager();
        clickGui = new ClickGUI();

        System.out.println("[" + name + "] Starting client, b" + version + ", created by " + creator);
        Display.setTitle(name + " b" + version);

        eventManager.register(this);
    }

    public void stopClient() {
        eventManager.unregister(this);
    }

    @EventTarget
    public void onKey(EventKey event) {
        moduleManager.getModules().stream().filter(module -> module.getKey() == event.getKey()).forEach(module -> module.toggle());
    }
}
