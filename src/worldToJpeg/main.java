package worldToJpeg;

import cn.nukkit.scheduler.AsyncTask;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.event.Listener;
import cn.nukkit.level.Position;
import cn.nukkit.Player;

import javax.imageio.ImageIO;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class main extends PluginBase implements Listener {

	@Override
	public void onEnable() {

		this.getServer().getPluginManager().registerEvents(this, this);
		this.getDataFolder().mkdirs();
		this.getServer().getScheduler().scheduleAsyncTask(new AsyncTask() {

			@Override
			public void onRun() {
				BufferedImage image;
				try {
					image = new BufferedImage(600, 600, BufferedImage.TYPE_INT_RGB);
					Graphics2D graphics = image.createGraphics();
					Position spawn = getServer().getDefaultLevel().getSafeSpawn();
					for (int x = 0; x != 600; x++) {
						for (int y = 0; y != 600; y++) {
						
							graphics.setColor(getServer().getDefaultLevel().getMapColorAt(spawn.getFloorX() - 300 + x,
									spawn.getFloorZ() - 300 + y));
							graphics.fillRect(x, y, x, y);
						}
					}
					
					try {
						File file = new File(getDataFolder() + "/world.jpeg");
						ImageIO.write(image, "jpeg", file);
						getLogger().info("finsh");
					} catch (IOException e) {
					return;
					}
				} finally {
				}
			}

		});
	}

}
