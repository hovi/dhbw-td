/*  Copyright (C) 2013 by Martin Kiessling, Tobias Roeding Inc. All rights reserved.
 *  Released under the terms of the GNU General Public License version 3 or later.
 *  
 *  Contributors:
 *  Martin Kiessling, Tobias Roeding - All
 */

package de.dhbw.td.core.waves;

import java.util.Collections;
import java.util.List;

import de.dhbw.td.core.enemies.Enemy;

/**
 * 
 * @author Martin Kiessling, Tobias Roeding
 * @version 1.0
 * 
 */
public class Wave {

	private final int enemyCount;
	private final int waveNumber;
	private List<Enemy> enemies;

	/**
	 * 
	 * @param waveNumber represents the number of the current Wave in the queue
	 * @param enemies is a list containing all enemies for this wave
	 */
	public Wave(int waveNumber, List<Enemy> enemies) {
		this.enemyCount = enemies.size();
		this.waveNumber = waveNumber;
		this.enemies = enemies;
		enemies = Collections.unmodifiableList(enemies);
	}

	/**
	 * 
	 * @return enemyCount as integer
	 */
	public int enemyCount() {
		return enemyCount;
	}

	/**
	 * 
	 * @return waveNumber as integer
	 */
	public int waveNumber() {
		return waveNumber;
	}

	/**
	 * 
	 * @return enemies as List<Enemy>
	 */
	public List<Enemy> enemies() {
		return enemies;
	}
}
