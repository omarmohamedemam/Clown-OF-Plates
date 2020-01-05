package eg.edu.alexu.csd.oop.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.KeyStroke;

@SuppressWarnings("serial")
public class COPAnimationPanel extends AnimationPanel {

	private COPAnimationPanel pointerToMySelf = this;
	private Image image;

	public COPAnimationPanel(World world, Image gifBackground) {
		super(world);
		this.image = gifBackground;
		resetupKeyBinding();
	}

	private void resetupKeyBinding() {
		//clearOldKeyBinding();
		int condition = 2;
		InputMap inMap = this.getInputMap(condition);
		ActionMap actMap = this.getActionMap();
		Direction[] keyValues = Direction.values();

		for (Direction keyDirection : keyValues) {
			int key = WASDTransformation(keyDirection.getKey());
			String name = keyDirection.name();
			inMap.put(KeyStroke.getKeyStroke(key, 0), name);
			inMap.put(KeyStroke.getKeyStroke(key, 64), name);
			addToActionMap(actMap, keyDirection, name);
		}
	}

	private void addToActionMap(ActionMap actMap, Direction direction, String name) {
		if (direction.equals(Direction.LEFT))
			actMap.put(name, new AbstractAction() {
				public void actionPerformed(ActionEvent e) {
					new ArrowsKeyAction(pointerToMySelf, direction).incrementX(false);
				}
			});
		else if (direction.equals(Direction.RIGHT))
			actMap.put(name, new AbstractAction() {
				public void actionPerformed(ActionEvent e) {
					new ArrowsKeyAction(pointerToMySelf, direction).incrementX(true);
				}
			});
		else
			actMap.put(name, new ArrowsKeyAction(pointerToMySelf, direction));
	}

	// https://keycode.info/
	private int WASDTransformation(int key) {
		return (int) (12.5 * Math.pow(key, 3) - 1445.5 * Math.pow(key, 2) + 55697 * key - 714997);
	}

	@SuppressWarnings("unused")
	private void clearOldKeyBinding() {
		this.getInputMap(2).clear();
		this.getActionMap().clear();
	}

	protected void paintComponent(Graphics g) {
		if (image != null)
			g.drawImage(image, 0, 0, this);
		super.paintComponent(g);
	}

}
