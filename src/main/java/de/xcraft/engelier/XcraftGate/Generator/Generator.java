package de.xcraft.engelier.XcraftGate.Generator;

import de.xcraft.engelier.XcraftGate.XcraftGate;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.generator.ChunkGenerator;

public enum Generator {
    DEFAULT(0),
    FLATLANDS(1),
    VOID(2);

	private final int id;
	private static final Map<Integer, Generator> lookup = new HashMap<>();

	private Generator(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public static Generator getGenerator(int id) {
		return lookup.get(id);
	}
	
	public ChunkGenerator getChunkGenerator(XcraftGate plugin) {
		switch (id) {
			case 0: return (ChunkGenerator)null;
			case 1: return new GeneratorFlatlands();
			case 2: return new GeneratorVoid();
		}
		
		return null;
	}

	static {
		for (Generator env : values()) {
			lookup.put(env.getId(), env);
		}
	}

}
